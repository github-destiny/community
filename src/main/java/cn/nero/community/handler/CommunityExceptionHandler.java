package cn.nero.community.handler;

import cn.nero.community.exception.ResidentException;
import cn.nero.community.exception.account.AccountAlreadyExistException;
import cn.nero.community.exception.account.AccountStateException;
import cn.nero.community.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.Map;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/6
 */
@RestControllerAdvice
@Slf4j
public class CommunityExceptionHandler {

    @ExceptionHandler(AccountStateException.class)
    public String accountStateHandler(Exception e){
        e.printStackTrace();
        return "您的账号正在审核中!请耐心等候";
    }

    @ExceptionHandler(AccountAlreadyExistException.class)
    public String accountExistHandler(Exception e){
        e.printStackTrace();
        return "您要注册的账号已经存在!";
    }

    @ExceptionHandler(IncorrectCredentialsException.class)
    public String accountOrPasswordException(Exception e){
        e.printStackTrace();
        return "密码错误!";
    }

    @ExceptionHandler(UnknownAccountException.class)
    public String unknownAccountHandler(Exception e){
        e.printStackTrace();
        return "账号不存在!";
    }

    @ExceptionHandler({IllegalArgumentException.class, NullPointerException.class})
    public Map<String, Object> ArgumentExceptionHandler(Exception e){
        e.printStackTrace();
        return ResponseUtil.getResponse("参数有误,请查看日志!", "error");
    }

    @ExceptionHandler(ResidentException.class)
    public String ResidentException(Exception e){
        e.printStackTrace();
        return e.getMessage();
    }

    @ExceptionHandler(SQLException.class)
    public Map<String, Object> SQLException(Exception e){
        e.printStackTrace();
        return ResponseUtil.getResponse("SQL异常,请检查相应SQL", "error");
    }

    @ExceptionHandler(AuthorizationException.class)
    public Map<String, Object> authorizationException(Exception e){
        e.printStackTrace();
        return ResponseUtil.getResponse("您的账号暂时无该权限,请联系管理员提升对应权限后在进行操作!", "warn");
    }

}
