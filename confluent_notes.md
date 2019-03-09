confluent.cloud use letsencrypt
we need to add it to the truststore as below (only required for older version of java ):

```
wget https://letsencrypt.org/certs/lets-encrypt-x3-cross-signed.der
keytool -trustcacerts -keystore $JAVA_HOME/jre/lib/security/cacerts -storepass changeit -noprompt -importcert -alias lets-encrypt-x3-cross-signed -file lets-encrypt-x3-cross-signed.der
```

create topic:

```
ccloud topic create kayo.platform.billingapi.subscription.new --partitions 3 --replication-factor 3

```


