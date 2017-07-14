package com.bitguiders.bjsbt_01.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bitguiders.bjsbt_01.dataaccess.dao.UserDAO;
import com.bitguiders.bjsbt_01.dataaccess.orm.UserORM;

@Service
public class UserService {
		
	@Resource
	UserDAO dao;
		public List<UserORM> getList(){
			return dao.findAll();
		}
		public UserORM getUserById(Long userId){
			return dao.findOne(userId);
		}
}
