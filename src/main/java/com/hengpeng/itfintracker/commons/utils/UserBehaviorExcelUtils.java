package com.hengpeng.itfintracker.commons.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;

import com.hengpeng.itfintracker.entity.UserBehaviorRecord;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class UserBehaviorExcelUtils {

	/*
	 * public static void main(String[] args) { File file = new
	 * File("E://commentExport.xls"); extractLoanEOT(file);
	 * 
	 * System.out.println(isNumeric("12b3")); }
	 */

	/**
	 * 导入excel
	 * 
	 * @param file
	 * @return
	 * @author
	 */
	public static List<UserBehaviorRecord> extractLoanEOT(File file) {
		List<UserBehaviorRecord> commentList = new ArrayList<UserBehaviorRecord>();// 获得导入的评价记录
		List<UserBehaviorRecord> errorcommList = new ArrayList<UserBehaviorRecord>();// 获得导入失败的记录的集合

		try {
			Workbook book = Workbook.getWorkbook(file);
			// 获得第一个工作表对象
			Sheet sheet = book.getSheet(0);// 读取第一个sheet
			int rows = sheet.getRows();
			int columns = sheet.getColumns();// 总列数
			for (int i = 1; i < rows; i++) {// 从第一行开始读取数据
				boolean flag = false;// 判断这条评论是否存在错误
				UserBehaviorRecord comment = new UserBehaviorRecord(); // 一共只有四列
				String userName = sheet.getCell(0, i).getContents();
				String productNum = sheet.getCell(1, i).getContents();
				String score = sheet.getCell(2, i).getContents();
				if (!"1".equals(score) && !"2".equals(score) && !"3".equals(score) && !"4".equals(score) && !"5".equals(score)) {
					// 表示输入的产品编号有问题
					// comment.setFailReason("请填写正确评分!");
					flag = true;// 表示导入失败
				}
				String content = sheet.getCell(3, i).getContents();
				// comment.setShowName(userName);//用户名
				// comment.setCommentUserId(userName);//暂时评论的用户名和id一致
				// comment.setType(1);//表示该评价的对象时产品
				// comment.setReferId(Long.parseLong((("".equals(productNum)?"0":productNum))));//编号和类型一致
				// type=1表示是对产品的评价 这个时候referId才是产品的编号
				// comment.setScore(Integer.parseInt((("".equals(score)?"0":score))));//产品评分
				// comment.setContent(content);//评价的内容
				// 将封装的每条评价对象加入集合
				if ("".equals(userName) && "".equals(score) && "".equals(productNum) && "".equals(content)) {
					// 什么也不操作
				} else {
					if (flag) {// 表示此条评价存在问题
						errorcommList.add(comment);
					} else {// 表示此条评价没有问题 加入数据库的
						commentList.add(comment);
					}

				}
			}
			book.close();
			// 将未导入成功的出错的评论进行导出
			if (CollectionUtils.isNotEmpty(errorcommList)) {
				System.out.println("noOk");
				// String filePath =
				// exportUserBehaviorRecordList(errorcommList,filePath);
				// System.out.println("filePath=" + filePath);
			} else {
				System.out.println("ok");
			}
			return commentList;
		} catch (Exception e) {
			System.out.println(e);
			commentList = null;
		}
		return commentList;
	}

	/**
	 * 导出用户的excel
	 * 
	 * @return
	 */
	public static String exportUserBehaviorRecordList(List<UserBehaviorRecord> list, String path) {
		WritableWorkbook book = null;
		String filepath = "";
		try {
			 File destDir = new File(path);
		        if (!destDir.exists()) {
		            destDir.mkdirs();
		        }
			// 打开文件
			String fileName = DateUtils.format(System.currentTimeMillis(),"yyyy_MM_dd_HH_mm_ss") + "_" + "用户行为报表" + ".xls";
			filepath = path + File.separator + fileName;// 导出的文件的路径
			book = Workbook.createWorkbook(new File(path, fileName));
			WritableSheet sheet = book.createSheet("用户行为列表", 0);// 第一个sheet的题目

			// 指定单元格位置是第一列第一行(0, 0)以及单元格内容
			// 设置导出的excel的表头
			Label label0 = new Label(0, 0, "按钮名称");
			sheet.addCell(label0);

			Label label1 = new Label(1, 0, "链接名称");
			sheet.addCell(label1);

			Label label2 = new Label(2, 0, "访问类型");
			sheet.addCell(label2);

			Label label3 = new Label(3, 0, "ip");
			sheet.addCell(label3);

			Label label4 = new Label(4, 0, "用户名");
			sheet.addCell(label4);

			Label label5 = new Label(5, 0, "触发时间");
			sheet.addCell(label5);

			Label label6 = new Label(6, 0, "新用户");
			sheet.addCell(label6);

			Label label7 = new Label(7, 0, "浏览器类型");
			sheet.addCell(label7);

			Label label8 = new Label(8, 0, "访问页面");
			sheet.addCell(label8);

			Label label9 = new Label(9, 0, "国家");
			sheet.addCell(label9);

			Label label10 = new Label(10, 0, "省份");
			sheet.addCell(label10);

			Label label11 = new Label(11, 0, "城市");
			sheet.addCell(label11);

			Label label12 = new Label(12, 0, "浏览时长");
			sheet.addCell(label12);

			Label label13 = new Label(13, 0, "页面标题");
			sheet.addCell(label13);

			Label label14 = new Label(14, 0, "上一页面");
			sheet.addCell(label14);

			Label label15 = new Label(15, 0, "系统");
			sheet.addCell(label15);

			Label label16 = new Label(16, 0, "分辨率");
			sheet.addCell(label16);

			Label label17 = new Label(17, 0, "页面类型");
			sheet.addCell(label17);

			Label label18 = new Label(18, 0, "访问来源");
			sheet.addCell(label18);

			Label label19 = new Label(19, 0, "搜索关键字");
			sheet.addCell(label19);
			
			Label label20 = new Label(20, 0, "浏览器版本");
			sheet.addCell(label20);

			for (int i = 0; i < list.size(); i++) {// 行数为数据集合的大小

				Label a = new Label(0, i + 1, list.get(i).getButtonposition());
				sheet.addCell(a);

				Label b = new Label(1, i + 1, list.get(i).getLinkposition());
				sheet.addCell(b);

				Label c = new Label(2, i + 1, list.get(i).getViewtype());
				sheet.addCell(c);

				Label d = new Label(3, i + 1, list.get(i).getIp());
				sheet.addCell(d);

				Label e = new Label(4, i + 1, list.get(i).getEnduserid());
				sheet.addCell(e);
				
				Label f = new Label(5, i + 1, DateUtils.format(list.get(i).getClienttime(), "yyyy-MM-dd HH:mm:ss"));
				sheet.addCell(f);
				
				Label g = new Label(6, i + 1, list.get(i).getNewuserflag());
				sheet.addCell(g);
				
				Label h = new Label(7, i + 1, list.get(i).getBrowsertype());
				sheet.addCell(h);
				
				Label j = new Label(8, i + 1, list.get(i).getPageurl());
				sheet.addCell(j);
				
				Label k = new Label(9, i + 1, list.get(i).getCountry());
				sheet.addCell(k);
				
				Label l = new Label(10, i + 1, list.get(i).getProvince());
				sheet.addCell(l);
				
				Label m = new Label(11, i + 1, list.get(i).getCity());
				sheet.addCell(m);
				
				Label n = new Label(12, i + 1, list.get(i).getStaytime());
				sheet.addCell(n);
				
				Label o = new Label(13, i + 1, list.get(i).getPagetitle());
				sheet.addCell(o);
				
				Label p = new Label(14, i + 1, list.get(i).getRefferpage());
				sheet.addCell(p);
				
				Label q = new Label(15, i + 1, list.get(i).getClientsystem());
				sheet.addCell(q);
				
				Label r = new Label(16, i + 1, list.get(i).getClientresolution());
				sheet.addCell(r);
				
				Label s = new Label(17, i + 1, list.get(i).getClientpagetype());
				sheet.addCell(s);
				
				Label t = new Label(18, i + 1, list.get(i).getFromwhere());
				sheet.addCell(t);
				
				Label u = new Label(19, i + 1, list.get(i).getSerachkeywords());
				sheet.addCell(u);
				
				Label v = new Label(20, i + 1, list.get(i).getBrowserversion());
				sheet.addCell(v);
			}
			// 写入数据并关闭文件
			book.write();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		} finally {
			if (book != null) {
				try {
					book.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return filepath;
	}

	/**
	 * 判断一个字符串是否为数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

}
