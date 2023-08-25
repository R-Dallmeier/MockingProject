package de.oth.mocker;

public class Enrollment {

    ToDatabaseConnection dbcon;

    private int studentId;
    private String name;

    public Enrollment(ToDatabaseConnection dbcon){

        this.dbcon = dbcon;

    }

    public void matriculate(int number, String name){

        this.studentId = number;

        this.name = name;

        if (dbcon != null) {
        dbcon.connect();
        dbcon.query("INSERT INTO stud (id, name) VALUES (" + studentId + ", " + name + ")");
        dbcon.close();
        } else {
            throw new NullPointerException("???");
        }
    }

}
