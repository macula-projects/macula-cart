node {
	stage('Checkout') {
    	checkout scm
		echo "My branch is: ${env.BRANCH_NAME}"
	}
	
    stage('Build') {
	    def mvnHome = tool 'M3'
    	sh "${mvnHome}/bin/mvn clean install -DskipTests"
    }
    	
 	stage('Example') {
		if (env.BRANCH_NAME == 'master') {
			echo 'I only execute on the master branch'
        } else {
            echo 'I execute elsewhere'
        }
	}    	
}