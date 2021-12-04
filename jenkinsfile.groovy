pipeline {
    agent any
    stages {
        stage ("SCM Checkout") {
            steps {
                 git url: "https://github.com/roshan-chandekar/webserverproject.git"
            }
        }
        Stage ("Archive Artifacts") {
            steps {
                archiveArtifacts "**/*.html"
            }
        }
        stage ("Transfer Artifacts") {
            steps {
              sshPublisher(publishers: [sshPublisherDesc(configName: "webserver", transfers: [sshTransfer(execCommand: " ", execTimeout: "12000", flatten: true, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', 
                                                                                                          remoteDirectory: '', remoteDirectorySDF: false, removePrefix: '', sourceFiles: '**/*.html')], 
                                                                                                          usePromotionTimeStamp: false, useWorkspaceInPromotion: false, verbose: true)])
             
      
            }
        }
    }
    
}
