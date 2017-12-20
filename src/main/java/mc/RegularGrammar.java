package mc;

import java.util.ArrayList;

/**
 * Created by Clusi on 11/15/2017.
 */
public class RegularGrammar {
    private ArrayList<String> nonTerminals;
    private ArrayList<String> terminals;
    private ArrayList<Production> productions;
    private ArrayList<Production> productions_without_or_between_them;
    private String firstState;

    public RegularGrammar(ArrayList<String> nonTerminals, ArrayList<String> terminals, ArrayList<Production> productions) {
        this.nonTerminals = nonTerminals;
        this.terminals = terminals;
        this.productions = productions;
        createProductionList();
        this.firstState = nonTerminals.get(0);
    }

    public void createProductionList(){
        productions_without_or_between_them = new ArrayList<>();
        for(Production production : this.getProductions()){
            for(String element : production.getRight().split("\\|")){
                productions_without_or_between_them.add(new Production(production.getLeft(),element));
            }
        }
    }
    public ArrayList<String> getNonTerminals() {
        return nonTerminals;
    }

    public void setNonTerminals(ArrayList<String> nonTerminals) {
        this.nonTerminals = nonTerminals;
    }

    public ArrayList<String> getTerminals() {
        return terminals;
    }

    public void setTerminals(ArrayList<String> terminals) {
        this.terminals = terminals;
    }

    public ArrayList<Production> getProductions() {
        return productions;
    }

    public void setProductions(ArrayList<Production> productions) {
        this.productions = productions;
    }

    public ArrayList<Production> getProductions_without_or_between_them() {
        return productions_without_or_between_them;
    }

    public void setProductions_without_or_between_them(ArrayList<Production> productions_without_or_between_them) {

        this.productions_without_or_between_them = productions_without_or_between_them;
    }

    public boolean isTerminal(String element){
        return getTerminals().contains(element);
    }

    public String getFirstState() {
        return firstState;
    }

    public void setFirstState(String firstState) {
        this.firstState = firstState;
    }
}
