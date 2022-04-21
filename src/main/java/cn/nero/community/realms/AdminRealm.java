package cn.nero.community.realms;

import cn.nero.community.domain.Admin;
import cn.nero.community.exception.account.AccountStateException;
import cn.nero.community.service.AdminService;
import cn.nero.community.utils.ApplicationContextUtils;
import cn.nero.community.utils.MyAsserts;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.ObjectUtils;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/6
 */
@Slf4j
public class AdminRealm extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        AdminService adminService = (AdminService) ApplicationContextUtils.getBean("adminService");
        String role = adminService.findRoleByAccount(primaryPrincipal);
        log.info("该角色[{}]当前权限: [{}]", primaryPrincipal, role);
        if (!ObjectUtils.isEmpty(role) || "".equals(role)) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            info.addRole(role);
            return info;
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 获取用户账号
        String principal = (String) authenticationToken.getPrincipal();
        log.info("管理员账号[{}]正在进行登录操作...", principal);
        // 注入AdminService
        AdminService adminService = (AdminService) ApplicationContextUtils.getBean("adminService");
        // 获取用户的信息
        Admin admin = adminService.findAdminByAccount(principal);
        // 断言
        MyAsserts.assertAdmin(admin);
        return new SimpleAuthenticationInfo(
                principal,
                admin.getPassword(),
                ByteSource.Util.bytes(admin.getSalt()),
                this.getName());
    }
}
