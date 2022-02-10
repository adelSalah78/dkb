FROM mysql:5.7

COPY ./script.sql /docker-entrypoint-initdb.d

EXPOSE 3307
COPY ./mysqld.cnf etc/mysql/mysql.conf.d/mysql.cnf