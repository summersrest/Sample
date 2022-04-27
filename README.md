
---
title: PMS v1.0.0
language_tabs:
  - shell: Shell
  - http: HTTP
  - javascript: JavaScript
  - ruby: Ruby
  - python: Python
  - php: PHP
  - java: Java
  - go: Go
toc_footers: []
includes: []
search: true
code_clipboard: true
highlight_theme: darkula
headingLevel: 2
generator: "@tarslib/widdershins v4.0.4"

---

# PMS

> v1.0.0

# 移动端/货位调整

## GET 临时货位查询

GET /pms/mobile/v1/GoodAreaSetAdjust/tempStorage/{warehouseCd}

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|warehouseCd|path|string|true|仓库号|
|partsNo|query|string|false|零件号|
|location|query|string|false|货位号|
|Authorization|header|string|false|none|

> 返回示例

> 成功

```json
{
  "success": true,
  "code": "200",
  "message": "操作成功",
  "data": {
    "sysCreateUserCd": null,
    "sysCreateUserName": null,
    "sysCreateTime": null,
    "sysUpdateUserCd": null,
    "sysUpdateUserName": null,
    "sysUpdateTime": null,
    "sysVersion": 0,
    "id": null,
    "warehouseCd": "001",
    "partsNo": "81606T2JH51    ",
    "location": "NE04J05",
    "storageNum": 3,
    "locationNum": 3,
    "dtlList": [
      {
        "id": "1505945914366365696",
        "tempLocation": "A",
        "storageNum": 0,
        "sysVersion": 4
      },
      {
        "id": "1505946516752306176",
        "tempLocation": "C",
        "storageNum": 0,
        "sysVersion": 1
      }
    ]
  }
}
```

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 返回数据结构

状态码 **200**

|名称|类型|必选|约束|说明|
|---|---|---|---|---|
|» success|boolean|true|none|状态|
|» code|string|true|none|编码|
|» message|string|true|none|消息|
|» data|object|true|none|数据|
|»» id|null|true|none|none|
|»» warehouseCd|string|true|none|仓库|
|»» partsNo|string|true|none|零件号|
|»» location|string|true|none|货位号|
|»» storageNum|integer|true|none|库存数量|
|»» locationNum|integer|true|none|主货位数量|
|»» dtlList|[object]|true|none|明细|
|»»» id|string|true|none|明细ID|
|»»» tempLocation|string|true|none|临时货位名称|
|»»» storageNum|integer|true|none|存放数量|
|»»» sysVersion|integer|true|none|版本号|

# 数据模型

