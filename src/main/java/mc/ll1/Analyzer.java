package mc.ll1;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Clusi on 12/16/2017.
 */
public class Analyzer {
    private Stack<String> inputStack;
    private Stack<String> workingStack;
    private String output;

    public Analyzer(List<String> inputStack, String first) {
        this.inputStack = new Stack<>();
        this.inputStack.push("#");
        this.inputStack.addAll(inputStack);
        this.workingStack = new Stack<>();
        workingStack.add("#");
        workingStack.add(first);
        this.output = "";
    }

    public String startAnalising(AnaliseTable analiseTable) {
        while (true) {
            String firstSequenceElement = inputStack.peek();
            String firstWorkingElement = workingStack.pop();
            AnaliseTableField analiseTableField = analiseTable.getAnaliseTableField(firstWorkingElement, firstSequenceElement);
            switch (analiseTableField.getType()) {
                case PUSH:
                    if (!analiseTableField.getAtom().contains("$")){
                        workingStack.addAll(analiseTableField.getAtom());
                    }
                    output += analiseTableField.getIndex();
                    break;
                case POP:
                    inputStack.pop();
                    break;
                case ACC:
                    return output;
                default:
                    return "";
            }
        }
    }
}
