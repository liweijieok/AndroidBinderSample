// UserService.aidl
package app.androidbinder;
import java.util.List;
import app.androidbinder.domain.UserInfo;

// Declare any non-default types here with import statements

interface UserService {


    String getUserName(int userId);

    void saveUser(in UserInfo param);

    UserInfo getUserInfo(int userId);

    List<UserInfo> queryUser();

    //for in out inout

    void handleIn(in UserInfo info);
    void handleOut(out UserInfo info);
    void handleInOut(inout UserInfo info);

}
