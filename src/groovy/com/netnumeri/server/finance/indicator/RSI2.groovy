package com.netnumeri.server.finance.indicator;


import com.netnumeri.server.finance.beans.TimeSeries

/**
 * Relative Strength Index. Implemented up to this specification:
 *
 * The RSI calculation appears in its original and derived form. Average Up and
 * Average Down are calculated using a simple average method for the initial
 * observation. NOTE: The initial observation is the first date shown on the
 * scrolling graph, which may or may not be seen. Subsequent values are computed
 * using these initial values in conjunction with a damping factor to smooth out
 * extreme points. The RSI equation and two averaging methods are presented
 * below. RSI = 100 - [ 100/(1 + [Avg Up/Avg Dn])] where Avg Up: Sum of all
 * changes for advancing periods divided by the total number of RSI periods.
 *
 * Avg Dn: Sum of all changes for declining periods divided by the total number
 * of RSI periods.
 *
 * Subsequent RSI calculations are based on up and down sums calculated as
 * follows:
 *
 * RSI = 100 - [100/(1 + [Next Avg Up/Next Avg Dn])]
 *
 * Next Avg Up = [([Previous Avg Up * (RSI periods - 1)]) + today's up
 * close]/(RSI periods) Next Avg Dn = [([Previous Avg Dn * (RSI periods - 1)]) +
 * today's dn close]/(RSI periods) NOTE: If there is no up or down close,
 * today's up/dn close is zero.
 */

public class RSI2 {

    private final int periodLength;

    public static double calculate(TimeSeries qh, Date date, int periodLength) {
        Stack<Averages> avgList;
        avgList = new Stack<Averages>();

        int lastBar = qh.matrix.getIndex(date);
        int firstBar = lastBar - periodLength + 1;

        double gains = 0, losses = 0, avgUp = 0, avgDown = 0;

        double delta = qh.getValue(lastBar) - qh.getValue(lastBar - 1)
        gains = Math.max(0, delta);
        losses = Math.max(0, -delta);

        if (avgList.isEmpty()) {
            for (int bar = firstBar + 1; bar <= lastBar; bar++) {
                double change = qh.getValue(bar) - qh.getValue(bar - 1)
                gains += Math.max(0, change);
                losses += Math.max(0, -change);
            }
            avgUp = gains / periodLength;
            avgDown = losses / periodLength;
            avgList.push(new Averages(avgUp, avgDown));

        } else {

            Averages avg = avgList.pop();
            avgUp = avg.getAvgUp();
            avgDown = avg.getAvgDown();
            avgUp = (avgUp * (periodLength - 1) + gains) / periodLength;
            avgDown = (avgDown * (periodLength - 1) + losses) / periodLength;
            avgList.add(new Averages(avgUp, avgDown));
        }
        double value = 100 - 100 / (1 + avgUp / avgDown);

        return value;
    }

}