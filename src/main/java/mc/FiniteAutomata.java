package mc;

import java.util.ArrayList;

/**
 * Created by Clusi on 11/15/2017.
 */
public class FiniteAutomata {
    private ArrayList<String> states;
    private ArrayList<String> alphabet;
    private String firstState;
    private ArrayList<String> finaleStates;
    private ArrayList<Transition> transitions;

    public FiniteAutomata(ArrayList<String> states, ArrayList<String> alphabet, String firstState, ArrayList<String> finaleStates, ArrayList<Transition> transitions) {
        this.states = states;
        this.alphabet = alphabet;
        this.firstState = firstState;
        this.finaleStates = finaleStates;
        this.transitions = transitions;
    }

    public ArrayList<String> getStates() {
        return states;
    }

    public void setStates(ArrayList<String> states) {
        this.states = states;
    }

    public ArrayList<String> getAlphabet() {
        return alphabet;
    }

    public void setAlphabet(ArrayList<String> alphabet) {
        this.alphabet = alphabet;
    }

    public ArrayList<Transition> getTransitions() {
        return transitions;
    }

    public void setTransitions(ArrayList<Transition> transitions) {
        this.transitions = transitions;
    }

    public ArrayList<String> getFinaleStates() {
        return finaleStates;
    }

    public void setFinaleStates(ArrayList<String> finaleStates) {
        this.finaleStates = finaleStates;
    }

    public String getFirstState() {
        return firstState;
    }

    public void setFirstState(String firstState) {
        this.firstState = firstState;
    }
}
