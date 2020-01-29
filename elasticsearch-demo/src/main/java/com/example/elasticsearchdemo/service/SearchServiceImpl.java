package com.example.elasticsearchdemo.service;

import com.example.elasticsearchdemo.repository.BaseRepository;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ElasticsearchTemplate template;

    public void hello()
    {
        logger.info("hello");
    }

    @Override
    public <T> void loadIndex(Class<T> clazz) throws Throwable{
        try {
            if(!template.indexExists(clazz)) {
                template.createIndex(clazz);
                template.putMapping(clazz);
            }
        }catch (Throwable e)
        {
            throw new Throwable(e);
        }
    }

    @Override
    public <T> void deleteIndexStructure(Class<T> clazz) throws Throwable {
        try {
                template.deleteIndex(clazz);
        }catch (Throwable e)
        {
            throw new Throwable(e);
        }
    }

    @Override
    public <T> void createIndex(Class<T> clazz, List<T> list, BaseRepository<T> repository) throws Throwable
    {
        try {
            loadIndex(clazz);

            repository.saveAll(list);
        }catch (Throwable e)
        {
            throw new Throwable(e);
        }
    }

    @Override
    public <T> Page<T> wildCardSearch(HashMap<String,Object> query, BaseRepository<T> repository, String sortField, SortOrder order,Integer pageNo, Integer pageSize) throws Throwable
    {
        try {
            NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
            BoolQueryBuilder bqb = QueryBuilders.boolQuery();
            PageRequest pageRequest = PageRequest.of(pageNo-1, pageSize);
            Object value;
            for(String field: query.keySet())
            {
                value = query.get(field);
                bqb.should(QueryBuilders.matchPhraseQuery(field, value));
            }

            BoolQueryBuilder bqb2 = QueryBuilders.boolQuery();
            bqb2.must(QueryBuilders.termQuery("delFlag", false));
            bqb2.must(bqb);

            queryBuilder.withQuery(bqb2).withSort(new FieldSortBuilder(sortField).order(order)).withPageable(pageRequest);

            Page<T> items = repository.search(queryBuilder.build());

            return items;
        }catch (Throwable e)
        {
            throw new Throwable(e);
        }
    }

    @Override
    public <T> Page<T> termSearch(String field,String query,BaseRepository<T> repository) throws Throwable
    {
        try {
            NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
            queryBuilder.withQuery(QueryBuilders.termQuery(field, query));

            Page<T> items = repository.search(queryBuilder.build());

            return items;
        }catch (Throwable e)
        {
            throw new Throwable(e);
        }
    }

    @Override
    public <T> void deleteIndex(List<T> list, BaseRepository<T> repository) throws Throwable
    {
        try
        {
            repository.deleteAll(list);
        }catch (Throwable e)
        {
            throw new Throwable(e);
        }
    }

    @Override
    public <T> void updateIndex(Class<T> clazz, List<T> list, BaseRepository<T> repository) throws Throwable
    {
        try
        {
            createIndex(clazz,list,repository);
        }catch (Throwable e)
        {
            throw new Throwable(e);
        }
    }

}
