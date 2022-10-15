# Gateway


## Route

配置

```yaml
spring:
  cloud:
    gateway:
      discovery:
        locator:
          enabled: true #开启从注册中心动态创建路由的功能，利用微服务名进行路由
      routes:
#       路由id，无规则但需唯一
      - id: after_route
#       路由转发地址
#        lb://web-server  根据服务名称做动态路由
        uri: https://example.org
#       断言
        predicates: 
          - After=2017-01-20T17:42:47.789-07:00[America/Denver]
#       过滤器
        filters:
          - AddRequestHeader=X-Request-red, blue
```

## predicates 路由断言

断言是gateway用来判断是否要经过路由转发的依据，如果符合断言才可以走这个路由，从这个路由转发出去

### After Route 在指定时间之后发生的请求

```yaml
- After=2017-01-20T17:42:47.789-07:00[America/Denver]
```

### Before Route 在指定时间之前发生的请求

```yaml
- Before=2017-01-20T17:42:47.789-07:00[America/Denver]
```

### Between Route 在时间范围内，后一个时间必须要比前一个时间大

```yaml
 - Between=2017-01-20T17:42:47.789-07:00[America/Denver], 2017-01-21T17:42:47.789-07:00[America/Denver]
```

### Cookie Route 对cookie名称进行正则验证

参数是cookie name 和正则表达式

```yaml
- Cookie=chocolate, ch.p
```

### Header Route 

header中的参数校验，header参数名称, 正则表达式

```yaml
- Header=X-Request-Id, \d+
```

### Host Route 参数是一个规则列表，对请求的hostname 进行断言

```yaml
- Host=**.somehost.org,**.anotherhost.org
```

### Method Route 请求方法类型断言

```yaml
- Method=GET, POST
```

### Path Route 请求路径校验

支持参数路径

```yaml
- Path=/web/**
```

### Query Route 查询参数

路径中必须要带有一个query parameter

```yaml
- Query=web
```

### RemoteAddr Route 请求远程地址，接受一个网段

```yaml
- RemoteAddr=192.168.1.1/24
```

### Weight Route 权重路由

参数接收 group, weight 指定路由的组别和权重，当route的分组一样时，根据权重设置决定要到分配到那个路由

```yaml
spring:
  cloud:
    gateway:
      routes:
      - id: weight_high
        uri: https://weighthigh.org
        predicates:
        - Weight=group1, 8
      - id: weight_low
        uri: https://weightlow.org
        predicates:
        - Weight=group1, 2
```

### XForwardedRemoteAddr Route

匹配header中的X-Forwarded-For参数，接收一个网段参数

```yaml
- XForwardedRemoteAddr=192.168.1.1/24
```

## filters 过滤器

### AddRequestHeader

### AddRequestParameter

### AddResponseHeader

### DedupeResponseHeader

### RequestLateLimiter

### RateLimiter

### RedirectTo

### RemoveRequestHeader

### RemoveResponseHeader

### RemoveRequestParameter

### RequestHeaderSize

### RewritePath

### RewriteLocationResponseHeader

### RewriteResponseHeader

### SaveSession

### StripPrefix

过滤前缀，会将前缀从请求中删除，例如设置 `- StripPrefix=/gateway/`，请求是 /gateway/web，由于设置了过滤会把/gateway删除，变成/web