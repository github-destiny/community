package cn.nero.community.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/18
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ResidentNucleicVO {

    private String id;
    private String idCard;
    private String name;
    private String age;
    private String gender;
    private String address;
    private String phone;
    private String result;
    private String time;

}
