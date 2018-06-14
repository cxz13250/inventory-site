package cn.iselab.inventory.site.configure;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 上午12:28 2018/6/15
 * @Modified By:
 */
public class MyFilter extends FormAuthenticationFilter {

    private static final Logger log = LoggerFactory.getLogger(Process.class.getName());

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        HttpServletRequest httpRequest = WebUtils.toHttp(request);

        HttpServletResponse httpResponse = WebUtils.toHttp(response);

        if (isLoginRequest(request, response)) {

            if (isLoginSubmission(request, response)) {

                if (log.isTraceEnabled()) {
                    log.trace("Login submission detected. Attempting to execute login.");
                }
                return executeLogin(request, response);

            } else {
                if (log.isTraceEnabled()) {
                    log.trace("Login page view.");
                }
                // allow them to see the login page ;)
                return true;
            }
        } else {
            if (log.isTraceEnabled()) {
                log.trace("Attempting to access a path which requires authentication. Forwarding to the "
                        + "Authentication url [" + getLoginUrl() + "]");
            }
            // 判断session里是否有用户信息
            if (httpRequest.getHeader("X-Requested-With") != null
                    && httpRequest.getHeader("X-Requested-With").equalsIgnoreCase("XMLHttpRequest")) {
                // 如果是ajax请求响应头会有，x-requested-with
                httpResponse.sendError(HttpStatus.UNAUTHORIZED.value());
                //redirectToLogin(request, response);
            } else {
                httpResponse.sendError(HttpStatus.UNAUTHORIZED.value());
            }
            return false;
        }
    }
}
