##### 串行GC

参数: -Xmx1024m -Xms1024m -XX:+UseSerialGC

```shell
[GC (Allocation Failure)  279616K->92790K(1013632K), 0.0741311 secs]
[GC (Allocation Failure)  372012K->173493K(1013632K), 0.0930081 secs]
[GC (Allocation Failure)  453109K->249923K(1013632K), 0.0623035 secs]
[GC (Allocation Failure)  529539K->319963K(1013632K), 0.0771716 secs]
[GC (Allocation Failure)  599579K->393473K(1013632K), 0.0873767 secs]
执行结束!共生成对象次数:5273
```

参数: -Xmx512m -Xms512m -XX:+UseSerialGC

```shell
[GC (Allocation Failure)  139644K->41163K(506816K), 0.0449375 secs]
[GC (Allocation Failure)  180896K->83190K(506816K), 0.0411498 secs]
[GC (Allocation Failure)  222900K->124616K(506816K), 0.0312014 secs]
[GC (Allocation Failure)  264392K->171131K(506816K), 0.0460614 secs]
[GC (Allocation Failure)  310907K->220238K(506816K), 0.0357104 secs]
[GC (Allocation Failure)  360014K->262108K(506816K), 0.0318009 secs]
[GC (Allocation Failure)  401777K->308646K(506816K), 0.1072767 secs]
[GC (Allocation Failure)  448422K->351103K(506816K), 0.0375045 secs]
[Full GC (Allocation Failure)  490879K->278503K(506816K), 0.0760671 secs]
执行结束!共生成对象次数:5120
```

参数: -Xmx2048m -Xms2048m -XX:+UseSerialGC

```shell
[GC (Allocation Failure)  559232K->146035K(2027264K), 0.1095294 secs]
[GC (Allocation Failure)  705267K->267349K(2027264K), 0.1925002 secs]
执行结束!共生成对象次数:5462
```

**结论：堆内存设置越大YGC次数越少，且越不容易FGC，但单次YGC的时间也逐渐延长。**



##### 并行GC-parNew

参数: -Xmx512m -Xms512m -XX:+UseParNewGC

```shell
[GC (Allocation Failure)  139723K->45912K(506816K), 0.0217425 secs]
[GC (Allocation Failure)  185688K->92069K(506816K), 0.0289755 secs]
[GC (Allocation Failure)  231845K->140144K(506816K), 0.0378393 secs]
[GC (Allocation Failure)  279920K->183425K(506816K), 0.0327748 secs]
[GC (Allocation Failure)  323124K->227313K(506816K), 0.0430577 secs]
[GC (Allocation Failure)  367089K->264575K(506816K), 0.0310309 secs]
[GC (Allocation Failure)  404351K->311059K(506816K), 0.0358992 secs]
[GC (Allocation Failure)  450835K->356647K(506816K), 0.0362763 secs]
[Full GC (Allocation Failure)  496423K->282216K(506816K), 0.0572055 secs]
[GC (Allocation Failure)  421859K->322159K(506816K), 0.0084418 secs]
[Full GC (Allocation Failure)  461662K->299572K(506816K), 0.1138277 secs]
执行结束!共生成对象次数:6077
```

参数: -Xmx1024m -Xms1024m -XX:+UseParNewGC

```shell
[GC (Allocation Failure)  279616K->88492K(1013632K), 0.0414378 secs]
[GC (Allocation Failure)  368108K->166463K(1013632K), 0.0529017 secs]
[GC (Allocation Failure)  445961K->252737K(1013632K), 0.0701474 secs]
[GC (Allocation Failure)  532353K->338123K(1013632K), 0.0635300 secs]
[GC (Allocation Failure)  617739K->427194K(1013632K), 0.0734902 secs]
执行结束!共生成对象次数:5905
```

参数: -Xmx2048m -Xms2048m -XX:+UseParNewGC

```shell
[GC (Allocation Failure)  559232K->139455K(2027264K), 0.0656447 secs]
[GC (Allocation Failure)  698687K->267937K(2027264K), 0.1061265 secs]
执行结束!共生成对象次数:6151
```

**结论：由小内存堆可以发现，单次YGC的时间相对串行GC来说总体有降低，FGC时间总体保持一致，堆内存设置越大YGC次数越少，且越不容易FGC，但单次YGC的时间也逐渐延长。**

