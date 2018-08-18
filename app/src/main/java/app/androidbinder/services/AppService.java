package app.androidbinder.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import app.androidbinder.UserLocalService;

/**
 * 作者：黎伟杰 on 2018/8/12.
 * 邮箱：liweijie@qq.com
 * description：
 * update by:
 * update day:
 * <p>
 * 被服务器调用的，测试双端通信
 *
 * @author liweijie
 */
public class AppService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return localService;
    }

    private UserLocalService.Stub localService = new UserLocalService.Stub() {
        @Override
        public int add(int x, int y) throws RemoteException {
            return x+y;
        }
    };



}
