package cn.nero.community.controller;

import cn.nero.community.domain.Strategy;
import cn.nero.community.domain.Task;
import cn.nero.community.domain.vo.PaginationVO;
import cn.nero.community.domain.vo.TaskVO;
import cn.nero.community.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/18
 */
@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/find")
    public PaginationVO<TaskVO> findTasks(@RequestParam(value = "staff_id", required = false) String staff_id,
                                          @RequestParam(value = "state", required = false) String state,
                                          @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                                          @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize){
        int skipCount = (pageNo - 1) * pageSize;
        Task task = new Task();
        task.setState(state).setStaff_id(staff_id);
        return taskService.findTaskList(task, skipCount, pageSize);
    }

    @GetMapping("/accept")
    public void acceptTask(@RequestParam("taskId") String taskId,
                           @RequestParam("staff_id") String staff_id){
        taskService.acceptTask(taskId, staff_id);
    }

    @PostMapping("/update")
    public void updateTask(Task task){
        taskService.updateTask(task);
    }

    @GetMapping("/submit")
    public void submitTask(@RequestParam(value = "id") String id,
                           @RequestParam(value = "content", required = false) String content){
        Task task = new Task();
        task.setId(id).setState("已完成").setContent(content);
        taskService.updateTask(task);
    }

    @GetMapping("/find/list")
    public Map<String, Object> findTaskList(@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                                            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize){
        int skipCount = (pageNo - 1) * pageSize;
        return taskService.findTasks(skipCount, pageSize);
    }

    @GetMapping("/get/strategy")
    public List<Strategy> findStrategies(){
        return taskService.findStrategies();
    }

    @GetMapping("/get/myTask")
    public List<Task> myTask(@RequestParam("staffId") String staffId,@RequestParam("state") String state){
        return taskService.findMyTask(staffId, state);
    }

}
