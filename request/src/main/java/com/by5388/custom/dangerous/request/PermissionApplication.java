package com.by5388.custom.dangerous.request;

import android.app.Application;
import android.content.pm.PackageManager;

/**
 * @author Administrator  on 2019/9/23.
 */
public class PermissionApplication extends Application {
    private static final int REQUEST_CODE_DANGEROUS_PERMISSION = 100;
    private static final String ACTION_TO_NORMAL = "com.by5388.custom.permission.action.normal";
    private static final String ACTION_TO_DANGEROUS = "com.by5388.custom.permission.action.dangerous";
    private static final String CUSTOM_PERMISSION_DANGEROUS = "com.by5388.custom.permission.dangerous";

    @Override
    public void onCreate() {
        super.onCreate();
        final int permission = checkSelfPermission(CUSTOM_PERMISSION_DANGEROUS);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            // TODO: 2019/9/23 只能在这里判断获取权限情况，但不能在Application中获取权限;如果
            //不能在这里统一处理的话，就需要构建一个抽象的类A，对可能需要调用权限的类来对类A进行统一扩展
        }


    }
}
