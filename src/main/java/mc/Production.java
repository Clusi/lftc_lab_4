package mc;

/**
 * Created by Clusi on 11/15/2017.
 */
public class Production {
   private String left;
   private String right;

    public Production(String left, String right) {
        this.left = left;
        this.right = right;
    }

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Production{" +
                "left='" + left + '\'' +
                ", right='" + right + '\'' +
                '}';
    }
}
