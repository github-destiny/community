package cn.nero.community.service;

import cn.nero.community.domain.User;
import cn.nero.community.domain.vo.PaginationVO;
import cn.nero.community.domain.vo.UserVO;

import java.util.List;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/19
 */
public interface UserService {

    /**
     * 创建用户账号
     * @param user
     */
    void saveUser(User user);

    /**
     * 更新用户账号
     * @param user
     */
    void updateUser(User user);

    /**
     * 删除用户账号
     * @param condition
     */
    void deleteUser(String condition);

    /**
     * 分页于模糊查询
     * @param user
     * @param skipCount
     * @param pageSize
     * @return
     */
    PaginationVO<UserVO> findUserVO(User user, Integer skipCount, Integer pageSize);

    /**
     * 封禁单个账号
     * @param account
     */
    void banAccount(String account);

    /**
     * 批量封禁帐号
     * @param accounts
     */
    void batchBanAccount(List<String> accounts, String reason);

    /**
     * 通过账号查找user
     * @param account
     * @return
     */
    User findUserByAccount(String account);



}
