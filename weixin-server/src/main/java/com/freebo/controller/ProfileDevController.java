package com.freebo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.freebo.service.SpeakLanguage;

@RestController
@Profile("dev")//这个注解要从 controller开始 一直配置 才有效 单配service层无法被注入
@RequestMapping("/profiletest")
public class ProfileDevController {

	@Autowired
	private SpeakLanguage speakLanguage;
	
    @RequestMapping("/")
    public String helloworld() {
        return "helloworld";
    }
    
    @RequestMapping("/helloworld2")
    public String helloworld2() {
    	return speakLanguage.speakSomething();
    }
    
}
