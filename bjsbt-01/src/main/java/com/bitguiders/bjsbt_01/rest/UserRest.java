package com.bitguiders.bjsbt_01.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bitguiders.bjsbt_01.dataaccess.orm.UserORM;
import com.bitguiders.bjsbt_01.service.UserService;

@RestController
@RequestMapping("/userr")
public class UserRest {
	
	@Autowired
	UserService service;
	
	@RequestMapping(method=RequestMethod.GET , produces=MediaType.APPLICATION_JSON_VALUE )
	public List<UserORM> getList(){
		return service.getList();
	}

	//@ResponseBody
	@RequestMapping(value="/{id}", method=RequestMethod.GET , produces=MediaType.APPLICATION_JSON_VALUE)
	public  UserORM getUserById(@PathVariable("id")Long userId){
		return service.getUserById(userId);
	}
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public UserORM addUser(@RequestBody UserORM userOrm){
		System.out.println("post called "+userOrm.getUserName());
		return service.save(userOrm);
	}

	@RequestMapping(value="/update", method=RequestMethod.PUT)
	public UserORM updateUser(@RequestBody UserORM userOrm){
		System.out.println("update called "+userOrm.getUserName());
		service.update(userOrm);
		return userOrm;
	}
	@RequestMapping(value="/delete", method=RequestMethod.DELETE)
	public UserORM deleteUser(@RequestBody UserORM userOrm){
		System.out.println("update called "+userOrm.getUserName());
		service.delete(userOrm);
		return userOrm;
	}

}
