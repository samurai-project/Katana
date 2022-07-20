pipeline {
    agent any
    stages {
        stage('Compile'){
            steps{
                sh "chmod +x ./gradlew"
                sh './gradlew compileDebugSources'
            }
        }

        stage('Static Analysis'){
            steps{
                sh "chmod +x ./gradlew"
                sh './gradlew detekt'
            }
        }

        stage('Unit Test'){
            steps{
                sh "chmod +x ./gradlew"
                sh './gradlew test'
            }
        }
    }
}
