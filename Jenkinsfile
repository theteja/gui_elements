pipeline {
    agent any

    tools {
        jdk 'JDK21'
        maven 'Manven3'
    }

    stages {
        stage('Build') {
            steps {
                bat 'mvn clean test'
            }
        }
    }

    post {
        always {
            publishHTML([
                allowMissing: false,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'reports',
                reportFiles: 'ExtentReport.html',
                reportName: 'Extent Report'
            ])
        }
    }
}
