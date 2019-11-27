# GAYYONG项目接口

请求说明

|请求方式|请求头|请求头的值|说明|
|---|---|---|---|
|GET|无|无|参数以"url?参数&参数"的形式位于Url|
|POST|Content-Type|x-www-form-urlencoded|参数以"参数&参数"的形式位于Body|
|返回内容|Content-Type|application/json;charset=UTF-8|返回内容为JSON格式|

### 1 User

---

#### 1.1 添加用户

##### 1.1.1 接口说明

|url|/user/add|
|---|---|
|请求方式|POST|
|Content-Type|x-www-form-urlencoded|

##### 1.1.2 请求参数

|参数名词|是否必须|描述|
|---|---|---|
|userId|是|用户id|
|pwd|是|用户密码|
|sex|是|性别|
|nickname|是|昵称|
|headUrl|否|头像url地址|

##### 1.1.3 返回样例
```json
{
    "status": 200,
    "msg": "添加成功",
    "data": {
        "userId": "123456",
        "pwd": "7ac66c0f148de9519b8bd264312c4d64",
        "sex": "女",
        "nickname": "ovo",
        "headUrl": "testtesttest",
        "collectionArticleList": null,
        "articleList": null,
        "follow": null,
        "follower": null
    }
}
```

#### 1.2 查询用户

##### 1.2.1 接口说明

|url|/user/get|
|---|---|
|请求方式|GET|

##### 1.2.2 请求参数

|参数名词|是否必须|描述|
|---|---|---|
|id|是|用户id|

##### 1.1.3 返回样例

###### 成功

```json
{
    "status": 200,
    "msg": "查询用户成功",
    "data": {
        "userId": "123456",
        "pwd": "7ac66c0f148de9519b8bd264312c4d64",
        "sex": "女",
        "nickname": "ovo",
        "headUrl": "testtesttest",
        "collectionArticleList": [],
        "articleList": [
            {
                "artId": 1,
                "title": "test",
                "artText": "iboigwegoi",
                "lasttime": "2019-11-17 18:35:29",
                "artTag": "qoq"
            }
        ],
        "follow": [],
        "follower": [
            {
                "userId": "12345",
                "pwd": "7ac66c0f148de9519b8bd264312c4d64",
                "sex": "女",
                "nickname": "ovo",
                "headUrl": "testtesttest"
            }
        ]
    }
}
```

###### 失败

```json
{
    "status": 200,
    "msg": "查询用户失败",
    "data": null
}
```

#### 1.3 修改用户

##### 1.3.1 接口说明

|url|/user/update|
|---|---|
|请求方式|POST|
|Content-Type|x-www-form-urlencoded|

##### 1.3.2 请求参数

|参数名词|是否必须|描述|
|---|---|---|
|userId|是|用户id|
|pwd|否|用户密码|
|sex|否|性别|
|nickname|否|昵称|
|headUrl|否|头像url地址|

##### 1.3.3 返回样例
```json
{
    "status": 200,
    "msg": "修改用户成功",
    "data": {
        "userId": "123456",
        "pwd": "7ac66c0f148de9519b8bd264312c4d64",
        "sex": "男",
        "nickname": "ovo",
        "headUrl": "testtesttest",
        "collectionArticleList": [],
        "articleList": [
            {
                "artId": 1,
                "title": "test",
                "artText": "iboigwegoi",
                "lasttime": "2019-11-17 18:35:29",
                "artTag": "qoq"
            }
        ],
        "follow": [],
        "follower": [
            {
                "userId": "12345",
                "pwd": "7ac66c0f148de9519b8bd264312c4d64",
                "sex": "女",
                "nickname": "ovo",
                "headUrl": "testtesttest"
            }
        ]
    }
}
```

#### 1.4 删除用户

##### 1.4.1 接口说明

|url|/user/delete|
|---|---|
|请求方式|GET|

##### 1.4.2 请求参数

|参数名词|是否必须|描述|
|---|---|---|
|id|是|用户id|

##### 1.4.3 返回样例

###### 成功

```json
{
    "status": 200,
    "msg": "删除成功",
    "data": null
}
```

###### 失败

```json
{
    "status": 200,
    "msg": "用户不存在",
    "data": null
}
```

#### 1.5 关注用户

