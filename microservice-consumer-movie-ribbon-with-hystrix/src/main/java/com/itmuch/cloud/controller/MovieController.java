package com.itmuch.cloud.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.itmuch.cloud.entity.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class MovieController {
	
	@Autowired
	private RestTemplate restTemplate;

	
	// http://127.0.0.1:7900/simple/
	// vip
	@GetMapping("/movie/{id}")
	@HystrixCommand(fallbackMethod = "findByIdFallBack")
	public User findById(@PathVariable Long id) {
		return restTemplate.getForObject("http://microservice-provider-user/simple/" + id, User.class);
	}
	
	public User findByIdFallBack(Long id) {
		User user = new User();
		user.setId(0L);
		return user;
	}
	
	
	@GetMapping("/list-all")
	public List<User> listAll(){
		// wrong
		// List<User> l1 = restTemplate.getForObject("http://microservice-provider-user/list-all/", List.class);
		
		User[] users = restTemplate.getForObject("http://microservice-provider-user/list-all/", User[].class);
		List<User> list = Arrays.asList(users);
		return list;
	}
}
