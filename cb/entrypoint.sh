#!/bin/bash
set -e

[[ "$1" == "couchbase-server" ]] && {
    echo "Starting Couchbase Server -- Web UI available at http://<ip>:8091"
    /opt/couchbase/bin/couchbase-server -- -noinput
}

exec "$@"
