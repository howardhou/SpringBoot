package com.example.elasticsearchdemo.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.NoRepositoryBean;


@NoRepositoryBean
public interface BaseRepository<T> extends ElasticsearchRepository<T, String>{

}
