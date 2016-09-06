package com.hengpeng.itfintracker.commons.model;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.web.multipart.MultipartFile;

public class MultipartFileValidator {
	private String[] allowedContentTypes;

	public MultipartFileValidator() {
		this.allowedContentTypes = new String[] { "image/jpeg", "image/jpg", "image/pjpeg", "image/png", "image/x-png", "image/bmp" };
	}

	public void validate(MultipartFile file) throws Exception {
		if (!ArrayUtils.contains(allowedContentTypes, file.getContentType())) {
			throw new Exception("The content type '" + file.getContentType() + "' is not a valid content type !");
		}
	}

	public void setAllowedContentTypes(String[] allowedContentTypes) {
		this.allowedContentTypes = allowedContentTypes;
	}

}
