package com.mythesis.ssh.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mythesis.ssh.base.BaseDaoImpl;
import com.mythesis.ssh.model.Comment;
import com.mythesis.ssh.service.CommentService;

/**
 * @author anbang
 * @description 评论Service实现类
 * @date 2016年2月2日 下午8:36:29
 */
@Service("commentService")
@Transactional
public class CommentServiceImpl extends BaseDaoImpl<Comment> implements CommentService {

}
