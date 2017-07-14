package com.bitguiders.bjsbt_01.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bitguiders.bjsbt_01.dataaccess.orm.UserORM;
import com.bitguiders.bjsbt_01.service.UserService;

@RestController
@RequestMapping("/user")
public class UserRest {
	
	@Autowired
	UserService service;
	
	@RequestMapping(value="/list", method=RequestMethod.GET )
	public List<UserORM> getList(){
		return service.getList();
	}

	//@ResponseBody
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public UserORM getUserById(@PathVariable("id")Long userId){
		return service.getUserById(userId);
	}
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public UserORM addUser(@RequestBody UserORM userOrm){
		System.out.println("post called "+userOrm.getUserName());
		return userOrm;
	}

}
