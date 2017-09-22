package com.bitguiders.bjsbt_01.rest;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import com.bitguiders.bjsbt_01.MainApplication;
import com.bitguiders.bjsbt_01.dataaccess.orm.UserORM;
import com.bitguiders.bjsbt_01.service.UserService;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes= MainApplication.class)
@WebAppConfiguration
public class UserRestTest {
	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(),Charset.forName("utf8"));
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private UserService service;
	
	private UserORM expectedUser;
	private Long userId;
	private HttpMessageConverter mappingJackson2HttpMessageConverter;
	 @Autowired
	    void setConverters(HttpMessageConverter<?>[] converters) {

	        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
	            .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
	            .findAny()
	            .orElse(null);

	        assertNotNull("the JSON message converter must not be null",
	                this.mappingJackson2HttpMessageConverter);
	    }
	 
	@Before
	public void setup(){
		System.out.println("_-_-_-_-_-_-_-TEST CALLED_-_-_-_-_-_-_-_-_-");
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
		userId=new Long(4);
		expectedUser = new UserORM();
		//expectedUser.setUserId(userId);
		expectedUser.setUserName("Amina");
		expectedUser.setPhone("872-3");
	}
	@Test 
	public void addUserTest() throws IOException, Exception{
		System.out.println("_-_-_-_-_-_-_-POST USER REST TEST_-_-_-_-_-_-_-_-_-");
		
		MvcResult result = mockMvc.perform(post("/userr/add")
                .content(this.json(expectedUser))
                .contentType(contentType))
                .andExpect(status().isOk())
                .andReturn();
		String str = result.getResponse().getContentAsString();
		str = str.substring(str.indexOf("<userId>")+8,str.indexOf("</userId>"));
		userId = Long.parseLong(str);
		expectedUser.setUserId(userId);
		System.out.println("New Id = "+userId);
	}
	@Test 
	public void updateUserTest() throws IOException, Exception{
		System.out.println("_-_-_-_-_-_-_-PUT USER REST TEST_-_-_-_-_-_-_-_-_-");
		expectedUser.setUserName("Amina Kareem");
		mockMvc.perform(put("/userr/update")
                .content(this.json(expectedUser))
                .contentType(contentType))
                .andExpect(status().isOk());
	}

	@Test
	public void getUserTest() throws Exception{
		System.out.println("_-_-_-_-_-_-_-GET USER REST TEST_-_-_-_-_-_-_-_-_- id ="+userId);
		System.out.println(expectedUser.getUserName());
		mockMvc.perform(get("/userr/"))
//		.andExpect(status().isOk())
		.andExpect(jsonPath("$.userName",is("Amina Kareem")));
	}
	
	@Test 
	public void deleteUserTest() throws IOException, Exception{
		System.out.println("_-_-_-_-_-_-_-DELETE USER REST TEST_-_-_-_-_-_-_-_-_-");
		
		mockMvc.perform(delete("/userr/delete")
                .content(this.json(expectedUser))
                .contentType(contentType))
                .andExpect(status().isOk());
	}
	
	protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}
