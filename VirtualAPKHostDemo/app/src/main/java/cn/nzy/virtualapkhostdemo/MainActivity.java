package cn.nzy.virtualapkhostdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.didi.virtualapk.PluginManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void test(View view) {
        if (PluginManager.getInstance(this).getLoadedPlugin("cn.nzy.virualapkplugindemo") == null) {
            Toast.makeText(this, "plugin  not loaded", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent();
            intent.setClassName("cn.nzy.virualapkplugindemo", "cn.nzy.virualapkplugindemo.MyPluginAcitivity");
            startActivity(intent);
        }
    }
}
