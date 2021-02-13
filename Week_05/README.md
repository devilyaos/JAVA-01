# 作业内容

#### 第九课

1. (选做) 使用Java里的动态代理,实现一个简单的AOP
2. **(必做)** 写代码实现Spring Bean的装配, 方式越多越好(Xml, Annotation都可以), 提交到github
3. (选做) 实现一个spring XML自定义配置, 配置一组bean, 例如Student, Klass, School等
4. (选做) 将网关的frontend/backend/filter/router线程池都改造成spring配置方式
5. (选做) 基于AOP改造Netty网关, filter和router使用aop的方式实现
6. (选做) 基于前述改造, 将网关请求前后端分离, 使用JMS传递消息
7. (选做) 尝试使用ByteBuddys实现一个简单的基于类的AOP
8. (选做) 尝试使用ByteBuddy与Instrument实现一个简单JavaAgent实现无侵入下的AOP

#### 第十课

1. (选做) 总结一下, 单例的各种写法, 比较他们的优劣
2. (选做) maven/spring的profile机制,都有什么用法
3. **(必做)** 给前面课程提供的Student/Klass/School实现自动配置和Starter
4. (选做) 总结Hibernate与MyBatis的各方面异同点
5. (选做) 学习Mybatis-generator的用法和原理, 学会自定义TypeHandler处理复杂类型
6. **(必做)** 研究一下JDBC接口和数据库连接池, 掌握他们的设计和用法
    
    * 使用JDBC原生接口, 实现数据库的增删改查操作
    * 使用事务, PrepareStatement方式, 批处理方式, 改进上述操作
    * 配置Hikari连接池, 改进上述操作, 代码提交到Github
    
7. (选做) 基于AOP和自定义注解, 实现@MyCache(60)对于指定方法返回值缓存60秒
8. (选做) 自定义实现一个数据库连接池, 并整合Hibernate/Mybatis/Spring/SpringBoot
9. (选做) 基于Mybatis实现一个简单的分库分表+读写分离+分布式ID生成方案