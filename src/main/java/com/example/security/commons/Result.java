package com.example.security.commons;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022-03-10 19:45
 * @Description:
 */
public class Result {

    private boolean flag;

    private Integer code;

    private String message;

    private Object data;

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-01-08 12:55
     * @Description:
     */
    public Result() {

    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-01-08 12:55
     * @Description:
     */
    public Result(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    /**
     * @Author: LGX-LUCIFER
     * @Date: 2022-01-08 12:55
     * @Description:
     */
    public Result(boolean flag, Integer code, String message, Object data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
