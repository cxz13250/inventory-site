package cn.iselab.inventory.site.web.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ROGK on 2017/11/1.
 */
public class HttpUtil {

    public String getIpByRequest(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if (isNotValidIP(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (isNotValidIP(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (isNotValidIP(ip)) {
            ip = request.getRemoteAddr();
        }
        if (isNotValidIP(ip)) {
            ip = request.getHeader("http_client_ip");
        }
        if (isNotValidIP(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip.equals("0:0:0:0:0:0:0:1")) {
            ip = "127.0.0.1";
        }
        if (ip.split(",").length > 1) {
            ip = ip.split(",")[0];
        }
        return ip;
    }

    private boolean isNotValidIP(String ip) {
        return ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip);
    }

}
