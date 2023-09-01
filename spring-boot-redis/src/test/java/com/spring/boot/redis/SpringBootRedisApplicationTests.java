package com.spring.boot.redis;

import com.alibaba.fastjson2.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;
import java.util.Map;

@SpringBootTest
class SpringBootRedisApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void test1() {
        Person person = Person.builder().userId(10000001L).age(21).address("上海市宝山区").build();
        redisTemplate.opsForValue().set("userId:10000001", person);
    }

    @Test
    void test2() {
        Person person = Person.builder().userId(10000001L).age(21).address("上海市宝山区").build();
        stringRedisTemplate.opsForValue().set("userId:10000001", JSON.toJSONString(person));

        String s = stringRedisTemplate.opsForValue().get("userId:10000001");
        Person person1 = JSON.parseObject(s, Person.class);
        System.out.println(person1);
    }

    @Test
    void test3() {
        redisTemplate.opsForHash().put("userId:10000001", "userId", "10000001");
        redisTemplate.opsForHash().put("userId:10000001", "age", "21");
        redisTemplate.opsForHash().put("userId:10000001", "address", "上海市宝山区");

        Map<Object, Object> entries = redisTemplate.opsForHash().entries("userId:10000001");
        entries.forEach((o, o2) -> System.out.println(o + " " + o2));

    }

    @Test
    void test4() {
        stringRedisTemplate.opsForHash().put("userId:10000001", "userId", "10000001");
        stringRedisTemplate.opsForHash().put("userId:10000001", "age", "21");
        stringRedisTemplate.opsForHash().put("userId:10000001", "address", "上海市宝山区");

        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries("userId:10000001");
        entries.forEach((o, o2) -> System.out.println(o + " " + o2));

    }

    @Autowired
    private RedisTransactionService redisTransactionService;

    /**
     * RedisTemplate 不保证事务中的所有操作都使用相同的连接
     *
     * @throws Exception
     */
    @Test
    void transaction1() throws Exception {
        redisTransactionService.test();
    }

    /**
     * 多个操作使用相同的连接时，使用 SessionCallback 接口
     */
    @Test
    void transaction2() {
        //execute a transaction
        List<Object> txResults = stringRedisTemplate.execute(new SessionCallback<>() {
            public List<Object> execute(RedisOperations operations) throws DataAccessException {
                operations.multi();

                stringRedisTemplate.opsForValue().set("product:1000004", "1");
                String s = stringRedisTemplate.opsForValue().get("product:1000003");
                if (s == null) {
                    throw new QueryTimeoutException("查询超时异常");
                }

                // This will contain the results of all operations in the transaction
                return operations.exec();
            }
        });
        System.out.println("Number of items added to set: " + txResults.get(0));
    }
}
