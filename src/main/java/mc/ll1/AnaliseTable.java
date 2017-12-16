package mc.ll1;

import mc.Production;
import mc.RegularGrammar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by Clusi on 12/16/2017.
 */
public class AnaliseTable {
    private HashMap<String, HashMap<String, AnaliseTableField>> analiseTable;
    private RegularGrammar regularGrammar;
    private First firstTable;
    private Follow followTable;

    public AnaliseTable() {
    }

    public AnaliseTable(RegularGrammar regularGrammar, First firstTable, Follow followTable) {
        this.regularGrammar = regularGrammar;
        this.firstTable = firstTable;
        this.followTable = followTable;
        this.analiseTable = new HashMap<>();

    }


    public AnaliseTableField getAnaliseTableField(String line, String column) {
        int index = 1;
        if (line.equals(column) && line.equals("#"))
            return new AnaliseTableField(Type.ACC);

        if (line.equals(column))
            return new AnaliseTableField(Type.POP);

        for (Production production : regularGrammar.getProductions_without_or_between_them()) {
            if (production.getLeft().equals(line)) {
                if (!production.getRight().contains("$")) {
                    Set<String> first = firstTable.getFirstTable().get(production.getRight().charAt(0) + "");
                    if (first.contains(column))
                        return new AnaliseTableField(Type.PUSH, production.getRight(), index);
                    else if (first.contains("$") && followTable.getFollowTable().get(line).contains(column))
                        return new AnaliseTableField(Type.PUSH, production.getRight(), index);
                } else if (followTable.getFollowTable().get(line).contains(column)) {
                    return new AnaliseTableField(Type.PUSH, production.getRight(), index);
                }
            }
            ++index;
        }

        return new AnaliseTableField(Type.ERR);
    }

    public void initializeTable() {
        ArrayList<String> lines = regularGrammar.getNonTerminals();
        lines.addAll(regularGrammar.getTerminals());
        lines.add("#");

        ArrayList<String> columns = regularGrammar.getTerminals();
        columns.add("#");

        for (String line : lines) {
            HashMap<String, AnaliseTableField> analiseTableLine = new HashMap<>();
            for (String column : columns) {
                analiseTableLine.put(column, getAnaliseTableField(line, column));
            }
            analiseTable.put(line, analiseTableLine);
        }
    }

    public HashMap<String, HashMap<String, AnaliseTableField>> getAnaliseTable() {
        return analiseTable;
    }

    @Override
    public String toString() {
        String res = "";
        for (String line : analiseTable.keySet()) {
            for (String column : analiseTable.get(line).keySet()) {
                res += "(" + line + ", " + column + ") => " + analiseTable.get(line).get(column) + "\n";
            }
        }
        return res;
    }

    public AnaliseTableField getCell(String line, String column) {
        return analiseTable.get(line).get(column);
    }
}
