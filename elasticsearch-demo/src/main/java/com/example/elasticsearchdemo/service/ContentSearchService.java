package com.example.elasticsearchdemo.service;


import com.example.elasticsearchdemo.ContentDomain;
import com.example.elasticsearchdemo.ContentSearchResult;
import org.elasticsearch.search.sort.SortOrder;

import java.util.HashMap;
import java.util.List;

public interface ContentSearchService extends SearchService {
    /**
     *
     * @param query
     * @param sortField
     * @param order
     * @param pageNo
     * @param pageSize
     * @return
     * @throws Throwable
     */
    public ContentSearchResult wildCardSearchContent(HashMap<String, Object> query, String sortField, SortOrder order, Integer pageNo, Integer pageSize) throws Throwable;

    /**
     *
     * @param starDeeds
     * @throws Throwable
     */
    public void createContentIndex(List<ContentDomain> starDeeds) throws Throwable;
}
