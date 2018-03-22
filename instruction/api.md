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

##### 测试接口

1、接口URL

    http://127.0.0.1:8080/xhuapp/user/test.do（注：部署到服务器后更改IP地址）

2、参数说明

    user_id(int)：用户ID(必填)
    
3、返回（JSON格式字符串）

    user_id：用户登录成功返回用户ID，登录失败返回-1
    username：用户姓名
    password：用户密码（鉴于安全，一般为空）
    gender：用户性别
    birthday：用户出生日期
    head_portrail：用户头像图片路径
    introduce：用户介绍
    telphone：用户电话号码
    （注：若登录失败，参数为默认值）

4、请求方式
    
    POST/GET
    
5、示例

    http://127.0.0.1:8080/xhuapp/user/test.do?user_id=1