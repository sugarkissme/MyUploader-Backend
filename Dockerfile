FROM java:8
MAINTAINER 841512926@qq.com
RUN mkdir /app
WORKDIR /app
ADD /target/uploader-1.0.0.jar /app
RUN ln -sf uploader-1.0.0.jar uploader-latest.jar

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' >/etc/timezone

CMD java -Xms64m -Xmx128m -jar /app/uploader-latest.jar