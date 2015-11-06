package com.study.ssh.domain;

import java.io.Serializable;

/**
 * 回复
 * 
 * @author anbang
 *
 */
public class Reply extends Article implements Serializable {
	private Topic topic;// 所属主题

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

}
