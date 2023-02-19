package com.snail.abell.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.snail.abell.entity.SeleniumNode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Abell
 * @date  2023/2/19
 */
@Mapper
public interface SeleniumNodeMapper extends BaseMapper<SeleniumNode> {
    int updateBatchSelective(List<SeleniumNode> list);
}
