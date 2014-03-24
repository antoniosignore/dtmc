package com.netnumeri.server.finance.beans

class GenericTimeSeries<T> extends TreeMap<Date, T> {

    @SuppressWarnings("unchecked")
    public GenericTimeSeries() {
        super(new DateComparator())
    }

    public T getFirstValue() {
        def key = firstKey();
        return get(key);
    }

    public Date getNextDate(Date date) {
        return higherKey(date);
    }

    public Date getPrevDate(Date date) {
        return lowerKey(date);
    }

    public T getNextValue(Date date) {
        Map.Entry<Date, T> entry = higherEntry(date)
        if (entry != null) return entry.value
        return null
    }

    public T getPrevValue(Date date) {
        Map.Entry<Date, T> entry = lowerEntry(date)
        if (entry != null) return entry.value
        return null
    }

    public T getLastValue() {
        def key = lastKey();
        return get(key);
    }

    public Date getFirstDate() {
        return firstKey();
    }

    public Date getLastDate() {
        return lastKey();
    }

    public TreeMap<Date, T> map() {
        return this
    }

    int noElements(Date firstCalendarDate, Date lastCalendarDate) {
        return subMap(firstCalendarDate, true, lastCalendarDate, true).size();
    }

    public Date getDateByIndex(int i) {
        Object[] array = keySet().toArray()
        return array[i] as Date
    }

    public T getValueByIndex(int i) {
        Object[] array = values().toArray()
        return array[i] as T
    }

    public int getIndex(Date date) {
        return headMap(date).size();
    }
}

class DateComparator implements Comparator<Date> {
    @Override
    public int compare(Date date1, Date date2) {
        return date1.compareTo(date2);
    }

}
