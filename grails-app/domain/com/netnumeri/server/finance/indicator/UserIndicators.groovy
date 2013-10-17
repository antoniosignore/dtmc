package com.netnumeri.server.finance.indicator

import com.dtmc.security.UserBean
import com.netnumeri.server.enums.IndicatorEnum
import com.netnumeri.server.finance.ta.Indicator

public class UserIndicators implements Serializable {

    static belongsTo = [user: UserBean]

    String name
    IndicatorEnum type

    static transients = ["indicator"]
    Indicator indicator;

    Integer smoothing1
    Integer smoothing2
    Integer smoothing3
    Integer method
    Integer period
    Integer order
    Integer length
    Double deviation
    Double k

    @Override
    public String toString() {
        return "UserIndicators{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", indicator=" + indicator +
                ", version=" + version +
                ", user=" + user +
                '}';
    }
}
