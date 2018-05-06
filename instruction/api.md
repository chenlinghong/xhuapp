## 返回格式说明

##### 返回JSON字符串示例

    {
        "code" : "",
        "message" : "",
        "data" : {}
    }

##### 返回参数说明

    code(请求状态返回码)
    
        1 : 请求成功
        0 ：请求失败
        
    message(请求结果说明)
    
        请求成功时描述请求成功
        请求数据失败时则描述请求失败原因
        
    data(请求数据)
    
        请求成功时返回请求数据
        请求失败时为空

##### 说明

本系统所返回数据均以以上描述固定格式

**后续仅对data数据进行说明**




#### 用户相关API

##### 一、用户登录

1、接口URL

    http://120.77.170.124:8080/xhuapp/user/login.do（注：部署到服务器后更改IP地址）

2、参数说明

    account(string)：用户登录帐号（手机号码或用户名）(必填)
    password(string)：用户密码(必填)
    
3、返回（JSON格式字符串）

    user_id：用户登录成功返回用户ID，登录失败返回-1
    username：用户姓名
    password：用户密码（鉴于安全，一般为空）
    gender：用户性别
    birthday：用户出生日期
    head_portrail：用户头像图片路径
    introduce：用户介绍
    telphone：用户电话号码
    longitude: 经度
    latitude: 纬度
    fans : 粉丝数
    attention : 关注数
    （注：若登录失败，返回为空）

4、请求方式
    
    POST/GET
    
5、示例

通过用户名和密码进行登录验证

    http://120.77.170.124:8080/xhuapp/user/login.do?account=Bob&password=123123

通过电话号码和密码进行登录验证

    http://120.77.170.124:8080/xhuapp/user/login.do?account=13008142300&password=123456


##### 二、用户注册

1、接口URL

    http://120.77.170.124:8080/xhuapp/user/register.do

2、参数说明

    username(string)：用户姓名(必填)
    password(string)：用户密码(必填)
    telphone(string)：用户电话号码(必填)
    gender(string)：用户性别
    birthday(string)：用户出生日期(yyyy-MM-dd)
    introduce(string)：用户介绍
    longitude(string): 经度
    latitude(string): 纬度
    code(string) : 短信验证码(必填)

3、返回（JSON格式字符串）

    user_id：用户注册成功返回用户ID
    username：用户姓名
    password：用户密码（鉴于安全，一般为空）
    gender：用户性别
    birthday：用户出生日期
    head_portrail：用户头像图片文件路径
    introduce：用户介绍
    telphone：用户电话号码
    （注：若注册成功，返回值均为数据库中查询当前注册用户所得。若注册失败，返回空）

4、示例

    http://120.77.170.124:8080/xhuapp/user/register.do?username=Alice&password=123456&gender=0&birthday=1999-1-1
        &introduce=good&telphone=13008142300&code=123456

##### 三、修改用户基本信息（姓名、密码、性别、出生日期、介绍、电话号码）

1、接口URL

    http://120.77.170.124:8080/xhuapp/user/modify.do

2、参数说明

    user_id(int)：用户ID(必填)
    username(string)：用户姓名
    password(string)：用户密码
    gender(string)：用户性别
    birthday(string)：用户出生日期(yyyy-MM-dd)
    introduce(string)：用户介绍
    telphone(string)：用户电话号码
    longitude(string):经度
    latitude(string):纬度

3、返回（JSON字符串）

    空

4、示例

    http://120.77.170.124:8080/xhuapp/user/modify.do?user_id=4&username=Alice&password=123123&gender=girl
        &birthday=1999-1-31&introduce=I am a good girl&telphone=13008142301


##### 四、修改用户头像

1、接口URL

    http://120.77.170.124:8080/xhuapp/user/modify/head_portrail.do

2、参数说明

    user_id(int)：用户ID(必填)
    file(MultipartFile):用户新头像图片文件（必填）

3、返回（JSON字符串）

    空

4、示例


##### 五、发送短信验证码

1、接口URL

    http://120.77.170.124:8080/xhuapp/user/sms.do

2、参数说明

    telphone(string)：电话号码(必填)

3、返回（JSON字符串）

    空

4、示例

    http://120.77.170.124:8080/xhuapp/user/sms.do?telphone=13008142306

##### 六、找回密码

1、接口URL

    http://120.77.170.124:8080/xhuapp/user/recover.do

2、参数说明

    code(string)：短信验证码(必填)
    password(string) : 新密码（必填）

3、返回（JSON字符串）

    空

4、示例

    http://120.77.170.124:8080/xhuapp/user/recover.do?code=123456&password=123123
   
##### 七、通过User_id获取用户信息

1、接口URL

    http://120.77.170.124:8080/xhuapp/user/find.do

2、参数说明

    user_id(int)：用户ID(必填)

3、返回（JSON字符串）

    user_id：用户ID
    username：用户姓名
    password：用户密码（鉴于安全，一般为空）
    gender：用户性别
    birthday：用户出生日期
    head_portrail：用户头像图片路径
    introduce：用户介绍
    telphone：用户电话号码
    longitude: 经度
    latitude: 纬度
    
