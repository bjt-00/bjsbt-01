package com.bitguiders.bjsbt_01.service;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.bitguiders.bjsbt_01.dataaccess.dao.UserDAO;
import com.bitguiders.bjsbt_01.dataaccess.orm.UserORM;

@Service
@Transactional
public class UserService {
		
	@Resource
	UserDAO dao;
	
		public List<UserORM> getList(){
			return dao.findAll();
		}
		public UserORM getUserById(Long userId){
			return dao.findById(userId).get();
		}
		
		public UserORM save(UserORM userOrm){
			return dao.save(userOrm);
		}
		public void update(UserORM userOrm){
			dao.updateUser(userOrm.getUserName(),userOrm.getPhone(),userOrm.getUserId());
		}
		public void delete(UserORM userOrm){
			dao.delete(userOrm);
		}
}
