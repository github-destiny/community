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
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class TaskVO {

    private String id;
    private String strategy;
    private String content;
    private String state;
    private String createTime;
    private String acceptTime;
    private String returneesId;
    private String name;
    private String gender;
    private String idCard;
    private String address;
    private String applyTime;
    private String phone;
    private String inoculationTimes;
    private String endTime;
    private String province;
    private String city;

}
