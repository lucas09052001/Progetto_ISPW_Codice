package main;

import boundery.Boundaries;

public interface Observer {
    void updateNewBoundary(Boundaries boundary);
    void errorOccurred(String errorMessage);
}
