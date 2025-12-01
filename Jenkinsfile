pipeline {
    agent any

    tools {
        jdk '17'
        gradle '8.10'
    }

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

        stage('Publish Jacoco HTML Reports') {
            steps {
                publishHTML(target: [
                    reportName: 'Jacoco Coverage',
                    reportDir: 'build/reports/jacoco/test/html',
                    reportFiles: 'index.html'
                ])
            }
        }

        stage('Publish Gradle HTML Reports') {
            steps {
                publishHTML(target: [
                    reportName: 'Gradle test',
                    reportDir: 'build/reports/tests/test',
                    reportFiles: 'index.html'
                ])
            }
        }
    }
}