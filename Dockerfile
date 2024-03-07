# JDK基镜像使用eclipse-temurin:版本
FROM eclipse-temurin:21
# 创建镜像的作者，填写邮箱
MAINTAINER lldwb324@gmail.com
# WORKDIR指令设置Dockerfile中的任何RUN，CMD，ENTRPOINT，COPY和ADD指令的工作目录
WORKDIR /
# 表示将jar包添加到镜像中，并重命名 recruit.jar
ADD target/recruit-0.0.1-SNAPSHOT.jar recruit.jar
# 暴露的端口是8080，不是服务器ip:8080 是容器ip:8080
EXPOSE 8080
# 启动时运行 java -jar
ENTRYPOINT ["java", "-jar"]
# 参数，这里是运行的具体的jar
CMD ["recruit.jar"]