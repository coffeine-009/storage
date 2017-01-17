Storage
====================================
[![Build Status](https://travis-ci.org/coffeine-009/storage.svg?branch=master)](https://travis-ci.org/coffeine-009/storage)

File storage. Store large files.

## Getting Started
### On the server
#### Docker
Instructions how to start via docker.

##### Server
```bash
docker build --rm -t coffeine/storage .
docker run -p 8080:8080 -d coffeine/storage
docker exec -it coffeine/virtuoso-api bash
```

##### Mongodb
```bash
cd config/db
docker build --rm -t coffeine/mongo .
```

#### Docker compose
```bash
docker-compose up
```

#### Configure replication

Connect to [primary] container.
```bash
docker exec -it storage_mongodb_1 bash
```

LogIn into via mongo client.
```bash
mongo admin
```

Create admin user
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

exit
```

Connect as admin.
```bash
mongo -u "root" -p "toor" --authenticationDatabase "admin" storage
```

Create use for app.
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
##### Replica
Connect as admin.
```javascript
config = {
    "_id" : "music-files",
    "members" : [
        {
            "_id" : 0,
            "host" : "mongodb:27017"
        },
        {
            "_id" : 1,
            "host" : "mongodb2:27017"
        },
        {
            "_id" : 2,
            "host" : "mongodb3:27017"
        }
    ]
}

rs.initiate(config)
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
