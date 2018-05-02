FROM couchbase:5.1.0
COPY init /

RUN chmod 777 init
RUN /init
RUN rm /init

COPY entrypoint.sh /
ENTRYPOINT ["/entrypoint.sh"]
CMD ["couchbase-server"]

EXPOSE 8091
#VOLUME /opt/couchbase/var