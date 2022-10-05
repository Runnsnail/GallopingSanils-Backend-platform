package com.snail.abell.dto;

import com.snail.abell.base.BaseDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Abell
 * @date
 */
@Data
public class SuiteUiDto extends BaseDTO implements Serializable {

    private Long id;

    private String name;

    private Long projectId;

    private static final long serialVersionUID = 1L;

}
