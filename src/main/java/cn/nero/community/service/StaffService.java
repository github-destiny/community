package cn.nero.community.service;

import cn.nero.community.domain.Staff;
import cn.nero.community.domain.vo.PaginationVO;

import java.util.List;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/10
 */
public interface StaffService {

    /**
     * 创建工作人员
     * @param staff
     */
    String saveStaff(Staff staff);

    /**
     * 修改工作人员信息
     * @param staff
     */
    void editStaff(Staff staff);

    /**
     * 通过账号或者id查询员工信息
     * @return
     */
    Staff findStaffByAdminIdOrAccount(String condition);

    /**
     * 查找符合条件的工作人员信息
     * @param staff
     * @param skipCount
     * @param pageSize
     * @return
     */
    PaginationVO<Staff> findStaffsByStaffCondition(Staff staff, Integer skipCount, Integer pageSize);

    /**
     * 批量审批账号
     * @param ids
     */
    void batchUpdateStaffLockState(List<String> ids);

}
