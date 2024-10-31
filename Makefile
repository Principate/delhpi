build-client:
	cd app/client && elm make src/main/elm/Main.elm --output=src/main/resources/dev/elm.js

start-local: build-client
	cd docker && docker-compose up -d
	sbt run

commit:
	sbt scalafmt
	sbt scalafix
	git add .
	git commit -m $(message)
