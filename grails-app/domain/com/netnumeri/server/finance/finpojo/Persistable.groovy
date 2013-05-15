package com.netnumeri.server.finance.finpojo


class Persistable implements Serializable {

    Long id

    String username;
    String name;
    def description;
    def active = true;
    def created = new Date();

}
