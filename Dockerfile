FROM gradle:6.8.3-jdk8 AS BUILD

ARG DATA_TO_BUILD

EXPOSE 9090

ENTRYPOINT java ${JVM_ARGS: - -Xms128m -Xmx128m} -Djava.security.egd=file:/dev/./urandom -DUser.language=pt -DUser.country=BR jar app.jar
ADD build/libs/auth-0.0.1-SNAPSHOT.jar app.jar

LABEL maintainer="SGM - Sistema de Gestão Integrada Municipal" \
      org.label-schema.name="SGM" \
      org.label-schema.vcs-url=https://github.com/tcc-sgm/auth \
      org.label.schema.build-date=$DATA_TO_BUILD