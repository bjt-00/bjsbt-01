package com.bitguiders.bjsbt_01.soap.consume;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import com.bitguiders.bjsbt_01.soap.service.UserRequest;
import com.bitguiders.bjsbt_01.soap.service.UserResponse;

public class UserSoapClient extends WebServiceGatewaySupport   {

	
	public UserResponse getUser(Long userId){
		UserRequest request = new UserRequest();
		request.setUserId(userId);
		
		UserResponse response =  (UserResponse) 
				getWebServiceTemplate().marshalSendAndReceive(request);
		return response;
	}
	
	public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SoapClientConfig.class);
        UserSoapClient client = context.getBean(UserSoapClient.class);
        System.out.println(client.getUser(new Long(3)).getUser().getUserName());
	}

}
