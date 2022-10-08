package com.nzy.viewstudy;

import android.os.Bundle;
import android.view.View;

import com.nzy.viewstudy.crash.BundleKey;
import com.nzy.viewstudy.crash.CrashCaptureManager;
import com.nzy.viewstudy.crash.FileExplorerFragment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

/**
 * @author niezhiyang
 * since 2020/11/3
 */
public class Maintactivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

    }

    public void click(View view) {
        int save = 1 / 0;
    }

    public void detail(View view) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(BundleKey.DIR_KEY, CrashCaptureManager.getInstance()
                .getCrashCacheDir());
        showContent(FileExplorerFragment.class, bundle);
    }

    private void showContent(Class<FileExplorerFragment> target, Bundle bundle){
        Fragment fragment = null;
        try {
            fragment = target.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .add(R.id.content, fragment)
                .addToBackStack("")
                .commit();
    }

}
