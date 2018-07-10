package com.bitguiders.bjsbt_01.rest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.nio.charset.Charset;
import java.util.Arrays;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.bitguiders.bjsbt_01.dataaccess.orm.UserORM;

public class RestClientWithBasicAuth {
	RestTemplate restTemplate = new RestTemplate();
//	String url="http://bitguiders.com/rest/ttts/?s=qb";
	String url="http://localhost:8080/bjsbt-01/userr";

	public  void post(){
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("id", 45+"");
       // map.add("data", toUpdate(qb));
        map.add("a","update");

		HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		ResponseEntity<String> response = restTemplate.postForEntity(url, req, String.class);
		System.out.println(response.getBody());
	}
	
	HttpHeaders createBasicAuthHeaders(String username, String password){

        HttpHeaders headers = new HttpHeaders();

               //encode basic user/pwd
            String auth = username + ":" + password;
            byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
            String authHeader = "Basic " + new String( encodedAuth );
               
            headers.set("Authorization",authHeader);
            return headers;
	}


public void getWithBasicAuthentication() {

        HttpEntity<UserORM[]> requestEntity = new HttpEntity<UserORM[]>(createBasicAuthHeaders("admin","pwd"));
        ResponseEntity<UserORM[]> userse = restTemplate.exchange(url,HttpMethod.GET,requestEntity, UserORM[].class);

        UserORM[] users = userse.getBody();

        for(UserORM user:users) {
        System.out.println(user.getUserName()+"-"+user.getPhone());
        }
 }
	public static void main(String[] args) {
		RestClientWithBasicAuth c = new RestClientWithBasicAuth();
		c.getWithBasicAuthentication();
		//c.post();
	}

}
