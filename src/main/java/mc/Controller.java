package mc;

import mc.ll1.AnaliseTable;
import mc.ll1.Analyzer;
import mc.ll1.First;
import mc.ll1.Follow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Clusi on 11/15/2017.
 */
public class Controller {
    private Repository repository;
    private String regularGrammarPath = "src/main/resources/regularGrammar.txt";
    private String finiteAutomataPath = "src/main/resources/finiteAutomata.txt";
    private String inputSequencePath = "src/main/resources/inputSequence.txt";
    private FiniteAutomata finiteAutomata;
    private RegularGrammar regularGrammar;
    private First firstTable = new First();
    private Follow followTable = new Follow();
    private AnaliseTable analiseTable;
    private List<String> inputSequence;
    private Analyzer analyzer;

    public Controller(Repository repository) {
        this.repository = repository;
        regularGrammar = repository.readRegularGrammarFromFile(regularGrammarPath);
        finiteAutomata = repository.readFiniteAutomataFromFile(finiteAutomataPath);
        inputSequence = repository.readInputSequence(inputSequencePath);
        this.analyzer = new Analyzer(inputSequence, "S");
    }

    public void printNonTerminal(RegularGrammar regularGrammar) {
        System.out.println("\nNon-terminals");
        for (String nonTerminal : regularGrammar.getNonTerminals())
            System.out.print(nonTerminal + " ");
    }

    public void printTerminals(RegularGrammar regularGrammar) {
        System.out.println("\nTerminals");
        for (String terminal : regularGrammar.getTerminals())
            System.out.print(terminal + " ");
    }

    public void printProductions(RegularGrammar regularGrammar) {
        System.out.println("\nProductions");
        for (Production production : regularGrammar.getProductions())
            System.out.println(production.getLeft() + " -> " + production.getRight());
    }

    public void printFirstTable(RegularGrammar regularGrammar) {
        firstTable.createFirstTable(regularGrammar);
        System.out.println(firstTable);
    }

    public void printFollowTable(RegularGrammar regularGrammar) {
        firstTable.createFirstTable(regularGrammar);
        followTable.createFollowTable(regularGrammar, firstTable);
        System.out.println(followTable);
    }

    public void createAnaliseTable(RegularGrammar regularGrammar) {
        firstTable.createFirstTable(regularGrammar);
        followTable.createFollowTable(regularGrammar, firstTable);
        analiseTable = new AnaliseTable(regularGrammar, firstTable, followTable);
        analiseTable.initializeTable();
    }

    public void printAnaliseTable(RegularGrammar regularGrammar) {
        createAnaliseTable(regularGrammar);
        System.out.println(analiseTable);
    }

    public void printIsSequenceAccepted(RegularGrammar regularGrammar) {
        createAnaliseTable(regularGrammar);
        System.out.println(analyzer.startAnalising(analiseTable));
    }

    public void printRegularGrammar(RegularGrammar regularGrammar) {
        //mc.RegularGrammar regularGrammar = repository.readRegularGrammarFromFile(regularGrammarPath);
        System.out.println("Non-terminals");
        for (String nonTerminal : regularGrammar.getNonTerminals())
            System.out.print(nonTerminal + " ");
        System.out.println();
        System.out.println("Terminals");
        for (String terminal : regularGrammar.getTerminals())
            System.out.print(terminal + " ");
        System.out.println();
        System.out.println("Productions");
        for (Production production : regularGrammar.getProductions())
            System.out.println(production.getLeft() + " -> " + production.getRight());
    }

    public void printProductionForNonTerminal(String nonTerminal) {
        RegularGrammar regularGrammar = repository.readRegularGrammarFromFile(regularGrammarPath);
        System.out.println("\nProdotion for non-terminal " + nonTerminal);
        for (Production production : regularGrammar.getProductions())
            if (nonTerminal.equals(production.getLeft()))
                System.out.println(production.getLeft() + " -> " + production.getRight());
    }

    // $ <=> multimea vida
    Boolean isRegularGrammar(RegularGrammar regularGrammar) {
        for (Production production : regularGrammar.getProductions()) {
            ArrayList<String> right = new ArrayList<>(Arrays.asList(production.getRight().split("\\|")));

            //special case when we have $
            String regex = "|[a-z]" + production.getLeft();
            if (right.contains("$"))
                for (String element : right)
                    if (element.matches(regex))
                        return false;

            //each element of right member need de be max 2 characters
            for (String element : right)
                if (element.length() > 2)
                    return false;

            //each element of right member can be terminal&nonTerminl | terminsl | $
            String rightProductionRegex = "[a-z0-9][A-Za-z]|[a-z0-9]|\\$";
            for (String element : right)
                if (!element.matches(rightProductionRegex))
                    return false;
        }
        return true;
    }

    public void printStates(FiniteAutomata finiteAutomata) {
        System.out.println("\nStates");
        for (String state : finiteAutomata.getStates())
            System.out.print(state + " ");
    }

