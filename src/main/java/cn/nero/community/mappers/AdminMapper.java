package cn.nero.community.mappers;

import cn.nero.community.domain.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/6
 */
@Mapper
@Repository
public interface AdminMapper {

    Admin findAdminByAccount(String account);

    void saveAdmin(Admin admin);

    List<Admin> findAdminByCondition(@Param("admin") Admin admin, @Param("skipCount") Integer skipCount, @Param("pageSize") Integer pageSize);

    void editAdmin(Admin admin);

    void approval(@Param("ids") List<String> ids);

    /**
     * 批量审核admin账号通过工作人员id
     * @param ids
     */
    void approvalByStaffId(@Param("ids") List<String> ids);

    int getTotal(Admin admin);

    String findRoleByAccount(String account);
}
