package cn.nero.community.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/13
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Returnees {

    private String id;
    private String name;
    private String gender;
    private String idCard;
    private String address;
    private String applyTime;
    private String from;
    private String phone;
    private String inoculationTimes;
    private String endTime;
    private String testTime;
    private String result;

}