> java8中已经不再推荐使用young区使用并行gc而old区使用串行gc了,会有提示: Java HotSpot(TM) 64-Bit Server VM warning: Using the ParNew young collector with the Serial old collector is deprecated and will likely be removed in a future release

##### 并行gc-Parallel

参数: -Xmx512m -Xms512m -XX:+UseParallelGC

```shell
[GC (Allocation Failure)  131584K->48945K(502784K), 0.0242005 secs]
[GC (Allocation Failure)  180529K->88547K(502784K), 0.0261597 secs]
[GC (Allocation Failure)  220131K->134344K(502784K), 0.0226818 secs]
[GC (Allocation Failure)  265928K->172811K(502784K), 0.0197046 secs]
[GC (Allocation Failure)  304395K->213477K(502784K), 0.0217885 secs]
[GC (Allocation Failure)  345061K->258554K(430080K), 0.0252099 secs]
[GC (Allocation Failure)  317434K->279946K(466432K), 0.0094072 secs]
[GC (Allocation Failure)  338659K->299990K(466432K), 0.0120567 secs]
[GC (Allocation Failure)  358870K->317375K(466432K), 0.0169853 secs]
[GC (Allocation Failure)  376255K->330993K(466432K), 0.0316638 secs]
[Full GC (Ergonomics)  330993K->232212K(466432K), 0.0605444 secs]
[GC (Allocation Failure)  291039K->254196K(466432K), 0.0047475 secs]
[GC (Allocation Failure)  313076K->272635K(466432K), 0.0079224 secs]
[GC (Allocation Failure)  331515K->291832K(466432K), 0.0082436 secs]
[GC (Allocation Failure)  350712K->310770K(466432K), 0.0082986 secs]
[GC (Allocation Failure)  369553K->331101K(466432K), 0.0132905 secs]
[Full GC (Ergonomics)  331101K->268797K(466432K), 0.0416317 secs]
[GC (Allocation Failure)  327677K->287017K(466432K), 0.0058953 secs]
[GC (Allocation Failure)  345897K->305621K(466432K), 0.0074056 secs]
[GC (Allocation Failure)  364501K->323844K(466432K), 0.0072015 secs]
[GC (Allocation Failure)  382635K->343957K(466432K), 0.0168310 secs]
[Full GC (Ergonomics)  343957K->279657K(466432K), 0.0569792 secs]
执行结束!共生成对象次数:5913
```

参数: -Xmx1024m -Xms1024m -XX:+UseParallelGC

```shell
[GC (Allocation Failure)  262144K->77730K(1005056K), 0.0359279 secs]
[GC (Allocation Failure)  339586K->148636K(1005056K), 0.0570443 secs]
[GC (Allocation Failure)  410780K->223777K(1005056K), 0.0502707 secs]
[GC (Allocation Failure)  485921K->291971K(1005056K), 0.0397144 secs]
[GC (Allocation Failure)  553495K->357906K(1005056K), 0.0398378 secs]
[GC (Allocation Failure)  620050K->430087K(859648K), 0.0673315 secs]
[GC (Allocation Failure)  546823K->464745K(932352K), 0.0179547 secs]
执行结束!共生成对象次数:6817
```

参数: -Xmx2048m -Xms2048m -XX:+UseParallelGC

```shell
[GC (Allocation Failure)  524800K->135287K(2010112K), 0.0627908 secs]
[GC (Allocation Failure)  660087K->250272K(2010112K), 0.1636459 secs]
执行结束!共生成对象次数:5424
```

**结论：由小内存堆可以发现YGC的回收时间明显降低且平稳，FGC时间保持稳定持平，堆内存设置越大YGC次数越少，且越不容易FGC，但单次YGC的时间也逐渐延长。**

##### CMS GC

参数: -Xmx256m -Xms256m -XX:+UseConcMarkSweepGC

