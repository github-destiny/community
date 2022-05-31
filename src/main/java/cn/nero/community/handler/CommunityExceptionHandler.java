package cn.nero.community.handler;

import cn.nero.community.exception.*;
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
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
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
public class CommunityExceptionHandler extends ResponseEntityExceptionHandler {

    private final static Integer ERROR_CODE = ResponseEnum.FAILURE.getCode();

    private final static Integer SUCCESS_CODE = ResponseEnum.SUCCESS.getCode();

    //@ExceptionHandler(Exception.class)
    //public ResultData<?> allExceptionHandler(HttpServletRequest request, Throwable ex){
    //    HttpStatus status = getStatus(request);
    //    return ResultData.failure(status.value(), ex.getMessage());
    //}
    //
    //private HttpStatus getStatus(HttpServletRequest request){
    //    Integer code = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
    //    HttpStatus status = HttpStatus.resolve(code);
    //    return (status != null) ? status : HttpStatus.INTERNAL_SERVER_ERROR;
    //}

    @ExceptionHandler(AccountStateException.class)
    public ResultData<String> accountStateHandler(Exception e){
        e.printStackTrace();
        return ResultData.failure(ERROR_CODE, "您的账号正在审核中!请耐心等候");
    }

    @ExceptionHandler(AccountAlreadyExistException.class)
    public ResultData<String> accountExistHandler(Exception e){
        e.printStackTrace();
        return ResultData.failure(ERROR_CODE, "您要注册的账号已经存在!");
    }

    @ExceptionHandler(IncorrectCredentialsException.class)
    public ResultData<String> accountOrPasswordException(Exception e){
        e.printStackTrace();
        return ResultData.failure(ERROR_CODE, "您输入的密码有误!");
    }

    @ExceptionHandler(UnknownAccountException.class)
    public ResultData<String> unknownAccountHandler(Exception e){
        e.printStackTrace();
        return ResultData.failure(ERROR_CODE, "账号不存在!");
    }

    @ExceptionHandler({IllegalArgumentException.class, NullPointerException.class})
    public ResultData<String> ArgumentExceptionHandler(Exception e){
        e.printStackTrace();
        return ResultData.failure(ERROR_CODE, "参数有误,请查看日志!");
    }

    @ExceptionHandler(ResidentException.class)
    public ResultData<String> ResidentException(ResidentException e){
        e.printStackTrace();
        return ResultData.failure(ERROR_CODE, e.getMessage());
    }

    @ExceptionHandler(SQLException.class)
    public ResultData<String> SQLException(Exception e){
        e.printStackTrace();
        return ResultData.failure(ERROR_CODE, "SQL异常,请检查相应SQL");
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResultData<String> authorizationException(Exception e){
        e.printStackTrace();
        return ResultData.failure(ERROR_CODE, "您的账号暂时无该权限,请联系管理员提升对应权限后在进行操作!");
    }

    @ExceptionHandler(AccountLockException.class)
    public ResultData<String> accountLockException(AccountLockException e){
        e.printStackTrace();
        return ResultData.failure(ERROR_CODE, e.getMessage());
    }

    @ExceptionHandler(FamilyException.class)
    public ResultData<String> familyException(FamilyException e){
        e.printStackTrace();
        return ResultData.failure(ERROR_CODE, e.getMessage());
    }

    @ExceptionHandler(ReserveException.class)
    public ResultData<String> reserveException(ReserveException e){
        e.printStackTrace();
        return ResultData.failure(ERROR_CODE, e.getMessage());
    }

    @ExceptionHandler(IbatisException.class)
    public ResultData<String> ibatisException(IbatisException e){
        e.printStackTrace();
        return ResultData.failure(ERROR_CODE, "Mybatis出现异常!请查看日志!");
    }

    @ExceptionHandler(IOException.class)
    public ResultData<String> ioExceptionHandler(IOException e){
        e.printStackTrace();
        return ResultData.failure(ERROR_CODE, "IO异常,请检查后台日志!");
    }

    @ExceptionHandler(ReturneesException.class)
    public ResultData<String> returneesExceptionHandler(ReturneesException e){
        e.printStackTrace();
        return ResultData.failure(ERROR_CODE, e.getMessage());
    }


}
