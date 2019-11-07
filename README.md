# iot-websocket
基于SpringBoot2、Netty的高性能Websocket消息推送中间件，提供Swagger接口文档，只支持在线推送

## 开始使用

~~~
git clone https://github.com/kingshan/iot-websocket.git
~~~

## 功能列表
* 用户推送
* 群组(级联)推送
* 全部推送

## 建立连接
~~~
ws://127.0.0.1:8081?userId=xxx&groupId=xxxx
~~~

## 用户推送
~~~
curl -X POST "http://127.0.0.1:8443/pushServer/push/user" -H  "Request-Origion:SwaggerBootstrapUi" -H  "accept:*/*" -H  "Content-Type:application/json" -d "{\"id\":\"189\",\"message\":\"22\"}"
~~~

## 群组推送
~~~
curl -X POST "http://127.0.0.1:8443/pushServer/push/group" -H  "Request-Origion:SwaggerBootstrapUi" -H  "accept:*/*" -H  "Content-Type:application/json" -d "{\"id\":\"1000\",\"message\":\"2\"}"
~~~
邮编风格群组编码支持通配符推送子群组
~~~
curl -X POST "http://127.0.0.1:8443/pushServer/push/group" -H  "Request-Origion:SwaggerBootstrapUi" -H  "accept:*/*" -H  "Content-Type:application/json" -d "{\"id\":\"1000*\",\"message\":\"2\"}"
~~~
## 全部推送
~~~
curl -X POST "http://127.0.0.1:8443/pushServer/push/all" -H  "Request-Origion:SwaggerBootstrapUi" -H  "accept:*/*" -H  "Content-Type:application/json" -d "222"
~~~

## 其他接口
* 获取用户列表
* 获取用户数量
* 判断用户状态
* 获取群组列表
* 获取群组用户
* 获取群组数量
* 获取群组用户数量

* 等。。。