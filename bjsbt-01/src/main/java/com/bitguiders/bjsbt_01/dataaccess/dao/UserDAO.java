package com.bitguiders.bjsbt_01.dataaccess.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bitguiders.bjsbt_01.dataaccess.orm.UserORM;


public interface UserDAO extends JpaRepository<UserORM,Long> {
	
	@Query("select distinct u from UserORM u")
	List<UserORM> getList();

	@Query("select distinct u from UserORM u where u.userId= :userId")
	UserORM getUserById(@Param("userId") Long userId);

	//@Query("select distinct c from Car c where c.manufacturer= :param or c.carModel= :param")
	//List<UserORM> getList(@Param("param") String param);

}