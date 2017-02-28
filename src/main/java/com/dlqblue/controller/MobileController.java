package com.dlqblue.controller;

import com.dlqblue.model.NewsEntity;
import com.dlqblue.repository.NewsRepository;
import com.dlqblue.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/2/28.
 */
@RestController
public class MobileController {

    @Autowired
    NewsRepository newsRepository;

    @Autowired
    UserRepository userRepository;

    @ResponseBody
    @RequestMapping(value = "/admin/news/get/{id}" , method = RequestMethod.GET)
    public NewsEntity getNews(@PathVariable("id") int id) {
//        System.out.println(newsEntity);
        return newsRepository.findOne(id);
    }
}
