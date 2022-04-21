package cn.nero.community.service.impl;

import cn.nero.community.domain.City;
import cn.nero.community.domain.Returnees;
import cn.nero.community.domain.Task;
import cn.nero.community.domain.vo.PaginationVO;
import cn.nero.community.domain.vo.ReturneesCityVO;
import cn.nero.community.mappers.CityMapper;
import cn.nero.community.mappers.ReturneesMapper;
import cn.nero.community.mappers.TaskMapper;
import cn.nero.community.service.ReturneesService;
import cn.nero.community.utils.DateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/13
 */
@Service
@Transactional
@Slf4j
public class ReturneesServiceImpl implements ReturneesService {

    @Autowired
    private ReturneesMapper returneesMapper;

    @Autowired
    private CityMapper cityMapper;

    @Autowired
    private TaskMapper taskMapper;

    @Override
    public PaginationVO<ReturneesCityVO> findReturneesByCondition(Returnees returnees, City city, Integer skipCount, Integer pageSize) {
        List<ReturneesCityVO> dataList = returneesMapper.findReturneesByCondition(returnees, city, skipCount, pageSize);
        int total = returneesMapper.getTotal(returnees, city);
        return new PaginationVO<>(dataList, total);
    }

    @Override
    public void saveReturnees(Returnees returnees) {
        // 注入申请时间
        returnees.setApplyTime(DateTimeUtil.getTime());
        // 创建任务信息
        Task task = new Task();
        // 查询该人员返回地点的风险等级
        List<City> cities = cityMapper.findCityByCondition(new City().setId(returnees.getFrom()));
        String level = cities.get(0).getLevel();
        String endTime = "21";
        // 针对不同的风险等级,制定不同的任务
        if ("高风险".equals(level)) {
            // 集中隔离,封禁14+7,2天一次核酸检测
            task.setStrategy("1");
        } else if("中风险".equals(level)) {
            // 居家隔离,封禁14+7,三天一次核酸
            task.setStrategy("2");
        } else {
            // 居家隔离,健康检测7天,两次核酸检测
            task.setStrategy("3");
            endTime = "7";
        }
        // 获取结束时间
        endTime = returneesMapper.getDate("DATE_ADD", returnees.getApplyTime(), endTime, "DAY");
        // 注入结束时间
        returnees.setEndTime(endTime);
        // 保存该人员信息
        returneesMapper.saveReturnees(returnees);
        // 注入属性
        task.setReturnees_id(returnees.getId());
        task.setCreateTime(DateTimeUtil.getTime());
        task.setState("未接受");
        // 保存任务信息
        taskMapper.saveTask(task);
    }

    @Override
    public void updateReturnees(Returnees returnees) {
        returneesMapper.updateReturnees(returnees);
    }

    @Override
    public Map<String, Object> getReturneesNucleicResult(String startTime, String endTime) {
        Map<String, Object> map = new HashMap<>();
        // 查询总人数
        int total = returneesMapper.getReturneesNucleicAll(null, null, null);
        int negative = returneesMapper.getReturneesNucleicAll("阴性", startTime, endTime);
        int positive = returneesMapper.getReturneesNucleicAll("阳性", startTime, endTime);
        if (0 != positive) {
            List<Returnees> returneesList = returneesMapper.getPositiveReturnees();
            map.put("level", "danger");
            map.put("returnees", returneesList);
            map.put("desc", "发现" + positive + "例疑似病例!");
        }
        map.put("time", startTime + "--" + endTime);
        map.put("total", total);
        map.put("negative", negative);
        map.put("positive", positive);
        return map;
    }


}
