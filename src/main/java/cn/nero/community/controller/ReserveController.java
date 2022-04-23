package cn.nero.community.controller;

import cn.nero.community.domain.Reserve;
import cn.nero.community.service.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/22
 */
@RestController
@RequestMapping("/reserve")
public class ReserveController {

    @Autowired
    private ReserveService reserveService;

    @PostMapping("/save")
    public void save(@RequestParam("residentId") String residentId){
        Reserve reserve = new Reserve();
        reserve.setResident_id(residentId);
        reserveService.saveReserve(reserve);
    }

    @PostMapping("/revoke")
    public void update(@RequestParam("residentId") String residentId){
        reserveService.revokeReserve(residentId);
    }

    @PostMapping("/find")
    public Map<String, Object> checkReserve(@RequestParam("idCard") String idCard){
        return reserveService.checkReserve(idCard);
    }

    @GetMapping("/get/appointment")
    public Map<String, Object> findAppointment(){
        return reserveService.findAppointment();
    }

    @GetMapping("/get/total")
    public Map<String, Object> getTotal(String time){
        return reserveService.statistics(time);
    }

}