```shell
[GC (Allocation Failure)  69516K->21898K(253440K), 0.0110812 secs]
[GC (Allocation Failure)  91479K->48758K(253440K), 0.0160519 secs]
[GC (Allocation Failure)  118682K->66669K(253440K), 0.0156033 secs]
[GC (Allocation Failure)  136570K->91812K(253440K), 0.0242187 secs]
[GC (Allocation Failure)  161764K->114089K(253440K), 0.0222517 secs]
[GC (CMS Initial Mark)  114279K(253440K), 0.0002225 secs]
[GC (Allocation Failure)  183557K->133548K(253440K), 0.0181546 secs]
[GC (Allocation Failure)  203340K->156435K(253440K), 0.0213764 secs]
[GC (Allocation Failure)  226387K->181583K(253440K), 0.0252553 secs]
[GC (CMS Final Remark)  181944K(253440K), 0.0021148 secs]
[Full GC (Allocation Failure)  239265K->168207K(253440K), 0.0390150 secs]
[GC (CMS Initial Mark)  168495K(253440K), 0.0022335 secs]
[GC (CMS Final Remark)  168495K(253440K), 0.0054593 secs]
[Full GC (Allocation Failure)  238159K->185340K(253440K), 0.0541194 secs]
[GC (CMS Initial Mark)  185628K(253440K), 0.0002104 secs]
[GC (CMS Final Remark)  197381K(253440K), 0.0017688 secs]
[Full GC (Allocation Failure)  253218K->199963K(253440K), 0.0418175 secs]
[GC (CMS Initial Mark)  200118K(253440K), 0.0001795 secs]
[GC (CMS Final Remark)  216130K(253440K), 0.0013819 secs]
[Full GC (Allocation Failure)  252646K->207297K(253440K), 0.0443308 secs]
[GC (CMS Initial Mark)  207585K(253440K), 0.0002639 secs]
[GC (CMS Final Remark)  219414K(253440K), 0.0082110 secs]
[Full GC (Allocation Failure)  253234K->214385K(253440K), 0.0855069 secs]
[GC (CMS Initial Mark)  214457K(253440K), 0.0002167 secs]
[GC (CMS Final Remark)  229520K(253440K), 0.0012600 secs]
[Full GC (Allocation Failure)  253082K->218829K(253440K), 0.0459969 secs]
[GC (CMS Initial Mark)  221652K(253440K), 0.0002335 secs]
[GC (CMS Final Remark)  221652K(253440K), 0.0015488 secs]
[Full GC (Allocation Failure)  253085K->224584K(253440K), 0.0732673 secs]
[GC (CMS Initial Mark)  226130K(253440K), 0.0003177 secs]
[GC (CMS Final Remark)  245435K(253440K), 0.0017012 secs]
[Full GC (Allocation Failure)  252808K->232645K(253440K), 0.0700158 secs]
[GC (CMS Initial Mark)  234518K(253440K), 0.0002944 secs]
[GC (CMS Final Remark)  244290K(253440K), 0.0014443 secs]
执行结束!共生成对象次数:3659
```

参数: -Xmx512m -Xms512m -XX:+UseConcMarkSweepGC

```shell
[GC (Allocation Failure)  139776K->45180K(506816K), 0.0215076 secs]
[GC (Allocation Failure)  184650K->91449K(506816K), 0.0291759 secs]
[GC (Allocation Failure)  231225K->132820K(506816K), 0.0364680 secs]
[GC (Allocation Failure)  272596K->175385K(506816K), 0.0377441 secs]
[GC (Allocation Failure)  315041K->227113K(506816K), 0.0491831 secs]
[GC (CMS Initial Mark)  227874K(506816K), 0.0002957 secs]
[GC (Allocation Failure)  366853K->273434K(506816K), 0.0380225 secs]
[GC (Allocation Failure)  413210K->312774K(506816K), 0.0316844 secs]
[GC (Allocation Failure)  452106K->353847K(506816K), 0.0328418 secs]
[GC (CMS Final Remark)  354511K(506816K), 0.0016376 secs]
[GC (Allocation Failure)  447354K->345843K(506816K), 0.0142916 secs]
[GC (CMS Initial Mark)  345887K(506816K), 0.0002899 secs]
[GC (CMS Final Remark)  354658K(506816K), 0.0023962 secs]
[GC (Allocation Failure)  394415K->294193K(506816K), 0.0124489 secs]
[GC (CMS Initial Mark)  294481K(506816K), 0.0001859 secs]
[GC (Allocation Failure)  433969K->340660K(506816K), 0.0134973 secs]
[Full GC (Allocation Failure)  480152K->293923K(506816K), 0.1024906 secs]
[GC (Allocation Failure)  433699K->334814K(506816K), 0.0098294 secs]
[GC (CMS Initial Mark)  336268K(506816K), 0.0003791 secs]
[GC (Allocation Failure)  474590K->505728K(506816K), 0.0176500 secs]
[Full GC (Allocation Failure)  505728K->310479K(506816K), 0.0604770 secs]
执行结束!共生成对象次数:7260
```

