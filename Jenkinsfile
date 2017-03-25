def profile = getProfile()
 
// 设置Pipe只保存最近5次的build
def discarder = [$class: 'BuildDiscarderProperty', strategy: [$class: 'LogRotator', numToKeepStr: '5']]

if (profile == 'prd') {
	properties([discarder])
} else {
	// 定时0点的某个时间点运行
	def triggers = pipelineTriggers([cron('H/2 * * * *')])
	
	properties([discarder, triggers])
}

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
		def email_to = "rainwzp@163.com"
		(currentBuild.result != "ABORTED") && node("master") {
     		// Send e-mail notifications for failed or unstable builds.
     		// currentBuild.result must be non-null for this step to work.
     		step([$class: 'Mailer', notifyEveryUnstableBuild: true, recipients: "${email_to}", sendToIndividuals: true])
 		}
	}
}


def getProfile() {
	def profile = "dev"
	
	// 开发环境(develop和feature/hotfix分支)
    def matcher1 = (env.BRANCH_NAME =~ /develop|(feature|hotfix)\/(.*)|/)
	if (matcher1.matches()) {
		profile = "dev"
	}
	
	// 测试环境
	def matcher2 = (env.BRANCH_NAME =~ /release\/(.*)/)
	if (matcher2.matches()) {
		profile = "test"
	}
	
	// 准生产环境和生产环境
	if (env.BRANCH_NAME == "master") {
		if (env.JOB_NAME.contains("-prd")) {
			profile = "prd"
		} else {	
			profile = "staging"
		}
	}
	
	profile
}