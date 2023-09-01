DELETE FROM `db`.t_user;

INSERT INTO `db`.t_user (id, name, age, email)
VALUES (1, 'Jone',   18, 'test1@baomidou.com'),
       (2, 'Jack',   20, 'test2@baomidou.com'),
       (3, 'Tom',    28, 'test3@baomidou.com'),
       (4, 'Sandy',  21, 'test4@baomidou.com'),
       (5, 'Billie', 24, 'test5@baomidou.com');

DELETE FROM `db`.t_address;

INSERT INTO `db`.t_address (id, user_id, name)
VALUES (1, 1, '上海市宝山区'),
       (2, 1, '上海市杨浦区'),
       (3, 2, '上海市黄浦区'),
       (4, 2, '上海市徐汇区'),
       (5, 2, '上海市宝山区');
