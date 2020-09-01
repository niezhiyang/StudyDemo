package com.xiangxue.usercenter;

import android.content.Intent;

import com.google.auto.service.AutoService;
import com.xiangxue.base.BaseApplication;
import com.xiangxue.common.autoservice.IUserCenterService;

@AutoService({IUserCenterService.class})
public class IUserCenterServiceImpl implements IUserCenterService {
    @Override
    public boolean isLogined() {
        return false;
    }

    @Override
    public void login() {
        Intent intent = new Intent(BaseApplication.sApplication, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        BaseApplication.sApplication.startActivity(intent);
    }
}
