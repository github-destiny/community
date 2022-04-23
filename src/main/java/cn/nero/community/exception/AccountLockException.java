package cn.nero.community.exception;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/21
 */
public class AccountLockException extends CommunityException{

    public AccountLockException() {
        super();
    }

    public AccountLockException(String message) {
        super(message);
    }
}
