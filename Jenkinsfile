pipeline {
    agent any
    tools {
            maven 'maven'
    }
    stages {
        stage('Build JAR file') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Create Container') {
           steps {
                sh 'docker build -t custardapple08/oaes-app-server .  '
            }
        }
        stage('Push image ') {
            steps{
                withDockerRegistry([ credentialsId: "jenkinsID", url: "" ]) {
                    sh 'docker push custardapple08/oaes-app-server'
                }
             }
        }
    }
}