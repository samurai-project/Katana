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

        stage('Deploy Release'){
            // when{
            //     branch 'main'
            // }
            steps{
                sh "chmod +x ./gradlew"
                sh '''export FIREBASE_TOKEN=${{ secrets.FIREBASE_TOKEN }}
                ./gradlew assembleRelease appDistributionUploadRelease
                '''
            }
        }
    }
}
