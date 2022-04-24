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
    private String staffId;
    private String name;
    private String age;
    private String gender;
    private String idCard;
    private String phone;
    private String job;
    private String state;

}
