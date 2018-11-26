package com.gy.pluginset.app;

import android.app.Application;
import android.content.SharedPreferences;

import com.gy.pluginset.until.DaoUntils;
import com.idescout.sql.SqlScoutServer;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DaoUntils.init(this);
        /*查看数据库插件配置*/
        SqlScoutServer.create(this, getPackageName());

        SharedPreferences sp = getSharedPreferences("test",MODE_PRIVATE);


    }
}
