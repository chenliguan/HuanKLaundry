package com.guan.o2o.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.guan.o2o.R;
import com.guan.o2o.common.Constant;
import com.guan.o2o.utils.LogUtil;
import com.guan.o2o.utils.RegularExpressUtil;
import com.guan.o2o.utils.SharedPfeUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnItemSelected;

/**
 * 用户信息页面
 *
 * @author Guan
 * @file com.guan.o2o.activity
 * @date 2015/10/30
 * @Version 1.0
 */
public class UserInfoActivity extends FrameActivity {

    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.et_name)
    EditText etName;
    @InjectView(R.id.btn_man)
    Button btnMan;
    @InjectView(R.id.btn_woman)
    Button btnWoman;
    @InjectView(R.id.et_phone)
    EditText etPhone;
    @InjectView(R.id.spr_area)
    Spinner sprArea;
    @InjectView(R.id.et_commu)
    EditText etCommu;
    @InjectView(R.id.et_addr)
    EditText etAddr;

    private boolean userGender;
    private String userArea;
    private String[] mArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        ButterKnife.inject(this);

        /**
         * 初始化变量
         */
        initVariable();
        /**
         * 自定义Spinner样式
         */
        setSpinner();
    }

    /**
     * 初始化变量
     */
    private void initVariable() {
        tvTitle.setText(R.string.title_user_info);
        Resources res = getResources();
        mArea = res.getStringArray(R.array.area);

        // 读取SHARED_NAME_USERINFO中的数据
        SharedPreferences preferences_user = getSharedPreferences(
                Constant.SHARED_NAME_USERINFO, Context.MODE_PRIVATE);
        // 读取是否存入用户信息到本地，判断显示哪一种布局
        userGender = preferences_user.getBoolean(Constant.SHARED_KEY_GENDER, true);
        userArea = preferences_user.getString(Constant.SHARED_KEY_AREA, "");
        etName.setText(preferences_user.getString(Constant.SHARED_KEY_NAME, ""));
        etPhone.setText(preferences_user.getString(Constant.SHARED_KEY_USERPHONE, ""));
        etCommu.setText(preferences_user.getString(Constant.SHARED_KEY_COMMU, ""));
        etAddr.setText(preferences_user.getString(Constant.SHARED_KEY_DETAIL_ADDR, ""));
    }

    /**
     * 自定义Spinner样式
     */
    private void setSpinner() {
        // 1.定义适配器
        ArrayAdapter<String> adapter;
        // 2.自定义布局，以及传入数据area。
        adapter = new ArrayAdapter<String>(this, R.layout.item_spinner, mArea);
        // 3.为适配器设置下拉列表下拉时的菜单样式。
        adapter.setDropDownViewResource(R.layout.item_spinner_dropdown);
        // 4.将适配器添加到下拉列表上
        sprArea.setAdapter(adapter);
        // 5.Spinner点击监听
        // 6.根据值, 设置spinner默认选中:
        setSpinnerItemByValue(sprArea, userArea);
    }

    /**
     * Spinner点击监听
     * @param view
     */
    @OnItemSelected(R.id.spr_area)
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        userArea = mArea[position];
    }

    /**
     * 监听实现
     * @param view
     */
    @OnClick({R.id.iv_back, R.id.btn_man, R.id.btn_woman, R.id.btn_save})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.iv_back:
                finish();
                break;

            case R.id.btn_man:
                btnMan.setTextColor(getResources().getColor(R.color.white));
                btnMan.setBackgroundColor(getResources().getColor(R.color.main_blue));
                btnWoman.setTextColor(getResources().getColor(R.color.text_grey));
                btnWoman.setBackgroundColor(getResources().getColor(R.color.white));
                // 男为true
                userGender = true;
                break;

            case R.id.btn_woman:
                btnMan.setTextColor(getResources().getColor(R.color.text_grey));
                btnMan.setBackgroundColor(getResources().getColor(R.color.white));
                btnWoman.setTextColor(getResources().getColor(R.color.white));
                btnWoman.setBackgroundColor(getResources().getColor(R.color.main_blue));
                // 女为false
                userGender = false;
                break;

            case R.id.btn_save:
                String userName = String.valueOf(etName.getText());
                String userPhone = String.valueOf(etPhone.getText());
                String userCommu = String.valueOf(etCommu.getText());
                String userDetailAddr = String.valueOf(etAddr.getText());

                // 权限验证
                if (RegularExpressUtil.isNullNo(userPhone)) {
                    showTipsWindow(view, getString(R.string.pop_tip_title), getString(R.string.phone_null));
                } else if (!RegularExpressUtil.isMobileNO(userPhone)) {
                    showTipsWindow(view, getString(R.string.pop_tip_title), getString(R.string.pop_tip_content));
                } else if (RegularExpressUtil.isNullNo(userName)) {
                    showTipsWindow(view, getString(R.string.pop_tip_title), getString(R.string.name_null));
                } else if (RegularExpressUtil.isNullNo(userArea)) {
                    showTipsWindow(view, getString(R.string.pop_tip_title), getString(R.string.area_null));
                } else if (RegularExpressUtil.isNullNo(userCommu)) {
                    showTipsWindow(view, getString(R.string.pop_tip_title), getString(R.string.commu_null));
                } else if (RegularExpressUtil.isNullNo(userDetailAddr)) {
                    showTipsWindow(view, getString(R.string.pop_tip_title), getString(R.string.detail_addr_null));
                } else {
                    boolean isShared = true;
                    // 保存到本地
                    SharedPfeUtil.sharedUserInfo(this, isShared, userName, userPhone, userGender,
                            userArea, userCommu, userDetailAddr);
                    // 向BasketFragment发送广播,更新界面
                    sendBroadcast();
                }
                break;

            default:
                break;
        }
    }

    /**
     * 向BasketFragment发送广播,更新界面
     */
    private void sendBroadcast() {
        Intent intent = new Intent(Constant.ACTION_BU_UPDATEUI);
        intent.putExtra(Constant.INTENT_KEY, Constant.VALUE_UserInfo_ACTIVITY);
        sendBroadcast(intent);
        finish();
    }

    /**
     * 根据值, 设置spinner默认选中
     * @param spinner
     * @param value
     */
    public static void setSpinnerItemByValue(Spinner spinner,String value){
        //得到SpinnerAdapter对象
        SpinnerAdapter apsAdapter= spinner.getAdapter();
        int k= apsAdapter.getCount();
        for(int i=0;i<k;i++){
            if(value.equals(apsAdapter.getItem(i).toString())){
                // 默认选中项
                spinner.setSelection(i,true);
                break;
            }
        }
    }

}
