package com.bitguiders.bjsbt_01.dataaccess.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bitguiders.bjsbt_01.dataaccess.orm.UserORM;

@Repository
public interface UserDAO extends JpaRepository<UserORM,Long> {
	
	@Query("select distinct u from UserORM u")
	List<UserORM> getList();

	@Query("select distinct u from UserORM u where u.userId= :userId")
	UserORM getUserById(@Param("userId") Long userId);

	@Modifying
	@Query("update UserORM u set u.userName = ?1, u.phone = ?2 where u.userId = ?3")
	void updateUser(String userName, String phone, Long userId);
	
	//@Query("select distinct c from Car c where c.manufacturer= :param or c.carModel= :param")
	//List<UserORM> getList(@Param("param") String param);

}
