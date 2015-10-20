package com.guan.o2o.activity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.guan.o2o.R;
import com.guan.o2o.adapter.ServiceNoteAdapter;
import com.guan.o2o.application.App;
import com.guan.o2o.common.HttpPath;
import com.guan.o2o.model.ServiceNote;
import com.guan.o2o.volley.VolleyHandler;
import com.guan.o2o.volley.VolleyHttpRequest;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 服务说明页面
 *
 * @author Guan
 * @file com.guan.o2o.activity
 * @date 2015/10/17
 * @Version 1.0
 */
public class ServiceNoteActivity extends FrameActivity {

    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.lv_note)
    ListView lvNote;

    private ServiceNoteAdapter mSNAdapter;
    public ServiceNote serviceNote;
    public List<ServiceNote.MeansInfoEntity> meansInfo;
    public VolleyHandler<String> volleyRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_note);
        ButterKnife.inject(this);

        /**
         * 初始化变量
         */
        initVariable();
        /**
         * 绑定数据
         */
        bindData();
    }

    /**
     * 初始化变量
     */
    private void initVariable() {
        tvTitle.setText(R.string.title_service_note);
    }

    /**
     * 监听实现
     */
    @OnClick(R.id.iv_back)
    public void onClick() {
        finish();
    }

    /**
     * 绑定数据
     */
    private void bindData() {
        getSerNoteHttpData();
    }

    /**
     * 获取网络数据
     *
     * @return
     */
    public void getSerNoteHttpData() {

        volleyRequest = new VolleyHandler<String>() {
            @Override
            public void reqSuccess(String response) {

                if (response == null) {
                    showMsg(getString(R.string.msg_loading_error));
                } else {
                    serviceNote = ServiceNote.praseJson(response);
                    meansInfo = new ArrayList<ServiceNote.MeansInfoEntity>();
                    meansInfo = serviceNote.MeansInfo;
                    mSNAdapter = new ServiceNoteAdapter(ServiceNoteActivity.this, meansInfo);
                    // 配置适配器
                    lvNote.setAdapter(mSNAdapter);
                }
            }

            @Override
            public void reqError(String error) {
                showMsg(getString(R.string.msg_con_server_error));
            }
        };

        // 请求网络
        VolleyHttpRequest.String_request("ServiceNote", HttpPath.getSerNotefo(), volleyRequest);
    }

    @Override
    public void onStop() {
        super.onStop();
        App.getQueue().cancelAll("ServiceNote");
    }

}
