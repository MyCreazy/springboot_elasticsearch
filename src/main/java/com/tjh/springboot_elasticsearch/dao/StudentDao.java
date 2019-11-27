package com.tjh.springboot_elasticsearch.dao;

import com.tjh.springboot_elasticsearch.model.StudentTestVo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDao extends ElasticsearchRepository<StudentTestVo,String>{
}
