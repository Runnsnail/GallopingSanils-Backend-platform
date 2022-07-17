package com.snail.abell.webUI.dao;

import com.snail.abell.webUI.dto.TestUiDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TTestcaseUiNewDtoMapper {

    TestUiDto selectDtoByPrimaryKey(Long id);

    TestUiDto selectByDtoByPrimaryKeyAndCaseType(@Param("id") Long id, @Param("caseType") Integer caseType);

    List<TestUiDto> selectDtoBySuiteId(Long id);
}
