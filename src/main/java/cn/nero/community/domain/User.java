package cn.nero.community.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/12
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class User {

    private String id;
    private String account;
    private String password;
    private String nick_name;
    private String lockState;
    private String lockEndTime;
    private String lockReason;
    private String phone;
    private String createTime;
    private String salt;
    private String resident_id;


}
