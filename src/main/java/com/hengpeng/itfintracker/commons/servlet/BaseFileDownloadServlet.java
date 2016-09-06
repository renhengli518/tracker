package com.hengpeng.itfintracker.commons.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.util.StreamUtils;

import com.hengpeng.itfintracker.commons.thumbimage.ThumbnailImageTransfer;

public class BaseFileDownloadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(BaseFileDownloadServlet.class);

	public BaseFileDownloadServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String path = request.getPathInfo();
		String fileStr = path.substring(path.lastIndexOf('/') + 1);
		// classId_clsClassroomId_pic
		logger.debug("fileStr:" + fileStr);
		String classId = fileStr.substring(0, fileStr.indexOf("_"));
		String clsClassroomId = fileStr.substring(fileStr.indexOf("_") + 1, fileStr.lastIndexOf("_"));
		String fileName = fileStr.substring(fileStr.lastIndexOf("_")+1);// 文件名
		//ImageConfig config = SpringContext.getBean(ImageConfig.class);
		String floder = "D://";
		File file = new File(BaseFileUploadServlet.buildFile(floder, classId, clsClassroomId, fileName));
		if (!file.exists()) {
			if (fileName.contains(".small.")) {
				String bigFileName = fileName.replace(".small", "");
				file = new File(BaseFileUploadServlet.buildFile(floder, classId, clsClassroomId, bigFileName));
				File smallFile = new File(BaseFileUploadServlet.buildFile(floder, classId, clsClassroomId, fileName));
				//file = 小图
				if(file.exists()){
					InputStream in = new FileInputStream(file);
					smallFile.createNewFile();
					ThumbnailImageTransfer transfer = new ThumbnailImageTransfer();
					String suffix = "";
					suffix = fileName.substring(fileName.lastIndexOf(".")+1);
					suffix = suffix.toLowerCase();
					InputStream smallInput = null;
					try {
						smallInput = transfer.getImageInputStreamByResizeFix(200, 200, in, suffix);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					file = smallFile;
					OutputStream out = new FileOutputStream(smallFile);
					StreamUtils.copy(smallInput, out);
					smallInput.close();
					out.close();
					in.close();
				}
				
			} 
			if (!file.exists()) {
				return ;
			}
		}
		InputStream input = new FileInputStream(file);
		response.addHeader("Cache-Control", "max-age=864000");
		StreamUtils.copy(input, response.getOutputStream());
		input.close();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
