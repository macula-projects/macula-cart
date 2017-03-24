node {
	try {
		stage('Checkout') {
	    	checkout scm
			echo "My branch is: ${env.BRANCH_NAME}"
		}
		
	    stage('Build') {
		    def mvnHome = tool 'M3'
	    	sh "${mvnHome}/bin/mvnx clean install -DskipTests"
	    }
	    	
	 	stage('Example') {
			if (env.BRANCH_NAME == 'master') {
				echo 'I only execute on the master branch'
	        } else {
	            echo 'I execute elsewhere'
	        }
		}
	} catch (err) {
		currentBuild.result = "FAILURE"
		String recipient = 'rain.wang@infinitus-int.com'
		mail subject: "${env.JOB_NAME} (${env.BUILD_NUMBER}) failed",
		        body: "It appears that ${env.BUILD_URL} is failing, somebody should do something about that",
		          to: recipient,
		     replyTo: recipient,
				from: 'noreply@ci.jenkins.io'
			
		throw err
	} finally {
		(currentBuild.result != "ABORTED") && node("master") {
     		// Send e-mail notifications for failed or unstable builds.
     		// currentBuild.result must be non-null for this step to work.
     		step([$class: 'Mailer', notifyEveryUnstableBuild: true, recipients: "${email_to}", sendToIndividuals: true])
 		}
	}
}