package com.freebo.service.impl;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.freebo.service.SpeakLanguage;

@Service
@Profile({"test", "prd"})
public class SpeakLanguageEnglish implements SpeakLanguage {

	@Override
	public String speakSomething() {
		System.out.println("I can speak English");
		return "I can speak English";
	}

}
