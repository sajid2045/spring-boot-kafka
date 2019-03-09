MESSAGE=$(date)
echo "GOING TO PUT: $MESSAGE"
curl -X POST -F "message=$MESSAGE" http://localhost:9000/kafka/subscription/new
curl -X POST -F "message=$MESSAGE" http://localhost:9000/kafka/subscription/resubscribe