参数: -Xmx1024m -Xms1024m -XX:+UseConcMarkSweepGC

```shell
[GC (Allocation Failure)  272640K->78861K(1014528K), 0.0377917 secs]
[GC (Allocation Failure)  351501K->144250K(1014528K), 0.0475342 secs]
[GC (Allocation Failure)  416890K->223891K(1014528K), 0.0666448 secs]
[GC (Allocation Failure)  496531K->297996K(1014528K), 0.0570660 secs]
[GC (Allocation Failure)  570636K->380489K(1014528K), 0.0678866 secs]
[GC (Allocation Failure)  653129K->459732K(1014528K), 0.1085088 secs]
[GC (CMS Initial Mark)  465700K(1014528K), 0.0003453 secs]
执行结束!共生成对象次数:6494
```

参数: -Xmx2048m -Xms2048m -XX:+UseConcMarkSweepGC

```shell
[GC (Allocation Failure)  272640K->74704K(2063104K), 0.0371404 secs]
[GC (Allocation Failure)  347232K->156771K(2063104K), 0.0712119 secs]
[GC (Allocation Failure)  429411K->236456K(2063104K), 0.0700576 secs]
[GC (Allocation Failure)  508733K->310507K(2063104K), 0.0629922 secs]
[GC (Allocation Failure)  583147K->382201K(2063104K), 0.0655647 secs]
[GC (Allocation Failure)  654841K->464604K(2063104K), 0.1283784 secs]
执行结束!共生成对象次数:6141
```

结论：堆内存设置越大YGC次数越少，且越不容易FGC，但单次YGC的时间也逐渐延长；CMS GC还出现了CMS Initial Mark（初始标记，一是标记老年代中所有的GC Roots；二是标记被年轻代中活着的对象引用的对象）与CMS Final Remark（完成标记整个年老代的所有的存活对象），两个标记均STW。

##### G1 GC

参数: -Xmx512m -Xms512m -XX:+UseG1GC -XX:MaxGCPauseMillis=50