##### 1.5.1 接口说明

|url|/user/follow|
|---|---|
|请求方式|GET|

##### 1.5.2 请求参数

|参数名词|是否必须|描述|
|---|---|---|
|userId|是|用户id|
|targetId|是|关注的id|

##### 1.5.3 返回样例

###### 成功

```json
{
    "status": 200,
    "msg": "关注成功",
    "data": null
}
```

###### 失败

```json
{
    "status": 500,
    "msg": "targetId未找到用户",
    "data": null
}
```

#### 1.6 取消关注

##### 1.6.1 接口说明

|url|/user/unfollow|
|---|---|
|请求方式|GET|

##### 1.6.2 请求参数

|参数名词|是否必须|描述|
|---|---|---|
|userId|是|用户id|
|targetId|是|取消关注的id|

##### 1.6.3 返回样例

###### 成功

```json
{
    "status": 200,
    "msg": "取消关注成功",
    "data": null
}
```

###### 失败

```json
{
    "status": 500,
    "msg": "userId和targetId未找到",
    "data": null
}
```

#### 1.7 用户登录

##### 1.7.1 接口说明

|url|/user/login|
|---|---|
|请求方式|GET|

##### 1.7.2 请求参数

|参数名词|是否必须|描述|
|---|---|---|
|userId|是|用户id|
|pwd|是|密码|

##### 1.7.3 返回样例

###### 成功

```json
{
    "status": 200,
    "msg": "登录成功",
    "data": {
        "userId": "123456",
        "pwd": "e10adc3949ba59abbe56e057f20f883e",
        "sex": "男",
        "nickname": "TEST",
        "headUrl": null,
        "collectionArticleList": [],
        "articleList": [],
        "follow": [],
        "follower": []
    }
}
```

###### 失败

```json
{
    "status": 200,
    "msg": "登录失败",
    "data": null
}
```

---

### 2 Article

---

#### 2.1 添加文章

##### 2.1.1 接口说明

|url|/article/add|
|---|---|
|请求方式|POST|
|Content-Type|x-www-form-urlencoded|

##### 2.1.2 请求参数

|参数名词|是否必须|描述|
|---|---|---|
|artId|否|文章id|
|title|是|文章标题|
|artText|是|文章内容|
|artTag|否|文章标签|
|userId|是|发表文章的用户id|

##### 2.1.3 返回样例
```json
{
    "status": 200,
    "msg": "保存成功",
    "data": {
        "artId": 3,
        "title": "test2qwq",
        "artText": "bilibilibilibilibilibili",
        "lasttime": "2019-11-17 19:24:50",
        "artTag": "hahaha",
        "collectNum": null,
        "user": {
            "userId": "12345",
            "pwd": "7ac66c0f148de9519b8bd264312c4d64",
            "sex": "女",
            "nickname": "ovo",
            "headUrl": "testtesttest"
        },
        "comments": null
    }
}
```

#### 2.2 查询文章

##### 2.2.1 接口说明

|url|/article/get|
|---|---|
|请求方式|GET|

##### 2.2.2 请求参数

|参数名词|是否必须|描述|
|---|---|---|
|artId|是|文章id|

##### 2.2.3 返回样例

###### 成功

```json
{
    "status": 200,
    "msg": "查询文章成功",
    "data": {
        "artId": 1,
        "title": "test",
        "artText": "aowiegnoiwaebgoiaweoigjawioeghi",
        "lasttime": "2019-11-17 15:57:46",
        "artTag": "ovo",
        "collectNum": [],
        "user": {
            "userId": "123456",
            "pwd": "7ac66c0f148de9519b8bd264312c4d64",
            "sex": "男",
            "nickname": "ovo",
            "headUrl": "testtest123345"
        },
        "comments": []
    }
}
```

###### 失败

```json
{
    "status": 200,
    "msg": "未找到文章",
    "data": null
}
```

#### 2.3 修改文章

##### 2.3.1 接口说明

|url|/article/update|
|---|---|
|请求方式|POST|
|Content-Type|x-www-form-urlencoded|

##### 2.3.2 请求参数

|参数名词|是否必须|描述|
|---|---|---|
|artId|是|文章id|
|title|否|文章标题|
|artText|否|文章内容|
|artTag|否|文章标签|

