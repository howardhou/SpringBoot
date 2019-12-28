package com.example.elasticsearchdemo;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/elastic")
public class ElasticController {

    @Autowired
    private IElasticService elasticService;

    @GetMapping("/init")
    public void init(){
        elasticService.createIndex();
        List<DocBean> list =new ArrayList<>();
        list.add(new DocBean(1L,"13916536025","XX8064","在发开过程中，追踪请求和监控请求与返回数据是我们经常会需要的一个需求，在Mac端，Charles是一款非常易用的抓包工具。",1));
        list.add(new DocBean(2L,"18923121243","XX7475","下载好压缩包后，解压打开，将软件包拖入应用程序文件夹中，这时候一个原版的软件就可以让我们使用，只是有一个试用期",1));
        list.add(new DocBean(3L,"12983454321","XX8097","在软件安装完成后，我们已经可以在mac上截取一般的网络请求了，打开软件，将Proxy设置中的Mac OS X Proxy勾选",1));

        elasticService.saveAll(list);
    }

    @GetMapping("/all")
    public Iterator<DocBean> all(){
        return elasticService.findAll();
    }
}
