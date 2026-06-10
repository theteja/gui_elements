<<<<<<< HEAD
pipeline {
    agent any

    tools {
        jdk 'JDK21'
        maven 'Maven3'
    }

    stages {

        stage('Checkout') {
            steps {
                git '<https://github.com/theteja/gui_elements.git>'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean test'
            }
        }
    }

    post {

        always {

            publishHTML([
                reportDir: 'reports',
                reportFiles: 'ExtentReport.html',
                reportName: 'Extent Report'
            ])
        }
    }
}
=======
pipeline {
    agent any

    tools {
        jdk 'JDK21'
        maven 'Maven3'
    }

    stages {

        stage('Checkout') {
            steps {
                git '<https://github.com/theteja/gui_elements.gitl>'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean test'
            }
        }
    }

    post {

        always {

            publishHTML([
                reportDir: 'reports',
                reportFiles: 'ExtentReport.html',
                reportName: 'Extent Report'
            ])
        }
    }
}
>>>>>>> 2a49490772a58617288b3bbddc48438b16b6c07f
