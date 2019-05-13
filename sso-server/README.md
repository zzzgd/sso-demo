### cas-server
cas是一个实现SSO单点登录的框架. 

#### 1.环境:
- mysql
- java 1.8+

#### 2.密码验证 
1.可以直接配置中写死
```
##
# CAS Authentication Credentials
#
cas.authn.accept.users=casuser::Mellon
```
2.可以查询校验数据库明文密码
```
#默认加密策略，通过encodingAlgorithm来指定算法，默认NONE不加密
cas.authn.jdbc.query[0].passwordEncoder.type=NONE
```
3.可以查询校验数据库签名密码
```
cas.authn.jdbc.query[0].passwordEncoder.type=DEFAULT
cas.authn.jdbc.query[0].passwordEncoder.characterEncoding=UTF-8
cas.authn.jdbc.query[0].passwordEncoder.encodingAlgorithm=MD5
```
4.可以查询校验数据库加密密码(各种hash算法和base64编码,以及16进制处理,只知道验证过程,要还原出加密过程比较复杂)
```
cas.authn.jdbc.encode[0].passwordEncoder.type=DEFAULT
cas.authn.jdbc.encode[0].passwordEncoder.characterEncoding=UTF-8
#MD5加密策略
cas.authn.jdbc.encode[0].passwordEncoder.encodingAlgorithm=MD5
cas.authn.jdbc.encode[0].passwordEncoder.secret=
```