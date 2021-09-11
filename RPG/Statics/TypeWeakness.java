package Statics;

public class TypeWeakness {
    static int TypeC = 7;
    static String str = 
   //NWFEDLES
    "2111221" +
    "3214223" +
    "3421222" +
    "3142222" +
    "2222242" +
    "2222423" +
    "4322322";
    public static double calculateStrength(int def, int att) {
        return (str.charAt(att+def*TypeC)-'0')*0.5;
    }

    public static String getName(int type) {
        return new String[] {
            "Normal",
            "Water",
            "Fire",
            "Earth",
            "Dark",
            "Light",
            "Electro",
        }[type];
    }
}
