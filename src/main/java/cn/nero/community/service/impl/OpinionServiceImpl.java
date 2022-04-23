package cn.nero.community.service.impl;

import cn.nero.community.domain.Opinion;
import cn.nero.community.domain.vo.OpinionVO;
import cn.nero.community.domain.vo.PaginationVO;
import cn.nero.community.mappers.OpinionMapper;
import cn.nero.community.service.OpinionService;
import cn.nero.community.utils.DateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/21
 */
@Service
@Slf4j
@Transactional
public class OpinionServiceImpl implements OpinionService {

    @Autowired
    private OpinionMapper opinionMapper;

    @Override
    public void saveOpinion(Opinion opinion) {
        opinion.setCreateTime(DateTimeUtil.getTime());
        opinionMapper.saveOpinion(opinion);
    }

    @Override
    public PaginationVO<OpinionVO> findOpinions(Integer skipCount, Integer pageSize) {
        List<OpinionVO> dataList = opinionMapper.findOpinions(skipCount, pageSize);
        int total = opinionMapper.getTotal();
        return new PaginationVO<>(dataList, total);
    }
}
