/*
 *  Copyright
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.snail.abell.permission.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.snail.abell.base.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @author Zheng Jie
 * @date 2018-11-23
 */
@Getter
@Setter
public class UserDto extends BaseDTO implements Serializable {

    private Long id;

    private Set<RoleDto> roles;

    private Set<JobDto> jobs;

    private String username;

    private String nickName;

    private String email;

    private String phone;

    private String member;

    private String avatarName;

    private String avatarPath;

    @JSONField(serialize = false)
    private String password;

    private Long enabled;

    @JSONField(serialize = false)
    private Object isAdmin = false;

    private Date pwdResetTime;
}
