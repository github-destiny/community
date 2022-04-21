package cn.nero.community.utils;

import cn.nero.community.domain.Admin;
import cn.nero.community.exception.account.AccountStateException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.util.ObjectUtils;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/6
 */
@Slf4j
public class MyAsserts {

    public static void assertAdmin(Admin admin){
        if (ObjectUtils.isEmpty(admin)) {
            throw new UnknownAccountException();
        }
        if ("2".equals(admin.getLockState())) {
            log.warn("--->账号[{}]正在审核!<---", admin.getAccount());
            throw new AccountStateException("您的帐号正在审核中...");
        } else if ("3".equals(admin.getLockState())) {
            log.warn("--->账号[{}]已被封禁!<---", admin.getAccount());
            throw new AccountStateException("您的账号已被封禁,请联络管理员...");
        }
    }

    public static boolean isEmpty(Object obj){
        if (null == obj) {
            throw new IllegalArgumentException();
        }
        return true;
    }

}
