#/bin/bash

function outputBuildComment() {
 echo "************************************"
 echo ""
 echo " " $1
 echo ""
 echo "************************************"
}

function clean() {
	outputBuildComment "Cleaning assembly"
	./sbt clean
}

function run_assembly() {

	clean

	outputBuildComment "Building assembly"
	./sbt assembly

	outputBuildComment "Running assembly"
	java -jar inspectacle-service/target/inspectacle-service.jar server InspectacleServiceConfig.yml
}

function run_dev() {
	outputBuildComment "Running dev"
	./sbt "project inspectacle-service" "run-main com.kainos.inspectacle.InspectacleApplication server InspectacleServiceConfig.yml"
}



REPO_DIR="$( cd "$( dirname "${BASH_SOURCE:-$0}" )" && pwd )"
cd $REPO_DIR	

if [ "$1" = "-a" ]; then
	run_assembly
else
	run_dev
fi