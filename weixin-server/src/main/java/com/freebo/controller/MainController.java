package com.freebo.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("main")
public class MainController {

	@RequestMapping("")
	public String hello(Map<String, Object> map) {
		map.put("msg", "我是主页");
		return "main";
	}
	
}
