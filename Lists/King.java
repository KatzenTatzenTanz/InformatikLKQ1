public class King {
    public static void main(String[] args) {
        boolean[] open = new boolean[100];
        for(int i = 1; i < 100; ++i) {
            for(int o = i-1; o < 100; o+=i) {
                open[o] = !open[o];
            }
        }
        for(int i = 0; i < 100; ++i) {
            if(open[i])
                System.out.println(i);
        }
    }
}