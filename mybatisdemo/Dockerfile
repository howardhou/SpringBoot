# 基于哪个镜像
FROM openjdk:8-alpine
# 作者
MAINTAINER "houdongdong"

# 为镜像设置元数据
LABEL base.name="eureka server" \
	  base.versioin="0.0.1"

# 设置时区
RUN apk update && \
    apk add tzdata && \
    cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && \
    apk del tzdata

# 添加 app 用户组 和 app 用户
RUN addgroup -S -g 2200 app && \
	adduser -S -D -s /bin/sh -h /home/app -G app -u 2200 app

# 设置默认的工作目录
WORKDIR /home/app

# 复制文件到容器
ADD target/eureka-server-0.0.1-SNAPSHOT.jar app.jar

RUN chown app:app /home/app && \
    chown app:app /tmp

# 环境变量
ENV EUREKA_SERVER_LIST="http://eureka1:1111/eureka,http://eureka2:1111/eureka"

VOLUME /tmp

# 声明要暴露的端口
EXPOSE 1111

# 为接下来的构建步骤指定用户和用户组
USER app:app

# 容器启动后要执行的命令
ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom -jar app.jar --eureka-server-list=$EUREKA_SERVER_LIST

