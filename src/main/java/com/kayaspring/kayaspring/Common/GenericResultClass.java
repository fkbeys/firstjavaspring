package com.kayaspring.kayaspring.Common;

import com.kayaspring.kayaspring.Api.Middlewares.Logging.ILogger;
import jakarta.persistence.Transient;

public class GenericResultClass {
    @Transient
    private static ILogger logger;
    private long totalItemCount;
    private Object data;
    private String message;
    private boolean isSuccess;


    public GenericResultClass(Object _data, long _totalItemCount, String _message, boolean _isSuccess) {
        this.data = _data;
        this.message = _message;
        this.totalItemCount = _totalItemCount;
        this.isSuccess = _isSuccess;
    }

    public static GenericResultClass Success(Object Data, long _totalItemCount) {

        return new GenericResultClass(Data, _totalItemCount, "Ok", true);
    }



    public static GenericResultClass Error(Exception ex, ILogger logger) {

        StackTraceElement[] stackTrace = ex.getStackTrace();
        String errorMessage = "";
        if (stackTrace.length > 0) {
            int lineNumber = stackTrace[0].getLineNumber();
            String className = stackTrace[0].getFileName();
            errorMessage = "Class: " + className + " at line : " + lineNumber + "  " + ex.getMessage();
        } else {
            errorMessage = ex.getMessage();
        }

        if (logger != null) {
            logger.log("Error", errorMessage);
        }
        return new GenericResultClass(null, 0, errorMessage, false);
    }

    public Object getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public long getTotalItemCount() {
        return totalItemCount;
    }

    public void setTotalItemCount(int totalItemCount) {
        this.totalItemCount = totalItemCount;
    }
}
