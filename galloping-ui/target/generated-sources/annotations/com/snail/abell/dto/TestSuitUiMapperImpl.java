package com.snail.abell.dto;

import com.snail.abell.Vo.TestSuitUiVo;
import com.snail.abell.entity.TTestsuiteUi;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-12T18:14:32+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_191 (Oracle Corporation)"
)
@Component
public class TestSuitUiMapperImpl implements TestSuitUiMapper {

    @Override
    public TTestsuiteUi toEntity(TestSuitUiVo dto) {
        if ( dto == null ) {
            return null;
        }

        TTestsuiteUi tTestsuiteUi = new TTestsuiteUi();

        tTestsuiteUi.setId( dto.getId() );
        tTestsuiteUi.setProjectId( dto.getProjectId() );
        tTestsuiteUi.setParentId( dto.getParentId() );
        tTestsuiteUi.setSort( dto.getSort() );
        tTestsuiteUi.setIsLeaf( dto.getIsLeaf() );
        tTestsuiteUi.setCreateBy( dto.getCreateBy() );
        tTestsuiteUi.setUpdateBy( dto.getUpdateBy() );
        tTestsuiteUi.setCreateTime( dto.getCreateTime() );
        tTestsuiteUi.setUpdateTime( dto.getUpdateTime() );

        return tTestsuiteUi;
    }

    @Override
    public List<TTestsuiteUi> toEntity(List<TestSuitUiVo> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<TTestsuiteUi> list = new ArrayList<TTestsuiteUi>( dtoList.size() );
        for ( TestSuitUiVo testSuitUiVo : dtoList ) {
            list.add( toEntity( testSuitUiVo ) );
        }

        return list;
    }

    @Override
    public List<TestSuitUiVo> toDto(List<TTestsuiteUi> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<TestSuitUiVo> list = new ArrayList<TestSuitUiVo>( entityList.size() );
        for ( TTestsuiteUi tTestsuiteUi : entityList ) {
            list.add( tTestsuiteUiToTestSuitUiVo( tTestsuiteUi ) );
        }

        return list;
    }

    @Override
    public TestSuitUiVo toDto(TTestsuiteUi testSuitUi) {
        if ( testSuitUi == null ) {
            return null;
        }

        TestSuitUiVo testSuitUiVo = new TestSuitUiVo();

        testSuitUiVo.setLabel( testSuitUi.getName() );
        testSuitUiVo.setId( testSuitUi.getId() );
        testSuitUiVo.setProjectId( testSuitUi.getProjectId() );
        testSuitUiVo.setParentId( testSuitUi.getParentId() );
        testSuitUiVo.setSort( testSuitUi.getSort() );
        testSuitUiVo.setIsLeaf( testSuitUi.getIsLeaf() );

        return testSuitUiVo;
    }

    protected TestSuitUiVo tTestsuiteUiToTestSuitUiVo(TTestsuiteUi tTestsuiteUi) {
        if ( tTestsuiteUi == null ) {
            return null;
        }

        TestSuitUiVo testSuitUiVo = new TestSuitUiVo();

        testSuitUiVo.setCreateBy( tTestsuiteUi.getCreateBy() );
        testSuitUiVo.setUpdateBy( tTestsuiteUi.getUpdateBy() );
        testSuitUiVo.setCreateTime( tTestsuiteUi.getCreateTime() );
        testSuitUiVo.setUpdateTime( tTestsuiteUi.getUpdateTime() );
        testSuitUiVo.setId( tTestsuiteUi.getId() );
        testSuitUiVo.setProjectId( tTestsuiteUi.getProjectId() );
        testSuitUiVo.setParentId( tTestsuiteUi.getParentId() );
        testSuitUiVo.setSort( tTestsuiteUi.getSort() );
        testSuitUiVo.setIsLeaf( tTestsuiteUi.getIsLeaf() );

        return testSuitUiVo;
    }
}
