package com.example.elasticsearchdemo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ContentSearchResult implements Serializable {
    private static final long serialVersionUID = -4280558435028734975L;
    List<ContentDomain> result;
    int pageNo;
    int pageCount;
    long itemCount;
}
