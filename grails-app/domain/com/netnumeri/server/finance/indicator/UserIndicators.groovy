package com.netnumeri.server.finance.indicator

import com.dtmc.security.UserBean

public class UserIndicators implements Serializable {

    static belongsTo = [user: UserBean]

    String name

    Indicators indicator;

    static hasMany = [attributes: Attributes]

    @Override
    public java.lang.String toString() {
        return "UserIndicators{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", indicator=" + indicator +
                ", version=" + version +
                ", attributes=" + attributes +
                ", user=" + user +
                '}';
    }
}
