
# prepare first docker 

docker rm -f ldap

docker run -p 389:389 --name ldap --hostname ldap --detach osixia/openldap:1.1.7

# first test

docker exec ldap ldapsearch -x -h localhost -b dc=example,dc=org -D "cn=admin,dc=example,dc=org" -w admin

# adding phpldapadmin

docker run --name ldap-admin -p 6443:443 --hostname ldap-admin --link ldap:ldap-host --env PHPLDAPADMIN_LDAP_HOSTS=ldap-host --detach osixia/phpldapadmin:0.6.12

