package cn.nero.community.service;

import cn.nero.community.domain.Strategy;
import cn.nero.community.domain.Task;
import cn.nero.community.domain.vo.PaginationVO;
import cn.nero.community.domain.vo.TaskVO;

import java.util.List;
import java.util.Map;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/18
 */
public interface TaskService {

    PaginationVO<TaskVO> findTaskList(Task task, Integer skipCount, Integer pageSize);

    void acceptTask(String taskId, String staff_id);

    void updateTask(Task task);

    Map<String, Object> findTasks(Integer skipCount, Integer pageSize);

    List<Strategy> findStrategies();

    List<Task> findMyTask(String staffId, String state);
}
