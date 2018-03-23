## 返回格式说明

##### 返回JSON字符串示例

    {
        "code" : "1",
        "message" : "成功",
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

后续仅对data数据进行说明




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
    （注：若登录失败，user_id为-1，其余参数为默认值）

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

3、返回（JSON格式字符串）

    user_id：用户注册成功返回用户ID，登录失败返回-1
    username：用户姓名
    password：用户密码（鉴于安全，一般为空）
    gender：用户性别
    birthday：用户出生日期
    head_portrail：用户头像图片文件路径
    introduce：用户介绍
    telphone：用户电话号码
    （注：若注册成功，返回值均为数据库中查询当前注册用户所得。若注册失败，user_id为-1，其他值为原值）

4、示例

    http://120.77.170.124:8080/xhuapp/user/register.do?username=Alice&password=123456&gender=0&birthday=1999-1-1
        &introduce=good&telphone=13008142300

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

3、返回（JSON字符串）

    result:修改结果（布尔值）
    （注：如有属性不满足修改条件，则修改失败，返回false。反之返回true）

4、示例

    http://120.77.170.124:8080/xhuapp/user/modify.do?user_id=4&username=Alice&password=123123&gender=girl
        &birthday=1999-1-31&introduce=I am a good girl&telphone=13008142301


##### 四、修改用户头像(未进行测试)

1、接口URL

    http://120.77.170.124:8080/xhuapp/user/modify/head_portrail.do

2、参数说明

    user_id(int)：用户ID(必填)
    head_portrail(MultipartFile):用户新头像图片文件（必填）

3、返回（JSON字符串）

    result:修改结果（布尔值）
    （注：如有属性不满足修改条件，则修改失败，返回false。反之返回true）

4、示例



