package com.snail.abell.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * (TSuiteCaseUi)表实体类
 *
 * @author Abell
 * @since 2022-10-29 20:35:04
 */
@SuppressWarnings("serial")
@Data
public class TSuiteCaseUi extends Model<TSuiteCaseUi> {

    private Long id;

    private Long suiteId;

    private Long caseId;

    private Integer sort;
    //1:有效，2：无效
    private Integer isValid;


}

