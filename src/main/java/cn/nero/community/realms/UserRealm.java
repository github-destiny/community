package cn.nero.community.realms;

import cn.nero.community.domain.User;
import cn.nero.community.service.UserService;
import cn.nero.community.utils.ApplicationContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.ObjectUtils;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/20
 */
@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 获取登录凭证
        String principal = (String) authenticationToken.getPrincipal();
        log.info("普通账号[{}]正在执行登陆操作...", principal);
        UserService userService = (UserService) ApplicationContextUtils.getBean("userServiceImpl");
        User user = userService.findUserByAccount(principal);
        if (!ObjectUtils.isEmpty(user)) {
            return new SimpleAuthenticationInfo(principal,
                    user.getPassword(), ByteSource.Util.bytes(user.getSalt()), this.getName());
        }
        return null;
    }
}
