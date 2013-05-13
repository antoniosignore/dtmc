package com.netnumeri.server.finance.beans

class GenericTimeSeries<T> implements Serializable {

    TreeMap<Date, T> treeMap = new TreeMap<Date, T>(new DateComparator());

    @SuppressWarnings("unchecked")
    public GenericTimeSeries() {
    }

    public void put(Date date, T obj) {
        treeMap.put(date, obj)
    }

    public T get(Date date) {
        treeMap.get(date)
    }


    public Long size() {
        return treeMap.size()
    }

    public boolean isEmpty() {
        return treeMap.isEmpty()
    }

    public T getFirstValue() {
        def key = treeMap.firstKey();
        return treeMap.get(key);
    }

    public Date getNextDate(Date date) {
        return treeMap.higherKey(date);
    }

    public Date getPrevDate(Date date) {
        return treeMap.lowerKey(date);
    }

    public T getNextValue(Date date) {
        Map.Entry<Date, T> entry = treeMap.higherEntry(date)
        if (entry != null) return entry.value
        return null
    }

    public T getPrevValue(Date date) {
        Map.Entry<Date, T> entry = treeMap.lowerEntry(date)
        if (entry != null) return entry.value
        return null
    }

    public T getLastValue() {
        def key = treeMap.lastKey();
        return treeMap.get(key);
    }

    public Date getFirstDate() {
        return treeMap.firstKey();
    }

    public Date getLastDate() {
        return treeMap.lastKey();
    }

    public TreeMap<Date, T> map() {
        return treeMap
    }

    int noElements(Date firstCalendarDate, Date lastCalendarDate) {
        return treeMap.subMap(firstCalendarDate, true, lastCalendarDate, true).size();
    }

    public void clear() {
        treeMap.clear()
    }

    public Date getDateByIndex(int i) {
        Object[] array = treeMap.keySet().toArray()
        return array[i] as Date
    }

    public T getValueByIndex(int i) {
        Object[] array = treeMap.values().toArray()
        return array[i] as T
    }

    public int getIndex(Date date) {
        return treeMap.headMap(date).size();
    }

}

class DateComparator implements Comparator<Date>, Serializable {
    public int compare(Date date1, Date date2) {
        return date1.compareTo(date2);
    }
}
