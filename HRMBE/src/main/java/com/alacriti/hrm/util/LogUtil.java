package com.alacriti.hrm.util;

import com.alacriti.hrm.log.impl.AppLogger;


public class LogUtil
{

    public static AppLogger getLogger(Class<?> clazz)
    {
        return new AppLogger(clazz);
    }

    public static String getCurrentMethodName()
    {
        return Thread.currentThread().getStackTrace()[3].getMethodName();
    }

}
