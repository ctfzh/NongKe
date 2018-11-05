package com.nongke.jindao.base.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gaolei.basemodule.R;
import com.gaolei.basemodule.R2;
import com.nongke.jindao.base.mpresenter.BasePresenter;
import com.nongke.jindao.base.pay.PayResult;
import com.nongke.jindao.base.utils.ExitAppUtils;
import com.nongke.jindao.base.utils.PermissionUtil;
import com.nongke.jindao.base.utils.StatusBarUtil;
import com.umeng.analytics.MobclickAgent;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by gaolei on 2018/4/26.
 */

public abstract class BaseActivity extends com.nongke.jindao.base.activity.BasePermisssionActivity {

    public static Activity context;
//    @BindView(R2.id.iv_back)
//    public ImageView iv_back;
//    @BindView(R2.id.title)
//    public TextView title;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);

        ExitAppUtils.getInstance().addActivity(this);

        context = this;
        setStatusBarColor(R.color.app_color);

        Bundle bundle = getIntent().getBundleExtra("params");
        if (bundle == null) {
            bundle = savedInstanceState;
        }
        initData(bundle);
        ExitAppUtils.getInstance().addActivity(this);

    }


    protected abstract int getLayoutId();

    protected abstract void initData(Bundle bundle);

//    @OnClick({R2.id.iv_back})
//    public void click(View view) {
//        switch (view.getId()) {
//            case R2.id.iv_back:
//
//                finish();
//
//                break;
//
//        }
//    }

    /**
     * 设置状态栏颜色
     */
    public void setStatusBarColor(int resColor) {
        StatusBarUtil.setWindowStatusBarColor(this, resColor, true);
    }


    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    protected void onDestroy() {
        super.onDestroy();
        context = null;
        ExitAppUtils.getInstance().delActivity(this);
        ExitAppUtils.getInstance().delActivity(this);
    }


}
