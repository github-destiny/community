package cn.nero.community.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/22
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ResidentReserveVO {

    private String reserveId;
    private String reserveNum;
    private String appointment;
    private String residentId;
    private String name;
    private String age;
    private String gender;
    private String idCard;
    private String phone;

}
