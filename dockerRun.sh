source env.sh

echo "CONFLUENT_ENDPOINT=$CONFLUENT_ENDPOINT"

./mvnw compile jib:dockerBuild 
docker run -it -e CONFLUENT_USERNAME=$CONFLUENT_USERNAME -e CONFLUENT_PASSWORD=$CONFLUENT_PASSWORD -e CONFLUENT_ENDPOINT=$CONFLUENT_ENDPOINT  --rm kayo/confluent-poc /bin/sh

