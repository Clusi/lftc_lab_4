package mc.ll1;

import mc.Production;
import mc.RegularGrammar;
import sun.dc.pr.PRError;

import java.util.*;

/**
 * Created by Clusi on 12/12/2017.
 */
public class Follow {
    private HashMap<String, Set<String>> followTable = new HashMap<>();

    public void intializeFolllowTable(RegularGrammar grammar, String first, First firstTable) {
        followTable = new HashMap<>();
        for (String nonTerminal : grammar.getNonTerminals()) {
            Set<String> follow = new HashSet<>();
            if (nonTerminal.equals(first)) {
                follow.add("#");
            }
            followTable.put(nonTerminal, follow);
        }
    }

    public Set<String> createFollow(RegularGrammar grammar, First firstTable, String nonTerminal, String primu) {

        Set<String> follow = new HashSet<>();
        if (nonTerminal.equals(primu)) {
            follow.add("#");
        }

        for (Production production : grammar.getProductions_without_or_between_them())
            if (production.getRight().contains(nonTerminal)) {
                int elementPosition = production.getRight().indexOf(nonTerminal);
                elementPosition++;
                if (elementPosition < production.getRight().length()) {
                    String nextElement = String.valueOf(production.getRight().charAt(elementPosition));
                    follow.addAll(firstTable.getWithoutEpsilon(nextElement));
                    if (firstTable.getFirstTable().get(nextElement).contains("$")) {
                        if (!followTable.containsKey(nextElement)) {
                            follow.addAll(createFollow(grammar, firstTable, nextElement, primu));
                        } else {
                            follow.addAll(followTable.get(nextElement));
                        }
                    }
                } else if (elementPosition == production.getRight().length()) {
                    if (!nonTerminal.equals(production.getLeft())) {
                        if (!followTable.containsKey(production.getLeft())) {
                            follow.addAll(createFollow(grammar, firstTable, production.getLeft(), primu));
                        } else {
                            follow.addAll(followTable.get(production.getLeft()));
                        }
                    }
                }

            }

        return follow;
    }

    public void createFollowTable(RegularGrammar grammar, String primu, First firstTable) {

        for (String nonTerminal : grammar.getNonTerminals()) {
            if (!followTable.containsKey(nonTerminal)) {
                followTable.put(nonTerminal, createFollow(grammar, firstTable, nonTerminal, primu));
            }
        }
    }

    @Override
    public String toString() {
        String res = "";
        for (String element : followTable.keySet()) {
            res += element + " => ";
            for (String atom : followTable.get(element)) {
                res += atom + ",";
            }
            res += "\n";
        }
        return res;
    }

    public HashMap<String, Set<String>> getFollowTable() {
        return followTable;
    }
}
