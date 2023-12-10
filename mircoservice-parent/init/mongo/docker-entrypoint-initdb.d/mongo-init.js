print('START');

db = db.getSiblingDB('post-service');
db.createUser(
    {
        user: "rootadmin",
        pwd : "password",
        roles: [{role: 'readWrite',db: 'post-service'}],

    }
);

db.createCollection('user');

print('END');