```shell
[GC pause (G1 Evacuation Pause) (young) 30M->10M(512M), 0.0062925 secs]
[GC pause (G1 Evacuation Pause) (young) 34M->16M(512M), 0.0036024 secs]
[GC pause (G1 Evacuation Pause) (young) 43M->28M(512M), 0.0057979 secs]
[GC pause (G1 Evacuation Pause) (young) 58M->37M(512M), 0.0043276 secs]
[GC pause (G1 Evacuation Pause) (young) 64M->42M(512M), 0.0026129 secs]
[GC pause (G1 Evacuation Pause) (young) 66M->48M(512M), 0.0026904 secs]
[GC pause (G1 Evacuation Pause) (young) 71M->54M(512M), 0.0040515 secs]
[GC pause (G1 Evacuation Pause) (young) 95M->67M(512M), 0.0058051 secs]
[GC pause (G1 Evacuation Pause) (young) 106M->80M(512M), 0.0070679 secs]
[GC pause (G1 Evacuation Pause) (young) 130M->97M(512M), 0.0072134 secs]
[GC pause (G1 Evacuation Pause) (young) 186M->131M(512M), 0.0161041 secs]
[GC pause (G1 Evacuation Pause) (young) 184M->151M(512M), 0.0082837 secs]
[GC pause (G1 Evacuation Pause) (young) 232M->179M(512M), 0.0090087 secs]
[GC pause (G1 Humongous Allocation) (young) (initial-mark) 256M->206M(512M), 0.0091116 secs]
[GC concurrent-root-region-scan-start]
[GC concurrent-root-region-scan-end, 0.0002313 secs]
[GC concurrent-mark-start]
[GC concurrent-mark-end, 0.0045487 secs]
[GC remark, 0.0018063 secs]
[GC cleanup 221M->220M(512M), 0.0006608 secs]
[GC concurrent-cleanup-start]
[GC concurrent-cleanup-end, 0.0000101 secs]
[GC pause (G1 Evacuation Pause) (young) 315M->229M(512M), 0.0194469 secs]
[GC pause (G1 Evacuation Pause) (mixed) 243M->216M(512M), 0.0099358 secs]
[GC pause (G1 Evacuation Pause) (mixed) 243M->225M(512M), 0.0178200 secs]
[GC pause (G1 Humongous Allocation) (young) (initial-mark) 226M->225M(512M), 0.0172992 secs]
[GC concurrent-root-region-scan-start]
[GC concurrent-root-region-scan-end, 0.0005379 secs]
[GC concurrent-mark-start]
[GC concurrent-mark-end, 0.0027587 secs]
[GC remark, 0.0069025 secs]
[GC cleanup 234M->234M(512M), 0.0031540 secs]
[GC pause (G1 Evacuation Pause) (young)-- 429M->366M(512M), 0.0132139 secs]
[GC pause (G1 Evacuation Pause) (mixed) 372M->354M(512M), 0.0137549 secs]
[GC pause (G1 Humongous Allocation) (young) (initial-mark) 357M->355M(512M), 0.0069365 secs]
[GC concurrent-root-region-scan-start]
[GC concurrent-root-region-scan-end, 0.0005764 secs]
[GC concurrent-mark-start]
[GC concurrent-mark-end, 0.0053722 secs]
[GC remark, 0.0038174 secs]
[GC cleanup 361M->361M(512M), 0.0039117 secs]
[GC pause (G1 Evacuation Pause) (young) 422M->372M(512M), 0.0132967 secs]
[GC pause (G1 Evacuation Pause) (mixed) 395M->330M(512M), 0.0050750 secs]
[GC pause (G1 Evacuation Pause) (mixed) 358M->301M(512M), 0.0073719 secs]
[GC pause (G1 Evacuation Pause) (mixed) 330M->291M(512M), 0.0071841 secs]
[GC pause (G1 Humongous Allocation) (young) (initial-mark) 293M->292M(512M), 0.0019267 secs]
[GC concurrent-root-region-scan-start]
[GC concurrent-root-region-scan-end, 0.0001579 secs]
[GC concurrent-mark-start]
[GC concurrent-mark-end, 0.0029748 secs]
[GC remark, 0.0021526 secs]
[GC cleanup 307M->307M(512M), 0.0006437 secs]
[GC pause (G1 Evacuation Pause) (young)-- 424M->379M(512M), 0.0061570 secs]
[GC pause (G1 Evacuation Pause) (mixed) 395M->364M(512M), 0.0101570 secs]
[GC pause (G1 Humongous Allocation) (young) (initial-mark) 369M->365M(512M), 0.0095884 secs]
[GC concurrent-root-region-scan-start]
[GC concurrent-root-region-scan-end, 0.0017226 secs]
[GC concurrent-mark-start]
[GC concurrent-mark-end, 0.0044650 secs]
[GC remark, 0.0050207 secs]
[GC cleanup 366M->365M(512M), 0.0021402 secs]
[GC concurrent-cleanup-start]
[GC concurrent-cleanup-end, 0.0000104 secs]
[GC pause (G1 Evacuation Pause) (young) 405M->374M(512M), 0.0228642 secs]
执行结束!共生成对象次数:4728
```

参数: -Xmx512m -Xms512m -XX:+UseG1GC -XX:MaxGCPauseMillis=200

