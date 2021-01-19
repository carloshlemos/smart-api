FROM openjdk:11-jre-slim
USER root
COPY wso2-certificate.crt $JAVA_HOME/lib/security

RUN \
    cd $JAVA_HOME/lib/security \
    && keytool -keystore cacerts -storepass changeit -noprompt -trustcacerts -importcert -alias ldapcert -file wso2-certificate.crt

VOLUME /tmp
COPY target/smart-api-*.jar app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]