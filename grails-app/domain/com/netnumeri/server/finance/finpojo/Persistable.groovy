package com.netnumeri.server.finance.finpojo

class Persistable implements Serializable {

    static constraints = {
        username blank: false, unique: true
    }

    String username;
    String name;
    String description;

    def active = true;
    def dateCreated

}
