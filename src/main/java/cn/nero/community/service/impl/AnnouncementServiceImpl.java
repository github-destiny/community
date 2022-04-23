package cn.nero.community.service.impl;

import cn.nero.community.domain.Announcement;
import cn.nero.community.domain.vo.PaginationVO;
import cn.nero.community.mappers.AnnouncementMapper;
import cn.nero.community.service.AnnouncementService;
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
@Transactional
@Slf4j
public class AnnouncementServiceImpl implements AnnouncementService {

    @Autowired
    private AnnouncementMapper announcementMapper;

    @Override
    public void saveAnnouncement(Announcement announcement) {
        announcement.setCreateTime(DateTimeUtil.getTime());
        announcementMapper.saveAnnouncement(announcement);
    }

    @Override
    public void updateAnnouncement(Announcement announcement) {
        announcementMapper.updateAnnouncement(announcement);
    }

    @Override
    public void deleteAnnouncement(String id) {
        announcementMapper.deleteAnnouncement(id);
    }

    @Override
    public PaginationVO<Announcement> findAnnouncements(Integer skipCount, Integer pageSize) {
        List<Announcement> dataList = announcementMapper.findAllAnnouncements(skipCount, pageSize);
        int total = announcementMapper.getTotal();
        return new PaginationVO<>(dataList, total);
    }
}
