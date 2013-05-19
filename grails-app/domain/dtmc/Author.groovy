package dtmc

class Author {

    static constraints = {
    }
    static hasMany = [books: Book]

    String name
}
