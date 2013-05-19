package dtmc

class Book {

    static constraints = {
    }

    def Author author
    static belongsTo = [Author]

    String title
}
