package cn.nero.community.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/7
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ExcelTarget("resident")
public class Resident {

    @Excel(name = "编号")
    private String id;
    @Excel(name = "身份证号", width = 20.0)
    private String idCard;
    @Excel(name = "姓名")
    private String name;
    @Excel(name = "年龄")
    private String age;
    @Excel(name = "性别")
    private String gender;
    @Excel(name = "家庭住址", width = 25.0)
    private String address;
    @Excel(name = "联系方式", width = 15.0)
    private String phone;
    @Excel(name = "状态", replace = {"5_常驻"})
    private String state;


}
