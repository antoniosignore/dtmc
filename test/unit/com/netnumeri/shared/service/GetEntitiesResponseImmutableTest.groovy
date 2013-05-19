package com.netnumeri.shared.service;


import com.netnumeri.server.entity.OptionType
import com.netnumeri.server.finance.finpojo.derivative.equity.Option
import com.netnumeri.server.finance.utils.DateUtils

public class GetEntitiesResponseImmutableTest extends GroovyTestCase {

    public void testToString() throws Exception {

        Date d = DateUtils.Date("3/27/2007");

        ArrayList<Option> list = new ArrayList<Option>();
        Option option = new Option("1", "name", "ticket", OptionType.PUT, 1.1, d);
        list.add(option);

    }
}
