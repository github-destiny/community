package cn.nero.community.controller;

import cn.nero.community.domain.Admin;
import cn.nero.community.domain.Staff;
import cn.nero.community.domain.vo.PaginationVO;
import cn.nero.community.realms.CustomerToken;
import cn.nero.community.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
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
 * @Date 2022/4/6
 */
@RestController
@RequestMapping("/admin")
@Slf4j
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestParam(value = "account", required = false) String account,
                                     @RequestParam(value = "password", required = false) String password){
        log.info("account : {}", account);
        Map<String, Object> result = new HashMap<>();
        // 获取主题对象
        Subject subject = SecurityUtils.getSubject();
        String loginType = "Admin";
        // 登录
        subject.login(new CustomerToken(account, password, loginType));
        // 获取角色信息
        String role = adminService.findRoleByAccount(account);
        result.put("account", account);
        result.put("role", role);
        result.put("msg", "登陆成功");
        // 获取账号信息
        Staff staff = adminService.findStaffByAccount(account);
        result.put("sid", staff.getId());
        return result;
    }

    @PostMapping("/register")
    public Map<String, Object> register(@RequestParam("account") String account,
                                        @RequestParam("password") String password,
                                        @RequestParam("staffId") String staffId) {
        Admin admin = new Admin();
        admin.setAccount(account).setPassword(password).setLockState("2").setStaff_id(staffId);
        Map<String, Object> map = adminService.saveAdmin(admin);
        map.put("msg", "注册成功,您的账号已提交进行审核,请耐心等待.");
        return map;
    }

    @GetMapping("/find")
    //@RequiresRoles("admin")
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
        return "已成功退出登录.";
    }

    @PostMapping("/edit/password")
    @ResponseBody
    public String editPassword(String account, String oldPassword, String newPassword){
        adminService.editPassword(account, oldPassword, newPassword);
        return "修改成功";
    }

    @GetMapping("/approval")
    //@RequiresRoles("admin")
    public String approval(@RequestParam("ids") List<String> ids){
        adminService.approval(ids);
        return "操作成功";
    }

    /**
     * 修改账号,通常是封禁与权限提升,只能由admin操作
     */
    @GetMapping("/edit")
    public void editAdmin(Admin admin){
        log.info("正在实现中...");
    }

    /**
     * 封禁管理员账号,通过id或账号信息
     * @param condition
     */
    @GetMapping("/toggle")
    @RequiresRoles(value = "admin")
    public void ban(@RequestParam("condition") String condition){
        adminService.ban(condition);
    }
}
