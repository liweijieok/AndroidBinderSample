package app.androidbinder;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import app.androidbinder.domain.UserInfo;

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

    public void saveUser(View view) {
        UserInfo newInfo = new UserInfo(123, "哔哩哔哩", 25);
        try {
            userServerService.saveUser(newInfo);
            showToast("保存成功");
        } catch (RemoteException e) {
            e.printStackTrace();
            showToast("保存失败");
        }
    }

    public void getUserInfo(View view) {
        try {
            UserInfo query = userServerService.getUserInfo(123);
            showToast(query.getUserName());
        } catch (RemoteException e) {
            e.printStackTrace();
            showToast("查询失败");
        }
    }

    public void queryUser(View view) {
        try {
            List<UserInfo> list = userServerService.queryUser();
            showToast("size = " + list.size());
        } catch (RemoteException e) {
            e.printStackTrace();
            showToast("查询失败");
        }
    }


    public void handleIn(View view) {
        UserInfo newInfo = new UserInfo(12, "B站", 25);
        try {
            userServerService.handleIn(newInfo);
            showToast(newInfo.getUserName());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void handleOut(View view) {
        UserInfo newInfo = new UserInfo(12, "B站", 25);
        try {
            userServerService.handleOut(newInfo);
            showToast(newInfo.getUserName());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void handleInOut(View view) {
        UserInfo newInfo = new UserInfo(12, "B站", 25);
        try {
            userServerService.handleInOut(newInfo);
            showToast(newInfo.getUserName());
        } catch (RemoteException e) {
            e.printStackTrace();
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
