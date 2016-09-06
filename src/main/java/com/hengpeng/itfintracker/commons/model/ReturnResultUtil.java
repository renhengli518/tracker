/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
package com.hengpeng.itfintracker.commons.model;

import java.util.HashMap;
import java.util.Map;

public class ReturnResultUtil {
	private String resultCode;
	private String resultMsg;
	private Object resultValue;
	private Map extras = new HashMap();

	public Map getExtras() {
		return this.extras;
	}

	public void setExtras(Map extras) {
		this.extras = extras;
	}

	public ReturnResultUtil() {
		this.resultCode = "00";
		this.resultMsg = "�ɹ�";
	}

	public ReturnResultUtil(Object resultValue) {
		this.resultCode = "00";
		this.resultMsg = "�ɹ�";
		this.resultValue = resultValue;
	}

	public ReturnResultUtil(String resultCode, String resultMsg) {
		this.resultCode = resultCode;
		this.resultMsg = resultMsg;
	}

	public ReturnResultUtil(String resultCode, String resultMsg, Object resultValue) {
		this.resultCode = resultCode;
		this.resultMsg = resultMsg;
		this.resultValue = resultValue;
	}

	public String getResultCode() {
		return this.resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMsg() {
		return this.resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public Object getResultValue() {
		return this.resultValue;
	}

	public void setResultValue(Object resultValue) {
		this.resultValue = resultValue;
	}

	public String toString() {
		return "ReturnResultUtil [resultCode=" + this.resultCode + ", resultMsg=" + this.resultMsg + ", resultValue=" + this.resultValue + "]";
	}
}