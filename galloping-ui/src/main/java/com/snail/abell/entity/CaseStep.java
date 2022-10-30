package com.snail.abell.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * (CaseStep)实体类
 *
 * @author Abell
 * @since 2022-10-29 20:59:40
 */
@Data
@Builder
public class CaseStep implements Serializable {
    private static final long serialVersionUID = 428648639257854340L;
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    private String caseId;

    private Integer stepId;

}

