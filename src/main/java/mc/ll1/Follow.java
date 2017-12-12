package mc.ll1;

import mc.Production;
import mc.RegularGrammar;
import sun.dc.pr.PRError;

import java.util.*;

/**
 * Created by Clusi on 12/12/2017.
 */
public class Follow  {
    private HashMap<String, Set<String>> followTable;

    public  void intializeFolllowTable(RegularGrammar grammar, String first, First firstTable) {
        followTable = new HashMap<>();
        for (String nonTerminal: grammar.getNonTerminals()) {
            Set<String> follow = new HashSet<>();
            if (nonTerminal.equals(first)) {
                follow.add("#");
            }
            followTable.put(nonTerminal, follow);
        }
    }

    public void createFollowTable(RegularGrammar grammar, String first, First firstTable ){
        this.intializeFolllowTable(grammar, first, firstTable);
        for(String nonTerminal : grammar.getNonTerminals()){
            for(Production production : grammar.getProductions_without_or_between_them())
                    if(production.getRight().contains(nonTerminal)){
                        int elementPosition = production.getRight().indexOf(nonTerminal);
                        if (elementPosition < production.getRight().length()) {
                            String nextElement = String.valueOf(production.getRight().charAt(elementPosition));
                            followTable.get(nonTerminal).addAll(firstTable.getWithoutEpsilon(nextElement));
                            if (firstTable.getFirstTable().get(nextElement).contains("$")) {
                                followTable.get(nonTerminal).addAll(followTable.get(production.getLeft()));
                            }
                        }
                    }
        }
    }
}
