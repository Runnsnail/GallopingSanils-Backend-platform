package com.snail.abell.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author Abell
 * @date 2022/11/21
 */
@Data
@Builder
public class ElementObject {

    private String text;

    private String value;
}
