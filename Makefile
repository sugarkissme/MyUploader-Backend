#镜像库名称
REGISTRY_HOST=registry.cn-shanghai.aliyuncs.com
#命名空间
REGISTRY_NAMESPACE=unistrong
#镜像名称
SERVICE_NAME=upload
#端口
DEV_PORT=-p 8085:8085
TEST_PORT=-p 8086:8086

##DEV
build_dev:
	git pull
	mvn clean package -Dmaven.test.skip=true
	docker rm -f $(SERVICE_NAME)-dev || true
	docker rmi -f $(SERVICE_NAME):dev || true
	docker build -t $(SERVICE_NAME):dev .

run_dev:
	docker run --name $(SERVICE_NAME)-dev -d -it $(DEV_PORT) -v /etc/hosts:/etc/hosts -v /data/logs/unistrong/$(SERVICE_NAME)-dev:/app/logs -e SPRING_PROFILES_ACTIVE=dev $(SERVICE_NAME):dev
	docker logs -f $(SERVICE_NAME)-dev

build_run_dev:
	make build_dev
	make run_dev

##TEST

build_test:
	git pull
	mvn clean package -Dmaven.test.skip=true
	docker rm -f $(SERVICE_NAME)-test || true
	docker rmi -f $(SERVICE_NAME):test || true
	docker build -t $(SERVICE_NAME):test .

run_test:
	docker run --name $(SERVICE_NAME)-test -d $(TEST_PORT) -v /etc/hosts:/etc/hosts -v /data/logs/unistrong/$(SERVICE_NAME)-test:/app/logs -e SPRING_PROFILES_ACTIVE=test $(SERVICE_NAME):test
	docker logs -f $(SERVICE_NAME)-test

build_run_test:
	make build_test
	make run_test

docker_push:
	docker rmi -f $(REGISTRY_HOST)/$(REGISTRY_NAMESPACE)/$(SERVICE_NAME):prod || true
	docker tag $(SERVICE_NAME):test $(REGISTRY_HOST)/$(REGISTRY_NAMESPACE)/$(SERVICE_NAME):prod
	docker push $(REGISTRY_HOST)/$(REGISTRY_NAMESPACE)/$(SERVICE_NAME):prod

##PROD

docker_pull:
	docker rm -f $(SERVICE_NAME) || true
	docker rmi -f  $(REGISTRY_HOST)/$(REGISTRY_NAMESPACE)/$(SERVICE_NAME):prod || true
	docker pull $(REGISTRY_HOST)/$(REGISTRY_NAMESPACE)/$(SERVICE_NAME):prod

run_prod:
	docker run --name $(SERVICE_NAME) -d $(PORT) -v /etc/hosts:/etc/hosts -v /data/logs/unistrong/$(SERVICE_NAME):/app/logs -e SPRING_PROFILES_ACTIVE=prod $(REGISTRY_HOST)/$(REGISTRY_NAMESPACE)/$(SERVICE_NAME):prod
	docker logs -f $(SERVICE_NAME)

pull_run_prod:
	make docker_pull
	make run_prod