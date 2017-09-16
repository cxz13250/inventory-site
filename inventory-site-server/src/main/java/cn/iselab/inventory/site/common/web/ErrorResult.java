package cn.iselab.inventory.site.common.web;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by luyanliang on 2015/7/7.
 */
public class ErrorResult extends HashMap<String, Object> {

    private static Map<Integer, String> errorCodeMap = new HashMap<>();

    static {
        Field[] fields = StatusCode.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                errorCodeMap.put(field.getInt(StatusCode.class), field.getName());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }


    }

    public ErrorResult(int errorCode) {
        put(ResponseMessage.Error_Code, errorCode);

        String msg = errorCodeMap.get(errorCode);
        if (msg == null) {
            msg = "Unknown error";
        }

        put(ResponseMessage.Error_MSG, msg);
    }

    public ErrorResult(String message) {
        put(ResponseMessage.Error_Code, HttpServletResponse.SC_BAD_REQUEST);
        put(ResponseMessage.Error_MSG,message);
    }
}
