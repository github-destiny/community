package cn.nero.community.mappers;

import cn.nero.community.domain.User;
import cn.nero.community.domain.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/19
 */
@Mapper
@Repository
public interface UserMapper {

    /**
     * 创建用户
     * @param user
     */
    void saveUser(User user);

    /**
     * 更新用户信息,通常是昵称修改或者封禁帐号
     * @param user
     */
    void updateUser(User user);

    /**
     * 批量封禁帐号
     * @param accountList
     */
    void batchBanUser(@Param("accountList") List<String> accountList,
                      @Param("reason") String reason,
                      @Param("lockEndTime") String lockEndTime,
                      @Param("lockState") String lockState);

    /**
     * 删除账号,通常不会使用,但是可以通过id或者account
     * @param condition
     */
    void deleteUser(String condition);

    /**
     * 查询用户信息,通常包括他的账号信息以及部分居民信息
     * @param user
     * @param skipCount
     * @param pageSize
     * @return
     */
    List<UserVO> findUsers(@Param("user") User user,
                           @Param("skipCount") Integer skipCount,
                           @Param("pageSize") Integer pageSize);

    /**
     * 获取记录的总数
     * @param user
     * @return
     */
    int getTotal(User user);

    /**
     * 通过账号查找user信息
     * @param account
     * @return
     */
    User findUserByAccount(String account);

}
