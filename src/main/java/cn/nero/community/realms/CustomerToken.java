package cn.nero.community.realms;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/20
 */
public class CustomerToken extends UsernamePasswordToken {

    private String loginType;

    public CustomerToken(final String username, final String password, String loginType){
        super(username, password);
        this.loginType = loginType;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }
}
