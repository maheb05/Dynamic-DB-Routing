package com.dynamicrouting.config;

public class DBContextHolder {
    private static final ThreadLocal<String> CONTEXT = new ThreadLocal<>();

    public static void setDB(String dbName) {
        CONTEXT.set(dbName);
    }

    public static String getDB() {
        return CONTEXT.get();
    }

    public static void clear() {
        CONTEXT.remove();
    }
}

