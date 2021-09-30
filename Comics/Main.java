public class Main {
    private Comic[] alleComics = new Comic[10];

    public Main() {
        for (int x = 0; x < 10; ++x)
            alleComics[x] = new Comic();

        while (true) {
            String command = System.console().readLine();
            command(command);
        }
    }

    public void command(String command) {
        String[] comms = command.split(" ");
        switch (comms[0]) {
            case "get":
                get(Integer.parseInt(comms[1]), comms);
                break;
            case "getAll":
                for (int i = 0; i < 10; ++i)
                    command("get " + i + " " + comms[1]);
                break;
            case "set":
                set(Integer.parseInt(comms[1]), comms);
                break;
            case "find":
                lookup(comms);
                break;
            case "swap":
                swap(Integer.parseInt(comms[1]), Integer.parseInt(comms[2]));
                for (int i = 0; i < 10; ++i)
                    command("get " + i + " all");
                break;
            case "flip":
                for (int i = 0; i < 5; ++i)
                    swap(i,9-i);
            case "max":
                highest(comms);
                break;
            case "min":
                lowest(comms);
                break;
        }
    }

    String combine(int start, String[] args) {
        String s = args[start];
        for (int i = start + 1; i < args.length; ++i)
            s += " " + args[i];
        return s;
    }

    void get(int n, String[] args) {
        switch (args[2]) {
            case "name":
                System.out.println(alleComics[n].getName());
                break;
            case "year":
                System.out.println(alleComics[n].getYear());
                break;
            case "value":
                System.out.println(alleComics[n].getValue());
                break;
            case "all":
                System.out.println("Name  : " + alleComics[n].getName());
                System.out.println("Year  : " + alleComics[n].getYear());
                System.out.println("Value : " + alleComics[n].getValue());
                break;
        }
    }

    void highest(String[] args) {
        int max = 0;
        switch(args[1]) {
            case "year":
                for(int i = 1; i < 10; ++i)
                    if(alleComics[max].getYear() < alleComics[i].getYear())
                        max = i;
                break;
            case "value":
                for(int i = 1; i < 10; ++i)
                    if(alleComics[max].getValue() < alleComics[i].getValue())
                        max = i;
                break;
        }
        command("get " + max + " all");
    }

    void lowest(String[] args) {
        int min = 0;
        switch(args[1]) {
            case "year":
                for(int i = 1; i < 10; ++i)
                    if(alleComics[min].getYear() > alleComics[i].getYear() && alleComics[i].getYear() > 0)
                        min = i;
                break;
            case "value":
                for(int i = 1; i < 10; ++i)
                    if(alleComics[min].getValue() > alleComics[i].getValue() && alleComics[i].getYear() > 0)
                        min = i;
                break;
        }
        command("get " + min + " all");
    }


    void lookup(String[] args) {
        String s = combine(1, args);
        for (int i = 0; i < alleComics.length; ++i)
            if (s.compareTo(alleComics[i].getName()) == 0) {
                command("get " + i + " all");
                return;
            }
        System.out.println("nicht gefunden!");
    }

    void swap(int a, int b) {
        Comic temp = alleComics[a];
        alleComics[a] = alleComics[b];
        alleComics[b] = temp;
    }

    void set(int n, String[] args) {
        switch (args[2]) {
            case "name":
                String s = combine(3, args);
                alleComics[n].setName(s);
                break;
            case "year":
                alleComics[n].setYear(Integer.parseInt(args[3]));
                break;
            case "value":
                alleComics[n].setValue(Integer.parseInt(args[3]));
                break;
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
