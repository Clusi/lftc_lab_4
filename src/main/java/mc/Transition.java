package mc;

/**
 * Created by Clusi on 11/15/2017.
 */
public class Transition {
    private String stateOne;
    private String stateTwo;
    private String transition;

    public Transition(String stateOne, String stateTwo, String transition) {
        this.stateOne = stateOne;
        this.stateTwo = stateTwo;
        this.transition = transition;
    }

    public String getStateOne() {
        return stateOne;
    }

    public void setStateOne(String stateOne) {
        this.stateOne = stateOne;
    }

    public String getStateTwo() {
        return stateTwo;
    }

    public void setStateTwo(String stateTwo) {
        this.stateTwo = stateTwo;
    }

    public String getTransition() {
        return transition;
    }

    public void setTransition(String transition) {
        this.transition = transition;
    }
}
