// pipeline {
//     agent any
//
//     stages {
//         stage('Maven build') {
//             steps {
//                 script{
//                     sh 'mvn clean install'
//                 }
//             }
//         }
//         stage('Docker build') {
//             steps{
//                 script {
//                     imageName=docker.build("custardapple08/oaes-app-server:latest")
//                 }
//             }
//         }
//         stage('Docker image push to dockerhub') {
//             steps {
//                 script{
//                     docker.withRegistry('','DockerHub'){
//                     imageName.push()
//                     }
//                 }
//             }
//         }
//     }
// }
