package de.oth.mocker;

public class Frequency {

    private String key;

    private int amount;

    public Frequency(String key, int amount){

        this.key = key;
        this.amount = amount;

    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
