package mc;

import java.util.Scanner;

/**
 * Created by Clusi on 11/15/2017.
 */
public class Main {
    private static RegularGrammar regularGrammar;
    private static FiniteAutomata finiteAutomata;

    public static void showMenu() {
        System.out.println("\n0 - exit");
        System.out.println("1 - read grammar from file");
        System.out.println("2 - set of non-terminals");
        System.out.println("3 - set of terminals");
        System.out.println("4 - set of productions");
        System.out.println("5 - the productions of a given non-terminal symbol");
        System.out.println("6 - is the grammar regular?");
        System.out.println("7 - reads FA from file");
        System.out.println("8 - the set of states");
        System.out.println("9 - the alphabet ");
        System.out.println("10 - all the transitions");
        System.out.println("11 - the set of final state");
        System.out.println("12 - from grammar to finite automaton");
        System.out.println("13 - from finite automaton to regular grammar");
    }

    public static void main(String arg[]) {

        Controller controller = new Controller(new Repository());
        Scanner scanner = new Scanner(System.in);

        String cmd;
        while (true) {
            System.out.print("--->");
            cmd = scanner.next();
            switch (cmd) {
                case "M":
                    showMenu();
                    break;
                case "1":
                    regularGrammar = controller.getRegularGrammar();
                    break;
                case "2":
                    controller.printNonTerminal(regularGrammar);
                    break;
                case "3":
                    controller.printTerminals(regularGrammar);
                    break;
                case "4":
                    controller.printProductions(regularGrammar);
                    break;
                case "5":
                    System.out.println("Non-terminal=");
                    String nonTerminal = scanner.next();
                    controller.printProductionForNonTerminal(nonTerminal);
                    break;
                case "6":
                    System.out.println(controller.isRegularGrammar(regularGrammar));
                    break;
                case "7":
                    finiteAutomata = controller.getFiniteAutomata();
                    break;
                case "8":
                    controller.printStates(finiteAutomata);
                    break;
                case "9":
                    controller.printAlphabet(finiteAutomata);
                    break;
                case "10":
                    controller.printTransitions(finiteAutomata);
                    break;
                case "11":
                    controller.printFinalStates(finiteAutomata);
                    break;
                case "12":
                    controller.fromRegularGrammartoFiniteAutomata(regularGrammar);
                    break;
                case "13":
                    controller.fromFiniteAutomataToRegularGrammar(finiteAutomata);
                    break;
                case "14":
                    controller.printFirstTable(regularGrammar);
                    break;
                case "15":
                    controller.printFollowTable(regularGrammar);
                    break;
                case "16":
                    controller.printAnaliseTable(regularGrammar);
                    break;
                case "17":
                    controller.printIsSequenceAccepted(regularGrammar);
                    break;
                case "0":
                    return;
            }

        }
        //controller.printRegularGrammar();
        //controller.printProductionForNonTerminal("S");

        //System.out.println(controller.isRegularGrammar());
        //controller.printFiniteAutomata();
        //controller.fromRegularGrammartoFiniteAutomata(controller.getRegularGrammar());
        //controller.fromFiniteAutomataToRegularGrammar(controller.getFiniteAutomata());
    }
}
