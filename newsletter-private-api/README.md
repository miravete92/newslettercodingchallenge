# Generate jar
mvn clean package

# Docker build image
docker build -t miravete92/newsletter-private-api .
