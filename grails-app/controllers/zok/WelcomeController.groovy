package zok

class WelcomeController {

    def index() {}

    def show() {
        withFormat {
            html {
                render(view: "show")
            }
        }
    }

}
