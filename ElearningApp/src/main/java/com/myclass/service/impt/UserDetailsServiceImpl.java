package com.myclass.service.impt;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.myclass.dto.UserDto;
import com.myclass.entity.User;
import com.myclass.repository.UserRepository;
import com.myclass.until.CustomUserDetails;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//gọi hàm để kiểm tra
		User user = userRepository.findByLoginName(username);
		if(user == null) {
			throw new UsernameNotFoundException("Không tìm thấy tài khoản");
		}
		//Tạo đối tượng kiểu Userdetails  và lưu thông tin user vào đóng
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		String role = user.getRole();
		authorities.add(new SimpleGrantedAuthority(role));
		CustomUserDetails userDetails = new CustomUserDetails(user.getLoginName(), 
				user.getPassword(), authorities);
		userDetails.setFristName(user.getFristName());
		userDetails.setLastName(user.getLastName());
		//trả về 
		return userDetails;
	}

}
