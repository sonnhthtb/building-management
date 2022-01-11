package myteam.project4.config.tracing;

import lombok.extern.slf4j.Slf4j;
import myteam.project4.constant.SystemConstant;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
public class CustomURLFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

//		ThreadContext.put(ServletUtils.DEMO_UID, ObjectUtils.defaultIfNull(ServletUtils.getCurrentUserId(), -1L).toString());
        servletRequest.setAttribute(SystemConstant.Tracing.TIME_REQUEST, System.currentTimeMillis());
        logRequest((HttpServletRequest) servletRequest);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void logRequest(HttpServletRequest request) {
        if (request != null) {
            String data = "[" + request.getMethod() + ": " + request.getRequestURI() + "]" +
                    "[" + request.getQueryString() + "]";
            log.info(data);
        }
    }
}
