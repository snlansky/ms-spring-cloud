package com.itmuch.cloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecwid.consul.v1.ConsulClient;
import com.itmuch.cloud.entity.User;
import com.itmuch.cloud.repository.UserRepository;

@RestController
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ConsulClient eurekaClient;
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@GetMapping("/simple/{id}")
	public User findById(@PathVariable Long id) {
		return userRepository.findOne(id);
	}
	
	@GetMapping("/eureka-instance")
	public String serviceUrl() {
//	    InstanceInfo instance = eurekaClient.getNextServerFromEureka("MICROSERVICE-PROVIDER-USER", false);
//	    return instance.getHomePageUrl();
		return "";
	}
	
	@GetMapping("/instance-info")
	public ServiceInstance showInfo() {
		ServiceInstance localServiceInstance = discoveryClient.getLocalServiceInstance();
		return localServiceInstance;
	}
	
	@PostMapping("/user")
	public User postUser(@RequestBody User user) {
		return user;
	}
	
	@GetMapping("/list-all")
	public List<User> listAll(){		
		List<User> list = userRepository.findAll();
		return list;
	}
	
	@GetMapping("/health")
	public int getHealth() {
		return 200;
	}

}
