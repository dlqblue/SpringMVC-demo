package com.dlqblue.repository;

import com.dlqblue.model.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

/**
 * Created by Administrator on 2017/2/25.
 */
public interface NewsRepository extends JpaRepository<NewsEntity, Integer> {

    //修改博文操作
    @Modifying
    @Transactional
    @Query("update NewsEntity news set news.title=:qTitle, news.userByUserId.id=:qUserId," +
            " news.content=:qContent, news.pushDate=:qPushDate where news.id=:qId")
    void updateNews(@Param("qTitle") String title, @Param("qUserId") int userId, @Param("qContent") String content,
                    @Param("qPushDate") Date pubDate, @Param("qId") int id);
}
