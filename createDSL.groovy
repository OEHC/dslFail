package dsl

@Grab(group='org.yaml', module='snakeyaml', version='1.16')
import org.yaml.snakeyaml.Yaml

Yaml yaml
Map map
String jobName

String path = "dirToLookAt"
new File(__FILE__).parentFile.eachFileRecurse {
    if(it.name.endsWith('.yaml')) {
        yaml = new Yaml()
//        map = (Map)
        for (String s : yaml.loadAll(readFileFromWorkspace(it.absolutePath))) {
            println s
            map = (Map) s
            println map
        }
        println map
//        jobName = map.jobName

        println """
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
        }"""
    }
}