package com.hssh.demo;

import com.hssh.demo.http.HttpTest;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class CommonDemoApplication {

	public static void main(String[] args) throws IOException
	{
		//SpringApplication.run(CommonDemoApplication.class, args);

		//System.out.println(HttpTest.get(args[0]));

		HttpTest.httpsGet("https://isdtuis.5fun.com/feedback/userbx/ltgkvT?appid=20&clickid=3586672&idfa=EAD79E12-311C-44E5-B65A-1DA31AD17ABD");
	}
}
