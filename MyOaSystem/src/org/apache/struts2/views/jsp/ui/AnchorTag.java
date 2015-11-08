package org.apache.struts2.views.jsp.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;

import org.apache.struts2.components.Anchor;
import org.apache.struts2.components.Component;

import com.opensymphony.xwork2.util.ValueStack;
import com.study.ssh.domain.User;

/**
 * @see Anchor
 */
public class AnchorTag extends AbstractClosingTag {

	private static final long serialVersionUID = -1034616578492431113L;

	protected String href;
	protected String includeParams;
	protected String scheme;
	protected String action;
	protected String namespace;
	protected String method;
	protected String encode;
	protected String includeContext;
	protected String escapeAmp;
	protected String portletMode;
	protected String windowState;
	protected String portletUrlType;
	protected String anchor;
	protected String forceAddSchemeHostAndPort;

	@Override
	public int doEndTag() throws JspException {
		//当转到一个页面时，会检测页面中所有的<s:a/>是否可以显示
		// 当前登录用户
		User user = (User) pageContext.getSession().getAttribute("user");

		// 当前准备显示的链接对应的权限URL
		// >> 在开头加上'/'
		String privUrl = "/" + action;
		
		if (user.hasPrivilegeByUrl(privUrl)) {
			return super.doEndTag(); // 正常的生成并显示超链接 标签，并继续执行页面中后面的代码
		} else {
			return EVAL_PAGE; // 不生成与显示超链接 标签，只是继续执行页面中后面的代码
		}
	}

	public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
		return new Anchor(stack, req, res);
	}

	protected void populateParams() {
		super.populateParams();

		Anchor tag = (Anchor) component;
		tag.setHref(href);
		tag.setIncludeParams(includeParams);
		tag.setScheme(scheme);
		tag.setValue(value);
		tag.setMethod(method);
		tag.setNamespace(namespace);
		tag.setAction(action);
		tag.setPortletMode(portletMode);
		tag.setPortletUrlType(portletUrlType);
		tag.setWindowState(windowState);
		tag.setAnchor(anchor);

		if (encode != null) {
			tag.setEncode(Boolean.valueOf(encode).booleanValue());
		}
		if (includeContext != null) {
			tag.setIncludeContext(Boolean.valueOf(includeContext).booleanValue());
		}
		if (escapeAmp != null) {
			tag.setEscapeAmp(Boolean.valueOf(escapeAmp).booleanValue());
		}
		if (forceAddSchemeHostAndPort != null) {
			tag.setForceAddSchemeHostAndPort(Boolean.valueOf(forceAddSchemeHostAndPort).booleanValue());
		}
	}

	public void setHref(String href) {
		this.href = href;
	}

	public void setEncode(String encode) {
		this.encode = encode;
	}

	public void setIncludeContext(String includeContext) {
		this.includeContext = includeContext;
	}

	public void setEscapeAmp(String escapeAmp) {
		this.escapeAmp = escapeAmp;
	}

	public void setIncludeParams(String name) {
		includeParams = name;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setPortletMode(String portletMode) {
		this.portletMode = portletMode;
	}

	public void setPortletUrlType(String portletUrlType) {
		this.portletUrlType = portletUrlType;
	}

	public void setWindowState(String windowState) {
		this.windowState = windowState;
	}

	public void setAnchor(String anchor) {
		this.anchor = anchor;
	}

	public void setForceAddSchemeHostAndPort(String forceAddSchemeHostAndPort) {
		this.forceAddSchemeHostAndPort = forceAddSchemeHostAndPort;
	}
}
