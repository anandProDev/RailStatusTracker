FROM couchbase:5.1.0
COPY init.sh /
COPY entrypoint.sh /tmp/

RUN chmod 777 init.sh \
    && /init.sh \
    && rm init.sh

ENTRYPOINT ["/entrypoint.sh"]
CMD ["couchbase-server"]

EXPOSE 8091

VOLUME /opt/couchbase/var