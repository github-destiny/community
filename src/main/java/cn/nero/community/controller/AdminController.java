package cn.nero.community.controller;

import cn.nero.community.domain.Admin;
import cn.nero.community.domain.vo.PaginationVO;
import cn.nero.community.realms.CustomerToken;
import cn.nero.community.service.AdminService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/6
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public String login(String account, String password) {
        // 获取主题对象
        Subject subject = SecurityUtils.getSubject();
        String loginType = "Admin";
        // 登录
        subject.login(new CustomerToken(account, password, loginType));
        return "index";
    }

    @PostMapping("/register")
    public String register(@RequestParam("account") String account, @RequestParam("password") String password) {
        Admin admin = new Admin();
        admin.setAccount(account).setPassword(password).setLockState("2");
        adminService.saveAdmin(admin);
        return "login";
    }

    @GetMapping("/find")
    @ResponseBody
    @RequiresRoles("admin")
    public PaginationVO<Admin> findAdminByCondition(
            Admin admin,
            @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        int skipCount = (pageNo - 1) * pageSize;
        return adminService.findAdminByCondition(admin, skipCount, pageSize);
    }

    @GetMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }

    @PostMapping("/edit/password")
    @ResponseBody
    public String editPassword(String account, String oldPassword, String newPassword){
        adminService.editPassword(account, oldPassword, newPassword);
        return "success";
    }

    @GetMapping("/approval")
    @ResponseBody
    @RequiresRoles("admin")
    public void approval(@RequestParam("ids") List<String> ids){
        adminService.approval(ids);
    }
}
