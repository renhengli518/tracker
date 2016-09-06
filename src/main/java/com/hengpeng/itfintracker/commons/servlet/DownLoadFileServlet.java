package com.hengpeng.itfintracker.commons.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
/**
 * 进行文件的下载操作
 */
public class DownLoadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(DownLoadFileServlet.class);

	 public DownLoadFileServlet() {
	        super();
	    }
	 
	 
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String filePath = (String) request.getParameter("url");//文件的存储路径
		//System.out.println(filePath);
        //logger.error("sssssssssssssssssssssssssssssssssstoreFileNamePath"+filePath);
    	
		//设置下载响应头
	    response.setContentType("application/x-msdownload");

        BufferedInputStream bis = null;  
        BufferedOutputStream bos = null;  
        
        File file = new File(filePath);
        long fileLength = file.length();  
        String fileName=file.getName();
       // response.setContentType("application/octet-stream");  
        String agent = request.getHeader("User-Agent");
		boolean isFireFox = (agent != null && agent.indexOf("Firefox") != -1);
		if (isFireFox) {//火狐
			response.setHeader("Content-Disposition", "attachment;filename="+new String(fileName.getBytes("UTF-8"), "ISO-8859-1"));
			
		}else if(agent != null && agent.indexOf("AppleWebKit") != -1){//谷歌
			response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(fileName, "UTF-8"));
	
		} else {//默认ie
			response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(fileName, "UTF-8"));
		}
        response.setHeader("Content-Length", String.valueOf(fileLength));  //返回文件的大小
        bis = new BufferedInputStream(new FileInputStream(filePath));  
        bos = new BufferedOutputStream(response.getOutputStream());  
        byte[] buff = new byte[2048];  
        int bytesRead;  
        while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {  
            bos.write(buff, 0, bytesRead);  
        }  
        bis.close();  
        bos.close();    
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
