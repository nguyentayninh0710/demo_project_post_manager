package com.myclass.repository;

import java.util.Optional;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.myclass.entity.Country;
import com.myclass.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	public Optional<User> findByUserId(String userId);
	public int countByLoginName(String loginName);
	public User findByLoginName(String loginName);
	
	@Modifying
    @Transactional
	 public void deleteByUserId(String id);
	
	
	///thêm thuộc tính rolename trong dto user (Th có bảng role riêng) sau đó tại đây thêm vào phương thức 
	//tạo thêm câu @query join
	// @Query("SELECT new!{phải ghi đủ packege nếu càidto} com.myclass.dto.UserDto(
	//!{TẠi đây truyền từng cái thuộc tính trong dto}) FROM User u JOIN ROLE r ON u.roleId = r.id where u.email = e:email")
	// UserDto findByEmail()
}
