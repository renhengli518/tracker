package com.hengpeng.itfintracker.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hengpeng.itfintracker.commons.constant.ReturnConstant;
import com.hengpeng.itfintracker.commons.model.FileUpload;
import com.hengpeng.itfintracker.commons.model.MultipartFileValidator;
import com.hengpeng.itfintracker.commons.model.ReturnResultUtil;
import com.hengpeng.itfintracker.commons.utils.UserBehaviorExcelUtils;

@Controller
public class UploadController {
    private static final Logger logger = Logger.getLogger(UploadController.class);

    private static MultipartFileValidator validator;

    @Value("#{configProperties['temp.img.base.dir']}")
    private String tempBaseDir;

    @Value("#{configProperties['uploadFile.base.dir']}")
    private String uploadFileBaseDir;

    @Value("#{configProperties['image.url.base']}")
    private String imageUrlBase;


    @RequestMapping(value = "/common/uploadImage", method = RequestMethod.POST)
    @ResponseBody
    public FileUpload uploadImage(@RequestParam("imageFile") MultipartFile imageFile, @RequestParam(value = "imageDirBase", defaultValue = "") String imageDirBase) throws Exception {
        if (validator == null) {
            validator = new MultipartFileValidator();
        }
        validator.validate(imageFile);
        String imageWebBaseUrl = imageUrlBase + imageDirBase;
        File destDir = new File(uploadFileBaseDir + imageDirBase);
        if (!destDir.exists()) {
            destDir.mkdirs();
        }
        File destFile = new File(destDir.getCanonicalPath() + File.separator + createFileNameByUuid(imageFile.getOriginalFilename()));
        imageFile.transferTo(destFile);
        logger.info("-------图片已经上传至本地目录:" + destFile);
        FileUpload uploadedFile = new FileUpload();
        uploadedFile.setFileName(destFile.getName());
        uploadedFile.setRelativePath(imageWebBaseUrl + "/" + destFile.getName());
        return uploadedFile;
    }

    /**
     * 删除图片
     *
     * @param imgUrl 图片url访问地址
     * @return
     */
    @RequestMapping(value = "/common/delUploadImage",method = RequestMethod.POST)
    @ResponseBody
    public ReturnResultUtil delUploadImage(@RequestParam String imgUrl) {
        ReturnResultUtil returnResultUtil;
        try{
            File file = new File(uploadFileBaseDir + imgUrl);
            if (file.exists()) {
                file.delete();
                File parentFile = file.getParentFile();
                if (parentFile.isDirectory()) {
                    String[] parentFiles = parentFile.list();
                    if (parentFiles.length == 0) {
                        parentFile.delete();
                    }
                }
            }
            returnResultUtil = new ReturnResultUtil();
        }catch(Exception e){
            returnResultUtil = new ReturnResultUtil(ReturnConstant.RETURN_VALUE_01, e.toString());
        }

        return returnResultUtil;
    }

    @Deprecated
    @RequestMapping(value = "/upload-file", method = RequestMethod.POST)
    @ResponseBody
    public FileUpload handleUploadProcess(@RequestParam("imageFile") MultipartFile imageFile, @RequestParam(value = "imageDirBase", required = false) String imageDirBase) throws Exception {
        if (validator == null) {
            validator = new MultipartFileValidator();
        }
        validator.validate(imageFile);
        long currentTime = new Date().getTime();
        File destDir = new File(tempBaseDir + File.separator + currentTime);
        if (!destDir.exists()) {
            destDir.mkdir();
        }

        File destFile = new File(destDir.getCanonicalPath() + File.separator + createFileNameByUuid(imageFile.getOriginalFilename()));


        imageFile.transferTo(destFile);
        logger.info("-----temp image file is saved:-------" + destFile);
        FileUpload uploadedFile = new FileUpload();
        uploadedFile.setFileName(destFile.getName());
        uploadedFile.setDestDir(destDir.getCanonicalPath());
        uploadedFile.setDestFile(destFile.getCanonicalPath());
        uploadedFile.setFolderName(destDir.getName());
        uploadedFile.setRelativePath("temp/" + destDir.getName() + "/" + destFile.getName());
        return uploadedFile;
    }


