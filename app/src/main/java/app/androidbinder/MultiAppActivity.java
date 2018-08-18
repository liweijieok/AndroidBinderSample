package app.androidbinder;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * MultiAppActivity
 *
 * @author liweijie
 */
public class MultiAppActivity extends AppCompatActivity {

    private UserService userServerService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    ServiceConnection userConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            showToast("连接成功");
            userServerService = UserService.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            showToast("与服务器断开连接");
        }
    };

    public void startServer(View view) {
        Intent startServer = new Intent();
        startServer.setAction("app.androidbinder2.user_service.action");
        startServer.setComponent(new ComponentName("app.androidbinder2", "app.androidbinder2.services.App2Service"));
        bindService(startServer, userConnection, Service.BIND_AUTO_CREATE);
    }

    public void getUserName(View view) {
        try {
            String name = userServerService.getUserName(1);
            showToast(name);
        } catch (Exception e) {
            e.printStackTrace();
            showToast("出现错误");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(userConnection);
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
