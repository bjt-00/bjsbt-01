package com.bitguiders.bjsbt_01.soap.produce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.bitguiders.bjsbt_01.dataaccess.orm.UserORM;
import com.bitguiders.bjsbt_01.service.UserService;
import com.bitguiders.bjsbt_01.soap.service.User;
import com.bitguiders.bjsbt_01.soap.service.UserRequest;
import com.bitguiders.bjsbt_01.soap.service.UserResponse;


@Endpoint
public class UserSoapEndpoint  extends WebServiceGatewaySupport {
	private static final String NAMESPACE_URI = "http://service.soap.bjsbt_01.bitguiders.com";
	//"http://spring.io/guides/gs-producing-web-service";

	@Autowired
	private UserService userService;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "userRequest")
	@ResponsePayload
	public UserResponse getUser(@RequestPayload UserRequest request) {
		UserResponse response = new UserResponse();
		UserORM orm = userService.getUserById(request.getUserId());
		User u = new User();
		u.setUserId(orm.getUserId());
		u.setPhone(orm.getPhone());
		u.setUserName("~"+orm.getUserName());
		response.setUser(u);
		System.out.println("This method is called");
		return response;
	}
}