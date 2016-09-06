package com.hengpeng.itfintracker.base.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * 
 * 功能描述：文件上传Resolver <br>
 * 
 */

public class CustomMultipartResolver extends CommonsMultipartResolver {

	// private Logger logger = Logger.getLogger(CustomMultipartResolver.class);

	@Override
	protected MultipartParsingResult parseRequest(HttpServletRequest request) throws MultipartException {

		String sequence = request.getParameter("sequence");
		String encoding = determineEncoding(request);
		FileUpload fileUpload = prepareFileUpload(encoding);
		FileUploadProgressListener progressListener = new FileUploadProgressListener(request.getSession(), sequence);
		fileUpload.setProgressListener(progressListener);
		try {
			List<FileItem> fileItems = ((ServletFileUpload) fileUpload).parseRequest(request);
			return parseFileItems(fileItems, encoding);
		} catch (FileUploadBase.SizeLimitExceededException ex) {
			throw new MaxUploadSizeExceededException(fileUpload.getSizeMax(), ex);
		} catch (FileUploadException ex) {
			throw new MultipartException("Could not parse multipart servlet request", ex);
		}
	}

	@Override

	public boolean isMultipart(HttpServletRequest request) {

		// 当无sequence属性时交给Controller自己处理流

		String sequence = request.getParameter("sequence");

		if (StringUtils.isBlank(sequence))

			return false;

		return super.isMultipart(request);

	}

}