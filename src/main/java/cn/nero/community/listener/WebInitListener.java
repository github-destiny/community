package cn.nero.community.listener;

import cn.nero.community.domain.vo.Count;
import cn.nero.community.service.ResidentService;
import cn.nero.community.service.ReturneesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;
import java.util.Map;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/5/8
 */
@Component
@Slf4j
public class WebInitListener implements ServletContextListener {

    @Autowired
    private ResidentService residentService;

    @Autowired
    private ReturneesService returneesService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("[info] : web project initialization...");
        // 缓存模块
        // 获取年龄组成
        List<Map<String, Object>> echarts_age = residentService.getResidentGroupByAge();
        sce.getServletContext().setAttribute("echarts_age", echarts_age);
        log.info("年龄组成[echarts_age]缓存完毕...");
        // 接种次数
        List<Map<String, Object>> echarts_times = residentService.getInoculationData();
        sce.getServletContext().setAttribute("echarts_times", echarts_times);
        log.info("居民接种次数[echarts_times]缓存完毕...");
        // 外来人员接种情况统计
        List<Count> echarts_returnees_times = returneesService.getReturneesInoculationTimes();
        sce.getServletContext().setAttribute("echarts_returnees_times", echarts_returnees_times);
        log.info("外来人员接种次数[echarts_returnees_times]缓存完毕...");
        // 外来人员人数统计
        List<Map<String, Object>> pre14 = returneesService.getCountOneDay("14");
        sce.getServletContext().setAttribute("pre14", pre14);
        log.info("前14天外来人员数[pre14]缓存完毕...");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("项目注销中...");
    }
}
