package com.snail.abell.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.snail.abell.entity.MessageSend;

import java.util.List;
/**
 * @author Abell
 * @date  2023/3/12
 */
public interface MessageSendService extends IService<MessageSend>{


    int updateBatchSelective(List<MessageSend> list);

    boolean saveOrUpdateByMessage(MessageSend message);
}
