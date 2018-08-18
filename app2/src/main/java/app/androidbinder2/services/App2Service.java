package app.androidbinder2.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import app.androidbinder.UserService;
import app.androidbinder.domain.UserInfo;

/**
 * 作者：黎伟杰 on 2018/8/12.
 * 邮箱：liweijie@qq.com
 * description：
 * update by:
 * update day:
 *
 * @author liweijie
 */
public class App2Service extends Service {
    /**
     * 模拟一些测试数据
     */
    private List<UserInfo> data = new ArrayList<>();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        data.clear();
        data.add(new UserInfo(1, "A", 20));
        data.add(new UserInfo(2, "B", 30));
        data.add(new UserInfo(3, "C", 40));
        data.add(new UserInfo(4, "D", 50));
        return userService;
    }


    private Binder userService = new UserService.Stub() {
        @Override
        public String getUserName(int userId) throws RemoteException {
            for (UserInfo item : data) {
                if (item.getUserId() == userId) {
                    return item.getUserName();
                }
            }
            return null;
        }

        @Override
        public void saveUser(UserInfo param) throws RemoteException {
            data.add(param);
        }

        @Override
        public UserInfo getUserInfo(int userId) throws RemoteException {
            for (UserInfo item : data) {
                if (item.getUserId() == userId) {
                    return item;
                }
            }
            return null;
        }

        @Override
        public List<UserInfo> queryUser() throws RemoteException {
            return data;
        }

        @Override
        public void handleIn(UserInfo info) throws RemoteException {
            info.setUserName("嘿嘿嘿");
        }

        @Override
        public void handleOut(UserInfo info) throws RemoteException {
            if (info == null) {
                Log.e("App2Service", "UserInfi server is null");
                info = new UserInfo();
            }
            info.setUserName("嘻嘻嘻");
        }

        @Override
        public void handleInOut(UserInfo info) throws RemoteException {
            info.setUserName("哈哈哈");
        }
    };
}
