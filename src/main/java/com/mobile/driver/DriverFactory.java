package com.mobile.driver;

import org.openqa.selenium.WebDriver;

import com.mobile.enums.Modes;

import java.net.MalformedURLException;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public final class DriverFactory {
    private DriverFactory(){}
    private static final Supplier<WebDriver> LOCAL = ()-> new LocalDriverImpl().getDriver();
    private static final Supplier<WebDriver> BS = ()-> {
		try {
			return new BrowserStackImpl().getDriver();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	};
    private static final Supplier<WebDriver> SAUCELABS = ()-> {
		try {
			return new SauceLabsImpl().getDriver();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	};


    private static final Map<Modes, Supplier<WebDriver>> MAP = new EnumMap<>(Modes.class);
    static {
        MAP.put(Modes.LOCAL,LOCAL);
        MAP.put(Modes.BS,BS);
        MAP.put(Modes.SAUCELABS,SAUCELABS);
    }
    public static WebDriver getDriver(Modes modes){
        return MAP.get(modes).get();
    }
}
