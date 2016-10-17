package com.xx.web.login.util;

import java.util.UUID;

/**
 * Created by Administrator on 2016/10/17.
 *
 * UUID工具类
 */

public class UUIDGenerator {

    public static String randomUUID() {
        return UUID.randomUUID().toString().replace("-","");
    }

}
