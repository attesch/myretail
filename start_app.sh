
# Start Docker and import test dataset
docker run -itd --rm --name mongo_myretail -p 27017:27017 mongo

mongoimport --db Products --collection product --drop --jsonArray --file ./import.json

gradle bootrun

docker stop mongo_myretail