package com.example.elasticsearchdemo;

import com.example.elasticsearchdemo.es.DocBean;
import com.example.elasticsearchdemo.es.IElasticService;
import com.example.elasticsearchdemo.service.ContentSearchService;
import lombok.extern.log4j.Log4j2;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

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


    @Autowired
    private ContentSearchService contentSearchService;

    @GetMapping("/initContent")
    public void initContent(){
        List<ContentDomain> list =new ArrayList<>();

        {
            ContentDomain contentDomain = new ContentDomain();
            contentDomain.setUuid("wqerfasfasdfaf");
            contentDomain.setContent("在发开过程中，追踪请求和监控请求与返回数据是我们经常会需要的一个需求，在Mac端，Charles是一款非常易用的抓包工具。");
            contentDomain.setContentType(1);
            contentDomain.setCreateTime(new Date());
            contentDomain.setCreator("13916536025");
            contentDomain.setTitle("XX8064");
            list.add(contentDomain);
        }

        {
            ContentDomain contentDomain = new ContentDomain();
            contentDomain.setUuid("nllmjytjmtoyhtyi");
            contentDomain.setContent("在软件安装完成后，我们已经可以在mac上截取一般的网络请求了，打开软件，将Proxy设置中的Mac OS X Proxy勾选");
            contentDomain.setContentType(1);
            contentDomain.setCreateTime(new Date());
            contentDomain.setCreator("12983454321");
            contentDomain.setTitle("XX8097");
            list.add(contentDomain);
        }

        {
            ContentDomain contentDomain = new ContentDomain();
            contentDomain.setUuid("aqiweufnsaifawo");
            contentDomain.setContent("下载好压缩包后，解压打开，将软件包拖入应用程序文件夹中，这时候一个原版的软件就可以让我们使用，只是有一个试用期");
            contentDomain.setContentType(1);
            contentDomain.setCreateTime(new Date());
            contentDomain.setCreator("18923121243");
            contentDomain.setTitle("XX7475");
            list.add(contentDomain);
        }

        try {
            contentSearchService.createContentIndex(list);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @GetMapping("/find/{text}")
    public Iterator<ContentDomain> find(@PathVariable String text){
        HashMap<String,Object> query = new HashMap<>();
        query.put("content",text);

        ContentSearchResult result = null;
        try {
            result = contentSearchService.wildCardSearchContent(query,"createTime", SortOrder.DESC,1,2);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        return result.getResult().iterator();
    }
}
