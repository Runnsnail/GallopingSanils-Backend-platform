package com.snail.abell.config;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Abell
 * @date 2022/10/7
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler{






        @Override
        public void insertFill(MetaObject metaObject) {
            this.strictInsertFill(metaObject, "createTime", Date.class, DateUtil.date(Calendar.getInstance()));
            this.strictInsertFill(metaObject, "updateTime", Date.class, DateUtil.date(Calendar.getInstance()));
        }

        @Override
        public void updateFill(MetaObject metaObject) {
            this.strictUpdateFill(metaObject, "updateTime", Date.class, DateUtil.date(Calendar.getInstance()));
        }

}
