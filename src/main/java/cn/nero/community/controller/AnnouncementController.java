package cn.nero.community.controller;

import cn.nero.community.domain.Announcement;
import cn.nero.community.domain.vo.PaginationVO;
import cn.nero.community.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/21
 */
@RestController
@RequestMapping("/announcement")
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;

    @PostMapping("/save")
    public void saveAnnouncement(Announcement announcement){
        announcementService.saveAnnouncement(announcement);
    }

    @PostMapping("/update")
    public void updateAnnouncement(Announcement announcement){
        announcementService.updateAnnouncement(announcement);
    }

    @GetMapping("/delete/{id}")
    public void deleteAnnounceme0nt(@PathVariable("id") String id){
        announcementService.deleteAnnouncement(id);
    }

    @GetMapping("/find")
    public PaginationVO<Announcement> findAnnouncement(@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                                                       @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize){
        return announcementService.findAnnouncements((pageNo - 1) * pageSize, pageSize);
    }

}
