package de.oth.mocker;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class MockerInvocationHandler implements InvocationHandler {


    public static ArrayList<String> argumentArray = new ArrayList<>();
    public static ArrayList<String> methodCallsArray = new ArrayList<>();  //Names of called methods

    private static boolean enableMethodCallAdding = true;




    @Override
    public Object invoke(Object proxy, Method method, Object[] args){




        //normaler Logging mode
        if(enableMethodCallAdding == true) {

            String methodName = method.getName();


            methodCallsArray.add(methodName);


            if (args != null) {      //check if a method was used withoud arguments
                for (Object arg : args) {

                    //argumentArray.add(arg.toString());
                    argumentArray.add((String) arg);

                }
            } else {                              //to ensure both arrays are of the same size
                argumentArray.add("noArgInput(debug only)");
            }

        }else{  //verification mode

            String latestMethodCall = method.getName();

            //Amount of methods called

            int expectedCallAmount = Mocker.frequencyCheck.getAmount();     //Amount that was specified in the constructor and should therefore be executed


            int actualCallAmount = 0;




            for(String name :  MockerInvocationHandler.methodCallsArray){

                //use of the verification mode, otherwise all methods in the Handler would be added to the latest method calls:

                if(latestMethodCall.equals(name))
                    actualCallAmount++;

            }



            switch(Mocker.frequencyCheck.getKey()){

                case "atLeast":
                    if(actualCallAmount < expectedCallAmount)
                        throw new AssertionError("Nicht oft genug: "+actualCallAmount +" statt "+expectedCallAmount);
                    break;

                case "times":

                    if(actualCallAmount != expectedCallAmount)
                        throw new AssertionError(latestMethodCall +" ist: "+expectedCallAmount + " mal statt " + actualCallAmount +" mal gelaufen");
                    break;

                case "never":
                    if(actualCallAmount != 0)
                        throw new AssertionError("Mehr als einmal gelaufen:" +actualCallAmount);
                    break;

                case "atMost":
                    if(actualCallAmount > expectedCallAmount)
                        throw new AssertionError("Oefter als maximum gelaufen: " +actualCallAmount + " groesser " + expectedCallAmount);
                    break;


            }




            MockerInvocationHandler.setEnableMethodCallAdding(true);



        }


        return proxy;
    }



    public static boolean isEnableMethodCallAdding() {
        return enableMethodCallAdding;
    }

    public static void setEnableMethodCallAdding(boolean enableMethodCallAdding) {
        MockerInvocationHandler.enableMethodCallAdding = enableMethodCallAdding;
    }




    public static ArrayList<String> getArgumentArray() {
        return argumentArray;
    }

    public static void setArgumentArray(ArrayList<String> argumentArray) {
        MockerInvocationHandler.argumentArray = argumentArray;
    }

    public static ArrayList<String> getMethodCallsArray() {
        return methodCallsArray;
    }

    public static void setMethodCallsArray(ArrayList<String> methodCallsArray) {
        MockerInvocationHandler.methodCallsArray = methodCallsArray;
    }
}
