// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.plugins.springsecurity.ui.encodePassword = false

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [
        all: '*/*',
        atom: 'application/atom+xml',
        css: 'text/css',
        csv: 'text/csv',
        form: 'application/x-www-form-urlencoded',
        html: ['text/html', 'application/xhtml+xml'],
        js: 'text/javascript',
        json: ['application/json', 'text/json'],
        multipartForm: 'multipart/form-data',
        rss: 'application/rss+xml',
        text: 'text/plain',
        xml: ['text/xml', 'application/xml']
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// What URL patterns should be processed by the resources plugin
grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/js/*', '/plugins/*']

// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart = false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = false

environments {
    development {
        grails.logging.jul.usebridge = true

        interceptUrlMap = [

                '/campaigns/edit': ['ADMIN_ROLE', 'EDITOR_ROLE'],
                '/campaigns/list': ['VIEWER_ROLE', 'EDITOR_ROLE', 'ADMIN_ROLE'],
                '/campaigns/view': ['VIEWER_ROLE', 'EDITOR_ROLE', 'ADMIN_ROLE'],
                '/campaigns/create': ['ADMIN_ROLE', 'EDITOR_ROLE'],
                '/campaigns/delete': ['ADMIN_ROLE', 'EDITOR_ROLE'],

                '/trash/edit': ['ADMIN_ROLE', 'EDITOR_ROLE'],
                '/trash/list': ['VIEWER_ROLE', 'EDITOR_ROLE', 'ADMIN_ROLE'],
                '/trash/view': ['VIEWER_ROLE', 'EDITOR_ROLE', 'ADMIN_ROLE'],
                '/trash/create': ['ADMIN_ROLE', 'EDITOR_ROLE'],
                '/trash/delete': ['ADMIN_ROLE', 'EDITOR_ROLE'],

                '/categories/edit': ['ADMIN_ROLE', 'EDITOR_ROLE'],
                '/categories/list': ['VIEWER_ROLE', 'EDITOR_ROLE', 'ADMIN_ROLE'],
                '/categories/view': ['VIEWER_ROLE', 'EDITOR_ROLE', 'ADMIN_ROLE'],
                '/categories/create': ['ADMIN_ROLE', 'EDITOR_ROLE'],
                '/categories/delete': ['ADMIN_ROLE', 'EDITOR_ROLE'],

                '/opcos/edit': ['ADMIN_ROLE'],
                '/opcos/list': ['VIEWER_ROLE', 'EDITOR_ROLE', 'ADMIN_ROLE'],
                '/opcos/view': ['VIEWER_ROLE', 'EDITOR_ROLE', 'ADMIN_ROLE'],
                '/opcos/create': ['ADMIN_ROLE'],
                '/opcos/delete': ['ADMIN_ROLE'],

                '/deviceFamilies/edit': ['ADMIN_ROLE'],
                '/deviceFamilies/list': ['VIEWER_ROLE', 'EDITOR_ROLE', 'ADMIN_ROLE'],
                '/deviceFamilies/view': ['VIEWER_ROLE', 'EDITOR_ROLE', 'ADMIN_ROLE'],
                '/deviceFamilies/create': ['ADMIN_ROLE'],
                '/deviceFamilies/delete': ['ADMIN_ROLE'],

                '/clients/edit': ['ADMIN_ROLE'],
                '/clients/list': ['VIEWER_ROLE', 'EDITOR_ROLE', 'ADMIN_ROLE'],
                '/clients/view': ['VIEWER_ROLE', 'EDITOR_ROLE', 'ADMIN_ROLE'],
                '/clients/create': ['ADMIN_ROLE'],
                '/clients/delete': ['ADMIN_ROLE'],

                '/publishHistory/list': ['VIEWER_ROLE', 'EDITOR_ROLE', 'ADMIN_ROLE'],
                '/publishHistory/view': ['VIEWER_ROLE', 'EDITOR_ROLE', 'ADMIN_ROLE'],
                '/publishHistory/delete': ['EDITOR_ROLE', 'ADMIN_ROLE'],

                '/publish/**': ['EDITOR_ROLE', 'ADMIN_ROLE'],

                '/**': ['VIEWER_ROLE', 'EDITOR_ROLE', 'ADMIN_ROLE']
        ]


    }
    production {
        grails.logging.jul.usebridge = false
        // TODO: grails.serverURL = "http://www.changeme.com"
    }
}

// log4j configuration
log4j = {
    // Example of changing the log pattern for the default console appender:
    //

    appenders {
        console name: 'stdout', layout: pattern(conversionPattern: '%c{2} %m%n')
    }

    debug 'org.codehaus.groovy.grails.orm.hibernate',
            'org.hibernate.SQL',
            'org.codehaus.groovy.grails.web.mapping'

    error 'org.codehaus.groovy.grails.web.servlet',        // controllers
            'org.codehaus.groovy.grails.web.pages',          // GSP
            'org.codehaus.groovy.grails.web.sitemesh',       // layouts
            'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
            'org.codehaus.groovy.grails.web.mapping',        // URL mapping
            'org.codehaus.groovy.grails.commons',            // core / classloading
            'org.codehaus.groovy.grails.plugins',            // plugins
            'org.codehaus.groovy.grails.orm.hibernate',      // hibernate integration
            'org.springframework',
            'org.hibernate',
            'net.sf.ehcache.hibernate'
}


grails.config.defaults.locations = [KickstartResources]

// Added by the Spring Security Core plugin:
grails.plugins.springsecurity.userLookup.userDomainClassName = 'com.dtmc.security.UserBean'
grails.plugins.springsecurity.userLookup.authorityJoinClassName = 'com.dtmc.security.UserRole'
grails.plugins.springsecurity.authority.className = 'com.dtmc.security.Role'

grails.plugins.springsecurity.securityConfigType = "InterceptUrlMap"

// Pessimistic Lockdown
grails.plugins.springsecurity.rejectIfNoRule = true

grails.plugins.springsecurity.interceptUrlMap = [
        '/secure/**': ['ROLE_ADMIN'],
        '/**': ['IS_AUTHENTICATED_ANONYMOUSLY'],
        '/portfolio/**': ['ROLE_USER', 'ROLE_ADMIN', 'IS_AUTHENTICATED_FULLY'],
        '/portfolioItem/**': ['ROLE_USER', 'ROLE_ADMIN', 'IS_AUTHENTICATED_FULLY'],
        '/userBean/**': ['ROLE_ADMIN', 'IS_AUTHENTICATED_FULLY'],
        '/_DemoPage/**': ['ROLE_SUPERUSER', 'IS_AUTHENTICATED_FULLY'],
        '/role/**': ['ROLE_SUPERUSER', 'IS_AUTHENTICATED_FULLY'],
        '/userRole/**': ['ROLE_ADMIN', 'IS_AUTHENTICATED_FULLY'],
        '/stock/**': ['IS_AUTHENTICATED_ANONYMOUSLY'],
        '/vanilla/**': ['ROLE_USER', 'ROLE_ADMIN', 'IS_AUTHENTICATED_FULLY'],
        '/trade/**': ['ROLE_USER', 'ROLE_ADMIN', 'IS_AUTHENTICATED_FULLY'],

        '/js/**': ['IS_AUTHENTICATED_ANONYMOUSLY'],
        '/css/**': ['IS_AUTHENTICATED_ANONYMOUSLY'],
        '/images/**': ['IS_AUTHENTICATED_ANONYMOUSLY'],
        '/*': ['IS_AUTHENTICATED_ANONYMOUSLY'],
        '/login/**': ['IS_AUTHENTICATED_ANONYMOUSLY'],
        '/logout/**': ['IS_AUTHENTICATED_FULLY', 'IS_AUTHENTICATED_REMEMBERED'],
        '/register/**': ['IS_AUTHENTICATED_ANONYMOUSLY'],
]

grails.gorm.default.constraints = {
    '*'(nullable: true)
}

grails {
    mail {
        host = "smtp.gmail.com"
        port = 465
        username = "antonio.signore@gmail.com"
        password = "Nicholas1#"
        props = ["mail.smtp.auth": "true",
                "mail.smtp.socketFactory.port": "465",
                "mail.smtp.socketFactory.class": "javax.net.ssl.SSLSocketFactory",
                "mail.smtp.socketFactory.fallback": "false"]

    }
}

//grails.plugin.cloudfoundry.username = "<your_username>"
//grails.plugin.cloudfoundry.password = "<pass>"

// Uncomment and edit the following lines to start using Grails encoding & escaping improvements

/* remove this line 
// GSP settings
grails {
    views {
        gsp {
            encoding = 'UTF-8'
            htmlcodec = 'xml' // use xml escaping instead of HTML4 escaping
            codecs {
                expression = 'html' // escapes values inside null
                scriptlet = 'none' // escapes output from scriptlets in GSPs
                taglib = 'none' // escapes output from taglibs
                staticparts = 'none' // escapes output from static template parts
            }
        }
        // escapes all not-encoded output at final stage of outputting
        filteringCodecForContentType {
            //'text/html' = 'html'
        }
    }
}
remove this line */
