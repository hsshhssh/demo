package com.hssh.demo;

import com.hssh.demo.http.HttpTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CommonDemoApplication {

	public static void main(String[] args) {
		//SpringApplication.run(CommonDemoApplication.class, args);

		System.out.println(HttpTest.get(args[0]));
	}
}
