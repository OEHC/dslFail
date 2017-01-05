package dsl

@Grab(group='org.yaml', module='snakeyaml', version='1.16')
import org.yaml.snakeyaml.Yaml

def useThis = readFileFromWorkspace("src/main/groovy/projects/Configure.groovy")

String configFile = useThis.text

Yaml yaml = new Yaml()
Map map = (Map) yaml.load(configFile)

String jobName = map.get("jobName")

println """job("${jobName}") {
    scm {
        git("https://github.com/OEHC/dsl", "*/master")
    }

    triggers {
        scmTrigger {
            scmpoll_spec("")
            ignorePostCommitHooks(false)
        }
    }
}"""

println "pwd".execute().text