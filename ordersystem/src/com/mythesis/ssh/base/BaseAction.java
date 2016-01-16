package com.mythesis.ssh.base;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import com.mythesis.ssh.service.EmployeeService;
import com.opensymphony.xwork2.ActionSupport;


/**
 * @author anbang
 * @description 所有不需要ModelDriven接口的都必须继承该Action
 * @date 2016年1月3日 下午2:34:11
 */
@SuppressWarnings("serial")
public class BaseAction extends ActionSupport {

	// ------------------------------Service提供-----------------------------------
	@Resource
	protected EmployeeService employeeService;//员工Service

	// ------------------------------获取当前登录-----------------------------------
	/*protected User getCurrentUser() {
		return (User) ActionContext.getContext().getSession().get("user");
	}*/

	// -----------------------------------------------------------------
	protected int pageNum = 1;// 当前页，默认是第一页
	protected int pageSize = 4;// 每页显示多少条，默认是第十页

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	// -----------------------------------------------------------------
	/**
	 * 保存上传的文件，并返回文件在服务端的真实存储路径
	 * 
	 * @param upload
	 * @return
	 */
	protected String saveUploadFile(File upload) {
		if (upload == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");
		// >> 获取路径
		String basePath = ServletActionContext.getServletContext().getRealPath("/WEB-INF/upload_files");
		String subPath = sdf.format(new Date());// 避免一个文件夹下有大量的文件
		// >> 如果文件夹不存在，就创建
		File dir = new File(basePath + subPath);
		if (!dir.exists()) {
			dir.mkdirs(); // 递归的创建不存在的文件夹
		}
		// >> 拼接路径，保证文件名不是中文引起的乱码
		String path = basePath + subPath + UUID.randomUUID().toString();
//		LoggerManager.printInfo(getClass(), path);
		// >> 移动文件
		upload.renameTo(new File(path)); // 如果目标文件夹不存在，或是目标文件已存在，就会不成功，返回false，但不报错。
		return path;
	}
}
