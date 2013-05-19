package com.netnumeri.server.servlets;

import com.netnumeri.server.finance.beans.GenericMatrix
import com.netnumeri.server.finance.beans.TimeSeries
import com.netnumeri.server.finance.finpojo.asset.Stock
import com.netnumeri.server.finance.utils.TestUtils

class GenericMatrixTest extends GroovyTestCase {

    GenericMatrix<Double> matrix;
    Stock stock;
    TimeSeries timeSeries;

    public void setUp() throws Exception {
        try {
            stock = TestUtils.buildStock("SSRI");
            timeSeries = stock.getCloseSeries();
            matrix = timeSeries.getMatrix();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    void testMap() {

        TreeMap<Date, Double> map = matrix.map(0);
        assertNotNull(map);
    }

    void testGet() {

        Date date = matrix.getFirstDate(0);
        Double daily = matrix.get(0, date);
        assertEquals(39.5, daily);

    }

    void testIsEmpty() {

        assertFalse(matrix.isEmpty(0));
        assertTrue(matrix.isEmpty(1));
    }


    void testGetLastValidData() {

        assertEquals(16.11, matrix.getLastValidData(0));

    }

    void testIsValidRow() {

        assertTrue(matrix.isValidRow(0));
        assertFalse(matrix.isValidRow(1));

    }

    void testSize() {

        assertEquals(321, matrix.size(0));
    }

    void testCloneIt() {

        GenericMatrix<Double> matrixCloned = matrix.cloneIt();

        assertEquals(321, matrix.size(0));

        assertEquals(321, matrixCloned.size(0));

    }

    void testGetFirstDate() {
        assertEquals("Wed Jan 02 00:00:00 CET 2008", matrix.getFirstDate(0).toString());
        assertEquals("Thu Apr 09 00:00:00 CEST 2009", matrix.getLastDate(0).toString());

        assertEquals(321, matrix.noElements(0));

        Date first = matrix.getFirstDate(0);
        Date next = matrix.getNextDate(0, first)
        assertEquals("Thu Jan 03 00:00:00 CET 2008", next.toString());

        Date last = matrix.getLastDate(0);
        Date prev = matrix.getPrevDate(0, last)
        assertEquals("Wed Apr 08 00:00:00 CEST 2009", prev.toString());

    }

    void testNoElements() {

    }
}
