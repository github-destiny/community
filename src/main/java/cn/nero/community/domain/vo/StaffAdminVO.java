package cn.nero.community.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/24
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class StaffAdminVO {

    private String adminId;
    private String account;
    private String createTime;
    private String role;
    private String lockState;
    private String staffId;
    private String name;
    private String age;
    private String gender;
    private String idCard;
    private String phone;
    private String job;
    private String state;

    public void setLockState(String lockState) {
        if ("1".equals(lockState)) {
            lockState = "正常使用";
        } else if("2".equals(lockState)) {
            lockState = "审核中";
        }
        this.lockState = lockState;
    }

    public void setState(String lockState){
        if ("1".equals(lockState)) {
            lockState = "正常使用";
        } else if("2".equals(lockState)) {
            lockState = "审核中";
        } else if ("3".equals(lockState)){
            lockState = "封禁";
        } else if ("4".equals(lockState)){
            lockState = "离职";
        }
        this.lockState = lockState;
    }



}
