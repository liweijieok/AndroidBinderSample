package app.androidbinder.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者：黎伟杰 on 2018/8/13.
 * 邮箱：liweijie@qq.com
 * description：
 * update by:
 * update day:
 *
 * @author liweijie
 */
public class UserInfo implements Parcelable {
    private int userId;
    private String userName;
    private int age;

    public UserInfo() {

    }

    public UserInfo(int userId, String userName, int age) {
        this.userId = userId;
        this.userName = userName;
        this.age = age;
    }

    protected UserInfo(Parcel in) {
        userId = in.readInt();
        userName = in.readString();
        age = in.readInt();
    }

    public static final Creator<UserInfo> CREATOR = new Creator<UserInfo>() {
        @Override
        public UserInfo createFromParcel(Parcel in) {
            return new UserInfo(in);
        }

        @Override
        public UserInfo[] newArray(int size) {
            return new UserInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(userId);
        dest.writeString(userName);
        dest.writeInt(age);
    }

    public void readFromParcel(Parcel in) {
        userId = in.readInt();
        userName = in.readString();
        age = in.readInt();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
