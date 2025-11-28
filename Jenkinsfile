pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Stage Build: Step 1: Starting Building..'
                sh './gradlew build'
            }
        }
        stage('Test') {
            steps {
                echo 'Stage Test: Step 1: Starting Testing..'
                sh './gradlew test'
            }
            post {
                always {
                    junit "build/reports/tests/test/*.xml"
                    publishHTML ([
                        reportDir: "build/reports/tests/test",
                        reportFiles: "index.html",
                        reportName: "Test Report",
                        alwaysLinkToLastBuild: true,
                        allowMissing: true,
                        keepAll: true
                    ])
                }
            }
        }
    }
}