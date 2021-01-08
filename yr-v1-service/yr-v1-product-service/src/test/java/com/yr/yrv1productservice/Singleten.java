package com.yr.yrv1productservice;

public class Singleten {

    private static Singleten singleten = null;

    private Singleten() {};

    public static Singleten getIntance() {
        if (singleten == null) {
            synchronized (Singleten.class) {
                if (singleten == null) {
                    singleten = new Singleten();
                }
            }
        }
        return singleten;
    }

}
