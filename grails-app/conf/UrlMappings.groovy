class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?" {
            constraints {
                // apply constraints here
            }
        }

        "/" controller: 'page'

        /**
         * HTTP Error redirects
         */
        "500"(view: '/error')
    }
}
