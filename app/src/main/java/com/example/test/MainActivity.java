package com.example.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.test.picker.PickerView;
import com.example.test.picker.entity.PickerData;
import com.example.test.picker.listener.OnPickerClickListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    /**
     * 所有省
     */
    protected String[] mProvinceDatas = new String[10];
    protected String[] mShiDatas= new String[10];
    protected String[] mQuDatas= new String[10];
    protected String[] mJieDaoDatas= new String[10];
    /**
     * key - 省 value - 市
     */
    protected Map<String, String[]> mCitisDatasMap = new HashMap<String, String[]>();
    /**
     * key - 市 values - 区
     */
    protected Map<String, String[]> mDistrictDatasMap = new HashMap<String, String[]>();

    /**
     * key - 区 values - 邮编
     */
    protected Map<String, String[]> mZipcodeDatasMap = new HashMap<String, String[]>();
    private Button mShowCitySelecter;
    private TextView mText;
    private PickerView pickerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < 10; i++) {
            mProvinceDatas[i] = i + "省";
        }

        for (int i = 0; i < 10; i++) {
            mShiDatas[i] = i + "市";
        }

        for (int i = 0; i < 10; i++) {
            mQuDatas[i] = i + "区";
        }

        for (int i = 0; i < 10; i++) {
            mJieDaoDatas[i] = i + "街道";
        }

        for (int i = 0; i < 10; i++) {
            mCitisDatasMap.put(i + "省", mShiDatas);
        }

        for (int i = 0; i < 10; i++) {
            mDistrictDatasMap.put( i + "市", mQuDatas);
        }


        for (int i = 0; i < 10; i++) {
            mZipcodeDatasMap.put( i + "区", mJieDaoDatas);
        }



        mShowCitySelecter = (Button) findViewById(R.id.show_city_selecter);
        mText = (TextView) findViewById(R.id.text);

        //选择器数据实体类封装
        PickerData data = new PickerData();
        //设置数据，有多少层级自己确定
        data.setFirstDatas(mProvinceDatas);
        data.setSecondDatas(mCitisDatasMap);
        data.setThirdDatas(mDistrictDatasMap);
        data.setFourthDatas(mZipcodeDatasMap);
        //设置弹出框的高度
        data.setHeight(1000);
        //设置初始化默认显示的菜单(此方法可以选择传参数量1到4个)
//        data.setInitSelectText("河北省", "石家庄市", "平山县");

        //初始化选择器
        pickerView = new PickerView(this, data);

        mShowCitySelecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //显示选择器
                pickerView.show(mShowCitySelecter);
            }
        });

        //选择器点击回调
        pickerView.setOnPickerClickListener(new OnPickerClickListener() {
            //选择列表时触发的事件（手动关闭）
            @Override
            public void OnPickerClick(PickerData pickerData) {
                mText.setText(pickerData.getSelectText());
//                pickerView.dismiss();//关闭选择器
            }

            //点击确定按钮触发的事件（自动关闭）
            @Override
            public void OnPickerConfirmClick(PickerData pickerData) {
                mText.setText(pickerData.getSelectText());
            }
        });
    }
}
