pipeline{

    agent any

    parameters {
        choice choices: ['chrome', 'firefox', 'edge'], description: 'Select the browser', name: 'browser'
    }

    stages {
           stage('Execute tests'){
            steps{
                bat "mvn clean test -Dbrowser=${params.browser}"
            }
        }
    }
}