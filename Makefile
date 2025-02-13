up-dev:
	docker-compose up --build -d

up-prod:
	docker-compose -f docker-compose.prod.yml up --build -d