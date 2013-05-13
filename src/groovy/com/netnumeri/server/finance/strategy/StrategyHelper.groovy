package com.netnumeri.server.finance.strategy

import com.netnumeri.server.entity.OptionType
import com.netnumeri.server.finance.finpojo.asset.Stock
import com.netnumeri.server.finance.finpojo.derivative.equity.Option

class StrategyHelper {

    public static boolean isInTheMoney(Option option) {
        if (option.type == OptionType.CALL)
            if (option.strike < option.underlying().last) return true
        if (option.type == OptionType.PUT)
            if (option.strike > option.underlying().last) return true
        return false
    }

    public static boolean isOutOfTheMoney(Option option) {
        if (option.type == OptionType.CALL)
            if (option.strike > option.underlying().last) return true
        if (option.type == OptionType.PUT)
            if (option.strike < option.underlying().last) return true
        return false

    }

    public static boolean isAtTheMoney(Option option) {
        if (option.type == OptionType.CALL)
            if (option.strike == option.underlying().last) return true
        if (option.type == OptionType.PUT)
            if (option.strike == option.underlying().last) return true
        return false
    }

    public static List<Option> getAtTheMoneyList(Stock stock, OptionType type) {

        List<Option> atmList = new ArrayList<Option>();

        def lastValue = stock.getLast();

        Map<Date, List<Option>> optionsMap;

        if (type == OptionType.CALL)
            optionsMap = stock.chain.calls;
        else
            optionsMap = stock.chain.puts;

        Set<Date> strings = optionsMap.keySet();

        Option atTheMoneyOption = null
        Double minDistance = Double.MAX_VALUE

        for (Iterator<Date> iterator = strings.iterator(); iterator.hasNext();) {
            Date next = iterator.next();
            List<Option> options = optionsMap.get(next);
            for (int i = 0; i < options.size(); i++) {
                Option option = options.get(i);
                def strike = option.strike();
                if (Math.abs(strike - lastValue) < minDistance) {
                    atTheMoneyOption = option;
                    minDistance = Math.abs(strike - lastValue)
                }
            }
            atmList.add(atTheMoneyOption);
            minDistance = Double.MAX_VALUE
        }
        return atmList
    }


}
