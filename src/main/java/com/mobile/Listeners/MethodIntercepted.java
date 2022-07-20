package com.mobile.Listeners;

import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

import java.util.ArrayList;
import java.util.List;

public class MethodIntercepted implements IMethodInterceptor {
    @Override
    public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {
        List<IMethodInstance> iMethodInstances = new ArrayList<>();
        iMethodInstances.add(methods.get(0));
        return iMethodInstances;
    }
}