    /**
     * 创建新的文件名称根据 uuid
     *
     * @return
     */
    public static String createFileNameByUuid(String pathFile) {
        String imgFileName = "";
        if (StringUtils.isEmpty(pathFile))
            return imgFileName;

        int suffixPosition = pathFile.lastIndexOf(".");   //后缀位置
        UUID uuid = UUID.randomUUID();
        String suffixName = pathFile.substring(suffixPosition);  //后缀名称
        imgFileName = uuid + suffixName;  //新建的文件名称
        return imgFileName;
    }

    @RequestMapping(value = "/image/read/{imageFolder}/{imageName:.+}")
    public void getImage(HttpServletRequest request, HttpServletResponse response, @PathVariable String imageFolder,
                         @PathVariable String imageName) {
        FileInputStream fis = null;
        response.setContentType("image/gif");
        try {
            OutputStream out = response.getOutputStream();
            File file = new File(tempBaseDir + File.separator + imageFolder + File.separator + imageName);
            fis = new FileInputStream(file);
            byte[] b = new byte[fis.available()];
            fis.read(b);
            out.write(b);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @RequestMapping(value = "/image/read")
    public void readImage(HttpServletRequest request, HttpServletResponse response, @RequestParam String imagePath) {
        FileInputStream fis = null;
        response.setContentType("image/gif");
        try {
            OutputStream out = response.getOutputStream();
            File file = new File(imagePath);
            fis = new FileInputStream(file);
            byte[] b = new byte[fis.available()];
            fis.read(b);
            out.write(b);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 删除图片
     *
     * @param filePath 文件路径
     * @return
     */
    @RequestMapping(value = "/file/del")
    @ResponseBody
    public String delImage(@RequestParam String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
            File parentFile = file.getParentFile();
            if (parentFile.isDirectory()) {
                String[] parentFiles = parentFile.list();
                if (parentFiles.length == 0) {
                    parentFile.delete();
                }
            }
        }


        return "";
    }



    /**
     *
     * @param filePath 文件路径
     * @return
     */
    @RequestMapping(value = "/common/mobile-application")
    public void downloadMobileSoftware (HttpServletRequest request,HttpServletResponse response,
                                        @RequestParam(value = "applicationType", defaultValue = "android") String applicationType) throws  Exception{


        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        java.io.BufferedInputStream bis = null;
        java.io.BufferedOutputStream bos = null;

        //获得请求文件名
        StringBuffer applicationPath = new StringBuffer("/static/mobile_application");

        if("android".equals(applicationType.trim())){
            applicationPath.append("/linkgap_android.apk");
        }  else if("ios".equals(applicationType.trim())){
        	applicationPath.append("/linkgap_ios.apk");
        }
        //设置Content-Disposition
        //读取目标文件，通过response将目标文件写到客户端
        //获取目标文件的绝对路径
        String fullFileName = request.getSession().getServletContext().getRealPath("/") + applicationPath.toString();

        //System.out.println(fullFileName);
        //读取文件
        try {
            long fileLength = new File(fullFileName).length();
            response.setContentType("application/x-msdownload;");
            response.setHeader("Content-disposition",
                    "attachment; filename=" + new String("linkgap.apk".getBytes("utf-8"), "ISO8859-1"));
            response.setHeader("Content-Length", String.valueOf(fileLength));
            bis = new BufferedInputStream(new FileInputStream(fullFileName));
            bos = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }
    }
    
    @RequestMapping("/common/importExcel")
    public String importExcel(HttpServletRequest request){
    	try{
    		String path=request.getRealPath("/");
    		path =path.substring(0, path.lastIndexOf("\\"));
    		path =path.substring(0, path.lastIndexOf("\\")+1);
    		File file = new File(path+"main/webapp/downloadTemp/commentsImport.xls");
    		UserBehaviorExcelUtils.extractLoanEOT(file);
    		return "ok";
    	}catch(Exception e){
    		return "no";
    	}
    }
}
