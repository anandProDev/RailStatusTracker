#!/bin/bash
set -e
echo "Removing existing config and data"
rm -Rf /opt/couchbase/var/lib/couchbase/config/*
echo "Copying the original configs and data"
cp -R /var/sky/config-bkp/* /opt/couchbase/var/lib/couchbase/config/ &&

[[ "$1" == "couchbase-server" ]] && {
    echo "Starting Couchbase Server -- Web UI available at http://<ip>:8091"
    /opt/couchbase/bin/couchbase-server -- -noinput
}

exec "$@"
