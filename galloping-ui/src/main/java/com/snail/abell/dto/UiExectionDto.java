package com.snail.abell.dto;

import lombok.Data;

/**
 * @author Abell
 * @date 2023/3/11
 */
@Data
public class UiExectionDto {

    private Long id;

    private String name;

    private String status;

    private String author;

    private String envName;

    private Integer envId;

    private Integer notificationType;

}
