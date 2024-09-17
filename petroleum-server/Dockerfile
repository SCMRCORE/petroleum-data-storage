# 基础镜像
FROM openjdk:11.0-jre-buster
# 设定时区
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
# 拷贝jar包
COPY petroleum-commom.jar /app.jar
COPY petroleum-pojo.jar /app.jar
COPY petroleum-server.jar /app.jar
# 入口
ENTRYPOINT ["java", "-jar", "/app.jar"]