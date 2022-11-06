package com.snail.abell.dto;

import lombok.Data;

/**
 * @author Abell
 * @date 2022/11/4
 */
@Data
public class TestCaseMetoDto {

    private Long suitId;

    private String caseName;

    private String status;

    private Long caseId;

    private Long envName;

    private Long projectName;

    private Long teamName;

}
