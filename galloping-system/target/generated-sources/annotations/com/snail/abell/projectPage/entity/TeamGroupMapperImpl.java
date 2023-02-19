package com.snail.abell.projectPage.entity;

import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-12T18:36:08+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_191 (Oracle Corporation)"
)
public class TeamGroupMapperImpl implements TeamGroupMapper {

    @Override
    public TeamGroup DTO(TeamGroups teamGroup) {
        if ( teamGroup == null ) {
            return null;
        }

        TeamGroup teamGroup1 = new TeamGroup();

        teamGroup1.setTeamId( teamGroup.getTeamId() );
        teamGroup1.setTeamName( teamGroup.getTeamName() );
        teamGroup1.setCardTitle( teamGroup.getCardTitle() );
        teamGroup1.setTeamDescription( teamGroup.getTeamDescription() );
        teamGroup1.setTeamResponsibility( teamGroup.getTeamResponsibility() );
        teamGroup1.setCreateTime( teamGroup.getCreateTime() );
        teamGroup1.setTeamMail( teamGroup.getTeamMail() );

        return teamGroup1;
    }
}
