FROM tomcat:jre8-alpine
MAINTAINER bengboba1984@163.com
COPY target/fkty_center.war webapps/fkty_center.war
CMD ["catalina.sh","run"]