for((i=1;i<=$1;i+=1)); do echo ">>>> $i"; curl -X POST -F "message=$i" http://localhost:9000/kafka/subscription/new; done 

