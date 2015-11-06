package com.study.ssh.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.ssh.base.BaseDaoImpl;
import com.study.ssh.domain.Reply;
import com.study.ssh.service.ReplyService;

@Service("replyService")
@Transactional
public class ReplyServiceImpl extends BaseDaoImpl<Reply> implements ReplyService {

}
