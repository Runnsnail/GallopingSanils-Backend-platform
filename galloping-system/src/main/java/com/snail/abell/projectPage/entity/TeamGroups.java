package com.snail.abell.projectPage.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * @author Abell
 * @date 2022/9/12
 */
@Data
public class TeamGroups {


    private Integer teamId;


    private String teamName;


    private String cardTitle;


    private String teamDescription;

    private String teamResponsibility;


    private ArrayList<HashMap<String, String>> teamMember;


    private Date createTime;


    private String teamMail;

    private static final long serialVersionUID = 1L;
}
