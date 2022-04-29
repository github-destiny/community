package cn.nero.community.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
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
@ExcelTarget("returnees")
public class ReturneesCityVO {

    @Excel(name = "编号")
    private String id;
    @Excel(name = "姓名")
    private String name;
    @Excel(name = "性别")
    private String gender;
    @Excel(name = "身份证号", width = 20.0)
    private String idCard;
    @Excel(name = "家庭住址", width = 25.0)
    private String address;
    @Excel(name = "返回时间", width = 20.0)
    private String applyTime;
    @ExcelIgnore
    private String from;
    @Excel(name = "手机号", width = 15.0)
    private String phone;
    @Excel(name = "接种次数")
    private String inoculationTimes;
    @Excel(name = "地区编号")
    private String cid;
    @Excel(name = "省份")
    private String province;
    @Excel(name = "城市")
    private String city;
    @Excel(name = "风险等级")
    private String level;

}
