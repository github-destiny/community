package cn.nero.community.controller;

import cn.nero.community.domain.Staff;
import cn.nero.community.service.StaffService;
import cn.nero.community.domain.vo.PaginationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/10
 */
@Controller
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @PostMapping("/save")
    @ResponseBody
    public void save(Staff staff){
        staffService.saveStaff(staff);
    }

    @PostMapping("/edit")
    @ResponseBody
    public void editStaffInfo(Staff staff) {
        staffService.editStaff(staff);
    }

    @GetMapping("/find/admin/{condition}")
    @ResponseBody
    public Staff findStaffByAdmin(@PathVariable("condition") String condition){
        return staffService.findStaffByAdminIdOrAccount(condition);
    }

    @GetMapping("/find")
    @ResponseBody
    public PaginationVO<Staff> findStaffsByStaffCondition(
            Staff staff,
            @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize
            ){
        int skipCount = (pageNo - 1) * pageSize;
        return staffService.findStaffsByStaffCondition(staff, skipCount, pageSize);
    }

    @GetMapping("/approval")
    @ResponseBody
    public void batchApproval(@RequestParam("ids") List<String> ids){
        staffService.batchUpdateStaffLockState(ids);
    }

}
