PROJECT = restsdp
VOLUME = restsdp-data

dev-env-up:
	docker volume create --name=$(VOLUME)
	docker compose -p $(PROJECT) -f docker/docker-compose.yaml up -d --build --remove-orphans

run-tests:
	mvn surefire:test

run-sonar:
	cd vehicleregistry/
	mvn clean verify sonar:sonar \
      -Dsonar.projectKey=restsdp \
      -Dsonar.host.url=http://localhost:9000 \
      -Dsonar.login=a3aed8b9f88f9cd92fad9b3c73db976647dd8819

dev-env-down:
	docker compose -p $(PROJECT) -f docker/docker-compose.yaml down -v --remove-orphans

dev-env-clean: dev-env-down
	docker volume remove $(VOLUME)
