package com.snail.abell.dto;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.snail.abell.entity.MessageSend;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Abell
 * @date  2023/3/12
 */
@Mapper
public interface MessageSendMapper extends BaseMapper<MessageSend> {
    int updateBatchSelective(List<MessageSend> list);
}
