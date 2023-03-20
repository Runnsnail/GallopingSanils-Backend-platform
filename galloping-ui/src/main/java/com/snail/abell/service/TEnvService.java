package com.snail.abell.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.snail.abell.entity.TEnv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * @author Abell
 * @date  2022/11/4
 */
public interface TEnvService extends IService<TEnv>{


    int updateBatch(List<TEnv> list);

    int updateBatchSelective(List<TEnv> list);

    int batchInsert(List<TEnv> list);

    int insertOrUpdate(TEnv record);

    int insertOrUpdateSelective(TEnv record);

    ArrayList<HashMap<String, String>> getUiEnv();

    boolean saveOrUpdateByEnv(TEnv env);
}
