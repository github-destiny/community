package cn.nero.community.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/23
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Accessors(chain = true)
public class Count {

    private String days;
    private String num;

}
