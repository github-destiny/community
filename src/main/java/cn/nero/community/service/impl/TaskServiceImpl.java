package cn.nero.community.service.impl;

import cn.nero.community.domain.Strategy;
import cn.nero.community.domain.Task;
import cn.nero.community.domain.vo.PaginationVO;
import cn.nero.community.domain.vo.TaskVO;
import cn.nero.community.mappers.TaskMapper;
import cn.nero.community.service.TaskService;
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
 * @Date 2022/4/18
 */
@Service
@Transactional
@Slf4j
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskMapper taskMapper;

    @Override
    public PaginationVO<TaskVO> findTaskList(Task task, Integer skipCount, Integer pageSize) {
        List<TaskVO> dataList = taskMapper.findTasks(task, skipCount, pageSize);
        int total = taskMapper.getTasksTotal(task);
        return new PaginationVO<>(dataList, total);
    }

    @Override
    public void acceptTask(String taskId, String staff_id) {
        Task task = new Task();
        task.setAcceptTime(DateTimeUtil.getTime());
        task.setId(taskId);
        task.setStaff_id(staff_id);
        task.setState("跟进中");
        taskMapper.updateTask(task);
    }

    @Override
    public void updateTask(Task task) {
        task.setUpdateTime(DateTimeUtil.getTime());
        taskMapper.updateTask(task);
    }

    @Override
    public Map<String, Object> findTasks(Integer skipCount, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        List<Task> dataList = taskMapper.findTaskList(skipCount, pageSize);
        map.put("dataList", dataList);
        map.put("total", taskMapper.getNotAllowTaskCount());
        return map;
    }

    @Override
    public List<Strategy> findStrategies() {
        return taskMapper.findStrategy();
    }

    @Override
    public List<Task> findMyTask(String staffId, String state) {
        return taskMapper.findMyTask(staffId, state);
    }

}
