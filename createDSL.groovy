package dsl

@Grab(group='org.yaml', module='snakeyaml', version='1.16')
import org.yaml.snakeyaml.Yaml

Yaml yaml
String jobName
for (LinkedHashMap m : readYaml()) {
    jobName = m.jobName

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

private ArrayList readYaml() {
    ArrayList ret = new ArrayList()
    new File(__FILE__).parentFile.eachFileRecurse {
        if (it.name.endsWith("yaml")) {
            yaml = new Yaml()
            for (LinkedHashMap m : yaml.loadAll(readFileFromWorkspace(it.absolutePath))) {
                ret.add(m)
            }
        }
    }
    return ret
}