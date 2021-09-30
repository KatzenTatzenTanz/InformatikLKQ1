public class CSGO_GAMBLING {

    public static int getDrop() {
        int i = 0;
        int r = (int)(Math.random() * 11);
        while(r == 10 && i < 4) {
            ++i;
            r = (int)(Math.random() * 11);
        }
        return i;
    }

    public static void main(String[] args) {
        int[] drops = new int[4];
        int lastDrop = getDrop();
        int cases = 1;
        float selling = 0;
        float[] prices = new float[] {0.05f, 0.3f, 5.0f, 15.0f};
        while(lastDrop != 4) {
            cases += 1;
            drops[lastDrop]++;
            selling += prices[lastDrop];
            lastDrop = getDrop();
        }
        System.out.println("FOUND KNIFE!");
        System.out.println("\033[34mIndustrial Grade: " + drops[0]);
        System.out.println("\033[32mMil-Spec: " + drops[1]);
        System.out.println("\033[35mRestricted: " + drops[2]);
        System.out.println("\033[31mClassified: " + drops[3]);
        System.out.println("\033[33mKnife: 1");
        System.out.println("\033[0mYou opened: " + cases + " Cases");
        System.out.println("\033[2mYou loose: " + (cases * 2.1f - selling) + "Eur");
    }
}
