package cn.nzy.tinkerdemo;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.tencent.tinker.lib.tinker.TinkerInstaller;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 加载补丁
        loadPatch();
    }

    private void loadPatch() {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/patch.apk";
        File file = new File(path);
        if (file.exists()){
            // 加载补丁
            TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(), path);
        }
    }

    public void test(View view) {
//        int i=1/0;
        Toast.makeText(this, "我被修好了,不崩溃了", Toast.LENGTH_SHORT).show();
    }
}
