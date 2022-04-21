package cn.nero.community.realms;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/20
 */
public class CustomerModularRealmAuthenticator extends ModularRealmAuthenticator {

    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 判断getRealms返回的是否为空
        assertRealmsConfigured();
        // 将token转换为自定义的
        CustomerToken customerToken = (CustomerToken) authenticationToken;
        String loginType = customerToken.getLoginType();
        // 获取所有的realm
        Collection<Realm> realms = getRealms();
        // 定义真正执行的realm集合
        Collection<Realm> typeRealms = new ArrayList<>();
        for (Realm realm : realms) {
            if (realm.getName().contains(loginType)){
                typeRealms.add(realm);
            }
        }
        // 判断是几个realm可以执行
        if (typeRealms.size() == 1) {
            return doSingleRealmAuthentication(typeRealms.iterator().next(), customerToken);
        } else {
            return doMultiRealmAuthentication(typeRealms, customerToken);
        }
    }
}
