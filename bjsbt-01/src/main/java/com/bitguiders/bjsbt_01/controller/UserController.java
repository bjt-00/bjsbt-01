package com.bitguiders.bjsbt_01.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bitguiders.bjsbt_01.dataaccess.orm.UserORM;
import com.bitguiders.bjsbt_01.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	UserService userService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String getUserList(Model model){
		logger.debug("Total Users Found : "+userService.getList().size());
		model.addAttribute("usersList", userService.getList());
		return "userList";
	}
	@RequestMapping(value="/add", method=RequestMethod.GET )
	public String addUser(Model model){
		model.addAttribute("user",new UserORM());
		model.addAttribute("action","save");
		return "userForm";
	}
	@RequestMapping(value="/save", method=RequestMethod.POST )
	public String saveUser(@ModelAttribute("user")UserORM user, Model model){
		userService.save(user);
		model.addAttribute("message",user.getUserName()+" Saved Successfully");
		return getUserList(model);
	}

	@RequestMapping(value="/edit", method=RequestMethod.GET )
	public String editUser(@RequestParam("id")Long userId, Model model){
		model.addAttribute("user",userService.getUserById(userId));
		model.addAttribute("action","update");
		return "userForm";
	}
	@RequestMapping(value="/update", method=RequestMethod.POST )
	public String updateUser(@ModelAttribute("user")UserORM user, Model model){
		userService.update(user);
		model.addAttribute("message",user.getUserName()+" Updated Successfully");
		return getUserList(model);
	}
	@RequestMapping(value="/confirm", method=RequestMethod.GET )
	public String deleteUser(@RequestParam("id")Long userId, Model model){
		model.addAttribute("user",userService.getUserById(userId));
		model.addAttribute("message","Are you sure to delete following User..?");
		model.addAttribute("action","delete");
		return "userForm";
	}
	@RequestMapping(value="/delete", method=RequestMethod.POST )
	public String updateConfirmed(@ModelAttribute("user")UserORM user, Model model){
		userService.delete(user);
		model.addAttribute("message",user.getUserName()+" Deleted Successfully");
		return getUserList(model);
	}

}
