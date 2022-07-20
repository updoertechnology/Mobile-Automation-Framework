package com.mobile.factory;

import com.mobile.enums.Locator;
import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public final class LocatorFactory {

    private LocatorFactory(){}

    private static final Map<Locator, Function<String, By>> MAP = new HashMap<>();

    static {
        MAP.put(Locator.XPATH,By::xpath);
        MAP.put(Locator.ID,By::id);
        MAP.put(Locator.NAME,By::name);
        MAP.put(Locator.CLASSNAME,By::className);
    }

    public static By getByLocator(Locator locator,String value){
        return MAP.get(locator).apply(value);
    }

}
