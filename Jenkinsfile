node {
    stage 'Checkout'

    	checkout scm
		echo "My branch is: ${env.BRANCH_NAME}"
		
    stage 'Build'
	    def mvnHome = tool 'M3'
    	sh "${mvnHome}/bin/mvn clean install -DskipTests"
}