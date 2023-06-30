package com.kayaspring.kayaspring.Common;

public class GenericResultClass {
    private Object _data;
    private String _message;
    private boolean _isSuccess;

    public Object get_data() {
        return _data;
    }

    public String get_message() {
        return _message;
    }

    public boolean is_isSuccess() {
        return _isSuccess;
    }

    private GenericResultClass(Object data, String xmessage, boolean isSuccess) {
        _data = data;
        _message = xmessage;
        _isSuccess = isSuccess;
    }

    public static GenericResultClass Success(Object Data) {

        return new GenericResultClass(Data, "Ok", true);
    }

    public static GenericResultClass Error(Exception e) {

        String errorMessage = "Error:" + e.getMessage();
        return new GenericResultClass(null, errorMessage, false);
    }

}
