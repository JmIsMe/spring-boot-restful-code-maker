package ${projectPath}.domain;

/**
 * @description :
 * @author : ${author}
 * @date : ${date}
 */
public class Result<T> {
    //结果代码
    private String errCode;

    //返回结果,失败为false,成功为true
    private boolean status;

    //返回信息提示
    private String msg;

    //返回的数据
    private T data;

    public void setAttributes(String errCode, boolean status, String msg, T data) {
    this.errCode = errCode;
    this.status = status;
    this.msg = msg;
    this.data = data;
    }

    public boolean isStatus() {
    return status;
    }

    public void setStatus(boolean status) {
    this.status = status;
    }

    public String getMsg() {
    return msg;
    }

    public void setMsg(String msg) {
    this.msg = msg;
    }

    public String getErrCode() {
    return errCode;
    }

    public void setErrCode(String errCode) {
    this.errCode = errCode;
    }

    public T getData() {
    return data;
    }

    public void setData(T data) {
    this.data = data;
    }

}
