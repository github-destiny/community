package cn.nero.community.controller;

import cn.nero.community.domain.Staff;
import cn.nero.community.domain.vo.PaginationVO;
import cn.nero.community.domain.vo.StaffAdminVO;
import cn.nero.community.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/10
 */
@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @PostMapping("/save")
    public Map<String, Object> save(Staff staff){
        String id = staffService.saveStaff(staff);
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        return map;
    }

    @PostMapping("/edit")
    public void editStaffInfo(Staff staff) {
        staffService.editStaff(staff);
    }

    @GetMapping("/find/admin/{condition}")
    public Staff findStaffByAdmin(@PathVariable("condition") String condition){
        return staffService.findStaffByAdminIdOrAccount(condition);
    }

    @GetMapping("/find")
    public PaginationVO<Staff> findStaffsByStaffCondition(
            Staff staff,
            @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize
            ){
        int skipCount = (pageNo - 1) * pageSize;
        return staffService.findStaffsByStaffCondition(staff, skipCount, pageSize);
    }

    @GetMapping("/approval")
    public void batchApproval(@RequestParam("ids") List<String> ids){
        staffService.batchUpdateStaffLockState(ids);
    }

    @GetMapping("/find/staff/admin")
    public PaginationVO<StaffAdminVO> findStaffAdminVO(@RequestParam(value = "state", required = false) String state,
                                                       @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                                                       @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize){
        return staffService.findStaffAdminVO(state, (pageNo - 1) * pageSize, pageSize);
    }

}
