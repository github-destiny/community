package cn.nero.community.mappers;

import cn.nero.community.domain.Announcement;
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
public interface AnnouncementMapper {

    /**
     * 添加一个公告
     * @param announcement
     */
    void saveAnnouncement(Announcement announcement);

    /**
     * 更新公告信息
     * @param announcement
     */
    void updateAnnouncement(Announcement announcement);

    /**
     * 删除一个公告
     * @param id
     */
    void deleteAnnouncement(String id);

    /**
     * 查看所有公告
     * @return
     */
    List<Announcement> findAllAnnouncements(@Param("skipCount") Integer skipCount, @Param("pageSize") Integer pageSize);

    /**
     * 获取公告总数
     * @return
     */
    int getTotal();


}
