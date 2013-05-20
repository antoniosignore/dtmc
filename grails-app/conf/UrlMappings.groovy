class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?" {
            constraints {
                // apply constraints here
            }
        }

        "/"(view: "/index")
        "500"(view: '/error')

        /**
         * Domain Controller mappings
         */
//        "/"(controller: "person", action: "index")

        /**
         * Spring Security Controller Mappings
         */
        "/login/$action?"(controller: "login")
        "/logout/$action?"(controller: "logout")

        /**
         * HTTP Error redirects
         */
        "500"(view: '/error')
    }
}
