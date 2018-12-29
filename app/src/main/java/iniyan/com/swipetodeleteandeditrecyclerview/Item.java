package iniyan.com.swipetodeleteandeditrecyclerview;

public class Item {
private String Country;
private int length;

    public Item(String country, int length) {
        this.Country = country;
        this.length = length;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
