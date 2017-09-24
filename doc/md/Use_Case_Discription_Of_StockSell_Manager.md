| **参与者**    | **用例**                                   |
| ---------- | ---------------------------------------- |
| **库存管理人员** | 1.商品分类  2.商品管理  3.库存查看  4.库存盘点  5.       |
| **进货销售人员** | 1.客户管理  2.制定进退货单  3.制定销售退货单              |
| **财务人员**   | 1.账户管理  2.收付款单  3.查看导出报表  4.期初建账  5.查询日志 |
| **总经理**    | 1.审批单据  2.查看报表  3.制定促销策略  4.查询日志         |



## 用例1 客户管理

| **ID**   | 1                                        | **名称**      | 客户管理 |
| -------- | ---------------------------------------- | ----------- | ---- |
| **创建者**  | 伏家兴                                      | **最后一次更新者** |      |
| **创建日期** | 2017/9/14                                | **最后更新日期**  |      |
| **参与者**  | 进货销售人员，目标是正确快速地完成对客户信息的操作                |             |      |
| **触发条件** | 有客户信息需要改动                                |             |      |
| **前置条件** | 进货销售人员必须已经被识别和授权。                        |             |      |
| **后置条件** | 系统存储日志记录                                 |             |      |
| **优先级**  | 高                                        |             |      |
| **正常流程** | 1． 登陆系统 <br />  2． 选择客户管理<br />   3． 如果是查询客户<br />   3.1 输入关键字（包括编号、分类（进货商、销售商）、级别、姓名、电话、地址、邮编、电子邮箱、应收额度、应收、应付、默认业务员。） <br />  3.2 系统从数据库中搜索符合关键字信息的客户  <br /> 3.3 系统显示符合搜索条件的所有客户 <br />  4． 如果是增加客户<br />   4.1 输入客户的编号、分类（进货商、销售商）、级别（五级，一级普通用户，五级VIP客户）、姓名、电话、地址、邮编、电子邮箱、应收额度、应收、应付、默认业务员。（应收应付不可修改）<br />   4.2 从系统中查找该客户，如果不存在此用户，则添加一则用户信息。 <br />  5． 如果是删除客户： <br />  5.1 输入要删除的客户的编号，在数据库中查询此客户<br />   5.2 如果编号一致，则从系统数据库中删除此用户的所有客户信息。 <br />  6． 如果是修改客户属性： <br />  6.1 通过关键字搜索或者编号信息来查询客户<br />   6.2 如果查询到客户，对其需要修改的属性进行修改（应收应付不可修改） |             |      |
| **扩展流程** | 1.3a. 数据库中没有存储查询的客户<br />   1. 系统显示不存在该客户，并提示重新查找  <br /> 2.1a. 输入的客户属性有缺少<br />   1. 系统提示缺少信息，提示操作人员核对信息重新输入<br />   2.2a.   数据库中已经存在要增加的客户的信息 <br />  1. 系统显示客户已存在，并提示核对信息重新添加。 <br />  3.1a.   数据库中不存在要删除的客户的编号 <br />  1. 提示该用户不存在并提示操作人员核对信息重新输入  <br /> 4.1a.   查询不到要查找的客户 <br />  1. 提示该用户不存在并提示操作人员核对信息重新输入   <br />4.2a. 操作人员对应收应付信息进行修改<br />   1. 提示该信息不可修改 |             |      |
| **特殊需求** |                                          |             |      |

## 用例2 制定进货单和进货退货单 

| **ID**   | 2                                        | **名称**      | 制定进货和进货退货单 |
| -------- | ---------------------------------------- | ----------- | ---------- |
| **创建者**  | 伏家兴                                      | **最后一次更新者** |            |
| **创建日期** | 2017/9/14                                | **最后更新日期**  |            |
| **参与者**  | 进货销售人员，目标是正确快速的制定进货单和进货退货单               |             |            |
| **触发条件** | 有需要处理的进货单或者进货退货单                         |             |            |
| **前置条件** | 进货退货人员必须已经被识别和授权。                        |             |            |
| **后置条件** | 存储制定进货单或者进货退货单的日志，包括操作人、货物信息等。           |             |            |
| **优先级**  | 高                                        |             |            |
| **正常流程** | 1.  操作分为制定进货单和制定进货退货单。<br />   2.  如果选择制定进货单。<br />         新建一个进货单包括：包括：**单据编号**（格式为：JHD-yyyyMMdd-xxxxx，后五位每天从1开始编号，所以一天最多可以生成99999条单子），**供应商，仓库，操作员，入库商品列表，备注，总额合计。**其中入库商品列表包含的信息有：**商品编号，名称（从商品选择界面进行选择），型号，数量（手动输入），单价（默认为商品信息中的进价），金额，备注（手动输入）**。没有下划线的部分是自动计算并填充进去的。进货单通过审批后，会更改库存数据和客户的应收应付数据。<br />   3.  如果选择制定销售退货单。<br />     新建一个进货退货单，单据号格式为：JHTHD-yyyyMMdd-xxxxx。其余信息和进货单内容相同 |             |            |
| **扩展流程** | 2a.   需要生成的单子超过最大范围（即超过99999条）<br />     1.  系统报错  <br /> 2b.   操作人员遗漏掉某些需要处理的货物信息  <br />        1.  系统提示有信息未填，并督促操作人员进行操作 |             |            |
| **特殊需求** |                                          |             |            |

 

## 用例3 制定销售单和销售退货单 

| **ID**   | 3                                        | **名称**      | 制定销售单和销售退货单 |
| -------- | ---------------------------------------- | ----------- | ----------- |
| **创建者**  | 伏家兴                                      | **最后一次更新者** |             |
| **创建日期** | 2017/9/14                                | **最后更新日期**  |             |
| **参与者**  | 进货销售人员，目标是正确快速的制定销售单和销售退货单               |             |             |
| **触发条件** | 有需要处理的销售单或者销售退货单                         |             |             |
| **前置条件** | 进货退货人员必须已经被识别和授权。                        |             |             |
| **后置条件** | 存储销售或者销售退货的日志，包括操作人、货物信息等。               |             |             |
| **优先级**  | 高                                        |             |             |
| **正常流程** | 1.  操作分为制定销售单和制定销售退货单。  <br /> 2.  如果选择制定销售单。<br />        新建一个销售单包括：包括：**单据编号**（XSD-yyyyMMdd-xxxxx），**客户**（仅显示销售商），**业务员**（和这个客户打交道的公司员工，可以设置一个客户的默认业务员），**操作员**（当前登录系统的用户），**仓库，出货商品清单，折让前总额，折让，使用代金卷金额，折让后总额，备注**。出货商品清单中要显示商品的**编号，名称**（从商品选择界面选择）**，**型号**，数量（**手工输入）**，单价**（默认为商品信息里的销售价，可修改）**，金额（自动生成），商品备注。**销售单通过审批后，会更改库存数据和客户的应收应付数据。 <br />  3.  如果选择制定销售退货单。<br />      新建一个销售退货单，单据号格式为：XSTHD-yyyyMMdd-xxxxx。其余信息和销售单内容相同 |             |             |
| **扩展流程** | 2a.   需要生成的单子超过最大范围（即超过99999条）<br />   1.  系统报错   <br />2b.   操作人员遗漏掉某些需要处理的货物信息  <br />1.  系统提示有信息未填，并督促操作人员进行操作 |             |             |
| **特殊需求** |                                          |             |             |

 