pipeline {
  agent any
  stages {
    stage('') {
      agent {
        docker {
          image 'openjdk'
        }

      }
      steps {
        sh 'gradlew build test'
      }
    }
  }
}