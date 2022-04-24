package cn.nero.community.mappers;

import cn.nero.community.domain.Staff;
import cn.nero.community.domain.vo.StaffAdminVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/7
 */
@Mapper
@Repository
public interface StaffMapper {

    /**
     * 保存工作人员信息
     * @param staff
     */
    void saveStaffInfo(Staff staff);

    /**
     * 修改工作人员的信息
     * @param staff
     */
    void editStaff(Staff staff);

    /**
     * 根据id或者账号查询员工信息
     * @param condition
     * @return
     */
    Staff findStaffByAdminIdOrAccount(String condition);

    /**
     * 根据staff的字段查询staff信息
     * @param staff
     * @return
     */
    List<Staff> findStaffByStaffCondition(@Param("staff") Staff staff, @Param("skipCount") Integer skipCount, @Param("pageSize") Integer pageSize);

    /**
     * 获取符合条件数据的总数
     * @param staff
     * @return
     */
    Integer getTotal(Staff staff);

    /**
     * 批量对注册账号进行审批
     * @param ids
     */
    void batchUpdateStaffState(@Param("ids") List<String> ids);

    /**
     * 查看工作人员信息与账号之间的关联
     *
     * @return
     */
    List<StaffAdminVO> findStaffAdminList(@Param("state") String state, @Param("skipCount") Integer skipCount, @Param("pageSize") Integer pageSize);

    /**
     * 查看工作人员信息与账号之间列表的总数
     * @param state
     * @return
     */
    int getStaffAdminVOTotal(String state);

}
