package com.myclass.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception  {
		
		http.csrf().disable()
		.antMatcher("/**") //kiểm với url bắt đầu bằng /...
		.authorizeRequests()
		.antMatchers("/auth/login")
		.permitAll() // Ko bị ktr đăng nhập phân quyền
		.antMatchers("/user/**")
		.hasAnyRole("ADIM")
		.antMatchers("/ship/**")
		.hasAnyRole("ADIM")
		.anyRequest() // mọi request gửi lên từ clinet
		.authenticated(); // bắt buộc phải ktr login
		
		
		http.formLogin().loginPage("/auth/login")
		.loginProcessingUrl("/auth/login")
		.usernameParameter("name")
		.passwordParameter("password")
		.defaultSuccessUrl("/home", true)
		.failureUrl("/auth/login?error=true");
		
		http.logout().logoutUrl("/auth/logout")
		.logoutSuccessUrl("/auth/login");
	}
	
	
	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**","/fonts/**", "/icon/**", "/images/**", "/plugins/**");
	}
}
