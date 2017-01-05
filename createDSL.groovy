package dsl

@Grab(group='org.yaml', module='snakeyaml', version='1.16')
import org.yaml.snakeyaml.Yaml

Yaml yaml
Map map
String jobName

String path = "dirToLookAt"
new File(new File(__FILE__).parent).eachFileRecurse {
    if(it.name.endsWith('.yaml')) {
        println it
        yaml = new Yaml()
        map = (Map) yaml.load(readFileFromWorkspace(it))
        jobName = map.jobName

        job("${jobName}") {
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
    }
}