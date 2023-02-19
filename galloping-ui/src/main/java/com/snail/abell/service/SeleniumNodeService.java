package com.snail.abell.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.snail.abell.entity.SeleniumNode;

import java.util.List;
/**
 * @author Abell
 * @date  2023/2/19
 */
public interface SeleniumNodeService extends IService<SeleniumNode>{


    int updateBatchSelective(List<SeleniumNode> list);

}
