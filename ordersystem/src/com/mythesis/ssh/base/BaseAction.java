package com.mythesis.ssh.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.mythesis.ssh.service.ChairService;
import com.mythesis.ssh.service.CommentService;
import com.mythesis.ssh.service.CountService;
import com.mythesis.ssh.service.CuisineService;
import com.mythesis.ssh.service.EmployeeService;
import com.mythesis.ssh.service.KnowledgeService;
import com.mythesis.ssh.service.MenuService;
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
	
	@Resource
	protected MenuService menuService;//菜单Service
	
	@Resource
	protected CommentService commentService;//评论Service
	
	@Resource
	protected CountService countService;//统计Service

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
	/*protected String saveUploadFile(File upload, String uploadFileName) {
		if (upload == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("/yyyy-MM-dd/");
		// >> 获取服务器路径(项目根路径)
		String basePath = ServletActionContext.getServletContext().getRealPath("/upload_files");
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
		String path1 = "/upload_files" + subPath + uploadFileName;
		return path1;
	}*/
	/**
	 * 保存上传的图片文件，并返回文件的相对路径
	 * 
	 * @param upload
	 * @return
	 */
	protected String saveUploadFile(File upload, String uploadFileName) {
		if (upload == null) {
			return "";
		}
		// >> 获取服务器路径(项目根路径)
		String basePath = ServletActionContext.getServletContext().getRealPath("/upload_files");
		LoggerManager.printInfo(getClass(), "basePath----->" + basePath);
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(upload);
			os = new FileOutputStream(new File(basePath + "/", uploadFileName));
			byte[] buffer = new byte[1024];
			int length = 0;
			  
		    while (-1 != (length = is.read(buffer))){
		    	os.write(buffer,0,length);
		    }
		    if (os != null) {
		    	os.close();
		    	os = null;
			}
		    if (is != null) {
		    	is.close();
		    	is = null;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//保存相对路径到数据库，而不是绝对路径
		String path = "upload_files/" + uploadFileName;
		return path;
	}
}