##### 2.3.3 返回样例
```json
{
    "status": 200,
    "msg": "修改文章成功",
    "data": {
        "artId": 1,
        "title": "test",
        "artText": "iboigwegoi",
        "lasttime": "2019-11-17 18:35:28",
        "artTag": "qoq",
        "collectNum": [],
        "user": {
            "userId": "123456",
            "pwd": "7ac66c0f148de9519b8bd264312c4d64",
            "sex": "男",
            "nickname": "ovo",
            "headUrl": "testtest123345"
        },
        "comments": []
    }
}
```

#### 2.4 删除文章

##### 2.4.1 接口说明

|url|/article/delete|
|---|---|
|请求方式|GET|

##### 2.4.2 请求参数

|参数名词|是否必须|描述|
|---|---|---|
|artId|是|文章id|

##### 2.4.3 返回样例

###### 成功

```json
{
    "status": 200,
    "msg": "删除成功",
    "data": null
}
```

###### 失败
```json
{
    "status": 200,
    "msg": "文章不存在",
    "data": null
}
```

#### 2.5 收藏文章

##### 2.5.1 接口说明

|url|/article/collect|
|---|---|
|请求方式|GET|

##### 2.5.2 请求参数

|参数名词|是否必须|描述|
|---|---|---|
|userId|是|用户id|
|artId|是|收藏文章的id|

##### 2.5.3 返回样例

###### 成功

```json
{
    "status": 200,
    "msg": "收藏成功",
    "data": null
}
```

###### 失败
```json
{
    "status": 500,
    "msg": "userId未找到用户",
    "data": null
}
```

#### 2.6 取消收藏文章

##### 2.6.1 接口说明

|url|/article/uncollect|
|---|---|
|请求方式|GET|

##### 2.6.2 请求参数

|参数名词|是否必须|描述|
|---|---|---|
|userId|是|用户id|
|artId|是|收藏文章的id|

##### 2.6.3 返回样例

###### 成功

```json
{
    "status": 200,
    "msg": "取消收藏成功",
    "data": null
}
```

###### 失败
```json
{
    "status": 500,
    "msg": "userId未找到用户",
    "data": null
}
```

#### 2.7 查询文章列表

##### 2.7.1 接口说明

|url|/article/list|
|---|---|
|请求方式|GET|

##### 2.7.2 请求参数

|参数名词|是否必须|描述|
|---|---|---|
|order|否|排序方式，逆序为"desc"，顺序为"asc"，默认为顺序|
|property|否|根据属性排序，可选["artId","title","artText","lasttime","artTag"],默认为"artId"|

##### 2.7.3 返回样例

###### 成功

```json
{
    "status": 200,
    "msg": "获取文章列表成功",
    "data": [
        {
            "artId": 5,
            "title": "test2qwq",
            "artText": "bilibilibilibilibilibili",
            "lasttime": "2019-11-17 20:15:09",
            "artTag": "hahaha",
            "collectNum": [],
            "user": {
                "userId": "12345",
                "pwd": "7ac66c0f148de9519b8bd264312c4d64",
                "sex": "女",
                "nickname": "ovo",
                "headUrl": "testtesttest"
            },
            "comments": [
                {
                    "commentId": 6,
                    "comText": "modified"
                },
                {
                    "commentId": 8,
                    "comText": "评论测试"
                }
            ]
        },
        {
            "artId": 4,
            "title": "test2qwq",
            "artText": "bilibilibilibilibilibili",
            "lasttime": "2019-11-17 19:24:51",
            "artTag": "hahaha",
            "collectNum": [],
            "user": {
                "userId": "12345",
                "pwd": "7ac66c0f148de9519b8bd264312c4d64",
                "sex": "女",
                "nickname": "ovo",
                "headUrl": "testtesttest"
            },
            "comments": []
        },
        {
            "artId": 2,
            "title": "test2qwq",
            "artText": "bilibilibilibilibilibili",
            "lasttime": "2019-11-17 18:39:45",
            "artTag": "hahaha",
            "collectNum": [
                {
                    "userId": "12345",
                    "pwd": "7ac66c0f148de9519b8bd264312c4d64",
                    "sex": "女",
                    "nickname": "ovo",
                    "headUrl": "testtesttest"
                }
            ],
            "user": {
                "userId": "12345",
                "pwd": "7ac66c0f148de9519b8bd264312c4d64",
                "sex": "女",
                "nickname": "ovo",
                "headUrl": "testtesttest"
            },
            "comments": []
        },
        {
            "artId": 1,
            "title": "test",
            "artText": "iboigwegoi",
            "lasttime": "2019-11-17 18:35:29",
            "artTag": "qoq",
            "collectNum": [],
            "user": {
                "userId": "123456",
                "pwd": "7ac66c0f148de9519b8bd264312c4d64",
                "sex": "男",
                "nickname": "ovo",
                "headUrl": "testtesttest"
            },
            "comments": []
        }
    ]
}
```

