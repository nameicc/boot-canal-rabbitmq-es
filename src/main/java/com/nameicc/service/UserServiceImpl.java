package com.nameicc.service;


import cn.hutool.core.bean.BeanUtil;
import com.nameicc.entity.UserEntity;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserServiceImpl implements UserService {

    private String INDEX_NAME = "test_user";

    @Resource
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public void insert(UserEntity user) {
        elasticsearchRestTemplate.save(user);
    }

    @Override
    public void update(UserEntity user) {
        UpdateQuery updateQuery = UpdateQuery
                .builder(user.getId())
                .withDocument(Document.from(BeanUtil.beanToMap(user, false, false)))
                .build();
        elasticsearchRestTemplate.update(updateQuery, IndexCoordinates.of(INDEX_NAME));
    }

    @Override
    public void delete(UserEntity user) {
        elasticsearchRestTemplate.delete(user);
    }

    @Override
    public UserEntity findUserByName(String name) {
        TermQueryBuilder queryBuilder = QueryBuilders.termQuery("name", name);
        NativeSearchQuery query = new NativeSearchQuery(queryBuilder);
        SearchHits<UserEntity> searchHits = elasticsearchRestTemplate.search(query, UserEntity.class);
        if (searchHits.getTotalHits() > 0) {
            SearchHit<UserEntity> searchHit = searchHits.getSearchHit(0);
            return searchHit.getContent();
        }
        return null;
    }
}
