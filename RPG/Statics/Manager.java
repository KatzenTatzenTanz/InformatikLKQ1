package Statics;

import Utility.Comms;

public class Manager {
    // this is the main communcations class, since we do not need more than 1 we can simply create a static class which will be accessible form all other classes
    public static final Comms C = new Comms(true);
}
