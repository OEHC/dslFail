job("DSLJob") {
    scm {
        git("https://github.com/OEHC/dsl", "*/master")
    }

    triggers {
        scmTrigger {
            scmpoll_spec("")
            ignorePostCommitHooks(false)
        }
    }
}
