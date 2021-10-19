package com.sum.sample.base.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.sum.sample.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author liujiang
 * Desc:权限申请基类
 */
public class PermissionActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {
    protected static final int RC_PERM = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    /**
     * 权限回调接口
     */
    private CheckPermListener mListener;

    public interface CheckPermListener {
        //权限通过后的回调方法
        void superPermission();

        //权限拒绝后的回调方法
        void refusePermission();

        //设置权限后返回的回调方法
        void settingPermission();
    }

    /**
     * 权限申请
     * @param listener
     * @param resString
     * @param mPerms
     */
    public void checkPermission(CheckPermListener listener, int resString, String... mPerms) {
        mListener = listener;
        if (EasyPermissions.hasPermissions(this, mPerms)) {
            if (mListener != null)
                mListener.superPermission();
        } else {
            EasyPermissions.requestPermissions(this, getString(resString),
                    RC_PERM, mPerms);
        }
    }

    /**
     * 权限申请
     * @param listener
     * @param mPerms
     */
    public void checkPermission(CheckPermListener listener, String... mPerms) {
        mListener = listener;
        if (EasyPermissions.hasPermissions(this, mPerms)) {
            if (mListener != null)
                mListener.superPermission();
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.ask_again),
                    RC_PERM, mPerms);
        }
    }

    /**
     * 用户权限处理,
     * 如果全部获取, 则直接过.
     * 如果权限缺失, 则提示Dialog.
     *
     * @param requestCode  请求码
     * @param permissions  权限
     * @param grantResults 结果
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == EasyPermissions.SETTINGS_REQ_CODE) {
            //设置返回
            if (mListener != null)
                mListener.settingPermission();
        }
    }


    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
//        L.showD("同意了某些权限可能不是全部");
        //同意了某些权限可能不是全部
//        if (mListener != null)
//            mListener.refusePermission();
    }

    @Override
    public void onPermissionsAllGranted() {
        if (mListener != null)
            mListener.superPermission();//同意了全部权限的回调
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        //拒绝了某些权限
        if (mListener != null)
            mListener.refusePermission();
        EasyPermissions.checkDeniedPermissionsNeverAskAgain(this,
                getString(R.string.perm_tip),
                R.string.setting, R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (mListener != null)
                            mListener.refusePermission();
                    }
                }, perms);
    }

    public abstract static class SimpleCheckPermListener implements CheckPermListener {

        @Override
        public void refusePermission() {

        }

        @Override
        public void settingPermission() {

        }
    }
} 
