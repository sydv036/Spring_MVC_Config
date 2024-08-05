package com.example.JSFW_L_A103.common;

public class ValidationCommon {

    public static String isCheckProcess(Object obj) {
        if (obj != null) {
            return "Process successfully!";
        } else {
            return "Process failed!";
        }
    }
}