```shell
[GC pause (G1 Evacuation Pause) (young) 38M->11M(512M), 0.0036277 secs]
[GC pause (G1 Evacuation Pause) (young) 42M->21M(512M), 0.0039232 secs]
[GC pause (G1 Evacuation Pause) (young) 59M->36M(512M), 0.0060038 secs]
[GC pause (G1 Evacuation Pause) (young) 96M->60M(512M), 0.0078771 secs]
[GC pause (G1 Evacuation Pause) (young) 116M->74M(512M), 0.0081806 secs]
[GC pause (G1 Evacuation Pause) (young)-- 415M->295M(512M), 0.0210291 secs]
[GC pause (G1 Humongous Allocation) (young) (initial-mark) 297M->296M(512M), 0.0076958 secs]
[GC concurrent-root-region-scan-start]
[GC concurrent-root-region-scan-end, 0.0001737 secs]
[GC concurrent-mark-start]
[GC concurrent-mark-end, 0.0055624 secs]
[GC remark, 0.0038297 secs]
[GC cleanup 314M->314M(512M), 0.0005150 secs]
[GC pause (G1 Evacuation Pause) (young)-- 437M->375M(512M), 0.0060633 secs]
[GC pause (G1 Evacuation Pause) (mixed) 388M->331M(512M), 0.0049285 secs]
[GC pause (G1 Evacuation Pause) (mixed) 356M->293M(512M), 0.0038523 secs]
[GC pause (G1 Evacuation Pause) (mixed) 319M->272M(512M), 0.0039466 secs]
[GC pause (G1 Humongous Allocation) (young) (initial-mark) 273M->271M(512M), 0.0011827 secs]
[GC concurrent-root-region-scan-start]
[GC concurrent-root-region-scan-end, 0.0001397 secs]
[GC concurrent-mark-start]
[GC concurrent-mark-end, 0.0023779 secs]
[GC remark, 0.0014649 secs]
[GC cleanup 282M->279M(512M), 0.0004663 secs]
[GC concurrent-cleanup-start]
[GC concurrent-cleanup-end, 0.0000112 secs]
[GC pause (G1 Evacuation Pause) (young) 420M->309M(512M), 0.0060748 secs]
[GC pause (G1 Evacuation Pause) (mixed) 320M->269M(512M), 0.0049453 secs]
[GC pause (G1 Evacuation Pause) (mixed) 297M->267M(512M), 0.0031750 secs]
[GC pause (G1 Humongous Allocation) (young) (initial-mark) 273M->269M(512M), 0.0014137 secs]
[GC concurrent-root-region-scan-start]
[GC concurrent-root-region-scan-end, 0.0001691 secs]
[GC concurrent-mark-start]
[GC concurrent-mark-end, 0.0025882 secs]
[GC remark, 0.0015004 secs]
[GC cleanup 280M->280M(512M), 0.0004437 secs]
[GC pause (G1 Evacuation Pause) (young) 411M->313M(512M), 0.0147759 secs]
[GC pause (G1 Evacuation Pause) (mixed) 327M->290M(512M), 0.0150704 secs]
[GC pause (G1 Humongous Allocation) (young) (initial-mark) 292M->290M(512M), 0.0045176 secs]
[GC concurrent-root-region-scan-start]
[GC concurrent-root-region-scan-end, 0.0001242 secs]
[GC concurrent-mark-start]
[GC concurrent-mark-end, 0.0066384 secs]
[GC remark, 0.0059628 secs]
[GC cleanup 297M->297M(512M), 0.0048207 secs]
[GC pause (G1 Evacuation Pause) (young) 402M->321M(512M), 0.0062959 secs]
[GC pause (G1 Evacuation Pause) (mixed) 337M->306M(512M), 0.0070559 secs]
[GC pause (G1 Humongous Allocation) (young) (initial-mark) 309M->306M(512M), 0.0018380 secs]
[GC concurrent-root-region-scan-start]
[GC concurrent-root-region-scan-end, 0.0001221 secs]
[GC concurrent-mark-start]
[GC concurrent-mark-end, 0.0033434 secs]
[GC remark, 0.0023897 secs]
[GC cleanup 320M->320M(512M), 0.0008107 secs]
[GC pause (G1 Evacuation Pause) (young) 408M->334M(512M), 0.0059296 secs]
[GC pause (G1 Evacuation Pause) (mixed) 352M->319M(512M), 0.0099031 secs]
[GC pause (G1 Humongous Allocation) (young) (initial-mark) 319M->318M(512M), 0.0017994 secs]
[GC concurrent-root-region-scan-start]
[GC concurrent-root-region-scan-end, 0.0001621 secs]
[GC concurrent-mark-start]
[GC concurrent-mark-end, 0.0032387 secs]
[GC remark, 0.0030085 secs]
[GC cleanup 334M->334M(512M), 0.0011415 secs]
[GC concurrent-cleanup-start]
[GC concurrent-cleanup-end, 0.0000108 secs]
[GC pause (G1 Evacuation Pause) (young) 405M->338M(512M), 0.0113216 secs]
[GC pause (G1 Evacuation Pause) (mixed) 358M->326M(512M), 0.0133333 secs]
执行结束!共生成对象次数:5534
```

参数: -Xmx1024m -Xms1024m -XX:+UseG1GC -XX:MaxGCPauseMillis=50

