package cn.nero.community.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/6
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Admin {

    private String id;
    private String account;
    private String password;
    private String salt;
    private String staff_id;
    private String resident_id;
    private String lockState;
    private String role;
    private String createTime;


}
