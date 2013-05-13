package com.netnumeri.server.finance.finpojo.asset;


import com.netnumeri.server.finance.finpojo.Instrument

class Asset extends Instrument implements Serializable {

    public Asset() {
    }

    public Asset(String name) {
        setName(name);
    }

}