```shell
[GC pause (G1 Evacuation Pause) (young) 66M->22M(1024M), 0.0092953 secs]
[GC pause (G1 Evacuation Pause) (young) 78M->41M(1024M), 0.0072235 secs]
[GC pause (G1 Evacuation Pause) (young) 96M->57M(1024M), 0.0065812 secs]
[GC pause (G1 Evacuation Pause) (young) 114M->77M(1024M), 0.0067244 secs]
[GC pause (G1 Evacuation Pause) (young) 134M->99M(1024M), 0.0102718 secs]
[GC pause (G1 Evacuation Pause) (young) 155M->119M(1024M), 0.0074261 secs]
[GC pause (G1 Evacuation Pause) (young) 168M->137M(1024M), 0.0070856 secs]
[GC pause (G1 Evacuation Pause) (young) 196M->155M(1024M), 0.0077498 secs]
[GC pause (G1 Evacuation Pause) (young) 210M->175M(1024M), 0.0081080 secs]
[GC pause (G1 Evacuation Pause) (young) 228M->191M(1024M), 0.0065595 secs]
[GC pause (G1 Evacuation Pause) (young) 257M->215M(1024M), 0.0119778 secs]
[GC pause (G1 Evacuation Pause) (young) 277M->231M(1024M), 0.0100307 secs]
[GC pause (G1 Evacuation Pause) (young) 315M->262M(1024M), 0.0110643 secs]
[GC pause (G1 Evacuation Pause) (young) 395M->300M(1024M), 0.0181838 secs]
[GC pause (G1 Evacuation Pause) (young) 388M->321M(1024M), 0.0123498 secs]
[GC pause (G1 Evacuation Pause) (young) 436M->346M(1024M), 0.0127734 secs]
[GC pause (G1 Evacuation Pause) (young) 468M->374M(1024M), 0.0137002 secs]
[GC pause (G1 Humongous Allocation) (young) (initial-mark) 514M->404M(1024M), 0.0233993 secs]
[GC concurrent-root-region-scan-start]
[GC concurrent-root-region-scan-end, 0.0002115 secs]
[GC concurrent-mark-start]
[GC concurrent-mark-end, 0.0094196 secs]
[GC remark, 0.0036581 secs]
[GC cleanup 419M->406M(1024M), 0.0013766 secs]
[GC concurrent-cleanup-start]
[GC concurrent-cleanup-end, 0.0000243 secs]
[GC pause (G1 Evacuation Pause) (young) 457M->412M(1024M), 0.0066819 secs]
[GC pause (G1 Evacuation Pause) (mixed) 462M->399M(1024M), 0.0072784 secs]
[GC pause (G1 Evacuation Pause) (mixed) 456M->393M(1024M), 0.0071617 secs]
[GC pause (G1 Evacuation Pause) (mixed) 450M->381M(1024M), 0.0078692 secs]
[GC pause (G1 Evacuation Pause) (mixed) 440M->389M(1024M), 0.0058847 secs]
[GC pause (G1 Humongous Allocation) (young) (initial-mark) 466M->411M(1024M), 0.0071251 secs]
[GC concurrent-root-region-scan-start]
[GC concurrent-root-region-scan-end, 0.0004400 secs]
[GC concurrent-mark-start]
[GC concurrent-mark-end, 0.0038249 secs]
[GC remark, 0.0029523 secs]
[GC cleanup 424M->422M(1024M), 0.0009514 secs]
[GC concurrent-cleanup-start]
[GC concurrent-cleanup-end, 0.0000102 secs]
执行结束!共生成对象次数:6864
```

参数: -Xmx1024m -Xms1024m -XX:+UseG1GC -XX:MaxGCPauseMillis=200

```shell
[GC pause (G1 Evacuation Pause) (young) 65M->23M(1024M), 0.0089157 secs]
[GC pause (G1 Evacuation Pause) (young) 77M->48M(1024M), 0.0084824 secs]
[GC pause (G1 Evacuation Pause) (young) 105M->68M(1024M), 0.0073790 secs]
[GC pause (G1 Evacuation Pause) (young) 119M->87M(1024M), 0.0066540 secs]
[GC pause (G1 Evacuation Pause) (young) 161M->110M(1024M), 0.0102623 secs]
[GC pause (G1 Evacuation Pause) (young) 318M->167M(1024M), 0.0244620 secs]
[GC pause (G1 Evacuation Pause) (young) 246M->193M(1024M), 0.0104642 secs]
[GC pause (G1 Evacuation Pause) (young) 342M->244M(1024M), 0.0165424 secs]
[GC pause (G1 Evacuation Pause) (young) 420M->293M(1024M), 0.0179170 secs]
[GC pause (G1 Evacuation Pause) (young) 533M->355M(1024M), 0.0332195 secs]
[GC pause (G1 Humongous Allocation) (young) (initial-mark) 667M->430M(1024M), 0.0414100 secs]
[GC concurrent-root-region-scan-start]
[GC concurrent-root-region-scan-end, 0.0004232 secs]
[GC concurrent-mark-start]
[GC concurrent-mark-end, 0.0063856 secs]
[GC remark, 0.0027610 secs]
[GC cleanup 452M->440M(1024M), 0.0013731 secs]
[GC concurrent-cleanup-start]
[GC concurrent-cleanup-end, 0.0000223 secs]
[GC pause (G1 Evacuation Pause) (young) 575M->453M(1024M), 0.0460007 secs]
[GC pause (G1 Evacuation Pause) (mixed) 486M->374M(1024M), 0.0126413 secs]
[GC pause (G1 Evacuation Pause) (mixed) 427M->389M(1024M), 0.0070269 secs]
执行结束!共生成对象次数:6338
```

