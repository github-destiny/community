package cn.nero.community.handler;

import cn.nero.community.exception.AccountLockException;
import cn.nero.community.exception.FamilyException;
import cn.nero.community.exception.ReserveException;
import cn.nero.community.exception.ResidentException;
import cn.nero.community.exception.account.AccountAlreadyExistException;
import cn.nero.community.exception.account.AccountStateException;
import cn.nero.community.response.ResponseEnum;
import cn.nero.community.response.ResultData;
import cn.nero.community.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.exceptions.IbatisException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
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

    private static final String WARN = "WARN";
    private static final String ERROR = "ERROR";
    private static final String INFO = "INFO";

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
    public ResultData<String> ArgumentExceptionHandler(Exception e){
        e.printStackTrace();
        return ResultData.failure(ResponseEnum.FAILURE.getCode(), "参数有误,请查看日志!");
    }

    @ExceptionHandler(ResidentException.class)
    public ResultData<String> ResidentException(ResidentException e){
        e.printStackTrace();
        return ResultData.failure(ResponseEnum.FAILURE.getCode(), e.getMessage());
    }

    @ExceptionHandler(SQLException.class)
    public ResultData<String> SQLException(Exception e){
        e.printStackTrace();
        return ResultData.failure(ResponseEnum.FAILURE.getCode(), "SQL异常,请检查相应SQL");
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResultData<String> authorizationException(Exception e){
        e.printStackTrace();
        return ResultData.failure(ResponseEnum.FAILURE.getCode(), "您的账号暂时无该权限,请联系管理员提升对应权限后在进行操作!");
    }

    @ExceptionHandler(AccountLockException.class)
    public ResultData<String> accountLockException(AccountLockException e){
        e.printStackTrace();
        return ResultData.failure(ResponseEnum.FAILURE.getCode(), e.getMessage());
    }

    @ExceptionHandler(FamilyException.class)
    public ResultData<String> familyException(FamilyException e){
        e.printStackTrace();
        return ResultData.failure(ResponseEnum.FAILURE.getCode(), e.getMessage());
    }

    @ExceptionHandler(ReserveException.class)
    public ResultData<String> reserveException(ReserveException e){
        e.printStackTrace();
        return ResultData.failure(ResponseEnum.FAILURE.getCode(), e.getMessage());
    }

    @ExceptionHandler(IbatisException.class)
    public ResultData<String> ibatisException(IbatisException e){
        e.printStackTrace();
        return ResultData.failure(ResponseEnum.FAILURE.getCode(), "Mybatis出现异常!请查看日志!");
    }

    @ExceptionHandler(IOException.class)
    public ResultData<String> ioExceptionHandler(IOException e){
        e.printStackTrace();
        return ResultData.failure(ResponseEnum.FAILURE.getCode(), "IO异常,请检查后台日志!");
    }


}
