package com.netnumeri.server.finance.indicator

import com.dtmc.security.UserBean
import com.netnumeri.server.finance.ta.Indicator

public class UserIndicators implements Serializable {

    static belongsTo = [user: UserBean]

    String name
    Indicator indicator;

    static transients = ["indicator"]

    @Override
    public java.lang.String toString() {
        return "UserIndicators{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", indicator=" + indicator +
                ", version=" + version +
                ", user=" + user +
                '}';
    }
}
