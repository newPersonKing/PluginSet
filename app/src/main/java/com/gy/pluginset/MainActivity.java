package com.gy.pluginset;

import android.Manifest;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.gy.pluginset.bean.User;
import com.gy.pluginset.until.DaoUntils;

import butterknife.ButterKnife;
import butterknife.OnClick;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getPermission();
    }

    private void getPermission(){

        MainActivityPermissionsDispatcher.NeedsPermissionWithPermissionCheck(this);
    }

    @OnClick({
            R.id.btn_insert
    })
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_insert:
                insertUser();
                break;
        }
    }


    private void insertUser(){
        User user = new User();
        user.setName("name");
        user.setPassword("password");
        DaoUntils.insertUser(user);
    }


    @NeedsPermission({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void NeedsPermission() {
        Log.i("cccccccccccc","NeedsPermission");

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MainActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @OnShowRationale({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void OnShowRationable(final PermissionRequest request) {

        /*如果第一次拒绝 调用proceed会再次执行权限申请*/
        request.proceed();

    }

    @OnPermissionDenied({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void OnPermissionDenied() {
        Log.i("cccccccccccc","OnPermissionDenied");
    }

    @OnNeverAskAgain({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void OnNeverAskAgain() {
        Log.i("cccccccccccc","OnPermissionDenied");
    }
}