---

### 3 Comment

---
#### 3.1 添加评论

##### 3.1.1 接口说明

|url|/comment/add|
|---|---|
|请求方式|POST|
|Content-Type|x-www-form-urlencoded|

##### 3.1.2 请求参数

|参数名词|是否必须|描述|
|---|---|---|
|commentId|否|评论id|
|comText|是|评论内容|
|userId|是|用户id|
|artId|是|文章id|

##### 3.1.3 返回样例
```json
{
    "status": 200,
    "msg": "添加评论成功",
    "data": {
        "commentId": 6,
        "comText": "评论测试",
        "user": {
            "userId": "123456",
            "pwd": "7ac66c0f148de9519b8bd264312c4d64",
            "sex": "男",
            "nickname": "ovo",
            "headUrl": "testtesttest"
        },
        "article": {
            "artId": 5,
            "title": "test2qwq",
            "artText": "bilibilibilibilibilibili",
            "lasttime": "2019-11-17 20:15:09",
            "artTag": "hahaha"
        }
    }
}
```

#### 3.2 查询评论

##### 3.2.1 接口说明

|url|/comment/get|
|---|---|
|请求方式|GET|

##### 3.2.2 请求参数

|参数名词|是否必须|描述|
|---|---|---|
|commentId|是|评论id|

##### 3.2.3 返回样例

###### 成功

```json
{
    "status": 200,
    "msg": "查询评论成功",
    "data": {
        "commentId": 6,
        "comText": "评论测试",
        "user": {
            "userId": "123456",
            "pwd": "7ac66c0f148de9519b8bd264312c4d64",
            "sex": "男",
            "nickname": "ovo",
            "headUrl": "testtesttest"
        },
        "article": {
            "artId": 5,
            "title": "test2qwq",
            "artText": "bilibilibilibilibilibili",
            "lasttime": "2019-11-17 20:15:09",
            "artTag": "hahaha"
        }
    }
}
```

###### 失败

```json
{
    "status": 200,
    "msg": "未找到评论",
    "data": null
}
```

#### 3.3 修改评论

##### 3.3.1 接口说明

|url|/comment/update|
|---|---|
|请求方式|POST|
|Content-Type|x-www-form-urlencoded|

##### 3.3.2 请求参数

|参数名词|是否必须|描述|
|---|---|---|
|commentId|是|评论id|
|comText|否|评论内容内容|

##### 3.3.3 返回样例
```json
{
    "status": 200,
    "msg": "修改评论成功",
    "data": {
        "commentId": 6,
        "comText": "modified",
        "user": {
            "userId": "123456",
            "pwd": "7ac66c0f148de9519b8bd264312c4d64",
            "sex": "男",
            "nickname": "ovo",
            "headUrl": "testtesttest"
        },
        "article": {
            "artId": 5,
            "title": "test2qwq",
            "artText": "bilibilibilibilibilibili",
            "lasttime": "2019-11-17 20:15:09",
            "artTag": "hahaha"
        }
    }
}
```

#### 3.4 删除文章

##### 3.4.1 接口说明

|url|/comment/delete|
|---|---|
|请求方式|GET|

##### 3.4.2 请求参数

|参数名词|是否必须|描述|
|---|---|---|
|commentId|是|评论id|

##### 3.4.3 返回样例

###### 成功

```json
{
    "status": 200,
    "msg": "删除成功",
    "data": null
}
```

###### 失败
```json
{
    "status": 200,
    "msg": "评论不存在",
    "data": null
}
```