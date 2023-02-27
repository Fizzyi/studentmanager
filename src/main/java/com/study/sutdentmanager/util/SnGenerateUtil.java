package com.study.sutdentmanager.util;

public class SnGenerateUtil {

    /**
     * 随机生成学生学号
     *
     * @param clazzId
     * @return
     */
    public static String generateSn(int clazzId) {
        return "S" + clazzId + System.currentTimeMillis();
    }

    /**
     * 随机生成老师学号
     *
     * @param clazzId
     * @return
     */
    public static String generateTeacherSn(int clazzId) {
        return "T" + clazzId + System.currentTimeMillis();
    }
}
