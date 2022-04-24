package cn.nero.community.service.impl;

import cn.nero.community.domain.Staff;
import cn.nero.community.domain.vo.StaffAdminVO;
import cn.nero.community.mappers.AdminMapper;
import cn.nero.community.mappers.StaffMapper;
import cn.nero.community.service.StaffService;
import cn.nero.community.domain.vo.PaginationVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/10
 */
@Service
@Transactional
@Slf4j
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffMapper staffMapper;

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public String saveStaff(Staff staff) {
        // 设置审核状态
        staff.setState("2");
        staffMapper.saveStaffInfo(staff);
        return staff.getId();
    }

    @Override
    public void editStaff(Staff staff) {
        staffMapper.editStaff(staff);
    }

    @Override
    public Staff findStaffByAdminIdOrAccount(String condition) {
        return staffMapper.findStaffByAdminIdOrAccount(condition);
    }

    @Override
    public PaginationVO<Staff> findStaffsByStaffCondition(Staff staff, Integer skipCount, Integer pageSize) {
        List<Staff> dataList = staffMapper.findStaffByStaffCondition(staff, skipCount, pageSize);
        Integer total = staffMapper.getTotal(staff);
        return new PaginationVO<>(dataList, total);
    }

    @Override
    public void batchUpdateStaffLockState(List<String> ids) {
        // 批量审核staff账号,修改审核状态为可用
        staffMapper.batchUpdateStaffState(ids);
        // 批量修改admin账号的审核状态为可用
        adminMapper.approvalByStaffId(ids);
    }

    @Override
    public PaginationVO<StaffAdminVO> findStaffAdminVO(String state, Integer skipCount, Integer pageSize) {
        List<StaffAdminVO> dataList = staffMapper.findStaffAdminList(state, skipCount, pageSize);
        int total = staffMapper.getStaffAdminVOTotal(state);
        return new PaginationVO<>(dataList, total);
    }
}
