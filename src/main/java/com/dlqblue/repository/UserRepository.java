package com.dlqblue.repository;

import com.dlqblue.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2017/2/23.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Modifying //修改操作
    @Transactional //事务性操作
    //定义查询，@Param注解用于提取参数
    @Query("update UserEntity us set us.nickname=:qNickname, us.password=:qPassword where us.id=:qId")
    void updateUser(@Param("qNickname") String nickname, @Param("qPassword") String password, @Param("qId") Integer id);
}
