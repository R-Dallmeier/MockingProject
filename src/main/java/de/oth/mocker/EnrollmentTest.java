package de.oth.mocker;

import org.junit.*;

import java.util.ArrayList;

import static de.oth.mocker.Mocker.mock;
import static de.oth.mocker.Mocker.verify;
import static de.oth.mocker.MockerInvocationHandler.getArgumentArray;
import static de.oth.mocker.MockerInvocationHandler.getMethodCallsArray;
import static de.oth.mocker.ToFrequency.*;


public class EnrollmentTest {

    @Test
    public void testEnrollmentDatabaseConnection() {
        // creating the mock, a fake object looking like DatabaseConnection
        ToDatabaseConnection dbcon = mock(ToDatabaseConnection.class);


        Enrollment enroll = new Enrollment(dbcon);
        enroll.matriculate(1, "John Doe");

        //You can call all the methods on verify because verify returns the passed dbcon object, which actually implements the interfaces

        verify(dbcon, times(1)).connect();
        verify(dbcon, times(1)).query("INSERT INTO stud (id, name) VALUES (1, John Doe)");
        //verify(dbcon, times(1)).execute();
        verify(dbcon, never()).delete();
        verify(dbcon, atLeast(1)).close();
        // if enroll Object didn’t use dbcon Object as stated above, your implementation
        // will throw an unchecked AssertionError at runtime


        ArrayList<String> mockMethodCallsArray = getMethodCallsArray();
        ArrayList<String> mockArgumentArray = getArgumentArray();

        System.out.println("Used method calls:");

        for (String method : mockMethodCallsArray) {

            System.out.println(method);

        }

        System.out.println();

        System.out.println("Used arguments:");
        for (String argument : mockArgumentArray) {

            System.out.println(argument);

        }

    }

}
