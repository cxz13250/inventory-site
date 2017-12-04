package cn.iselab.inventory.site.common.web;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午8:49 2017/10/4
 * @Modified By:
 */
public class SuccessResult extends HashMap<String, Object> {
    public SuccessResult() {
        put(ResponseMessage.Error_Code, HttpServletResponse.SC_OK);
    }

    public static SuccessResult ok() {
        return new SuccessResult();
    }

    public static SuccessResult ok(String key, Object value) {
        SuccessResult result = new SuccessResult();
        result.put(key, value);
        return result;
    }
}
