package com.dlqblue.controller;

import com.dlqblue.json.JsonMapper;
import com.dlqblue.model.NewsEntity;
import com.dlqblue.repository.NewsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * Created by Administrator on 2017/2/28.
 */
@RestController
public class MobileController {

    @Autowired
    NewsRepository newsRepository;

//    @ResponseBody
//    @RequestMapping(value = "/admin/news/get/{id}", method = RequestMethod.GET)
//    public String getNews(@PathVariable("id") int id) throws IOException {
//        NewsEntity newsEntity = newsRepository.findOne(id);
//        ObjectMapper objectMapper = new ObjectMapper();
//        String json = objectMapper.writeValueAsString(newsEntity);
//        return json;
//    }

    // 根据id获取news
    @ResponseBody
    @RequestMapping(value = "/admin/news/get/{id}", method = RequestMethod.GET)
    public String getNews(@PathVariable("id") int id) throws IOException {
        NewsEntity newsEntity = newsRepository.findOne(id);
        // 去掉密码
        newsEntity.getUserByUserId().setPassword("******");
        return JsonMapper
                .nonDefaultMapper()
                .toJson(newsEntity);
    }
}
