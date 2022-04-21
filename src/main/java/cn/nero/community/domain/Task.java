package cn.nero.community.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/16
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Task {

    private String id;
    private String returnees_id;
    private String strategy;
    private String content;
    private String staff_id;
    private String createTime;
    private String acceptTime;
    private String state;
    private String updateTime;

}
