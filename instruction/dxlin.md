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

#### 动态相关API

##### 测试接口
    一。查找单个动态
    1、接口URL
    
        http://127.0.0.1:8080/xhuapp/Dynamic/findDynamicById.do?（注：部署到服务器后更改IP地址）
    
    2、参数说明
    
        dynamic_id int (必填)
        
    3、返回（JSON格式字符串）
    
        code int 是否成功的信息，成功返回1，失败返回0
        msg String 返回操作信息
        data
            dynamic_id int 动态的ID，成功找到返回正确值，若失败无返回下同
            title String 动态的标题
            introduce String 动态的介绍正文
            picture String 动态的图片地址
            user_id_f int 动态的用户从属
            dynamic_type bool true or false 1为需求0为动态
            address(String)：用户的地址
            prize(int):价格
            look_persons(int):浏览量
    4、请求方式
        
        POST/GET
        
    5、示例
    
        http://127.0.0.1:8080/xhuapp/Dynamic/findDynamicById.do?dynamic_id=1
        
    二，查找用户的多个动态
    1、接口URL
    
        http://127.0.0.1:8080/xhuapp/Dynamic/findDynamicByUserId.do?（注：部署到服务器后更改IP地址）
    
    2、参数说明
    
        user_id_f int (必填)
        
    3、返回（JSON格式字符串）
    
        code int 是否成功的信息，成功返回1，失败返回0
        msg String 返回操作信息
        dynamicList List<Dynamic> 动态的List数组每个对象包含下列属性
            dynamic_id int 动态的ID，成功找到返回正确值，若失败无返回下同
            title String 动态的标题
            introduce String 动态的介绍正文
            picture String 动态的图片地址
            user_id_f int 动态的用户从属
            dynamic_type int true or false 1为需求0为动态
            address(String)：用户的地址
            prize(int):价格
            look_persons(int):浏览量
    
    4、请求方式
        
        POST/GET
        
    5、示例
    
        http://127.0.0.1:8080/xhuapp/Dynamic/findDynamicByUserId.do?user_id_f=1
    三，通过动态的ID修改动态
    1、接口URL
    
        http://127.0.0.1:8080/xhuapp/Dynamic/modifyDynamicById.do?（注：部署到服务器后更改IP地址）
    
    2、参数说明
    
        dynamic_id int (必填)
        title String 标题(必填)如果不修改请填原数据
        introduce String 介绍正文(必填)如果不修改请填原数据
        address(String):地址（必填）如果不修改请填原数据
        prize(int):价格（必填）如果不修改请填原数据
        look_persons(int):浏览量（必填）如果不修改请填原数据
        
    3、返回（JSON格式字符串）
    
        code int 是否成功的信息，成功返回1，失败返回0
        msg String 返回操作信息
        dynamic_id int 动态的ID，成功找到返回正确值，若失败无返回下同
        title String 动态的标题
        introduce String 动态的介绍正文
        user_id_f int 动态的用户从属
        address(String)：用户的地址
        prize(int):价格
        look_persons(int):浏览量       

    
    4、请求方式
        
        POST/GET
        
    5、示例
    
    http://localhost:8080/Dynamic/modifyDynamicById.do?dynamic_id=14&title=erwre&introduce=esrsr&user_id_f=3 
    
    四，通过动态的ID修改图片
    1、接口URL
    
        http://127.0.0.1:8080/xhuapp/Dynamic/modifyPicById.do?（注：部署到服务器后更改IP地址）
    
    2、参数说明
    
        dynamic_id int (必填)
        prepicfilepath String 要修改的图片服务器地址
        picfile file 重新加入的图片流文件，如果不填就表示删之前的图片
        
    3、返回（JSON格式字符串）
    
        code int 是否成功的信息，成功返回1，失败返回0
        msg String 返回操作信息
        dynamic_id int 动态的ID，成功找到返回正确值，若失败无返回下同
        picture 图片的服务器端如果只删除返回null 
 
    
    4、请求方式
        
        POST
        
    5、示例
    
    http://localhost:8080/Dynamic/modifyPicById.do?dynamic_id=14&prepicfilepath="服务器端图片地址"&picfile=63909109_p0.jpg

    五，删除一个动态
    1、接口URL
    
        http://127.0.0.1:8080/xhuapp/Dynamic/deleteOneDynamicById.do?（注：部署到服务器后更改IP地址）
    
    2、参数说明
    
        dynamic_id int 动态的ID
        
    3、返回（JSON格式字符串）
    
        code int 是否成功的信息，成功返回1，失败返回0
        msg String 返回操作信息
        
           
    4、请求方式
        
        POST/GET
        
    5、示例
    
    http://localhost:8080/Dynamic/deleteOneDynamicById.do?dynamic_id=2
    六，创建一个动态
    1、接口URL
    
        http://127.0.0.1:8080/xhuapp/Dynamic/insertOneDynamic.do?（注：部署到服务器后更改IP地址）
    
    2、参数说明
    
        title String 动态或者需求的标题(必填)
        introduce String 动态或者需求的介绍（必填）
        picfile CommonsMultipartFile[] 图片流数组
        user_id_f int 用户的id（必填）
        dynamic_type int 动态的类型（必填）
        address(String):地址默认为成都
        prize(int):价格默认为0
        look_persons(int):浏览人数默认为0
        
    3、返回（JSON格式字符串）
    
        code int 是否成功的信息，成功返回1，失败返回0
        msg String 返回操作信息
            title String 动态或者需求的标题
            introduce String 动态或者需求的介绍
            CommonsMultipartFile[] 图片流数组
            user_id_f int 用户的id
            dynamic_type boolean 动态的类型true(需求) or false(动态) 
            address(String):地址默认为成都
            prize(int):价格默认为0
            look_persons(int):浏览人数默认为0
           
    4、请求方式
        
        POST/GET
        
    5、示例
    
    http://localhost:8080/Dynamic/insertOneDynamic.do?title=test&introduce=test&picfile=63826574_p0.jpg&picfile=63902583_p0.jpg&user_id_f=3&dynamic_type=0
    
    七，查找所有的动态或者需求
    1、接口URL
    
        http://127.0.0.1:8080/xhuapp/Dynamic/findDynamicByType.do?（注：部署到服务器后更改IP地址）
    
    2、参数说明
    
        dynamic_type boolean 0为需求1为动态
        
    3、返回（JSON格式字符串）
    
        code int 是否成功的信息，成功返回1，失败返回0
        msg String 返回操作信息
        一般有多个，返回的每个信息如下
            title String 动态或者需求的标题
            introduce String 动态或者需求的介绍
            picture String 图片的服务器地址
            user_id_f int 用户的id
            dynamic_type boolean 动态的类型true(需求) or false(动态) 
            address(String):地址默认为成都
            prize(int):价格默认为0
            look_persons(int):浏览人数默认为0
    4、请求方式
        
        POST/GET
        
    5、示例
    
    http://localhost:8080/Dynamic/findDynamicByType.do?dynamic_type=0
 #### 视频相关API
 
 ##### 测试接口
     一，插入一个视频数据
     1、接口URL
     
         http://127.0.0.1:8080/xhuapp/Video/insertOneVideo.do?（注：部署到服务器后更改IP地址）
     
     2、参数说明
     
         title String 动态的标题（必填）
         videofile File 视频流文件（必填）
         video_picture File 图片流文件 (必填)
         introduce String 动态的介绍正文（必填）
         video_type int 0-3 分为4种视频（必填）
         user_id_f int 动态的用户从属（必填）
         address String 地址默认为成都
         prize int 价格 默认为0
         look_persons int 查看量 默认为0
         
 
         
     3、返回（JSON格式字符串）
     
         code int 是否成功的信息，成功返回1，失败返回0
         msg String 返回操作信息
         data{
                 title String 动态的标题（必填）
                 videofile String 视频地址
                 video_picture String 图片地址
                 introduce String 动态的介绍正文（必填）
                 video_type int 0-3 分为4种视频（必填）
                 user_id_f int 动态的用户从属（必填）
                 address String 地址
                 prize int 价格 默认为0
                 look_persons int 查看量 默认为0
         }
     
     4、请求方式
         
         POST
         
     5、示例
     
         http://localhost:8080/Video/insertOneVideo.do?title=test&videofile=63909109_p0.mp4&video_picture=123.jpg&user_id_f=3&video_type=0&introduce=123&address=成都prize=500&look_persons=1000
         
     二，通过视频数据的id查找视频数据
         1、接口URL
         
             http://127.0.0.1:8080/xhuapp/Video/findVideoById.do?（注：部署到服务器后更改IP地址）
         
         2、参数说明
         
             video_id (必填)
             
         3、返回（JSON格式字符串）
         
             code int 是否成功的信息，成功返回1，失败返回0
             msg String 返回操作信息
             data{
                       title String 动态的标题（必填）
                       introduce String 动态的介绍正文（必填）
                       video_type int 0-3 分为4种视频（必填）
                       user_id_f int 动态的用户从属（必填）
                       address String 地址
                       prize int 价格 默认为0
                       look_persons int 查看量 默认为0
                 }
     
         
         4、请求方式
             
             POST/GET
             
         5、示例
         
              http://127.0.0.1:8080/xhuapp/Video/findVideoById.do?video_id=1
     三，通过视频的类型查找视频数据
         1、接口URL
         
             http://127.0.0.1:8080/xhuapp/Video/findVideosByType.do?（注：部署到服务器后更改IP地址）
         
         2、参数说明
         
            video_type int 视频的类型
             
         3、返回（JSON格式字符串）
             code int 是否成功的信息，成功返回1，失败返回0
             msg String 返回操作信息
             data{
                       title String 动态的标题（必填）
                       introduce String 动态的介绍正文（必填）
                       video_type int 0-3 分为4种视频（必填）
                       user_id_f int 动态的用户从属（必填）
                       address String 地址
                       prize int 价格 默认为0
                       look_persons int 查看量 默认为0
                 }    一般为数组            
             
             
         4、请求方式
             
             POST/GET
             
         5、示例
         
         http://127.0.0.1:8080/xhuapp/Video/findVideosByType.do?video_type=0 
         注 此接口请
     四，通过用户的ID查找视频数据
         1、接口URL
         
             http://127.0.0.1:8080/xhuapp/Video/findVideosByUser_id_f.do?（注：部署到服务器后更改IP地址）
         
         2、参数说明
         
             user_id_f int 用户的id
             
         3、返回（JSON格式字符串）
             code int 是否成功的信息，成功返回1，失败返回0
             msg String 返回操作信息
             data{
                       title String 动态的标题（必填）
                       introduce String 动态的介绍正文（必填）
                       video_type int 0-3 分为4种视频（必填）
                       user_id_f int 动态的用户从属（必填）
                       address String 地址
                       prize int 价格 默认为0
                       look_persons int 查看量 默认为0
                 }    一般为数组           
         4、请求方式
             
             POST/GET
             
         5、示例
         
         http://127.0.0.1:8080/xhuapp/Video/findVideosByUser_id_f.do?use_id_f=1  
           
     五，修改一个视频数据（修改视频地址另有接口）
         1、接口URL
         
             http://127.0.0.1:8080/xhuapp/Video/modifyVideoById.do?（注：部署到服务器后更改IP地址）
         
         2、参数说明
             title String 动态的标题
             introduce String 动态的介绍正文
             address String 地址
             prize int 价格 默认为0
             look_persons int 查看量 默认为0        
             
                       
         3、返回（JSON格式字符串）
             code int 是否成功的信息，成功返回1，失败返回0
             msg String 返回操作信息        
             title String 动态的标题
             introduce String 动态的介绍正文
             address String 地址
             prize int 价格 默认为0
             look_persons int 查看量 默认为0  
             注：全部为用户所填数据            
                         
         4、请求方式
             
             POST/GET
             
         5、示例
         
         http://localhost:8080/Video/insertOneVideo.do?title=test&videofile=63909109_p0.mp4&videofile=&user_id_f=3&video_type=0&introduce=123&address=成都prize=500&look_persons=1000
     六，修改视频数据的视频和视频图片
         1、接口URL
         
             http://127.0.0.1:8080/xhuapp/Video/modifyVideo_videoById.do?（注：部署到服务器后更改IP地址）
         
         2、参数说明
         
             video_id int 视频数据的ID
             videofile File 视频流可为空，如果为空表示删除这个视频
             video_picture File 图片流可为空，如果为空表示删除这个视频图片
             
         3、返回（JSON格式字符串）
         
             code int 是否成功的信息，成功返回1，失败返回0
             msg String 返回操作信息
             video_id int 视频数据的ID
             videofile CommonsMultipartFile 视频数据的视频服务器地址
             
                
         4、请求方式
             
             POST
             
         5、示例
         
         http://localhost:8080/Video/modifyVideo_videoById.do?video_id=4&videofile=test.mp4    
     七，删除一个视频数据
         1、接口URL
         
             http://127.0.0.1:8080/xhuapp/Video/deleteVideoById.do?（注：部署到服务器后更改IP地址）
         
         2、参数说明
         
             video_id int 视频的id
             
         3、返回（JSON格式字符串）
         
             code int 是否成功的信息，成功返回1，失败返回0
             msg String 返回操作信息
             
                
         4、请求方式
             
             POST/GET
             
         5、示例
         
           http://127.0.0.1:8080/xhuapp/Video/deleteVideoById.do?video_id=5
