package de.oth.mocker;

public interface ToFrequency {

    static Frequency atLeast(int i){

        return new Frequency("atLeast",i);
    }

    static Frequency times(int i){

        return new Frequency("times",i);
    }

    static Frequency never(){

        return new Frequency("never",0);
    }

    static Frequency atMost(int i){

        return new Frequency("atMost",i);
    }

}
