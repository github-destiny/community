package cn.nero.community.service;

import cn.nero.community.domain.Opinion;
import cn.nero.community.domain.vo.OpinionVO;
import cn.nero.community.domain.vo.PaginationVO;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/21
 */
public interface OpinionService {

    void saveOpinion(Opinion opinion);

    PaginationVO<OpinionVO> findOpinions(Integer skipCount, Integer pageSize);

}
