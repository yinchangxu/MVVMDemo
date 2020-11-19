//package com.example.myapplication.util;
//
//import androidx.annotation.NonNull;
//
//import com.blankj.utilcode.util.SPUtils;
//import com.wicep.juheyijia.constant.SPConstant;
//import com.wicep.juheyijia.entity.network.UserInfoEntity;
//
//import static com.wicep.juheyijia.constant.SPConstant.LOGIN;
//import static com.wicep.juheyijia.constant.SPConstant.LOGIN_INFO;
//import static com.wicep.juheyijia.constant.SPConstant.SP_APP_NAME;
//
///**
// * Function: 用户工具类
// * Author: ShiJingFeng
// * Date: 2019/10/7 14:04
// * Description:
// */
//public class UserUtil {
//
//    private static volatile UserUtil sInstance;
//
//    /** 是否已登录  true: 已登录  false: 未登录 */
//    private boolean mLogin;
//    /** 登录数据 */
//    private UserInfoEntity mUserInfoEntity;
//
//    private UserUtil() {}
//
//    /**
//     * DCL双重检查单例
//     * @return 单例
//     */
//    public static UserUtil getInstance() {
//        if (sInstance == null) {
//            synchronized (UserUtil.class) {
//                if (sInstance == null) {
//                    sInstance = new UserUtil();
//                }
//            }
//        }
//        return sInstance;
//    }
//
//    /**
//     * 是否已登录
//     * @return true: 已登录  false: 未登录
//     */
//    public boolean isLogin() {
//        if (!mLogin) {
//            mLogin = SPUtils.getInstance(SP_APP_NAME).getBoolean(LOGIN, false);
//        }
//        return mLogin;
//    }
//
//    /**
//     * 获取用户ID
//     * @return 用户ID
//     */
//    public String getId() {
//        return getLoginInfo().user_id;
//    }
//
//    /**
//     * 获取用户ID
//     * @return 用户ID
//     */
//    public String getSessionKey() {
//        return getLoginInfo().session_key;
//    }
//
//    /**
//     * 获取登录数据
//     * @return 登录数据
//     */
//    public UserInfoEntity getLoginInfo() {
//        if (mUserInfoEntity == null) {
//            final String encryptLoginDataStr = SPUtils.getInstance(SP_APP_NAME).getString(LOGIN_INFO, "");
//            final String loginDataStr = SecurityUtil.decrypt(encryptLoginDataStr);
//
//            mUserInfoEntity = JsonUtils.deserialize(loginDataStr, UserInfoEntity.class);
//        }
//        return mUserInfoEntity;
//    }
//
//    /**
//     * 更新登录数据
//     * @param userInfoEntity 新的登录数据
//     */
//    public void updateLoginInfo(@NonNull UserInfoEntity userInfoEntity) {
//        mUserInfoEntity = userInfoEntity;
//        SPUtils.getInstance(SP_APP_NAME).put(LOGIN_INFO, SecurityUtil.encrypt(JsonUtils.serialize(userInfoEntity)), false);
//    }
//
//    /**
//     * 登录
//     * @param userInfoEntity 登录信息
//     */
//    public void login(@NonNull UserInfoEntity userInfoEntity) {
//        mLogin = true;
//        mUserInfoEntity = userInfoEntity;
//
//        SPUtils.getInstance(SP_APP_NAME).put(SPConstant.LOGIN, true);
//        SPUtils.getInstance(SP_APP_NAME).put(SPConstant.LOGIN_INFO, SecurityUtil.encrypt(JsonUtils.serialize(userInfoEntity)));
//    }
//
//    /**
//     * 退出登录
//     */
//    public void logout() {
//        mLogin = false;
//        mUserInfoEntity = null;
//
//        SPUtils.getInstance(SP_APP_NAME).remove(LOGIN, false);
//        SPUtils.getInstance(SP_APP_NAME).remove(LOGIN_INFO, false);
//    }
//
//}
