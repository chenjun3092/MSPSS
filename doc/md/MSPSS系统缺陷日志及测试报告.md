# 系统缺陷日志及测试报告

![](http://101.37.19.32:10080/FX/MSPSS/raw/master/doc/img/%E6%96%87%E6%A1%A3%E5%B0%81%E9%9D%A2/%E7%B3%BB%E7%BB%9F%E7%BC%BA%E9%99%B7%E6%97%A5%E5%BF%97%E6%8A%A5%E5%91%8A.png)

[TOC]

# 1．引言

##   	1.1文档标识

| 文件状态：[  ] 草稿[ √ ] 正式发布[  ] 正在修改 | 报告编号： | 1    |           |
| ------------------------------- | ----- | ---- | --------- |
| 当前版本：                           | 1.0   |      |           |
| 编写人：                            | 徐光耀   | 编写日期 | 2017.1.9  |
| 审批人：                            | FX全组  | 审批日期 | 2017.1.10 |

 

## 1.2范围

​	本测试报告为MSPSS(进销存管理系统）项目的测试报告，目的在于总结测试阶段的测试以及分析测试结果，描述系统是否符合需求。预期参考人员包括财务人员、进货销售人员、库存管理人员、总经理。

## **1.3参考文献**

​	1.[IEEE-829-2008]

​	2.MSPSS进销存管理系统用例文档

​	3.MSPSS进销存管理系统需求规格说明书

## 1.4术语和缩略语

| **术语或缩略语**             | **全意**                        |
| ---------------------- | ----------------------------- |
| FX                     | FX小组                          |
| MSPSS                  | 进销存管理系统                       |
| HCI                    | 持续集成                          |
| 严重缺陷                   | 系统发生死机，异常，终止，返回错误信息的情况。       |
| LOC                    | 代码行数                          |
| NULL Pointer Exception | 空指针异常，一般产生于没有正确初始化对象或者是对象赋值有误 |



# 2.详细情况

## **2.1总测试结果概述**

MSPSS系统的测试从2017-12-20开始持续到2018-1-8，共持续18天，按时完成测试计划。 

### **2.1.1测试用例设计**

1.系统测试

​			※概述：系统测试以功能性为主。(详见MSPSS进销存系统需求测试用例及回顾)

① 系统实现的主要功能

​				商品的进货退货销售的自动数据管理

​				商品分类，报表，单据的可视化管理

​				信息的集成与查看

​				日志、单据等的制定查看

② 系统为了安全性实现的功能 :

登陆功能:

系统的状态转换和权限控制。



##### 	※测试用例选择：主要是随机测试，应用等价类划分法和边界值分析法对需求阶段制品中的系统测试用例进行了一定的调整和修正。



​	2.集成测试

※概述：使用持续集成，体系结构设计阶段为每一层编写了Stub和Driver,用自动化集成工具完成，在构造阶段完成。

※测试用例选择：

​	随机选择+边界值分析

​	3.单元测试

​	※概述:

​			  ①测试了复杂的业务逻辑，包括查看报表，制定单据，期初建帐，

​				在构造阶段对业务逻辑层的逻辑进行了单元测试。

③DataBase以及Hibernate的读写进行了单元测试，确保数据层的操作正确。

​	   ※测试用例选择：

​			随机选择+边界值分析

### 2.1.2测试环境与配置

#### 2.1.2.1软硬件环境

表2.1.2.1 软硬件环境

| **硬件环境** | **应用服务器**                                | **客户端**                                  |
| -------- | ---------------------------------------- | ---------------------------------------- |
| 硬件配置     | CPU:Intel 酷睿i5-6300HQ 2.30GHZ Memory:4.00GB | CPU:Intel 酷睿i5-6300HQ 2.30GHZ Memory:4.00GB |
| 软件配置     | OS:windows10旗舰版 JDK: 1.8                 | OS:windows10旗舰版 JDK: 1.8                 |
| 网络环境     | 10M LAN                                  | 10M LAN                                  |

​		  

#### 2.1.2.2网络拓扑

​              ![img](http://101.37.19.32:10080/FX/MSPSS/raw/master/doc/img/%E7%BC%BA%E9%99%B7%E6%97%A5%E5%BF%97%E5%9B%BE%E7%89%87/wps6EEB.tmp.jpg)

图2.1.2.2 应用服务器端和客户端（分布式系统）

### 	2.1.3 测试方法和工具

表2.1.3 测试方法和工具

| **分类标准** | **类别**          | **测试方法**                                 | **测试工具**  | **备注** |
| -------- | --------------- | ---------------------------------------- | --------- | ------ |
| 测试对象     | 单元测试            | 黑盒与白盒测试相结合。部分使用TestDriver进行开发。对较为复杂的逻辑使用了语句覆盖的白盒测试，其他大多使用随机测试。 | Junit4.12 |        |
| 集成测试     | GitLabCI        |                                          |           |        |
| 系统测试     | 参照软件需求规格说明，手工测试 |                                          |           |        |
| 测试目标     | 功能              | 黑盒、手工、回归                                 | 随机测试      |        |
| 性能       | 黑盒、手工、回归        |                                          |           |        |
| 安全性      | 黑盒、手工、回归        |                                          |           |        |



## 2.2详细测试结果

### 2.2.1.系统测试

1.测试活动总结：

​	根据学习内容和系统需求变更，对需求阶段形成的需求测试用例进行了一定程度的修改。以功能性测试为主，对MSPSS系统进行了系统测试。这也是测试阶段主要完成的任务，测试活动时间较为紧张，通过增加工作时间，顺利完成预定的测试计划。

2.测试任务的结果总结：

​	对比系统需求规格说明书，对每项需求进行了测试。就已测试的部分而言，系统能够完成规格说明提出的所用功能性需求和其他的非功能性需求。

3.缺陷和辨析总结：

表2.2.1-1 系统测试重要缺陷分析表

| **模块名称**                                 | **缺陷编号** | **简要描述**                          | **分析结果**                                 | **测试通过** |
| ---------------------------------------- | -------- | --------------------------------- | ---------------------------------------- | -------- |
| blimpl.accountblimpl.*                   | 1.1      | 查看Account报错NULL Pointer Exception | UI层没有完成初始化                               | 失败->通过   |
| network.BillClientNetworkImpl            | 1.2      | 财务人员无法查看报表                        | 数据层hbm.xml文件映射发生错误，导致Entity中变量无法对应       | 失败->通过   |
| blimpl.commodityblimpl.CommodityBLImpl   | 1.3      | 商品分类的编号子分类无法正常显示                  | 数据库自动生成编号，但由于编号类型为VARCHAR(255),乱码显示比较影响美观，所以改为字母，但一个节点最多只有26个子节点 | 大部分情况通过  |
| uiadminui.*                              | 1.4      | 登陆时无法进行正常的角色识别                    | 测试时调用stub,集成没有及时更改为impl，导致使用的数据为桩中的虚假数据  | 失败->通过   |
| dataimpl.billdataimpl.*                  | 1.5      | 总经理审批单据之后，数据库状态没有修改               | hbm.xml映射文件中没有添加commit_time的映射，导致错误      | 失败->通过   |
| ui.cheifmanager.*                        | 1.6      | 当没有添加成功时，提示选课成功                   | 当没有添加任何商品，系统仍然根据系统时间，生成一条内容为空的记录。        | 失败->通过   |
| ui.financemanui.*                        | 1.7      | 刷新列表会产生垃圾数据                       | 在添加数据到table时没有clear table导致原来的数据还留在table上 | 失败->通过   |
| network.UserClientNetworkImpl            | 1.8      | 返回null并且不会报错RemoteException       | 没有return服务器的数据，而是直接catch并返回null          | 失败->通过   |
| blimpl.generalaccountblimpl.*            | 1.9      | 没有输入条件依然可以返回期初建账信息                | CriteriaClause在添加时没有add多重条件，导致返回交集       | 失败->通过   |
| blimpl.customerblimpl.*                  | 1.10     | 客户可以重复添加                          | ① 没有判断客户是否存在② 存在及时性的问题，即添加不同客户，此时课程还没有写回，服务器端会会认为不存在此客户可以创建。可以使用及时写回的策略。 | 失败->通过   |
| dataservice.PromotionDataService         | 1.11     | 无法范围查找                            | 接口错误，Object写为String,导致无法映射其他数据类型         | 失败->通过   |
| dataimpl.billdataimpl.BillDataServiceFactory | 1.12     | 当发生同一账号不同客户机登陆的时候，会出现问题。          | 把逻辑设置为单件。                                | 失败->通过   |
| ui.stocksalemanager.*                    | 1.13     | 计算价格时结果有问题                        | double写为int，导致转换错误                       | 失败->通过   |

 

表2.2.1-1系统测试残留问题和未解决问题列表

| **模块名称**                               | **缺陷编号** | **性质** | **简要描述**      | **原因分析**                                 |
| -------------------------------------- | -------- | ------ | ------------- | ---------------------------------------- |
| blimpl.commodityblimpl.CommodityBLImpl | 1.3      | 可忽略    | 子分类的节点不超过26个  | 数据库自动生成编号，但由于编号类型为VARCHAR(255),乱码显示比较影响美观，所以改为字母，但一个节点最多只有26个子节点 |
| Message.email                          | 无        | 可忽略    | 发送邮件功能运行不一定成功 | 网络问题以及邮箱是否有垃圾邮件防御                        |

 

4.产品质量评估

​	概述：总体来说，系统基本完成了所有系统需求规格说明书中对功能性方面要求的内容，对于其他的非功能性需求，做出了一定的努力，比如为了安全，将用户密码编码；为了易用性，更新了人机交互设计；但是易用性，性能，都没有经过测试工具的验证，需要持续改进。

​	备注：系统测试中发现的缺陷数目并不多，我们认为这是由于在集成测试和单元测试中消除了很多缺陷。

​	结论：达成预定目标。

5.度量数据

​	1.系统测试缺陷数据：

​	严重缺陷：13（具体情况见表2.2.1-1 系统测试重要缺陷分析表)

​	一般缺陷：2（表2.2.1-1系统测试残留问题和未解决问题列表）

无影响缺陷：3（主要体现在界面提示信息设置的合理性，界面跳转的合理性等方面与软件工程艺术性相关的东西，上节缺陷分析中省略）

​	2.系统测试覆盖度：

​	详见系统测试用例文档中的覆盖情况度量。

### 2.2.2.集成测试

1.测试活动总结

​	在体系结构设计阶段结束的时候，为每一层开发了Stub和部分层开发了Driver。在构造阶段，除了对数据文件的读写外，其余全部采用自顶向下的开发结构，每开发一个层次，就使用下一个层次的Stub，进行maven build。逐步将下层集成至已经开发完成的代码当中。

2.测试任务的结果总结

​	任务1：

​	描述：集成Data层对数据库的操作。

​	编写了Data层对实体类读写进行封装和实现的Hibernate，用Driver对其进行了驱动测试，以便以后集成。

​	结论：通过。足以完成需求。

​	问题：无

​	任务2：

​	描述:集成UI与BL层。

​	完成业务逻辑层之后将UI层中使用Stub的部分替换为实现代码将BL层集成进系统。

​	结论：最终通过。

​	任务3：

​	描述：集成Data层

​	完成Data层之后将BL层中使用Stub的部分替换为实现代码，将Data层集成进系统。

​	结论：最终通过。

3.缺陷和辨析

​						表2.2.2-1 集成测试严重缺陷分析

| **模块名称** | **缺陷编号** | **简要描述**                                 | **分析结果**                  | **测试通过** |
| -------- | -------- | ---------------------------------------- | ------------------------- | -------- |
| 客户端模块    | 2.1      | ui层与bl层之间采用controller和controllerFactory进行交互，但是ui层采用角色进行划分，bl层使用功能进行划分，导致层与层之间的依赖关系过于复杂不符合分层风格的设计原则。 | 修改风格，改为分层风格               | 通过       |
| BL和UI    | 2.2      | Build报错，NULL Pointer Exception           | 原因是Factory没有正确初始化         | 通过       |
| Data     | 2.3      | 服务器报错，系统显示找不到class。                      | 没有导入Hibernate依赖到pom.xml文件 | 通过       |
| BL       | 2.4      | Build报错，显示NullPointereException。         | 没有处理NULL的情况               | 通过       |
| RMI      | 2.5      | Rmi 和Remote Exception。                   | rmi无法识别ServerNetworkImpl  | 通过       |
| BL       | 2.6      | NullPointerException                     | Network没有return服务器端的返回值   | 通过       |
| UI       | 2.7      | 无法识别fxml文件路径                             | 路径错误                      | 通过       |
| MYSQL    | 2.8      | String类型显示“？？？”乱码                        | 数据库要加入UTF-8限制             | 通过       |
| Ui       | 2.9      | ui层登陆界面之后界面跳转有问题。                        | 没有改为stub为impl             | 通过       |

​						

表2.2.2-2 集成测试残留缺陷及未解决问题列表

| **模块名称** | **缺陷编号** | **性质** | **简要描述** | **原因分析** |
| -------- | -------- | ------ | -------- | -------- |
|          |          |        |          |          |

 

4.产品质量评估：

​	概述，在集成过程中使用gitlabCI进行自动化构建，最终构建结果是SUCCESS。期间出现build失败的情况，查找缺陷，定位缺陷并更正。

​	结论：达成预期目标，测试通过。

5.度量数据

1.集成测试缺陷数据：

​	严重缺陷：9

​	一般缺陷：0

​	无影响缺陷：0

2.集成测试覆盖度：

### 2.2.3.单元测试

1.测试活动总结：

​	大体上使用了TestDriver的思想指导开发，在构造阶段，对于有较为复杂的逻辑处理和外部内部接口的类，先编写Junit测试用例。并且编写了mock object以隔离外界环境对此类的影响，而后进行开发，开发完成之后，先进行Junit自动化测试，通过之后提交。

2.测试任务的结果总结：

​	保证所有进行单元测试的类，方法自动化测试通过。也即保证了该方法就有系统所期待的输入输出和不变式。

3.缺陷和辨析

表2.2.3-1 单元测试重要缺陷分析

| **模块名称**       | **缺陷编号** | **简要描述** | **分析结果**                    | **测试通过** |
| -------------- | -------- | -------- | --------------------------- | -------- |
| GeneralAccount | 3.1      | List映射错误 | Hibernate list无法对应ArrayList | 通过       |
| CustomerBL     | 3.2      | Bill编号错误 | 数据库获取编号方法没有匹配               | 通过       |

 

表2.2.3-2 单元测试残留于未解决问题列表

| **模块名称** | **缺陷编号** | **性质** | **简要描述** | **原因分析** |
| -------- | -------- | ------ | -------- | -------- |
|          |          |        |          |          |

 

4.产品质量评估：

​	1.概述：单元测试全部测试用例Junit自动化测试SUCCESS，达到预定的目标，对复杂逻辑基本实现代码覆盖。

​	2.备注：上表只显示了系统排课算法的缺陷分析，主要有两个原因。①这是系统中最复杂的逻辑，而且复杂度基本集中在构造阶段，对外接口较为简单，实现较为复杂。②在开发过程中没有记录debug的情况，没有历史记录。

​	3.结论：质量达到预期标准，可以进行后续的打包安装工作。

5.度量数据：

​	1.缺陷度量：

​	缺陷数目难以找到真实可靠的数据。

​	2.覆盖度度量：

​	使用Maven Cobertura工具进行度量。度量结果如下图示例

 

图2.2.3.5 覆盖度度量

​	总体来说对于有较为复杂业务逻辑（这里指不是只含有getter和setter）的BL层和Data层基本实现了方法的全覆盖。

### 2.2.3评估产品质量

​	经过对系统测试，集成测试以及单元测试结果的分析总结以及对测试过程的回顾和反思。按照系统仍然存在的缺陷数目大致与最后一次测试缺陷数目持平的原则，我们认为系统的质量在功能性，部分非功能性上达到了预期，符合客户需求，可以交付使用。

### 2.2.4度量数据总结

​	大量LOC分布在ui层，尤其是使用可视化界面编辑工具产生的不可修改代码当中，对这些代码，难以同时也没有必要开发测试用例。而系统的主要复杂度只要集中在BL层，对BL层的单元测试测试用例覆盖度更有意义。所以我们认为测试的质量是得到保证的。

​	

## 2.3决策理由

​	1.系统测试：

通过：完全按照测试用例设计的输入，得到预期的输出，不变式（如果有的话）符合要求。

不通过：对于所有的输入，系统没有给出预期的输出，或者发生严重出现严重错误（死机，提示错误等）

有条件通过：①在一定条件下，产生符合预期的输出，在其他条件下产生错误的输出或者无输出。②对于部分输入，产生符合预期的输出，对于其他输入产生错误的输出或者无输出。

​	2.集成测试：

​		通过：maven clean build输出结果为SUCCESS

​		不通过：maven clean build 输出结果为FAILTURE

​		有条件通过：无

​	3.单元测试：

​		通过：junit自动测试执行结果为全部通过SUCCESS

​		不通过：junit自动测试执行结果为全部FAILTURE

​		有条件通过：junit自动测试执行结果部分SUCCESS部分FAILTURE

## 2.4总结与建议

### 2.4.1总体性评价

产品是为MSPSS开发的选课系统，业务目标是提高用户满意度，总体来说我们任务业务目标实现的可能性很大。首先用户满意度大半取决于人机交互设计，为此，专门进行了二次人机交互设计，对艺术性，可用性，易用性，易学性做了专门的考量。功能齐全，覆盖了系统用例文档中的全部用例。

同时，项目失败的可能性是存在的，比如对选课没有做到完全的仿真，没有考量校区。

​	另使用文件系统对当下打包安装有利，但是为后续大批量数据的积累埋下了隐患，可能会对系统的性能造成一定的影响。没有进行压力测试，并发性是可能达不到实际要求。

### 2.4.2安装环境

​	安装环境为windows10（经过测试），客户下载压缩包，解压之后直接使用单击.exe文件就可以使用![img](http://101.37.19.32:10080/FX/MSPSS/raw/master/doc/img/%E7%BC%BA%E9%99%B7%E6%97%A5%E5%BF%97%E5%9B%BE%E7%89%87/0ZS0TB%7D4I4$SIF2UO35%7B7%7DH.png)

### 2.4.3经验总结

①重视需求，尽可能的在需求阶段发现所有的需求（实际上认为不可能在需求阶段发现所有的需求），并形成清晰的说明，有助于后续的开发。反之，如果在需求中存在没有发现的重要需求，会严重影响系统的逻辑完备性，功能全面性，大大加大后续工作量，并且直接影响设计的质量。项目进行中，在体系结构设计阶段新增了需求，感受到工作量远比需求阶段发现需求多，认为需要提高需求工作的质量。

②体系结构设计阶段最好由核心程序员为主导，（如果可能的话，负责最复杂业务逻辑的程序员应该是核心程序员），以实现为依据进行规划设计。明确层与层之间的调用接口。

③制定规约，所有人在体系结构设计完成之后，必须按照体系结构设计产生的项目框架进行详细设计和后续的构造。

④详细设计阶段中可以更多的加入构造阶段的东西，我们发现在真正构造之前，所有详细设计的结果基本都是无效的不现实的，但是为了避免出现build& fix的情况，这个阶段的构造应该以编写接口为主。

⑤在构造阶段一定不要轻易修改接口，会导致一系列严重的问题，依赖于此的程序可能会崩溃，最直观的是配置管理中的版本控制会出现版本冲突，持续集成会中断。在实现的时候可以考虑只增不改，增量式开发。

⑥任务的分配要科学合理，量体裁衣，按照个人的优缺点和劳动生产力进行分配。

⑧一致性的问题，提前商量命名规范遵循一定的命名规范，出于可读性的考量。







#### 





