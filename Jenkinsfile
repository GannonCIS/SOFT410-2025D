pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                sh './gradlew clean test jacocoTestReport'
            }
        }

        stage('Publish Test Results') {
            steps {
                junit 'build/test-results/test/*.xml'
            }
        }

        stage('Publish Coverage') {
            steps {
                jacoco execPattern: 'build/jacoco/test.exec',
                       classPattern: 'build/classes/java/main',
                       sourcePattern: 'src/main/java'
            }
        }

        stage('Publish HTML Reports') {
            steps {
                publishHTML(target: [
                    reportName: 'Jacoco Coverage Report',
                    reportDir: 'build/reports/jacoco/test/html',
                    reportFiles: 'index.html'
                ])
            }
        }
    }
}