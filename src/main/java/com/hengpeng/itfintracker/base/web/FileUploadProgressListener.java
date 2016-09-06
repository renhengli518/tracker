package com.hengpeng.itfintracker.base.web;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.ProgressListener;

import com.hengpeng.itfintracker.commons.utils.Progress;

/**
 * 
 * 功能描述： 文件上传进度<br>
 * 
 */

public class FileUploadProgressListener implements ProgressListener {
	public static final String SESSION_PROGRESS_NAME = "upload_progress";
	private HttpSession session;
	private String sequence;

	public FileUploadProgressListener(HttpSession session, String sequence) {
		this.session = session;
		this.sequence = sequence;
	}

	/**
	 * 
	 * 更新文件读取状态
	 * 
	 * @param pBytesRead
	 *            到目前为止读取文件的比特数
	 * 
	 * @param pContentLength
	 *            文件总大小
	 * 
	 * @param pItems
	 *            目前正在读取第几个文件
	 * 
	 */

	public void update(long pBytesRead, long pContentLength, int pItems) {

		@SuppressWarnings("unchecked")

		Map<String, Progress> map = (Map<String, Progress>) session.getAttribute(SESSION_PROGRESS_NAME);

		if (map != null) {

			Progress status = map.get(sequence);

			status.setBytesRead(pBytesRead);

			status.setContentLength(pContentLength);

			status.setItems(pItems);

		}

	}

}