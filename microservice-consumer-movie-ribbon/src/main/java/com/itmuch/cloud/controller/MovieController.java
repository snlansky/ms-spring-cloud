package com.itmuch.cloud.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.itmuch.cloud.entity.User;

@RestController
public class MovieController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private LoadBalancerClient loadBalancerClient;
	
	// http://127.0.0.1:7900/simple/
	// vip
	@GetMapping("/movie/{id}")
	public User findById(@PathVariable Long id) {
		return restTemplate.getForObject("http://microservice-provider-user/simple/" + id, User.class);
	}
	
	@GetMapping("/test")
	public ServiceInstance test() {
		ServiceInstance serviceInstance = loadBalancerClient.choose("microservice-provider-user");
		return serviceInstance;
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
