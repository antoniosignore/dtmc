modules = {
    application {
        resource url: 'js/application.js'
    }

    bootstrap {
        resource url: 'less/custom-bootstrap.less', attrs: [rel: "stylesheet/less", type: 'css']
        dependsOn 'jquery'
        dependsOn 'bootstrapJs'
    }

    bootstrapJs {
        resource url: 'js/bootstrap/bootstrap-affix.js'
        resource url: 'js/bootstrap/bootstrap-alert.js'
        resource url: 'js/bootstrap/bootstrap-button.js'
        resource url: 'js/bootstrap/bootstrap-carousel.js'
        resource url: 'js/bootstrap/bootstrap-collapse.js'
        resource url: 'js/bootstrap/bootstrap-dropdown.js'
        resource url: 'js/bootstrap/bootstrap-modal.js'
        resource url: 'js/bootstrap/bootstrap-popover.js'
        resource url: 'js/bootstrap/bootstrap-scrollspy.js'
        resource url: 'js/bootstrap/bootstrap-tab.js'
        resource url: 'js/bootstrap/bootstrap-tooltip.js'
        resource url: 'js/bootstrap/bootstrap-transition.js'
        resource url: 'js/bootstrap/bootstrap-typeahead.js'
    }
}