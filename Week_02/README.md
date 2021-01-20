##### 串行GC

* 参数: -Xmx1024m -Xms1024m -XX:UseSerialGC
* 结果: 执行结束!共生成对象次数:5240

##### 并行GC-parNew

* 参数: -Xmx1024m -Xms1024m -XX:+UseParNewGC
* 结果: 执行结束!共生成对象次数:6269
> java8中已经不再推荐使用young区使用并行gc而old区使用串行gc了,会有提示: Java HotSpot(TM) 64-Bit Server VM warning: Using the ParNew young collector with the Serial old collector is deprecated and will likely be removed in a future release

##### 并行gc-Parallel

* 参数: -Xmx1024m -Xms1024m -XX:+UseParallelGC
* 结果: 执行结束!共生成对象次数:6295

##### CMS GC

* 参数: -Xmx1024m -Xms1024m -XX:+UseConcMarkSweepGC
* 结果: 执行结束!共生成对象次数:5041

##### G1 GC

* 参数: -Xmx1024m -Xms1024m -XX:+UseG1GC -XX:MaxGCPauseMillis=50
* 结果: 执行结束!共生成对象次数:6683