package cn.nero.community.service;

import cn.nero.community.domain.Admin;
import cn.nero.community.domain.vo.PaginationVO;

import java.util.List;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/6
 */
public interface AdminService {

    /**
     * 通过账号查找账户信息,用作登录接口
     * @param account
     * @return
     */
    Admin findAdminByAccount(String account);

    /**
     * 注册接口,保存管理员
     * @param admin
     */
    void saveAdmin(Admin admin);

    /**
     * 通过条件查找Admin信息
     * @return
     */
    PaginationVO<Admin> findAdminByCondition(Admin admin, Integer skipCount, Integer pageSize);

    /**
     * 通过用户名查找他的权限
     * @param account
     * @return
     */
    String findRoleByAccount(String account);

    /**
     * 更新admin信息
     * @param admin
     */
    void editAdminInfo(Admin admin);

    /**
     * 修改密码
     * @param account
     * @param oldPassword
     * @param newPassword
     */
    void editPassword(String account, String oldPassword, String newPassword);

    /**
     * 获取admin总数,可以根据admin的条件进行筛选
     * lockState,role,createTime
     * @param admin
     * @return
     */
    int getTotal(Admin admin);

    /**
     * 批量审核账号
     * @param ids
     */
    void approval(List<String> ids);

}
