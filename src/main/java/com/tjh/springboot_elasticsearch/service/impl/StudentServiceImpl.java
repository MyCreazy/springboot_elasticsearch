package com.tjh.springboot_elasticsearch.service.impl;

import com.tjh.springboot_elasticsearch.dao.StudentDao;
import com.tjh.springboot_elasticsearch.model.StudentTestVo;
import com.tjh.springboot_elasticsearch.service.StudentService;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 本类展示两种操作es的方法
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    public ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private StudentDao studentDao;

    @Override
    public String addStudent(StudentTestVo studentTestVo) {
        Object result = studentDao.save(studentTestVo);
        return result.toString();
    }

    @Override
    public List<StudentTestVo> queryStudent(String name) {
        SearchQuery searchQuery = (SearchQuery) new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchQuery("name", name)).build();
        List<StudentTestVo> studentTestVoList = elasticsearchTemplate.queryForList(searchQuery, StudentTestVo.class);

        return studentTestVoList;
    }

   /* （1）统计某个字段的数量
    ValueCountAggregationBuilder vcb=  AggregationBuilders.count("自定义").field("uid");
（2）去重统计某个字段的数量
    CardinalityAggregationBuilder cb= AggregationBuilders.cardinality("distinct_count_uid").field("uid");
（3）聚合过滤
    FilterAggregationBuilder fab= AggregationBuilders.filter("uid_filter").filter(QueryBuilders.queryStringQuery("uid:001"));
（4）按某个字段分组
    TermsAggregationBuilder tb=  AggregationBuilders.terms("group_name").field("name");
（5）求和
    SumAggregationBuilder  sumBuilder=    AggregationBuilders.sum("sum_price").field("price");
（6）求平均
    AvgAggregationBuilder ab= AggregationBuilders.avg("avg_price").field("price");
（7）求最大值
    MaxAggregationBuilder mb= AggregationBuilders.max("max_price").field("price");
（8）求最小值
    MinAggregationBuilder min=    AggregationBuilders.min("min_price").field("price");
（9）按日期间隔分组
    DateHistogramAggregationBuilder dhb= AggregationBuilders.dateHistogram("dh").field("date");
（10）获取聚合里面的结果
    TopHitsBuilder thb=  AggregationBuilders.topHits("top_result");
（11）嵌套的聚合
    NestedAggregationBuilder nb= AggregationBuilders.nested("negsted_path").path("quests");
（12）反转嵌套
AggregationBuilders.reverseNested("res_negsted").path("kps ");*/

    @Override
    public List<StudentTestVo> queryStudentByAge(String age, int optType) {
        SearchQuery searchQuery = (SearchQuery) new NativeSearchQueryBuilder().withQuery(QueryBuilders.rangeQuery("age").gt(age)).build();
        List<StudentTestVo> studentTestVoList = elasticsearchTemplate.queryForList(searchQuery, StudentTestVo.class);

        return studentTestVoList;
    }

    @Override
    public void deleteStudent(int id) {
        StudentTestVo studentTestVo = new StudentTestVo();
        studentTestVo.setSkuId("1");
        studentDao.delete(studentTestVo);
    }
}
