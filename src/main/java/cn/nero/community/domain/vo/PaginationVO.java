package cn.nero.community.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

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
public class PaginationVO<T> {

    private List<T> dataList;

    private int total;

}
