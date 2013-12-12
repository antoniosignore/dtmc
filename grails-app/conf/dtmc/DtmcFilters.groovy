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

            }
            afterView = { Exception e ->

            }
        }
    }
}
