#!/bin/bash
#
# chkconfig: - 80 20
# description: jsvc


. /etc/init.d/functions

readonly JAVA_HOME=$(readlink /etc/alternatives/jre_1.8.0)
readonly DAEMON=$(which jsvc)
readonly APP_USER=$(whoami)
readonly APP_NAME=example-server
readonly BASE_DIR=$(dirname ${0} | xargs realpath | xargs dirname)
readonly LOG_DIR=${BASE_DIR}/logs
readonly LIB_DIR=${BASE_DIR}/target/scala-2.11
readonly PIDFILE=${LOG_DIR}/${APP_NAME}.pid
readonly LOCKFILE=${LOG_DIR}/${APP_NAME}.lock
readonly CLASSPATH="${JAVA_HOME}/lib/tools.jar:${LIB_DIR}/example-server-assembly-1.0.jar"

start()
{
    echo -n "Starting ${APP_NAME}: "
    ${DAEMON} \
    -java-home ${JAVA_HOME} \
    -user ${APP_USER} \
    -home ${JAVA_HOME} \
    -wait 10 \
    -pidfile ${LOG_DIR}/${APP_NAME}.pid \
    -outfile ${LOG_DIR}/${APP_NAME}.daemon.log \
    -errfile ${LOG_DIR}/${APP_NAME}.daemon.log \
    -cp ${CLASSPATH} \
    -procname ${APP_NAME} \
    -Djava.net.preferIPv4Stack=true \
    com.github.yoshiyoshi441.HttpEchoServerDaemon \
    -admin.port=:8080 \
    -log.output=${LOG_DIR}/${APP_NAME}.app.log \
    -log.level=DEBUG \
    -bind=:9000

    RETVAL=$?
    if [ $RETVAL = 0 ]; then
        echo_success
        touch $LOCKFILE
    else
        echo_failure
    fi
    echo
}

stop()
{
    echo -n "Shutting down ${APP_NAME}: "
    ${DAEMON} \
    -stop \
    -pidfile ${PIDFILE} \
    com.github.yoshiyoshi441.HttpEchoServerDaemon

    RETVAL=$?
    if [ $RETVAL = 0 ]; then
        echo_success
        rm -f ${PIDFILE} ${LOCKFILE}
    else
        echo_failure
    fi
    echo
}

case "$1" in
    start)
        start
        ;;
    stop)
        stop
        ;;
    restart)
        stop
        start
        ;;
    status)
        status ${DAEMON}
        RETVAL=$?
        ;;
    *)
        echo $"Usage: ${APP_NAME} {start|stop|restart|status}"
        exit 1
        ;;
esac
