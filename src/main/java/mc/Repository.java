package mc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Clusi on 11/15/2017.
 */
public class Repository {
    /*
    *
    * */
    public RegularGrammar readRegularGrammarFromFile(String fileName) {
        ArrayList<String> nonterminals = new ArrayList<>();
        ArrayList<String> terminals = new ArrayList<>();
        ArrayList<Production> productions = new ArrayList<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            int nrNonterminals = Integer.parseInt(bufferedReader.readLine());
            for (int i = 0; i < nrNonterminals; i++)
                nonterminals.add(bufferedReader.readLine());

            int nrTerminals = Integer.parseInt(bufferedReader.readLine());
            for (int i = 0; i < nrTerminals; i++)
                terminals.add(bufferedReader.readLine());

            int nrProduction = Integer.parseInt(bufferedReader.readLine());
            for (int i = 0; i < nrProduction; i++) {
                String line = bufferedReader.readLine();
                String production[] = line.split("->");
                productions.add(new Production(production[0], production[1]));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new RegularGrammar(nonterminals, terminals, productions);
    }

    public FiniteAutomata readFiniteAutomataFromFile(String fileName) {
        ArrayList<String> states = new ArrayList<>();
        ArrayList<String> alphabet = new ArrayList<>();
        ArrayList<Transition> transitions = new ArrayList<>();
        ArrayList<String> finaleStates = new ArrayList<>();
        String firstState = "";

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            states = new ArrayList<>(Arrays.asList(bufferedReader.readLine().split(" ")));
            alphabet = new ArrayList<>(Arrays.asList(bufferedReader.readLine().split(" ")));
            firstState = bufferedReader.readLine();
            finaleStates = new ArrayList<>(Arrays.asList(bufferedReader.readLine().split(" ")));
            String line;
            while((line = bufferedReader.readLine()) != null){
                String elements[] = line.split(" ");
                transitions.add(new Transition(elements[0],elements[1],elements[2]));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new FiniteAutomata(states,alphabet,firstState,finaleStates,transitions);
    }
}
