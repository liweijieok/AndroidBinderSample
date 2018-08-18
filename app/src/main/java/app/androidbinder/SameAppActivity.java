package app.androidbinder;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import app.androidbinder.services.AppService;

/**
 * 作者：黎伟杰 on 2018/8/19.
 * 邮箱：liweijie@qq.com
 * description：
 * update by:
 * update day:
 *
 * @author liweijie
 */
public class SameAppActivity extends AppCompatActivity {

    private UserLocalService localService;

    private ServiceConnection localConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            localService = UserLocalService.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_same);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(localConnection);
    }

    public void bindLocal(View view) {
        Intent local = new Intent(this, AppService.class);
        bindService(local, localConnection, Service.BIND_AUTO_CREATE);
    }

    public void addByService(View view) {
        try {
            Toast.makeText(this,String.valueOf(localService.add(1,2)),Toast.LENGTH_SHORT).show();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
