package com.spring.boot.mongo;

import com.mongodb.client.MongoCollection;
import com.spring.boot.mongo.entity.Product;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationExpression;
import org.springframework.data.mongodb.core.aggregation.DateOperators;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.index.IndexOperations;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class SpringBootMongoApplicationTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void test1() throws InterruptedException {
        // 删除Collection
        String collectionName = "product";
        mongoTemplate.dropCollection(collectionName);

        MongoCollection<Document> collection;

        // 创建Collection
        boolean collectionExists = mongoTemplate.collectionExists(collectionName);
        if (!collectionExists) {
            collection = mongoTemplate.createCollection(collectionName);
        } else {
            collection = mongoTemplate.getCollection(collectionName);
        }

        // 创建多列唯一索引
        Index index = new Index().unique();
        index.named("batch_productCode_index"); // 索引名称
        index.on("batch", Sort.Direction.ASC); // 索引字段
        index.on("productCode", Sort.Direction.ASC); // 索引字段
        IndexOperations indexOperations = mongoTemplate.indexOps(collectionName);
        indexOperations.ensureIndex(index); // 创建索引

        // 创建TTL索引
        index = new Index().unique();
        index.named("expireDate_index"); // 索引名称
        index.on("expireDate", Sort.Direction.ASC); // 索引字段
        index.expire(10, TimeUnit.SECONDS); // 该列有数据的话，10秒后自动删除
        indexOperations = mongoTemplate.indexOps(collectionName);
        indexOperations.ensureIndex(index); // 创建索引

        // 查询索引 方式1
        mongoTemplate.indexOps(collectionName).getIndexInfo().forEach(System.out::println);

        // 查询索引 方式2
        collection.listIndexes().forEach(System.out::println);

        // 新增数据
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // 提交任务到线程池
        for (int i = 0; i < 10; i++) {
            executor.submit(() -> {
                // 任务逻辑
                System.out.println("Task executed by " + Thread.currentThread().getName());
                Product product = new Product();
                product.setBatch("batch1");
                product.setProductCode("productCode1");
                productRepository.save(product);

                product = new Product();
                product.setBatch("batch2");
                product.setProductCode("productCode2");
                mongoTemplate.save(product, collectionName);

                // 唯一索引冲突，会抛出异常
                product = new Product();
                product.setBatch("batch1");
                product.setProductCode("productCode1");
                productRepository.save(product);
            });
        }

        // 关闭线程池以不再接受新任务
        executor.shutdown();

        // 阻塞等待所有任务完成，最多等待1分钟
        boolean terminated = executor.awaitTermination(1, TimeUnit.MINUTES);
        if (terminated) {
            System.out.println("All tasks completed.");
        } else {
            System.out.println("Not all tasks completed within the given timeout.");
        }

//        while (!executor.isTerminated()) {
//            // 可以在这里做其他事情，而不是阻塞等待
//        }

        System.out.println("All tasks are finished after shutdown.");

        // 查询数据
        productRepository.findAll().forEach(System.out::println);
    }

    @Test
    public void test2() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

//        Aggregation aggregation = Aggregation.newAggregation(
//                Aggregation.project("createTime")
//                        // 该方式生成的是字符串
////                        .and(DateOperators.DateToString.dateOf("createTime").toString("yyyy-MM-dd")).as("createDate"),
//                        // 该方式生成的是Date
//                        .and(DateOperators.DateTrunc.truncateValueOf("createTime").to("day")).as("createDate"),
//                Aggregation.group("createDate")
//                        .count().as("count")
//                        .first("batch").as("batch")
//                        .first("productCode").as("productCode")
//                        .first("expireDate").as("expireDate")
//        );

        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.group(DateOperators.DateTrunc.truncateValueOf("createTime").to("day").toString())
                        .count().as("count")
                        .first("batch").as("batch")
                        .first("productCode").as("productCode")
                        .first("expireDate").as("expireDate")
        );

        System.out.println(aggregation);

    }
}
