package cn.nero.community.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.nero.community.domain.Reserve;
import cn.nero.community.domain.Resident;
import cn.nero.community.domain.vo.ResidentReserveVO;
import cn.nero.community.service.ReserveService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
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

    @GetMapping("/update/time")
    public void updateReserveTime(String time){

    }

    @PostMapping("/reserve")
    public ResidentReserveVO reverse(@RequestParam("idCard") String idCard,
                        @RequestParam(value = "times", required = false) String times){
        return reserveService.reverse(idCard, times);
    }

    @GetMapping("/find/info")
    public Map<String, Object> findReserveInfo(@RequestParam(value = "time", required = false) String time,
                                               @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                                               @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize){
        return reserveService.getReserveInfo(time, (pageNo - 1) * pageSize, pageSize);
    }

    @GetMapping("/export")
    public void export(HttpServletResponse response,@RequestParam(value = "time",required = false) String time) throws IOException {
        response.addHeader("content-disposition", "attachment;fileName=" + URLEncoder.encode("预约信息.xls", StandardCharsets.UTF_8));
        response.setHeader("Access-Control-Expose-Headers", "content-disposition");
        response.setHeader("content-disposition", "attachment;fileName=" + URLEncoder.encode("预约信息.xls", StandardCharsets.UTF_8));
        Workbook workbook = null;
        ServletOutputStream oos = null;
        try {
            List<ResidentReserveVO> allInfo = reserveService.findAllReserveInfo(time);
            workbook = ExcelExportUtil.exportExcel(new ExportParams("预约接种信息", "预约信息"), ResidentReserveVO.class, allInfo);
            oos = response.getOutputStream();
            workbook.write(oos);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                workbook.close();
            }
            if (oos != null) {
                oos.close();
            }
        }
    }

    @GetMapping("/update/appointment")
    public String updateAppointment(@RequestParam("time") String time) {
        reserveService.saveAppointment(time);
        return "更新成功";
    }

}
