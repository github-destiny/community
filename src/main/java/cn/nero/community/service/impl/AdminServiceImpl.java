package cn.nero.community.service.impl;

import cn.nero.community.domain.Admin;
import cn.nero.community.domain.vo.PaginationVO;
import cn.nero.community.exception.account.AccountAlreadyExistException;
import cn.nero.community.exception.account.AccountStateException;
import cn.nero.community.mappers.AdminMapper;
import cn.nero.community.service.AdminService;
import cn.nero.community.utils.DateTimeUtil;
import cn.nero.community.utils.Md5Util;
import cn.nero.community.utils.SaltUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/6
 */
@Service("adminService")
@Transactional
@Slf4j
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin findAdminByAccount(String account) {
        return adminMapper.findAdminByAccount(account);
    }

    @Override
    public void saveAdmin(Admin admin) {
        // 查询账号是否存在
        Admin info = adminMapper.findAdminByAccount(admin.getAccount());
        if (!ObjectUtils.isEmpty(info)){
            throw new AccountAlreadyExistException("账号已存在!");
        }
        // 获取随机盐
        String salt = SaltUtil.getSalt();
        // 加密密码
        String md5Pwd = Md5Util.getMd5(admin.getPassword(), salt);
        // 注入密码
        admin.setPassword(md5Pwd);
        // 注入盐
        admin.setSalt(salt);
        // 注入角色,默认是工作人员
        admin.setRole("staff");
        // 注入创建时间
        admin.setCreateTime(DateTimeUtil.getTime());
        // 注入数据库
        adminMapper.saveAdmin(admin);
    }

    @Override
    public PaginationVO<Admin> findAdminByCondition(Admin admin, Integer skipCount, Integer pageSize) {
        List<Admin> dataList = adminMapper.findAdminByCondition(admin, skipCount, pageSize);
        int total = getTotal(admin);
        return new PaginationVO<>(dataList, total);
    }

    @Override
    public String findRoleByAccount(String account) {
        return adminMapper.findRoleByAccount(account);
    }

    @Override
    public void editPassword(String account, String oldPassword, String newPassword) {
        Admin admin = findAdminByAccount(account);
        // 获取旧密码
        String password = admin.getPassword();
        String md5OldPwd = Md5Util.getMd5(oldPassword, admin.getSalt());
        if (!password.equals(md5OldPwd)) {
            throw new IncorrectCredentialsException("原密码错误!");
        }
        // 修改密码,对新密码进行加密
        String newMd5Pwd = Md5Util.getMd5(newPassword, admin.getSalt());
        // 注入admin
        admin.setPassword(newMd5Pwd);
        editAdminInfo(admin);
    }

    @Override
    public void editAdminInfo(Admin admin) {
        adminMapper.editAdmin(admin);
    }

    @Override
    public int getTotal(Admin admin) {
        return adminMapper.getTotal(admin);
    }

    @Override
    public void approval(List<String> ids) {
        adminMapper.approval(ids);
    }

}
