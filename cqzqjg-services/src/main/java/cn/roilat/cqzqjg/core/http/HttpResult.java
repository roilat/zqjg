package cn.roilat.cqzqjg.core.http;

/**
 * HTTP结果封装
 *
 * @author Louis
 * @date Oct 29, 2018
 */
public class HttpResult {

    private int code = 200;
    private String msg;
    private Object data;
    private boolean success = true;


    public static HttpResult error() {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
    }

    public static HttpResult error(String msg) {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
    }

    public static HttpResult error(int code, String msg) {
        HttpResult r = new HttpResult();
        r.setCode(code);
        r.setMsg(msg);
        r.setSuccess(false);
        return r;
    }

    public static HttpResult ok(String msg) {
        HttpResult r = new HttpResult();
        r.setMsg(msg);
        return r;
    }

    public static HttpResult ok(Object data) {
        HttpResult r = new HttpResult();
        r.setData(data);
        return r;
    }

    public static HttpResult ok(String msg, Object data) {
        HttpResult r = new HttpResult();
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    public static HttpResult ok(String msg, String data) {
        HttpResult r = new HttpResult();
        r.setMsg(msg);
        r.setData(data);
        return r;
    }


    public static HttpResult ok() {
        return new HttpResult();
    }

    public int getCode() {
        return code;
    }

    public HttpResult setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public HttpResult setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getData() {
        return data;
    }

    public HttpResult setData(Object data) {
        this.data = data;
        return this;
    }

    public boolean getSuccess() {
        return success;
    }

    public HttpResult setSuccess(boolean success) {
        this.success = success;
        return this;
    }

}
