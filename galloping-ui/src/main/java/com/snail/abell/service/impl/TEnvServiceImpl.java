package com.snail.abell.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snail.abell.dao.TEnvMapper;
import com.snail.abell.entity.TEnv;
import com.snail.abell.service.TEnvService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * @author Abell
 * @date  2022/11/4
 */
@Service
public class TEnvServiceImpl extends ServiceImpl<TEnvMapper, TEnv> implements TEnvService{

    @Resource
    private TEnvMapper envMapper;
    @Resource
    private TEnvService envService;

    @Override
    public int updateBatch(List<TEnv> list) {
        return baseMapper.updateBatch(list);
    }
    @Override
    public int updateBatchSelective(List<TEnv> list) {
        return baseMapper.updateBatchSelective(list);
    }
    @Override
    public int batchInsert(List<TEnv> list) {
        return baseMapper.batchInsert(list);
    }
    @Override
    public int insertOrUpdate(TEnv record) {
        return baseMapper.insertOrUpdate(record);
    }
    @Override
    public int insertOrUpdateSelective(TEnv record) {
        return baseMapper.insertOrUpdateSelective(record);
    }

    @Override
    public ArrayList<HashMap<String, String>> getUiEnv() {

        ArrayList<HashMap<String, String>> envMapArrayList = new ArrayList<HashMap<String, String>>();
        QueryWrapper<TEnv> queryWrapper = new QueryWrapper<>();
        List<TEnv> menuList = this.envMapper.selectList(queryWrapper);
        for (TEnv env:menuList) {
            HashMap<String, String> map = new HashMap<>();
            map.put("label",env.getName());
            map.put("value",env.getId().toString());
            envMapArrayList.add(map);
        }
        return envMapArrayList;
    }

    @Override
    public boolean saveOrUpdateByEnv(TEnv env) {

        UpdateWrapper<TEnv> updateByEnvVo = new UpdateWrapper<>();
        updateByEnvVo.eq("name",env.getName());

        return envService.saveOrUpdate(env,updateByEnvVo);
    }

}
