package com.freebo.service.impl;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.freebo.service.SpeakLanguage;

@Service
@Profile("dev")
public class SpeakLanguageChinese implements SpeakLanguage {

	@Override
	public String speakSomething() {
		System.out.println("我说中文");
		return "我说中文";
	}

}
