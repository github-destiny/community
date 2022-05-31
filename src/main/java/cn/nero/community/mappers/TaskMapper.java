package cn.nero.community.mappers;

import cn.nero.community.domain.Strategy;
import cn.nero.community.domain.Task;
import cn.nero.community.domain.vo.TaskVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/18
 */
@Mapper
@Repository
public interface TaskMapper {

    /**
     * 创建一个任务对象
     * @param task
     */
    void saveTask(Task task);

    /**
     * 查看任务列表
     * @return
     */
    List<TaskVO> findTasks(@Param("task") Task task, @Param("skipCount") Integer skipCount, @Param("pageSize") Integer pageSize);

    /**
     * 获取任务总数
     * @param task
     * @return
     */
    int getTasksTotal(Task task);

    /**
     * 更新task
     * @param task
     */
    void updateTask(Task task);

    List<Task> findTaskList(@Param("skipCount") Integer skipCount, @Param("pageSize") Integer pageSize);

    /**
     * 获取未接受的任务数量
     * @return
     */
    int getNotAllowTaskCount();

    /**
     * 查找封禁策略
     * @return
     */
    List<Strategy> findStrategy();

    List<Task> findMyTask(@Param("staffId") String staffId, @Param("state") String state);
}
