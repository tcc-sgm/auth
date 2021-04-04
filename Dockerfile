FROM gradle:4.7.0-jdk8-alpine
ARG DATA_TO_BUILD

LABEL maintainer="SGM - Sistema de Gestão Integrada Municipal" \
      org.label-schema.name="SGM" \
      org.label-schema.vcs-url=https://github.com/tcc-sgm/auth \
      org.label.schema.build-date=$DATA_TO_BUILD

EXPOSE 9090

ENTRYPOINT java ${JVM_ARGS: - -Xms128m -Xmx128m} -Djava.security.egd=file:/dev/./urandom -DUser.language=pt -DUser.country=BR jar app.jar
ADD build/libs/auth-0.0.1-SNAPSHOT.jar app.jar