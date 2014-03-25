import com.dtmc.club.Member

class DtmcFilters {


    def springSecurityService
    def filters = {

        all(controller: '*', action: '*') {
            before = {

                println "Controller: ${controllerName}"
                println "Action    : ${actionName}"

            }
            after = { Map model ->

                if (!model) model = [:]

                def grailsuser = springSecurityService.principal
                if (grailsuser != null) {
                    model.user = Member.get(grailsuser.id)
                    println "model.user = " + model.user.username
                }

            }
            afterView = { Exception e ->

            }
        }

    }
}
