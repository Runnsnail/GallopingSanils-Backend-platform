package com.snail.abell.dao;

import com.snail.abell.dto.TestUiDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TTestcaseUiNewDtoMapper {

    TestUiDto selectDtoByPrimaryKey(Long id);

    TestUiDto selectByDtoByPrimaryKeyAndCaseType(@Param("id") Long id, @Param("caseType") Integer caseType);

    List<TestUiDto> selectDtoBySuiteId(Long id);
}
