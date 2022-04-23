package cn.nero.community.mappers;

import cn.nero.community.domain.Opinion;
import cn.nero.community.domain.vo.OpinionVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/21
 */
@Mapper
@Repository
public interface OpinionMapper {

    void saveOpinion(Opinion opinion);

    List<OpinionVO> findOpinions(@Param("skipCount") Integer skipCount, @Param("pageSize") Integer pageSize);

    int getTotal();

}