#### 视频相关API
 
##### 测试接口
     一，插入一个评论
     1、接口URL
    
         http://127.0.0.1:8080/xhuapp/Commont/insertOneCommont.do?（注：部署到服务器后更改IP地址）
     
     2、参数说明
     
        user_id_f(int):用户的id（必填）
        commont_body(String):评论的内容（必填）
        father_commont_id(int):是否有父评论，如果有请填实际值,默认为-1
         
     3、返回（JSON格式字符串）
     
         code(int)：错误0，正确1
         message(string) : 具体信息
     
     4、请求方式
         
         POST
         
     5、示例
     
          http://127.0.0.1:8080/xhuapp/Commont/insertOneCommont.do?user_id_f=1&commont_body=hello&father_commont_id=-1
          
     二，通过评论id查找评论数据信息
    
         1、接口URL
         
          http://127.0.0.1:8080/xhuapp/Commont/findCommontById.do?
         
         2、参数说明
         
             commont_id(int):评论的id
             
         3、返回（JSON格式字符串）
         
             code(int): 是否成功的信息，成功返回1，失败返回0
             massage(String): 返回操作信息
             data{
                       commont_id(int):评论的id
                       user_id_f(int):用户的id
                       commont_body(String)：评论内容
                       father_commont_id(int)：父评论的id
                 }
     
         4、请求方式
             
             POST/GET
             
         5、示例
         
              http://127.0.0.1:8080/xhuapp/Commont/findCommontById.do?commont_id=1
     三，通过用户的id查找其所有的评论
         1、接口URL
         
             http://127.0.0.1:8080/xhuapp/Commont/findCommontsByUser_id.do?
         
         2、参数说明
         
            user_id_f(int): 用户的id
             
         3、返回（JSON格式字符串）
             code(int): 是否成功的信息，成功返回1，失败返回0
             massage(String): 返回操作信息
             data{
                       commont_id(int):评论的id
                       user_id_f(int):用户的id
                       commont_body(String)：评论内容
                       father_commont_id(int)：父评论的id
                 } 一般为数组            
             
             
         4、请求方式
             
             POST/GET
             
         5、示例
         
             http://127.0.0.1:8080/xhuapp/Commont/findCommontsByUser_id.do?user_id_f=1

     四，通过评论的id查找其父评论的评论数据信息
         1、接口URL
         
             http://127.0.0.1:8080/xhuapp/Commont/findFather_CommontByCommont_id.do?
         
         2、参数说明
         
             commont_id(int)：评论数据的id
             
         3、返回（JSON格式字符串）
              code(int): 是否成功的信息，成功返回1，失败返回0
              massage(String): 返回操作信息
              data{
                        commont_id(int):评论的id
                        user_id_f(int):用户的id
                        commont_body(String)：评论内容
                        father_commont_id(int)：父评论的id
                  } 一般为数组           
         4、请求方式
             
             POST/GET
             
         5、示例
         
             http://127.0.0.1:8080/xhuapp/Commont/findFather_CommontByCommont_id.do?commont_id=1
           
     五，修改一个视频数据（修改视频地址另有接口）
         1、接口URL
         
             http://127.0.0.1:8080/xhuapp/Commont/findSon_Commont_idByCommont_id?
         
        2、参数说明
              
              commont_id(int)：评论数据的id
                  
        3、返回（JSON格式字符串）
              code(int): 是否成功的信息，成功返回1，失败返回0
              massage(String): 返回操作信息
              data{
                       commont_id(int):评论的id
                       user_id_f(int):用户的id
                       commont_body(String)：评论内容
                       father_commont_id(int)：父评论的id
                  } 一般为数组           
                         
        4、请求方式
             
             POST/GET
             
        5、示例
         
             http://127.0.0.1:8080/xhuapp/Commont/findSon_Commont_idByCommont_id?commont_id=1
     六，删除一个评论
         1、接口URL
         
             http://127.0.0.1:8080/xhuapp/Commont/deleteCommontById.do?
         
         2、参数说明
         
             commont_id(int):评论数据的id
             
         3、返回（JSON格式字符串）
         
             code(int): 是否成功的信息，成功返回1，失败返回0
             massage(String): 返回操作信息
             
                
         4、请求方式
             
             POST
             
         5、示例
         
         http://localhost:8080/Commont/deleteCommontById.do?commont_id=1
