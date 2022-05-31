package cn.nero.community.controller;

import cn.nero.community.domain.Resident;
import cn.nero.community.domain.User;
import cn.nero.community.domain.vo.PaginationVO;
import cn.nero.community.domain.vo.UserVO;
import cn.nero.community.realms.CustomerToken;
import cn.nero.community.service.ResidentService;
import cn.nero.community.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/19
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ResidentService residentService;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestParam("account") String account,
                        @RequestParam("password") String password){
        String loginType = "User";
        CustomerToken token = new CustomerToken(account, password, loginType);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        // 在此注入user信息
        User user = userService.findUserByAccount(account);
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "登陆成功");
        map.put("account", account);
        map.put("role", "user");
        map.put("residentId", user.getResident_id());
        map.put("userId", user.getId());
        return map;
    }

    @GetMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "已成功退出登录";
    }

    @PostMapping("/register")
    public Map<String, Object> saveUser(User user){
        return userService.saveUser(user);
    }

    @PostMapping("/update")
    public void updateUser(User user){
        userService.updateUser(user);
    }

    @PostMapping("/update/password")
    public String updatePassword(@RequestParam("oldPassword") String oldPassword,
                               @RequestParam("newPassword") String newPassword,
                               @RequestParam("account") String account){
        userService.updatePassword(oldPassword, newPassword, account);
        return "更新成功";
    }

    @GetMapping("/delete/{condition}")
    @RequiresRoles("admin")
    public void deleteUser(@PathVariable("condition") String condition){
        userService.deleteUser(condition);
    }

    @GetMapping("/find")
    public PaginationVO<UserVO> findUserVO(User user,
                                           @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize){
        int skipCount = (pageNo - 1) * pageSize;
        return userService.findUserVO(user, skipCount, pageSize);
    }

    @GetMapping("/ban/{account}")
    @RequiresRoles(value = {"admin", "staff"}, logical = Logical.OR)
    public void banUser(@PathVariable("account") String account){
        userService.banAccount(account);
    }

    @GetMapping("/ban/batch")
    public void batchBan(@RequestParam("accounts") List<String> accounts){
        userService.batchBanAccount(accounts, "不当发言");
    }

    @GetMapping("/unblock/batch")
    public void batchUnBlockAccount(@RequestParam("accounts") List<String> accounts){
        userService.batchUnBlockAccount(accounts);
    }

    @GetMapping("/get/all")
    public Map<String, Object> getAllResidentInfo(@RequestParam("residentId") String residentId){
        return residentService.findAllResidentInfo(residentId);
    }

}
