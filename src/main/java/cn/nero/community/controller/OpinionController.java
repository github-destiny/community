package cn.nero.community.controller;

import cn.nero.community.domain.Opinion;
import cn.nero.community.domain.vo.OpinionVO;
import cn.nero.community.domain.vo.PaginationVO;
import cn.nero.community.service.OpinionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/21
 */
@RestController
@RequestMapping("/opinion")
public class OpinionController {

    @Autowired
    private OpinionService opinionService;

    @PostMapping("/save")
    public void save(Opinion opinion){
        opinionService.saveOpinion(opinion);
    }

    @GetMapping("/find")
    public PaginationVO<OpinionVO> findOpinions(@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                                                @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return opinionService.findOpinions((pageNo - 1) * pageSize, pageSize);
    }
}
