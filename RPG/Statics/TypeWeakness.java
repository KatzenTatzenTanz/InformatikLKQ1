package Statics;

public class TypeWeakness {
    public static final int TypeC = 7;
    public static final String[] Types = new String[] {
        "Normal",
        "Water",
        "Fire",
        "Earth",
        "Dark",
        "Light",
        "Electro",
    };
    public static final String Strengths = 
   //NWFEDLES
    "2111221" +
    "3214223" +
    "3421222" +
    "3142222" +
    "2222242" +
    "2222423" +
    "4322322";

    public static double calculateStrength(int def, int att) {
        return (Strengths.charAt(att+def*TypeC)-'0')*0.5;
    }

    public static String getName(int type) {
        return Types[type];
    }
}
