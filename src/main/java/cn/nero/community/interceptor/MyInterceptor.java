package cn.nero.community.interceptor;

import cn.nero.community.utils.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/18
 */
@Slf4j
public class MyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String, String[]> map = request.getParameterMap();
        for (String key : map.keySet()) {
            log.debug("[param key] : {}", key);
            log.debug("[param] : {}", map.get(key)[0]);
        }
        //String requestURI = request.getRequestURI();
        //ServletContext context = request.getServletContext();
        //if ("/resident/get/age".equals(requestURI)) {
        //    List<Map<String, Object>> age = (List<Map<String, Object>>) context.getAttribute("echarts_age");
        //    if (age != null) {
        //        JSONUtil.printJsonObj(response, age);
        //        return false;
        //    }
        //} else if ("/resident/get/times".equals(requestURI)) {
        //    List<Map<String, Object>> times = (List<Map<String, Object>>) context.getAttribute("echarts_times");
        //    if (times != null){
        //        JSONUtil.printJsonObj(response, times);
        //        return false;
        //    }
        //}
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
