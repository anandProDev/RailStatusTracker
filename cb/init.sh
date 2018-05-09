#!/bin/bash
set -e

/entrypoint.sh couchbase-server &
echo "Sleeping for next 15 seconds. Time for server warm-up"
sleep 15

curl -s -u Administrator:password -X POST http://localhost:8091/pools/default -d memoryQuota=512
/opt/couchbase/bin/couchbase-cli cluster-init -c localhost:8091 --cluster-username=admin --cluster-password=oogway --cluster-ramsize=512 --services=data,index,query --cluster-index-ramsize=256 --index-storage-setting=default
/opt/couchbase/bin/couchbase-cli bucket-create -c localhost:8091 --bucket=status-tracker --bucket-type=couchbase --bucket-replica=0 --bucket-ramsize=100 -u admin -p oogway

cp /tmp/entrypoint.sh /entrypoint.sh

# Original couchbase shares a volume, that means the above config change is overridden by the data stored in the volume during container start.
# To over come this issue:
# Step 1 - creating back-up of the above config change by copying the files available in /opt/couchbase/var/lib/couchbase/config/.
# Step 2 - copy the backup file to config location on container start. by changing the entrypoint.sh

mkdir -p /var/sky/config-bkp/
cp -R /opt/couchbase/var/lib/couchbase/config/* /var/sky/config-bkp/