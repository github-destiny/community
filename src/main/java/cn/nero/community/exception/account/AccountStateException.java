package cn.nero.community.exception.account;

import cn.nero.community.exception.CommunityException;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/6
 */
public class AccountStateException extends CommunityException {

    public AccountStateException() {
        super();
    }

    public AccountStateException(String message) {
        super(message);
    }
}
