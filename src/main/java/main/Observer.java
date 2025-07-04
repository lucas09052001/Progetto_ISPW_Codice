package main;

import boundery.Boundaries;

public interface Observer {
    void updateNewBoundery(Boundaries boundery);
    void errorOccurred(String errorMessage);
}
