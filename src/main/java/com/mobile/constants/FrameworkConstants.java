package com.mobile.constants;

import lombok.Getter;

import java.util.Objects;

@Getter
public final class FrameworkConstants {
    private FrameworkConstants() {
    }

    private static FrameworkConstants frameworkConstants;

    public static synchronized FrameworkConstants getInstance(){
        if (Objects.isNull(frameworkConstants)){
            frameworkConstants = new FrameworkConstants();
        }
        return frameworkConstants;
    }
    private final String RESOURCESPATH = System.getProperty("user.dir") + "/src/test/resources";
    private final String CONFIGFILEPATH = RESOURCESPATH + "/config/config.properties";
    private final String APPPATH = RESOURCESPATH + "/apkFiles/HiFlow_Partner.apk";
    @Getter
    private static final int EXPLICITLYWAIT = 10;
}
