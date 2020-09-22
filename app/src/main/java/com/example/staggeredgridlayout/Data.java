package com.example.staggeredgridlayout;

import javax.sql.DataSource;

public class Data {
    String reguler,small;

    public String getReguler() {
        return reguler;
    }

    public void setReguler(String reguler) {
        this.reguler = reguler;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public Data(String reguler, String small) {
        this.reguler = reguler;
        this.small = small;
    }
}
