package com.snail.abell.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snail.abell.dao.SeleniumNodeMapper;
import com.snail.abell.entity.SeleniumNode;
import com.snail.abell.service.SeleniumNodeService;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @author Abell
 * @date  2023/2/19
 */
@Service
public class SeleniumNodeServiceImpl extends ServiceImpl<SeleniumNodeMapper, SeleniumNode> implements SeleniumNodeService{

    @Override
    public int updateBatchSelective(List<SeleniumNode> list) {
        return baseMapper.updateBatchSelective(list);
    }
}
