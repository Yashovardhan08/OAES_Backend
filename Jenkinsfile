pipeline {
    agent any
    tools {
            maven 'maven'
    }
    stage('Initialize')
    {
        def dockerHome = tool 'MyDocker'
        def mavenHome  = tool 'MyMaven'
        env.PATH = "${dockerHome}/bin:${mavenHome}/bin:${env.PATH}"
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