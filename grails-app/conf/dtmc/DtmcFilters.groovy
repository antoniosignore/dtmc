package dtmc

import com.dtmc.club.Member

class DtmcFilters {


    def springSecurityService


    def filters = {
        all(controller: '*', action: '*') {
            before = {

                before = {
//                    println "Controller: ${controllerName}"
//                    println "Action    : ${actionName}"
                }

            }
            after = { Map model ->

                if (!model) {
                    model = [:]
                }

                Member currentLoggedInUser = springSecurityService.getCurrentUser();

                if (currentLoggedInUser != null)
                    println "currentLoggedInUser.username = $currentLoggedInUser.username"

                model.user = currentLoggedInUser
                model.username = currentLoggedInUser.username
                model.userid = currentLoggedInUser.id
                model.email = currentLoggedInUser.email
                model.user_type = currentLoggedInUser.memberType.toString()

            }
            afterView = { Exception e ->

            }
        }

        secure(controller: '*', action: '*') {
            before = {

                if (actionName != null) {

//                if (platformSession) {
//
//                    UserRole module = platformSession.user.getRoleForModule(Constants.MODULE_NAME)
//                    String userRole = module.role
//
//                    boolean lowercase = true
//                    AntUrlPathMatcher urlMatcher = new AntUrlPathMatcher(lowercase)
//                    String url = "/" + controllerName + "/" + actionName
//
//                    Map interceptMap = grailsApplication.config.interceptUrlMap
//
//                    for (Map.Entry<String, ArrayList<String>> entry : interceptMap.entrySet()) {
//                        String urlPattern = entry.getKey()
//                        boolean bool = urlMatcher.pathMatchesUrl(urlPattern, url)
//                        if (bool) {
//
//                            boolean passed = false;
//                            for (String allowedRole : entry.value()) {
//                                if (userRole.equals(allowedRole)) {
//                                    passed = Boolean.TRUE
//                                    break;
//                                }
//                            }
//                            if (!passed) {
//                                params.message = "User : " + platformSession.user.username + " has role: " + userRole + " not allowed for pattern : " + urlPattern
//                                redirect(controller: "error", action: "role", params: params)
//                            }
//                        }
//                    }
//                }
                }

            }
            after = { Map model ->

            }
            afterView = { Exception e ->

            }
        }
    }
}
