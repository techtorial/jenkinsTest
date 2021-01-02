pipeline {
agent any
environment {
    MAVEN_HOME="/opt/maven"
    PATH="$PATH:$MAVEN_HOME/bin"
}
stages {
    stage('Build') {
        steps {
            sh "mvn clean install -DskipTests"
        }
      }
    stage('Unit tests') {
        steps {
            echo "running unit tests"
            sleep 10
       }
    }
    stage('Deploy to QA env') {
        steps {
            echo "Deploying to QA env"
            sleep 10
        }
      }
    stage('Smoke test QA') {
        steps {
            sh "mvn test -Dtest=APITestRunner.java -DfailIfNoTests=false"
        }
    }
    stage('Deploy to STG env') {
        steps {
            echo "Deploying to STG env"
            sleep 10
        }
    }
    stage('Smoke test STG') {
        steps {
            sh "mvn test -Dtest=APITestRunner.java -DfailIfNoTests=false"
        }
    }
    stage('Deploying to PRD env') {
        steps {
            echo "Deploying to PRD env"
            sleep 10
        }
    }

    }


}