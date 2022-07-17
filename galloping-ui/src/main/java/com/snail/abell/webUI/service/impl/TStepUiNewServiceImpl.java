package com.snail.abell.webUI.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snail.abell.utils.SecurityUtils;
import com.snail.abell.webUI.dao.TStepUiNewDao;
import com.snail.abell.webUI.entity.TStepUiNew;
import com.snail.abell.webUI.service.TStepUiNewService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TStepUiNew)表服务实现类
 *
 * @author Abell
 * @since 2022-06-26 12:30:47
 */
@Service
public class TStepUiNewServiceImpl extends ServiceImpl<TStepUiNewDao,TStepUiNew> implements TStepUiNewService {
    @Resource
    private TStepUiNewDao tStepUiNewDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TStepUiNew queryById(Long id) {
        return this.tStepUiNewDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TStepUiNew> queryAllByLimit(int offset, int limit) {
        return this.tStepUiNewDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tStepUiNew 实例对象
     * @return 实例对象
     */
    @Override
    public TStepUiNew insert(TStepUiNew tStepUiNew) {
        this.tStepUiNewDao.insert(tStepUiNew);
        return tStepUiNew;
    }

    /**
     * 修改数据
     *
     * @param tStepUiNew 实例对象
     * @return 实例对象
     */
    @Override
    public TStepUiNew update(TStepUiNew tStepUiNew) {
        this.tStepUiNewDao.update(tStepUiNew);
        return this.queryById(tStepUiNew.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.tStepUiNewDao.deleteById(id) > 0;
    }

    @Override
    public String savaStep(List<TStepUiNew> uiTestStepList) {
        if (uiTestStepList.size() > 0) {
            QueryWrapper<TStepUiNew> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("testcase_id",uiTestStepList.get(0).getTestcaseId());
            List<TStepUiNew> byTestcaseId = tStepUiNewDao.selectList(queryWrapper);
            for (TStepUiNew stepUi : byTestcaseId) {
                boolean flag = false;
                for (TStepUiNew uiTestStep : uiTestStepList) {
                    if (uiTestStep.getId() != null && stepUi.getId().equals(uiTestStep.getId())) {
                        //如果存在步骤id不为空且在数据库存在，就不需要删除
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    tStepUiNewDao.deleteById(stepUi.getId());
                }
            }
            Long sort = 0L;
            for (TStepUiNew uiTestStep : uiTestStepList) {
                uiTestStep.setSort(sort);
                sort = sort + 1;
                if (uiTestStep.getId() == null) {
                    uiTestStep.setUpdateBy(SecurityUtils.getCurrentUsername());
                    uiTestStep.setCreateBy(SecurityUtils.getCurrentUsername());
                    tStepUiNewDao.insert(uiTestStep);
                } else {
                    uiTestStep.setUpdateBy(SecurityUtils.getCurrentUsername());
                    tStepUiNewDao.update(uiTestStep);
                }
            }
        }
        return "保存用例步骤成功";
    }
}