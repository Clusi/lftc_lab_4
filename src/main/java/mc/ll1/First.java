package mc.ll1;

import mc.Production;
import mc.RegularGrammar;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Clusi on 12/12/2017.
 */
public class First {
    private HashMap<String, Set<String>> firstTable = new HashMap<>();

    public Set<String> createFirst(String element, RegularGrammar grammar) {
        Set<String> first = new HashSet<>();
        if (grammar.getTerminals().contains(element)) {
            first.add(element);
        } else {
            for (Production production : grammar.getProductions()) {
                if (production.getLeft().equals(element)) {
                    for (String termen : production.getRight().split("\\|")) {
                        // terminal | $ regex
                        String nonTerminalRegex = "^[0-9a-z]|\\$|^[a-z0-9][A-Za-z]";

                        if (termen.matches(nonTerminalRegex))
                            first.add(termen.charAt(0) + "");
                        else {
                            for (String atom : termen.split("")) {
                                Set<String> daiUnNume = createFirst(atom, grammar);
                                if (!firstTable.containsKey(atom)) {
                                    firstTable.put(atom, daiUnNume);
                                }
                                first.addAll(daiUnNume);
                                if (!daiUnNume.contains("$")) {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        return first;
    }

    public void createFirstTable(RegularGrammar grammar) {
        // terminals
        for (String terminal : grammar.getTerminals()) {
            firstTable.put(terminal, new HashSet<>(Collections.singletonList(terminal)));
        }

        // non-terminals
        for(String nonTerminal : grammar.getNonTerminals()) {
            if (!firstTable.containsKey(nonTerminal)) {
                firstTable.put(nonTerminal, createFirst(nonTerminal, grammar));
            }
        }
    }

    public HashMap<String, Set<String>> getFirstTable() {
        return firstTable;
    }

    public Set<String> getWithoutEpsilon(String nonTerminal) {
        return firstTable.get(nonTerminal).stream().filter(x -> !x.equals("$")).collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        String res = "";
        for (String element: firstTable.keySet()) {
            res += element + " => ";
            for (String atom: firstTable.get(element)) {
                res += atom + ",";
            }
            res += "\n";
        }
        return res;
    }
}
