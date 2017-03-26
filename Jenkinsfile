def profile = getProfile()
 
// 设置Pipe只保存最近5次的build
def discarder = [$class: 'BuildDiscarderProperty', strategy: [$class: 'LogRotator', numToKeepStr: '5']]

if (profile == 'prd') {
	properties([discarder])
} else {
	// 定时0点的某个时间点运行
	def triggers = pipelineTriggers([[$class:"SCMTrigger", scmpoll_spec:"H/2 * * * *"]])
	
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
	    	sh "${mvnHome}/bin/mvn clean install -DskipTests"
	    }
	    	
	 	stage('Example') {
			if (env.BRANCH_NAME == 'master') {
				echo 'I only execute on the master branch'
	        } else {
	            echo 'I execute elsewhere'
	        }
		}
	}  catch (e) {
    	// If there was an exception thrown, the build failed
    	currentBuild.result = "FAILED"
    	throw e
  	} finally {
    	// Success or failure, always send notifications
    	notifyBuild(currentBuild.result)
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

def notifyBuild(String buildStatus = 'STARTED') {
  	// build status of null means successful
  	buildStatus = buildStatus ?: 'SUCCESS'

  	// Default values
  	def colorName = 'RED'
  	def colorCode = '#FF0000'
  	def subject = "${buildStatus}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'"
  	def summary = "${subject} (${env.BUILD_URL})"
  	def details = """<p>STARTED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p><p>Check console output at &QUOT;<a href='${env.BUILD_URL}'>${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>&QUOT;</p>"""

  	// Override default values based on build status
  	if (buildStatus == 'STARTED') {
    	color = 'YELLOW'
   		colorCode = '#FFFF00'
  	} else if (buildStatus == 'SUCCESS') {
    	color = 'GREEN'
    	colorCode = '#00FF00'
  	} else {
    	color = 'RED'
    	colorCode = '#FF0000'
  	}

  	// Send notifications
  	//slackSend (color: colorCode, message: summary)

  	//hipchatSend (color: color, notify: true, message: summary)

  	emailext (
      	subject: subject,
      	body: details,
		to: "rainsoft@163.com",
      	recipientProviders: [[$class: 'DevelopersRecipientProvider']]
    )
}