package com.snail.abell.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Builder;
import lombok.Data;

/**
 * (TSuiteCaseUi)表实体类
 *
 * @author Abell
 * @since 2022-10-29 20:35:04
 */
@SuppressWarnings("serial")
@Data
@Builder
public class TSuiteCaseUi extends Model<TSuiteCaseUi> {

    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    private Long suiteId;

    private Long caseId;

    private Integer sort;
    //1:有效，2：无效
    private Integer isValid;


}

