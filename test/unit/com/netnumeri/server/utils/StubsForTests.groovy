package com.netnumeri.server.utils;


import com.netnumeri.server.entity.OptionType
import com.netnumeri.server.finance.finpojo.derivative.equity.Option
import com.netnumeri.server.finance.utils.DateUtils

public class StubsForTests {

    static Date d = DateUtils.Date("3/27/2007");

    public static Option createDummyOption() {
        return new Option("1", "option123", "GOOG", OptionType.CALL, 180.0, d);
    }

    public static Option createDummyOption2() {
        return new Option("2", "option234", "GOOG", OptionType.PUT, 130.0, d);
    }

    public static List<Option> createDummyOptionList() {


        List<Option> list = new ArrayList<Option>();

        try {
            OptionsChain chain = YahooOptions.loadOptionChain("GOOG");

            Set<Map.Entry<Date, List<Option>>> entries = chain.calls.entrySet();

            for (Iterator<Map.Entry<Date, List<Option>>> iterator = entries.iterator(); iterator.hasNext();) {
                Map.Entry<Date, List<Option>> listEntry = (Map.Entry<Date, List<Option>>) iterator.next();

                List<Option> lOptions = listEntry.getValue();
                for (int i = 0; i < lOptions.size(); i++) {
                    Option option = lOptions.get(i);
                    list.add(option);
                }

            }

        } catch (Throwable e) {
            e.printStackTrace();
        }

//        list.add(createDummyOption());
//        list.add(createDummyOption2());

        return list;

    }
}