参数: -Xmx2048m -Xms2048m -XX:+UseG1GC -XX:MaxGCPauseMillis=50

```shell
[GC pause (G1 Evacuation Pause) (young) 137M->49M(2048M), 0.0154258 secs]
[GC pause (G1 Evacuation Pause) (young) 163M->87M(2048M), 0.0131357 secs]
[GC pause (G1 Evacuation Pause) (young) 200M->116M(2048M), 0.0130935 secs]
[GC pause (G1 Evacuation Pause) (young) 221M->149M(2048M), 0.0133893 secs]
[GC pause (G1 Evacuation Pause) (young) 255M->179M(2048M), 0.0132901 secs]
[GC pause (G1 Evacuation Pause) (young) 296M->211M(2048M), 0.0123529 secs]
[GC pause (G1 Evacuation Pause) (young) 315M->234M(2048M), 0.0190095 secs]
[GC pause (G1 Evacuation Pause) (young) 346M->267M(2048M), 0.0131800 secs]
[GC pause (G1 Evacuation Pause) (young) 380M->300M(2048M), 0.0170422 secs]
[GC pause (G1 Evacuation Pause) (young) 408M->335M(2048M), 0.0170842 secs]
[GC pause (G1 Evacuation Pause) (young) 447M->370M(2048M), 0.0166173 secs]
[GC pause (G1 Evacuation Pause) (young) 481M->402M(2048M), 0.0137239 secs]
[GC pause (G1 Evacuation Pause) (young) 517M->435M(2048M), 0.0260814 secs]
[GC pause (G1 Evacuation Pause) (young) 552M->459M(2048M), 0.0134778 secs]
[GC pause (G1 Evacuation Pause) (young) 564M->484M(2048M), 0.0107273 secs]
执行结束!共生成对象次数:6483
```

参数: -Xmx2048m -Xms2048m -XX:+UseG1GC -XX:MaxGCPauseMillis=200

```shell
[GC pause (G1 Evacuation Pause) (young) 138M->50M(2048M), 0.0153393 secs]
[GC pause (G1 Evacuation Pause) (young) 169M->87M(2048M), 0.0121780 secs]
[GC pause (G1 Evacuation Pause) (young) 205M->125M(2048M), 0.0142432 secs]
[GC pause (G1 Evacuation Pause) (young) 240M->160M(2048M), 0.0142144 secs]
[GC pause (G1 Evacuation Pause) (young) 283M->199M(2048M), 0.0118610 secs]
[GC pause (G1 Evacuation Pause) (young) 310M->238M(2048M), 0.0159230 secs]
[GC pause (G1 Evacuation Pause) (young) 360M->277M(2048M), 0.0184934 secs]
[GC pause (G1 Evacuation Pause) (young) 441M->322M(2048M), 0.0237702 secs]
[GC pause (G1 Evacuation Pause) (young) 488M->363M(2048M), 0.0237759 secs]
[GC pause (G1 Evacuation Pause) (young) 535M->413M(2048M), 0.0273226 secs]
[GC pause (G1 Evacuation Pause) (young) 648M->476M(2048M), 0.0325974 secs]
[GC pause (G1 Evacuation Pause) (young) 729M->536M(2048M), 0.0444182 secs]
执行结束!共生成对象次数:6633
```

**结论：G1 GC无论在大内存堆还是小内存堆，单次GC时间都非常稳定，同时内存提高，单次GC暂停时间越长，相对表现就会越好。**