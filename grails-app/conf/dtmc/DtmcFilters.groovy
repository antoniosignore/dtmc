package dtmc

class DtmcFilters {

    def filters = {
        all(controller:'*', action:'*') {
            before = {

                before = {
                    println "Controller: ${controllerName}"
                    println "Action    : ${actionName}"
                }

            }
            after = { Map model ->

                    if (!model) {
                        model = [:]
                    }

                    model.activeOpco = opcoStatus.currentOpco
                    model.username = opcoStatus.username
                    model.opcos = opcoStatus.opcos
                    model.roles = opcoStatus.roles

            }
            afterView = { Exception e ->

            }
        }
    }
}
