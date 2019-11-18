package my.app.gayyong.entiity;

import com.alibaba.fastjson.JSONObject;

public class JsonResult {
    private Integer status;
    private String msg;
    private Object data;

    private JsonResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public static JsonResult ok() {
        return JsonResult.ok("succuess", null);
    }

    public static JsonResult ok(String msg) {
        return JsonResult.ok(msg, null);
    }

    public static JsonResult ok(Object data) {
        return JsonResult.ok("success", data);
    }

    public static JsonResult ok(String msg, Object data) {
        return new JsonResult(200, msg, data);
    }

    public static JsonResult error502(){
        return new JsonResult(502,"token已过期",null);
    }

    public static JsonResult error500(String msg){
        return new JsonResult(500,msg,null);
    }

    public String toJSONString(){
        return JSONObject.toJSONString(this);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

