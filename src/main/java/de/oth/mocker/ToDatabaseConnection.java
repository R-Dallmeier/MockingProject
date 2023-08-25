package de.oth.mocker;

public interface ToDatabaseConnection {

    void connect();

    void delete();

    void close();

    void query(String nameInput);

    void execute();

}
