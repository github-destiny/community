package cn.nero.community.exception;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/6
 */
public class CommunityException extends RuntimeException{

    public CommunityException() {
        super();
    }

    public CommunityException(String message) {
        super(message);
    }
}
