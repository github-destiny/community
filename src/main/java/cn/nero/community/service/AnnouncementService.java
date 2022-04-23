package cn.nero.community.service;

import cn.nero.community.domain.Announcement;
import cn.nero.community.domain.vo.PaginationVO;

/**
 * @author Nero Claudius
 * @version 1.0.0
 * @Date 2022/4/21
 */
public interface AnnouncementService {

    void saveAnnouncement(Announcement announcement);

    void updateAnnouncement(Announcement announcement);

    void deleteAnnouncement(String id);

    PaginationVO<Announcement> findAnnouncements(Integer skipCount, Integer pageSize);

}
