grails.servlet.version = "2.5" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
//grails.project.war.file = "target/${appName}-${appVersion}.war"

// uncomment (and adjust settings) to fork the JVM to isolate classpaths
//grails.project.fork = [
//   run: [maxMemory:1024, minMemory:64, debug:false, maxPerm:256]
//]

grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // specify dependency exclusions here; for example, uncomment this to disable ehcache:
        // excludes 'ehcache'
    }
    log "error" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve
    legacyResolve false // whether to do a secondary resolve on plugin installation, not advised and here for backwards compatibility

    repositories {
        inherits true // Whether to inherit repository definitions from plugins

        grailsPlugins()
        grailsHome()
        grailsCentral()

        mavenLocal()
        mavenCentral()

        mavenRepo "http://snapshots.repository.codehaus.org"
        mavenRepo "http://repository.codehaus.org"
        mavenRepo "http://download.java.net/maven/2/"
        mavenRepo "http://repository.jboss.com/maven2/"
        mavenRepo "http://maven.springframework.org/milestone/"

    }

    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes e.g.

        // runtime 'mysql:mysql-connector-java:5.1.22'

        build "gov.nist.math:jama:1.0.2"

        runtime 'com.oracle.jdbc:com.springsource.oracle.jdbc:10.2.0.2'

        runtime('jaxen:jaxen:1.1.1') {
            exclude group: 'xerces', name: 'xmlParserAPIs'
        }
    }

    plugins {
        runtime ":hibernate:$grailsVersion"
        build ":tomcat:$grailsVersion"
//        runtime ":database-migration:1.3.2"
        runtime ":fields:1.3"
        compile ':cache:1.0.1'
        runtime ':resources:1.2'
        runtime ':raphael:2.0.1'
        compile ':gson:1.1.4'
//        runtime "jaxen:jaxen:1.1.1", {
//            transitive = false
//        }
//        compile ":spring-security-core:1.2.7.3"
//        compile ":kickstart-with-bootstrap:0.9.6"
//        runtime ':jquery:1.8.3'
//        compile ':lesscss-resources:1.3.0.3'

    }
}
