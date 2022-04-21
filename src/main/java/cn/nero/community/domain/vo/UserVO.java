package cn.nero.community.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/19
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserVO {

    private String userId;
    private String account;
    private String nick_name;
    private String lockState;
    private String createTime;
    private String residentId;
    private String idCard;
    private String realName;
    private String age;
    private String gender;
    private String address;
    private String phone;
    private String state;

}
