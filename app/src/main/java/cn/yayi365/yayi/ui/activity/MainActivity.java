package cn.yayi365.yayi.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cn.yayi365.yayi.R;
import cn.yayi365.yayi.tools.im.IMHelper;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
    }


    private void initData() {
        IMHelper.getInstance(getApplicationContext()).login("17706415106", "123456");
    }


}
