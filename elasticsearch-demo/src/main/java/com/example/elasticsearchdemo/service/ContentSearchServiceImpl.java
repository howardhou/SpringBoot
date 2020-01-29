package com.example.elasticsearchdemo.service;

import com.example.elasticsearchdemo.ContentDomain;
import com.example.elasticsearchdemo.ContentSearchResult;
import com.example.elasticsearchdemo.repository.ContentRepository;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class ContentSearchServiceImpl extends SearchServiceImpl implements ContentSearchService {

    @Autowired
    ContentRepository contentRepository;

    @Override
    public ContentSearchResult wildCardSearchContent(HashMap<String,Object> query, String sortField, SortOrder order, Integer pageNo, Integer pageSize) throws Throwable
    {
        Page<ContentDomain> result =  wildCardSearch(query,contentRepository,sortField,order,pageNo,pageSize);
//        PageInfo<ContentDomain> pageInfo = PageInfo.fromPage(result);

//        List<ContentDomain> contentListForSearchModels = new ArrayList<>();
//        for (ContentDomain row : pageInfo.getRows()) {
//            Content content = new Content();
//            Member member = new Member();
//            member.setNickname(row.getNickName());
//            member.setPhoto(row.getPhoto());
//            content.setMember(member);
//            BeanUtils.copyNullProperties(row, content);
//            contentListForSearchModels.add(content);
//        }
        ContentSearchResult searchResult = new ContentSearchResult();
//        searchResult.setItemCount(pageInfo.getTotal());
//        searchResult.setPageCount(pageInfo.getPages());
        searchResult.setResult(result.getContent());
        searchResult.setPageNo(pageNo);

        return searchResult;
    }

    @Override
    public void createContentIndex(List<ContentDomain> contents) throws Throwable
    {
//        List<ContentDomain> list = new ArrayList<>();
//        for (ContentDomain row : contents) {
//            ContentDomain contentDomain = new ContentDomain();
//            BeanUtils.copyNullProperties(row, contentDomain);
//            if(row.getMember() != null) {
//                contentDomain.setNickName(row.getMember().getNickname());
//                contentDomain.setPhoto(row.getMember().getPhoto());
//            }
//            list.add(contentDomain);
//        }

         createIndex(ContentDomain.class, contents, contentRepository);
    }

}
