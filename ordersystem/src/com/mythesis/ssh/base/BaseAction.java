package com.mythesis.ssh.base;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import com.mysql.jdbc.Field;
import com.mythesis.ssh.service.ChairService;
import com.mythesis.ssh.service.CuisineService;
import com.mythesis.ssh.service.EmployeeService;
import com.mythesis.ssh.service.KnowledgeService;
import com.mythesis.ssh.service.PrivilegeService;
import com.mythesis.ssh.service.RoleService;
import com.mythesis.ssh.service.StoreService;
import com.mythesis.ssh.service.UserService;
import com.mythesis.ssh.util.LoggerManager;
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
	
	@Resource
	protected RoleService roleService;//角色Service
	
	@Resource
	protected PrivilegeService privilegeService;//权限Service
	
	@Resource
	protected StoreService storeService;//本店信息Service
	
	@Resource
	protected KnowledgeService knowledgeService;//小知识Service
	
	@Resource
	protected UserService userService;//顾客Service
	
	@Resource
	protected CuisineService cuisineService;//菜系Service
	
	@Resource
	protected ChairService chairService;//桌椅Service

	// ---------------------------------------------------------------------------
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

	// ---------------------------------------------------------------------------
	/**
	 * 保存上传的文件，并返回文件在服务端的真实存储路径
	 * 
	 * @param upload
	 * @return
	 */
	protected String saveUploadFile(File upload, String uploadFileName) {
		if (upload == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("/yyyy-MM-dd/");
		// >> 获取服务器路径
		String basePath = ServletActionContext.getServletContext().getRealPath("/WEB-INF/upload_files");
		String subPath = sdf.format(new Date());// 避免一个文件夹下有大量的文件
		// >> 如果文件夹不存在，就创建
		File dir = new File(basePath + subPath);
		if (!dir.exists()) {
			dir.mkdirs(); // 递归的创建不存在的文件夹
		}
		// >> 拼接路径，保证文件名不是中文引起的乱码
//		String path = basePath + subPath + UUID.randomUUID().toString();
		String path = basePath + subPath + uploadFileName;
		LoggerManager.printInfo(getClass(), "图片服务器地址："+path);
		// >> 移动文件
		if (upload != null) {
			upload.renameTo(new File(path)); // 如果目标文件夹不存在，或是目标文件已存在，就会不成功，返回false，但不报错。
		}
		return path;
	}
}
