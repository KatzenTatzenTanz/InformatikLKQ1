import java.util.ArrayList;

public class Lotto {
    public static void main(String[] args) {
        ArrayList<Integer> i = new ArrayList<Integer>();
        for(int o = 0; o < 49; ++o)
            i.add(o);
        for(int o = 0; o < 7; ++o) {
            int index = (int)(Math.random() * i.size());
            System.out.println(i.get(index));
            i.remove(index);
        }
    }
}
