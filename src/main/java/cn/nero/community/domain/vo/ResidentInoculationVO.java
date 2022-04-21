package cn.nero.community.domain.vo;

import cn.nero.community.domain.Inoculation;
import cn.nero.community.domain.Resident;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/12
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ResidentInoculationVO {

    private Resident resident;

    private Inoculation inoculation;

}
