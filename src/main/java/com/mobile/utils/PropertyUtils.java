package com.mobile.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.mobile.constants.FrameworkConstants;



public class PropertyUtils {

    private static final   Properties properties = new Properties();
    private static final Map<String, String> MAP = new HashMap<>();

    static {
        try (FileInputStream fileInputStream = new FileInputStream(
                FrameworkConstants.getInstance().getCONFIGFILEPATH())){
            properties.load(fileInputStream);
            properties.forEach((key, value) -> MAP.put(key.toString(), value.toString()));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
            throw new  RuntimeException("There is problem in file path");
        }
    }

    public static synchronized String getPropertyValue(String key){
        return MAP.get(key);
    }









}
