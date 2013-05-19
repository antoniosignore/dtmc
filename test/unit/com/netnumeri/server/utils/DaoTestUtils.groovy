package com.netnumeri.server.utils;


import com.netnumeri.server.finance.finpojo.Portfolio

public class DaoTestUtils {

    public static Portfolio createTestPortfolio(String name, String email) {
        Portfolio portfolio = new Portfolio();
        portfolio.setId(null);
        portfolio.setUsername(email);
        portfolio.setCreated(new Date());
        portfolio.setName(name);
        return portfolio;
    }

}
