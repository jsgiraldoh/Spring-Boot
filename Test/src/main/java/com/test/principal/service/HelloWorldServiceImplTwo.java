package com.test.principal.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.test.principal.repositories.IHelloWorldService;

@Service
public class HelloWorldServiceImplTwo implements IHelloWorldService {

	
	@Qualifier("helloWorldServiceImplTwo")
	public String getHelloWorld() {
		return "Hola mundo";
	}

}
