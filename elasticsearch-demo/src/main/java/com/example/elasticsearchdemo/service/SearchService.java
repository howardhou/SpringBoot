package com.example.elasticsearchdemo.service;


import java.util.HashMap;
import java.util.List;

import com.example.elasticsearchdemo.repository.BaseRepository;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.Page;

public interface SearchService {

    /**
     * 加载索引
     * @param clazz
     * @param <T>
     */
    public <T> void loadIndex(Class<T> clazz) throws Throwable;

    /**
     * 删除索引结构
     * @param clazz
     * @param <T>
     * @throws Throwable
     */
    public <T> void deleteIndexStructure(Class<T> clazz) throws Throwable;

        /**
         * 创建索引
         *
         * @param clazz
         * @param list
         * @param repository
         */
    public <T> void createIndex(Class<T> clazz, List<T> list, BaseRepository<T> repository) throws Throwable;


    /**
     * 搜索
     * @param query
     * @param repository
     * @param sortField
     * @param order
     * @param pageNo
     * @param pageSize
     * @param <T>
     * @return
     * @throws Throwable
     */
    public <T> Page<T> wildCardSearch(HashMap<String, Object> query,BaseRepository<T> repository, String sortField, SortOrder order, Integer pageNo, Integer pageSize) throws Throwable;
    /**
     * Term search
     * @param field
     * @param query
     * @param repository
     * @param <T>
     * @return
     * @throws Throwable
     */
    public <T> Page<T> termSearch(String field, String query, BaseRepository<T> repository) throws Throwable;

    /**
     * 删除索引
     * @param list
     * @param repository
     * @param <T>
     * @throws Throwable
     */
    public <T> void deleteIndex(List<T> list, BaseRepository<T> repository) throws Throwable;

    /**
     * 更新索引
     * @param clazz
     * @param list
     * @param repository
     * @param <T>
     * @throws Throwable
     */
    public <T> void updateIndex(Class<T> clazz, List<T> list, BaseRepository<T> repository) throws Throwable;





}
