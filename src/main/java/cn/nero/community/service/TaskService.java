package cn.nero.community.service;

import cn.nero.community.domain.Task;
import cn.nero.community.domain.vo.PaginationVO;
import cn.nero.community.domain.vo.TaskVO;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/18
 */
public interface TaskService {

    PaginationVO<TaskVO> findTaskList(Task task, Integer skipCount, Integer pageSize);

    void acceptTask(String taskId, String staff_id);

    void updateTask(Task task);


}
