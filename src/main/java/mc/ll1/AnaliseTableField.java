package mc.ll1;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by Clusi on 12/16/2017.
 */
public class AnaliseTableField {
    private Type type;
    private ArrayList<String> atom = new ArrayList<>();
    private Integer index;
    private String atomStr;

    public AnaliseTableField() {
    }

    public AnaliseTableField(Type type, String atom, Integer index) {
        this.type = type;
        this.atom.addAll(Arrays.asList(atom.split("")));
        Collections.reverse(this.atom);
        this.atomStr = atom;
        this.index = index;
    }

    public AnaliseTableField(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public ArrayList<String> getAtom() {
        return atom;
    }

    public void setAtom(ArrayList<String> atom) {
        this.atom = atom;
    }

    public String getAtomStr() {
        return atomStr;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    @Override
    public String toString() {
        switch (type) {
            case PUSH:
                return "(" + atomStr + ", " + index + ")";
            case ACC:
                return "Accept";
            case POP:
                return "Pop";
            default:
                return "Error";
        }
    }
}

