public class Adder {
    int i;
    int step;

    public Adder(int step){
        this.step = step;
    }

    public void add(){
        this.i += step;
    }
    public int getValue(){
        return this.i;
    }
}
