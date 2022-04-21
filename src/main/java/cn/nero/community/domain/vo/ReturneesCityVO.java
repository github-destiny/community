package cn.nero.community.domain.vo;

import cn.nero.community.domain.City;
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
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ReturneesCityVO {

    private String id;
    private String name;
    private String gender;
    private String idCard;
    private String address;
    private String applyTime;
    private String from;
    private String phone;
    private String inoculationTimes;
    private String cid;
    private String province;
    private String city;
    private String level;

}
