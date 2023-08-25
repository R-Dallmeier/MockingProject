package de.oth.mocker;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Iterator;

public class Mocker {


    public static Frequency frequencyCheck;

    static <T> T mock(Class<T> classToMock){

        InvocationHandler handler = new MockerInvocationHandler();


        return (T) Proxy.newProxyInstance(classToMock.getClassLoader(), new Class<?>[]{classToMock}, handler);
    }





    static <M> M verify(M theMockObject, Frequency... frequencies) throws AssertionError{


        MockerInvocationHandler.setEnableMethodCallAdding(false);

        frequencyCheck = frequencies[0];


        return theMockObject;
    }

}
