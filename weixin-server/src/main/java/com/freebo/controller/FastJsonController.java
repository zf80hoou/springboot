package com.freebo.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.freebo.bean.UserInfo;
import com.freebo.common.MyException;

@Controller
@RequestMapping("fastjson")
public class FastJsonController {

	@RequestMapping("/test")
	@ResponseBody
	public UserInfo test() {
		UserInfo user = new UserInfo();
		
		user.setUserId(1);
		user.setUserName("freebo");
		user.setPassWord("123456");
		user.setBirthday(new Date());
		user.setTelNum("18914493216");
		
		int k = 0;
		if (0 == k) {
			throw new MyException("200", "抛出自定义异常");
		}
		
		return user;
	}
}

