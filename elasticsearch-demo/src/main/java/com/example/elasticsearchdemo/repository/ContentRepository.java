package com.example.elasticsearchdemo.repository;


import com.example.elasticsearchdemo.ContentDomain;
import org.springframework.stereotype.Repository;
import java.io.Serializable;


@Repository
public interface ContentRepository extends BaseRepository<ContentDomain>,Serializable{
}