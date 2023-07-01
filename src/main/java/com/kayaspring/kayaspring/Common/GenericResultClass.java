package com.kayaspring.kayaspring.Common;

import com.kayaspring.kayaspring.Middlewares.Logging.ILogger;

public class GenericResultClass {
    private Object data;
    private String message;
    private boolean isSuccess;

    private static ILogger logger;


    public Object getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return isSuccess;
    }


    private GenericResultClass(Object data, String xmessage, boolean isSuccess) {
        this.data = data;
        message = xmessage;
        this.isSuccess = isSuccess;
    }

    public static GenericResultClass Success(Object Data) {

        return new GenericResultClass(Data, "Ok", true);
    }


    public static GenericResultClass Error(Exception ex, ILogger logger) {

        StackTraceElement[] stackTrace = ex.getStackTrace();
        String errorMessage = "";
        if (stackTrace.length > 0) {
            int lineNumber = stackTrace[0].getLineNumber();
            String className = stackTrace[0].getFileName();
            errorMessage = "Class: " + className + " at line : " + lineNumber+"  "+ex.getMessage();
        } else {
            errorMessage = ex.getMessage();
        }

        logger.log("Error", errorMessage);
        return new GenericResultClass(null, errorMessage, false);
    }

}
