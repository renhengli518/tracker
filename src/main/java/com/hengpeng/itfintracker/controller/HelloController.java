package com.hengpeng.itfintracker.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hengpeng.itfintracker.commons.utils.Excel;
import com.hengpeng.itfintracker.commons.utils.ResultJson;

@Controller
public class HelloController {
	
	private Logger logger = Logger.getLogger(HelloController.class);
	
//	@Autowired
//	private BaseUserService baseUserService;
	
	@RequestMapping("/hello")
	public ModelAndView hello() {

		ModelAndView mv = new ModelAndView();

		mv.addObject("spring", "spring mvc");

		mv.setViewName("hello");

		return mv;

	}
	
	@RequestMapping("getUserList")
	public String getUserList(HttpServletRequest request){
		//List<BaseUser> list = baseUserService.getAllUsers();
		//request.setAttribute("userList", list);
		//return "userList";
		return null;
	}
	
	/**
	 * springmvc 上传文件（并利用poi读取 word table内容）
	 * @param request
	 * @param response
	 * @param file
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("readUploadDocFile")
	public String readUploadDocFile(HttpServletRequest request , HttpServletResponse response,@RequestParam("file") MultipartFile file) throws IOException{
//		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
//		resolver.setDefaultEncoding("UTF-8");
//		resolver.setMaxUploadSize(1024*1024*1024);
//		resolver.setMaxInMemorySize(1024*1024);
//		MultipartHttpServletRequest multiRequest = null;
//		multiRequest = resolver.resolveMultipart(request);
		//MultipartFile file = multiRequest.getFile("file");
		String original = file.getOriginalFilename();
		original = (original == null) ? "" : original;
		File file_1 = new File("d:/"+file.getOriginalFilename());
		file.transferTo(file_1);// 写入目标文件
		Excel.extractLoanEOT(file_1);
//		FileInputStream in = new FileInputStream("d:/"+file.getOriginalFilename());
//		POIFSFileSystem pfs = new POIFSFileSystem(in);
//		HWPFDocument hwfp = new HWPFDocument(pfs);
//		Range range = hwfp.getRange();
//		TableIterator it = new TableIterator(range);
//		try {
//			int index = 0;
//			while(it.hasNext()){
//				Table table = (Table)it.next();
//				for (int i = 0;i<table.numRows();i++) {
//					TableRow tr = table.getRow(i);
//					for(int j = 0;j<tr.numCells(); j++){
//						TableCell td = tr.getCell(j);
//						String text = td.text();
//						System.out.println("---------word text :-------------"+text);
//					}
//				}
//				index++;
//				in.close();
//			}
//		} catch (Exception e) {
//			in.close();
//			e.printStackTrace();
//		}
		ResultJson json = new ResultJson(true, 0, file.getName());
		response.getWriter().write(json.toString());
		logger.debug("File upload transferTo over");
		return "hello";
	}
	
	
}