4、示例

    http://120.77.170.124:8080/xhuapp/user/find.do?user_id=1
   
##### 八、获取粉丝列表

1、接口URL

    http://120.77.170.124:8080/xhuapp/user/fans/get.do

2、参数说明

    user_id(int)：用户ID(必填)

3、返回（JSON字符串）

    粉丝用户ID列表

4、示例

    http://120.77.170.124:8080/xhuapp/user/fans/get.do?user_id=1;

##### 九、获取关注列表

1、接口URL

    http://120.77.170.124:8080/xhuapp/user/attention/get.do

2、参数说明

    user_id(int)：用户ID(必填)

3、返回（JSON字符串）

    关注用户ID列表

4、示例

    http://120.77.170.124:8080/xhuapp/user/attention/get.do?user_id=1;


##### 十、添加关注

1、接口URL

    http://120.77.170.124:8080/xhuapp/user/attention/add.do

2、参数说明

    user_id(int)：用户ID(必填)
    attention_id(int):关注用户ID（必填）

3、返回（JSON字符串）

   反馈信息

4、示例

    http://120.77.170.124:8080/xhuapp/user/attention/add.do?user_id=1&attention_id=2


##### 十一、取消关注

1、接口URL

    http://120.77.170.124:8080/xhuapp/user/attention/delete.do

2、参数说明

    user_id(int)：用户ID(必填)
    attention_id(int):关注用户ID（必填）

3、返回（JSON字符串）

    反馈信息

4、示例

    http://120.77.170.124:8080/xhuapp/user/attention/delete.do?user_id=1&attention_id=2



#### 农场相关API

##### 一、获取农场信息

1、接口URL

    http://120.77.170.124:8080/xhuapp/user/farm/get.do

2、参数说明

    user_id（int）： (必填)
    
3、返回（JSON格式字符串）

    farm_id（int）：农场ID
    name（String）： 农场名称
    longitude（String）： 经度
    latitude（String）： 纬度
    location_name（String）： 位置名称
    user_id（int）： 用户ID
    area（int）： 农场面积
    introduce(String) : 农场介绍
    photo(String) : 农场图片URL（多个URL拼接字符串，用空格分割）
  

4、请求方式
    
    POST/GET
    
5、示例

    http://120.77.170.124:8080/xhuapp/user/farm/get.do?user_id=1

##### 二、新增农场

1、接口URL

    http://120.77.170.124:8080/xhuapp/user/farm/add.do

2、参数说明

    name（String）： 农场名称
    longitude（String）： 经度(必填)
    latitude（String）： 纬度（必填）
    location_name（String）： 位置名称
    user_id（int）： 用户ID（必填）
    area（int）： 农场面积【默认为0，单位：亩】
    introduce(String) : 农场介绍
    files （MultipartFile[]）: 文件流数组
    
3、返回（JSON格式字符串）

    无

4、请求方式
    
    POST/GET
    
5、示例

    无


##### 三、修改农场信息

1、接口URL

    http://120.77.170.124:8080/xhuapp/user/farm/modify.do

2、参数说明

    name（String）： 农场名称
    longitude（String）： 经度
    latitude（String）： 纬度
    location_name（String）： 位置名称
    user_id（int）： 用户ID（必填）
    area（int）： 农场面积【单位：亩】
    introduce(String) : 农场介绍
    
3、返回（JSON格式字符串）

    无

4、请求方式
    
    POST/GET
    
5、示例

    http://120.77.170.124:8080/xhuapp/user/farm/modify.do?user_id=1&area=2


##### 四、修改农场图片

1、接口URL

    http://120.77.170.124:8080/xhuapp/user/farm/modifyphoto.do

2、参数说明

    user_id（int）： 用户ID（必填）
    files （MultipartFile[]）: 文件流数组(必填)
    
3、返回（JSON格式字符串）

    无

4、请求方式
    
    POST/GET
    
5、示例

    无


#### 动态相关API

##### 一、查找单个动态

1、接口URL

    http://120.77.170.124:8080/xhuapp/Dynamic/findDynamicById.do

2、参数说明

    dynamic_id（int）： (必填)
    
3、返回（JSON格式字符串）

    code（int）：是否成功的信息，成功返回1，失败返回0
    msg（String）： 返回操作信息
    dynamic_id（int）： 动态的ID，成功找到返回正确值，若失败无返回下同
    title（String）： 动态的标题
    introduce（String）： 动态的介绍正文
    picture（String）： 动态的图片地址
    user_id_f（int）： 动态的用户从属
   

4、请求方式
    
    POST/GET
    
5、示例

    http://120.77.170.124:8080/xhuapp/Dynamic/findDynamicById.do?dynamic_id=1
    
##### 二、查找用户的多个动态

1、接口URL
    
    http://120.77.170.124:8080/xhuapp/Dynamic/findDynamicByUserId.do
    
