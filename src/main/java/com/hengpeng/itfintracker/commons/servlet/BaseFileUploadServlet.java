package com.hengpeng.itfintracker.commons.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hengpeng.itfintracker.base.web.CustomMultipartResolver;
import com.hengpeng.itfintracker.commons.utils.ResultJson;
import com.hengpeng.itfintracker.commons.utils.SecurityUtil;
/**
 * 
 * ClassName: BaseClassWorkUploadServlet
 * Function: 文件上传Servlet
 * Reason: TODO ADD REASON(可选)
 * @version  
 * @since JDK 1.7
 */
public class BaseFileUploadServlet extends HttpServlet {

	
	private static final long serialVersionUID = -7163921304268364778L;
	private Logger logger = Logger.getLogger(BaseFileUploadServlet.class);
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BaseFileUploadServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CustomMultipartResolver resolver = new CustomMultipartResolver();
		String clsClassroomId = request.getParameter("clsClassroomId");//教室ID
		String classId = request.getParameter("classId");//课堂ID
		resolver.setDefaultEncoding("UTF-8");
		resolver.setMaxUploadSize(5 * 1024 * 1024);//大小
		resolver.setServletContext(request.getSession().getServletContext());
		resolver.setMaxInMemorySize(1024 * 1024);
		//ImageConfig config = SpringContext.getBean(ImageConfig.class);
		String floder = "d://";
		resolver.setUploadTempDir(new FileSystemResource(floder + File.separator + "temp"));
		MultipartHttpServletRequest multiRequest = null;
		try {
			multiRequest = resolver.resolveMultipart(request);
		} catch (MaxUploadSizeExceededException e) {
			logger.debug("Upload file is too big");
			ResultJson json = new ResultJson(false, 1, "File is too big");
			response.getWriter().write(json.toString());
			return;
		}

		Collection<MultipartFile> files = multiRequest.getFileMap().values();
		if (files == null || files.size() == 0) {
			response.getWriter().write(new ResultJson(false, 2, "File content is empty!").toString());
			logger.debug("Upload file is empty");
			return;
		}

		MultipartFile multiFile = files.iterator().next();
		String original = multiFile.getOriginalFilename();
		original = (original == null) ? "" : original;
		String suffix = "";
		if (original != null) {
			int x = original.lastIndexOf('.');
			if (x >= 0) {
				suffix = original.substring(x);
			}
		}
		File file = createFile(classId,clsClassroomId,suffix);//创建目标文件
		multiFile.transferTo(file);// 写入目标文件
		ResultJson json = new ResultJson(true, 0, file.getName());
		response.getWriter().write(json.toString());
		logger.debug("File upload transferTo over");
	}
	
	public static File createFile(String classId,String clsClassroomId,String suffix) throws IOException {
		String uuid = UUID.randomUUID().toString();
		//ImageConfig config = SpringContext.getBean(ImageConfig.class);
		String floder = "d://";
		String filePath = buildFile(floder,classId,clsClassroomId, uuid + suffix);
		File file = new File(filePath);
		if (file.exists()) {
			return createFile(classId,clsClassroomId,suffix);
		} else {
			file.getParentFile().mkdirs();
			file.createNewFile();
			return file;
		}
	}
	
	/**
	 * 
	 * buildFile:采用课堂ID加密取前六位做文件夹路径+教室ID+文件名
	 * @author wangqiqi 
	 * @param folder
	 * @param clsClassroomId
	 * @param fileName
	 * @return
	 */
	public static String buildFile(String folder,String classId, String clsClassroomId,String fileName){
		String md5 = SecurityUtil.MD5String(classId);
		StringBuffer buffer = new StringBuffer();
		buffer.append(folder);
		buffer.append(File.separatorChar);
		buffer.append(md5.substring(0, 2));
		buffer.append(File.separatorChar);
		buffer.append(md5.substring(2, 4));
		buffer.append(File.separatorChar);
		buffer.append(md5.substring(4, 6));
		buffer.append(File.separatorChar);
		buffer.append(clsClassroomId);
		buffer.append(File.separatorChar);
		buffer.append(fileName);
		return buffer.toString();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
