package cn.nero.community.service.impl;

import cn.nero.community.domain.User;
import cn.nero.community.domain.vo.PaginationVO;
import cn.nero.community.domain.vo.UserVO;
import cn.nero.community.mappers.ReturneesMapper;
import cn.nero.community.mappers.UserMapper;
import cn.nero.community.service.UserService;
import cn.nero.community.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/19
 */
@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ReturneesMapper returneesMapper;

    @Override
    public Map<String, Object> saveUser(User user) {
        // 密码加密
        String plaintextPwd = user.getPassword();
        String salt = SaltUtil.getSalt();
        String md5Pwd = Md5Util.getMd5(plaintextPwd, salt);
        // 昵称进行校验
        // 昵称确认
        if (user.getNick_name() == null || "".equals(user.getNick_name())) {
            user.setNick_name(UUIDUtil.getNickName());
        }
        // 注入属性
        user.setCreateTime(DateTimeUtil.getTime())
                .setLockState("正常使用")
                .setPassword(md5Pwd)
                .setSalt(salt);
        userMapper.saveUser(user);
        return ResponseUtil.getMap("id", user.getId());
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public void deleteUser(String condition) {
        userMapper.deleteUser(condition);
    }

    @Override
    public PaginationVO<UserVO> findUserVO(User user, Integer skipCount, Integer pageSize) {
        List<UserVO> dataList = userMapper.findUsers(user, skipCount, pageSize);
        int total = userMapper.getTotal(user);
        return new PaginationVO<>(dataList, total);
    }

    private String getLockDate(Integer day){
        return returneesMapper.getDate("DATE_ADD", DateTimeUtil.getTime(), String.valueOf(day), "DAY");
    }

    @Override
    public void banAccount(String account) {
        User user = new User();
        String endTime = getLockDate(7);
        user.setAccount(account).setLockState("封禁").setLockEndTime(endTime);
        userMapper.updateUser(user);
    }

    @Override
    public void batchBanAccount(List<String> accounts, String reason) {
        String lockDate = getLockDate(7);
        userMapper.batchBanUser(accounts, reason, lockDate, "封禁");
    }

    @Override
    public User findUserByAccount(String account) {
        return userMapper.findUserByAccount(account);
    }

    @Override
    public void updatePassword(String oldPassword, String newPassword, String account) {
        User user = userMapper.findUserByAccount(account);
        String md5OldPwd = Md5Util.getMd5(oldPassword, user.getSalt());
        if (!md5OldPwd.equals(user.getPassword())) {
            throw new IncorrectCredentialsException("密码错误!");
        }
        String md5NewPwd = Md5Util.getMd5(newPassword, user.getSalt());
        user.setPassword(md5NewPwd);
        userMapper.updateUser(user);
    }

    @Override
    public void batchUnBlockAccount(List<String> accounts) {
        userMapper.batchBanUser(accounts, "无", "无", "正常使用");
    }

}
