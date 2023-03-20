package com.snail.abell.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snail.abell.dto.MessageSendMapper;
import com.snail.abell.entity.MessageSend;
import com.snail.abell.service.MessageSendService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Abell
 * @date  2023/3/12
 */
@Service
public class MessageSendServiceImpl extends ServiceImpl<MessageSendMapper, MessageSend> implements MessageSendService {

    @Resource
    private MessageSendService messageService;

    @Override
    public int updateBatchSelective(List<MessageSend> list) {
        return baseMapper.updateBatchSelective(list);
    }

    @Override
    public boolean saveOrUpdateByMessage(MessageSend message) {

        UpdateWrapper<MessageSend> updateWrapper = new UpdateWrapper<MessageSend>();
        updateWrapper.eq("suite_id",message.getSuiteId());
        return messageService.saveOrUpdate(message,updateWrapper);
    }
}
