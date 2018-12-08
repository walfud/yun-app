package com.yunapp.libx;

import android.app.Application;
import android.support.annotation.NonNull;

import com.yunapp.libx.utils.StorageUtil;

import java.io.File;

public class AppContext {

    private Config appConfig;

    /**
     * 获取webcore执行的代码路径
     * ../yun-app/$appId/source/service.html
     *
     * @return
     */
    public File getWebCoreJsFile() {
        return new File(appConfig.getAppSourceDir(), "service.html");
    }

    /**
     * 获取配置文件路径
     *
     * @return
     */
    public File getConfigJsonFile() {
        return new File(appConfig.getAppSourceDir(), "app.json");
    }

    public String getHomePage() {
        return new File(appConfig.getAppSourceDir(), "view.html").getAbsolutePath();
    }


    public static AppContext buildAppContext(@NonNull Config appConfig) {
        return new AppContext(appConfig);
    }

    private AppContext(Config appConfig) {
        this.appConfig = appConfig;
    }


    public static class Config {
        public static final String NAME = "YunApp";
        public static final String VERSION = "1.0";
        public static final boolean DEBUG = true;

        private Application context;
        public String appId;

        public Config(Application context, String id) {
            appId = id;
            this.context = context;
        }

        /**
         * ../yun-app/$appId/zip/
         *
         * @return app未解压zip包的位置
         */
        public File getZipDir() {
            return StorageUtil.getSubDir(context, appId, "zip");
        }

        /**
         * ../yun-app/$appId/source/
         *
         * @return app代码位置
         */
        public File getAppSourceDir() {
            return StorageUtil.getSubDir(context, appId, "source");
        }

        public File getStorageDir() {
            return StorageUtil.getSubDir(context, appId, "storage");
        }

        /**
         * ../yun-app/$appId/zip/$appId.zip
         *
         * @return app压缩包
         */
        public File getCodeZip() {
            return new File(getZipDir(), String.format("%s.zip", appId));
        }
    }
}
