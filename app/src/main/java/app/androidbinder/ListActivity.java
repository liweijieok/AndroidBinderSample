package app.androidbinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * 作者：黎伟杰 on 2018/8/19.
 * 邮箱：liweijie@qq.com
 * description：
 * update by:
 * update day:
 *
 * @author liweijie
 */
public class ListActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

    }

    public void multiAPP(View view) {
      startActivity(new Intent(this,MultiAppActivity.class));
    }

    public void sameApp(View view) {
        startActivity(new Intent(this,SameAppActivity.class));
    }
}
