package com.dtmc.finance.finpojo

import arrested.ArrestedUser

import com.dtmc.finance.finpojo.asset.Asset
import com.netnumeri.server.enums.PortfolioTypeEnum

class Portfolio extends Asset implements Serializable {

    static hasMany = [items: Entry, trades: Trade]

    static belongsTo = [user: ArrestedUser]

    static mapping = {
        trades cascade: 'all-delete-orphan'
        items cascade: 'all-delete-orphan'
    }

    PortfolioTypeEnum portfolioType
    String description
    Double wealth = 0;

//    Date firstDate;
//    Date lastDate;

//    static constraints = {
//        firstDate blank: true, nullable: true
//        lastDate blank: true, nullable: true
//    }

//    Entry portfolioItemByName(String name) {
//        Entry item = null;
//        if (items != null)
//            items.each {
//                Entry portfolioItem = it
//                if (portfolioItem.instrument.name.equalsIgnoreCase(name))
//                    item = portfolioItem;
//            }
//        return item;
//    }


}
