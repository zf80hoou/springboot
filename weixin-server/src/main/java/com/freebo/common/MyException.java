package com.freebo.common;

public class MyException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5315011475980820526L;
	
	private String code;
	private String msg;

	public MyException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	
}
