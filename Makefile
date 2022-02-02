PROJECT = restsdp
VOLUME = restsdp-data

dev-env-up:
	docker volume create --name=$(VOLUME)
	docker compose -p $(PROJECT) -f docker/docker-compose.yaml up -d --build --remove-orphans

dev-env-down:
	docker compose -p $(PROJECT) -f docker/docker-compose.yaml down -v --remove-orphans

dev-env-clean: dev-env-down
	docker volume remove $(VOLUME)
