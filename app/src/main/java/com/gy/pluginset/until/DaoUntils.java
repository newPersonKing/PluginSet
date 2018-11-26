package com.gy.pluginset.until;

import android.content.Context;


import com.gy.pluginset.bean.User;
import com.gy.pluginset.dao.DaoMaster;
import com.gy.pluginset.dao.DaoSession;
import com.gy.pluginset.dao.UserDao;

import java.util.List;

public class DaoUntils {

    public static DaoSession daoSession;
    public static Context mcontext;
    public static void init(Context context){
        mcontext=context;
        DaoMaster.DevOpenHelper openHelper=new DaoMaster.DevOpenHelper(context,"trans.db");
        DaoMaster daoMaster=new DaoMaster(openHelper.getWritableDb());
        daoSession=daoMaster.newSession();

    }

    public static long insertUser(User user){
        long result= daoSession.getUserDao().insert(user);
        return  result;
    }


    public static List<User> selctPdfPath(String code){
        return daoSession.getUserDao().queryBuilder().where(UserDao.Properties.Name.eq(code)).list();
    }

}
