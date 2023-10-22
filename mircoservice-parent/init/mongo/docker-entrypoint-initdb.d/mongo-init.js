print('START')

db = db.getSiblingDB('post-service');

db.createUser(
    {
        MONGO_INITDB_ROOT_USERNAME: 'rootadmin',
        MONGO_INITDB_ROOT_PASSWORD: 'password',
        roles: [{role: 'readWrite', db: 'post-service'}]

    }
);

db.createCollection('user');


print('END')