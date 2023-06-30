package com.kayaspring.kayaspring.Common;

public class GenericResultClass {
    private Object data;
    private String message;
    private boolean isSuccess;

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

    public static GenericResultClass Error(Exception e) {

        String errorMessage = "Error:" + e.getMessage();
        return new GenericResultClass(null, errorMessage, false);
    }

}