2、参数说明
    
    user_id_f（int）：(必填)
        
3、返回（JSON格式字符串）
    
    code（int）： 是否成功的信息，成功返回1，失败返回0
    msg（String）： 返回操作信息
    dynamicList（List<Dynamic>）： 动态的List数组每个对象包含下列属性
        dynamic_id（int）： 动态的ID，成功找到返回正确值，若失败无返回下同
        title（String）：动态的标题
        introduce（String）： 动态的介绍正文
        picture（String）： 动态的图片地址
        user_id_f（int）： 动态的用户从属
   
    
4、请求方式
        
    POST/GET
        
5、示例
    
    http://120.77.170.124:8080/xhuapp/Dynamic/findAllDynamicByUserId.do?user_id_f=1

##### 三、通过动态的ID修改动态
    
1、接口URL
    
    http://120.77.170.124:8080/xhuapp/Dynamic/modifyDynamicById.do
    
2、参数说明
    
    dynamic_id（int）： (必填)
    title（String）： 标题
    introduce（String）： 介绍正文
    user_id_f（int）：用户的id(一般不能修改)
        
3、返回（JSON格式字符串）
    
    code（int）： 是否成功的信息，成功返回1，失败返回0
    msg（String）： 返回操作信息
    dynamic_id（int）： 动态的ID，成功找到返回正确值，若失败无返回下同
    title（String）： 动态的标题
    introduce（String）： 动态的介绍正文
    user_id_f（int）： 动态的用户从属         

    
4、请求方式
        
    POST/GET
        
5、示例
    
    http://120.77.170.124:8080/Dynamic/modifyDynamicById.do?dynamic_id=14&title=erwre&
        introduce=esrsr&user_id_f=3 
        
##### 四、通过动态的ID修改图片
    
1、接口URL
    
    http://120.77.170.124:8080/xhuapp/Dynamic/modifyPicById.do
    
2、参数说明
    
    dynamic_id（int）： (必填)
    prepicfilepath（String): 要修改图片的服务器地址
    picfile(file): 需要更改的图片的文件流
        
3、返回（JSON格式字符串）
    
    code(int):  是否成功的信息，成功返回1，失败返回0
    msg(String): 返回操作信息
    dynamic_id(int): 动态的ID，成功找到返回正确值，若失败无返回下同
    picture 图片的服务器端如果只删除返回null 
 
    
4、请求方式
        
    POST
        
5、示例
    
    http://120.77.170.124:8080/Dynamic/modifyPicById.do?dynamic_id=14&prepicfilepath="服务器端图片地址"&picfile=63909109_p0.jpg

##### 五、删除一个动态
    
1、接口URL
    
    http://120.77.170.124:8080/xhuapp/Dynamic/deleteOneDynamicById.do
    
2、参数说明
    
    dynamic_id int 动态的ID
        
3、返回（JSON格式字符串）
    
    code int 是否成功的信息，成功返回1，失败返回0
    msg String 返回操作信息
        
           
4、请求方式
        
    POST/GET
        
5、示例
    
    http://120.77.170.124:8080/Dynamic/deleteOneDynamicById.do?dynamic_id=2

##### 六、创建一个动态

1、接口URL
    
    http://120.77.170.124:8080/xhuapp/Dynamic/insertOneDynamic.do
    
2、参数说明
    
    title String 动态或者需求的标题
    introduce String 动态或者需求的介绍
    CommonsMultipartFile[] 图片流数组
    user_id_f int 用户的id
    dynamic_type boolean 动态的类型
    request HttpServletRequest 请求对象
        
3、返回（JSON格式字符串）
    
    code int 是否成功的信息，成功返回1，失败返回0
    msg String 返回操作信息
    title String 动态或者需求的标题
    introduce String 动态或者需求的介绍
    CommonsMultipartFile[] 图片流数组
    user_id_f int 用户的id
    dynamic_type boolean 动态的类型true(需求) or false(动态) 
        
           
4、请求方式
        
    POST/GET
        
5、示例
    
    http://120.77.170.124:8080/Dynamic/insertOneDynamic.do?title=test&introduce=test&picfile=63826574_p0.jpg&picfile=63902583_p0.jpg&user_id_f=3&dynamic_type=0

##### 七、查找所有的动态或者需求

1、接口URL
    
    http://120.77.170.124:8080/xhuapp/Dynamic/findDynamicByType.do
    
2、参数说明
    
    dynamic_type boolean 0为需求1为动态
        
3、返回（JSON格式字符串）
    
    code int 是否成功的信息，成功返回1，失败返回0
    msg String 返回操作信息
    一般有多个，返回的每个信息如下
    title String 动态或者需求的标题
    introduce String 动态或者需求的介绍     picture String 图片的服务器地址
    user_id_f int 用户的id
    dynamic_type boolean 动态的类型true(需求) or false(动态) 
           
4、请求方式
        
    POST/GET
        
5、示例
    
    http://120.77.170.124:8080/Dynamic/findDynamicByType.do?dynamic_type=0
    
   