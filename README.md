Storage
====================================
[![Build Status](https://travis-ci.org/coffeine-009/storage.svg?branch=master)](https://travis-ci.org/coffeine-009/storage)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/e19357a5dc4c448d9ff7f0dff85382cb)](https://www.codacy.com/app/vitaliyacm/storage?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=coffeine-009/storage&amp;utm_campaign=Badge_Grade)
[![codecov](https://codecov.io/gh/coffeine-009/storage/branch/master/graph/badge.svg)](https://codecov.io/gh/coffeine-009/storage)

File storage. Store large files.

## Getting Started
### On the server
#### Docker
Instructions how to start via docker.

##### Server
Build image, run container in background and connect to container.
```bash
docker build --rm -t coffeine/storage .
docker run -p 8080:8080 -d coffeine/storage
docker exec -it coffeine/virtuoso-api bash
```

##### Mongodb
Build image for mongo database.
```bash
cd config/db
docker build --rm -t coffeine/mongo .
```

#### Docker compose
Run all containers.
```bash
docker-compose up
```

#### Sharding
##### Config servers replica
Connect to [primary] container.
```bash
docker exec -it storage_mongodb_config_0_1 bash
```

Create replica set of config servers.
```javascript
rs.initiate( {
   _id: "configSet",
   configsvr: true,
   members: [
      { _id: 0, host: "mongodb_config_0:27019" },
      { _id: 1, host: "mongodb_config_1:27019" },
      { _id: 2, host: "mongodb_config_2:27019" }
   ]
} )
```

##### Configure replication for each cluster
Connect to [primary] container.
```bash
docker exec -it storage_mongodb_0_0_1 bash
```

Configure replica set.
```javascript
rs.initiate( {
    "_id" : "music-notes",
    "members" : [
        {
            "_id" : 0,
            "host" : "mongodb_0_0:27018"
        },
        {
            "_id" : 1,
            "host" : "mongodb_0_1:27018"
        },
        {
            "_id" : 2,
            "host" : "mongodb_0_2:27018"
        }
    ]
} )
```

##### Enable security
LogIn into router via mongo client into admin database.
```bash
docker exec -it storage_mongodb_1 bash
mongo admin
```

###### Create admin user.
_Note_: router servers, 

```javascript
db.createUser({
    user: 'root',
    pwd: 'toor',
    roles: [
        {
            role: 'userAdminAnyDatabase',
            db: 'admin'
        },
        {
            role: "clusterAdmin",
            db: "admin"
        }
    ]
})
```

###### Create user for app
Connect as admin.
```bash
mongo -u "root" -p "toor" --authenticationDatabase "admin" storage
```

Create user for app.
```javascript
db.createUser({
    user: 'storage',
    pwd: 'storage',
    roles: [
        {
            role: 'readWrite',
            db: 'storage'
        }
    ]
})
```

##### Add shards.
```javascript
sh.addShard( "music-notes/mongodb_0_0:27018,mongodb_0_1:27018,mongodb_0_2:27018" );
sh.addShard( "music-files/mongodb_1_0:27018,mongodb_1_1:27018,mongodb_1_2:27018" );
```

##### Enable database sharding
```javascript
sh.enableSharding( "storage" )
sh.shardCollection( "storage.fs.chunks", { files_id : 1, n : 1 } )
```


## Documentation
_(Coming soon)_

## Examples
_(Coming soon)_

## Contributing
_(Coming soon)_

## Release History
_(Nothing yet)_

## License
Copyright (c) 2017 Vitaliy Tsutsman <vitaliyacm@gmail.com>  
Licensed under the MIT license.
