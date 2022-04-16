package com.midlanddigital.test.app.domain.exceptions;

public class ServiceException extends RuntimeException {

    public static final String UNKNOWN_SERVICE_ERROR = "TestApp_01";
    public static final String STAFF_NAME_REQUIRED = "TestApp_02";
    public static final String STAFF_UUID_REQUIRED = "TestApp_03";
    public static final String STAFF_INVALID = "TestApp_04";
    public static final String DATE_RANGE_INVALID = "TestApp_05";
    public static final String INVALID_STAFF_UUID_HEADER = "TestApp_06";
    public static final String INVALID_DATE_RANGE = "TestApp_07";
    public static final String INVALID_DATE_FORMAT = "TestApp_07";

    private final String errorCode;


    public ServiceException(String message) {
        super(message);
        this.errorCode = UNKNOWN_SERVICE_ERROR;
    }


    public ServiceException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }


}