    public void printAlphabet(FiniteAutomata finiteAutomata) {
        System.out.println("\nAlphabet");
        for (String ch : finiteAutomata.getAlphabet())
            System.out.print(ch + " ");
    }

    public void printFirststate(FiniteAutomata finiteAutomata) {
        System.out.println("\nFirst state");
        System.out.println(finiteAutomata.getFirstState());
    }

    public void printFinalStates(FiniteAutomata finiteAutomata) {
        System.out.println("\nFinal States");
        for (String finalState : finiteAutomata.getFinaleStates())
            System.out.print(finalState + " ");
    }

    public void printTransitions(FiniteAutomata finiteAutomata) {
        System.out.println("\nTransitions");
        for (Transition transition : finiteAutomata.getTransitions())
            System.out.println(transition.getStateOne() + "--" + transition.getTransition() + "-->" + transition.getStateTwo());
    }

    public void printFiniteAutomata(FiniteAutomata finiteAutomata) {
        System.out.println("\nStates");
        for (String state : finiteAutomata.getStates())
            System.out.print(state + " ");

        System.out.println("\nAlphabet");
        for (String ch : finiteAutomata.getAlphabet())
            System.out.print(ch + " ");

        System.out.println("\nFirst state");
        System.out.println(finiteAutomata.getFirstState());

        System.out.println("\nFinal States");
        for (String finalState : finiteAutomata.getFinaleStates())
            System.out.print(finalState + " ");

        System.out.println("\nTransitions");
        for (Transition transition : finiteAutomata.getTransitions())
            System.out.println(transition.getStateOne() + "--" + transition.getTransition() + "-->" + transition.getStateTwo());
    }

    public RegularGrammar getRegularGrammar() {
        return repository.readRegularGrammarFromFile(regularGrammarPath);
    }

    public FiniteAutomata getFiniteAutomata() {
        return repository.readFiniteAutomataFromFile(finiteAutomataPath);
    }

    public void fromRegularGrammartoFiniteAutomata(RegularGrammar regularGrammar) {
        if (isRegularGrammar(regularGrammar)) {
            ArrayList<String> states = regularGrammar.getNonTerminals();
            ArrayList<String> alphabet = regularGrammar.getTerminals();
            ArrayList<Transition> transitions = new ArrayList<>();
            ArrayList<String> finaleStates = new ArrayList<>();
            String firstState = "";

            for (Production production : regularGrammar.getProductions()) {
                for (String element : production.getRight().split("\\|")) {
                    if (firstState.equals(""))
                        firstState = production.getLeft();

                    if (element.length() == 2)
                        transitions.add(new Transition(production.getLeft(), element.charAt(1) + "", element.charAt(0) + ""));

                    if (element.length() == 1)
                        if (element.equals("$")) {
                            if (!finaleStates.contains(production.getLeft()))
                                finaleStates.add(production.getLeft());
                        } else {
                            transitions.add(new Transition(production.getLeft(), "K", element));
                            if (!finaleStates.contains("K")) {
                                finaleStates.add("K");
                                states.add("K");
                            }
                        }
                }
            }

            FiniteAutomata finiteAutomata = new FiniteAutomata(states, alphabet, firstState, finaleStates, transitions);
            printFiniteAutomata(finiteAutomata);

        } else
            System.out.println("The grammar it's not regular");

    }

    public void fromFiniteAutomataToRegularGrammar(FiniteAutomata finiteAutomata) {
        ArrayList<String> nonTerminals = finiteAutomata.getStates();
        ArrayList<String> terminals = finiteAutomata.getAlphabet();
        ArrayList<Production> productions = new ArrayList<>();

        if (finiteAutomata.getFinaleStates().contains(finiteAutomata.getFirstState()))
            productions.add(new Production(finiteAutomata.getFirstState(), "$"));

        for (Transition transition : finiteAutomata.getTransitions()) {
            Production pr = null;
            for (Production production : productions)
                if (production.getLeft().equals(transition.getStateOne()))
                    pr = production;
            if (pr != null) {
                if (finiteAutomata.getFinaleStates().contains(transition.getStateTwo())) {
                    pr.setRight(pr.getRight() + "|" + transition.getTransition() + transition.getStateTwo() + "|" + transition.getTransition());
                } else
                    pr.setRight(pr.getRight() + "|" + transition.getTransition() + transition.getStateTwo());
            } else {
                if (finiteAutomata.getFinaleStates().contains(transition.getStateTwo())) {
                    productions.add(new Production(transition.getStateOne(), transition.getTransition() + transition.getStateTwo() + "|" + transition.getTransition()));
                } else
                    productions.add(new Production(transition.getStateOne(), transition.getTransition() + transition.getStateTwo() + transition.getStateTwo()));
            }


        }

        RegularGrammar regularGrammar = new RegularGrammar(nonTerminals, terminals, productions);
        printRegularGrammar(regularGrammar);
        //fromRegularGrammartoFiniteAutomata(regularGrammar);
    }
}
