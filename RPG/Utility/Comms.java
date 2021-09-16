package Utility;

import java.util.ArrayList;

import Displays.CONSOLE;
import Displays.Display;
import Displays.Loadout;
import Displays.Utility;
import Statics.TypeWeakness;
import Structs.Enemy;
import Structs.Hero;
import Structs.Weapon;

import javafx.stage.Stage;

public class Comms {
    private Hero hero;
    private Display display;
    private Loadout loadout;
    private Utility utility;
    private CONSOLE console;
    private ArrayList<Enemy> enemies;
    private ArrayList<Weapon> weapons;
    private boolean Win;
    private boolean Loose;

    private ArrayList<Stage> Windows;

    private void update() {
        enemies.removeIf(x -> x.getHp() <= 0);
        for (int i = 0; i < enemies.size(); ++i) {
            command("att " + i);
        }
        if (hero.getHp() <= 0) {
            Loose = true;
            command("say Due to your low health you are forced to retreat!");
        }
        else if(enemies.size() == 0) {
            Win = true;
            command("say You have defeated all enemies!");
        }
    }

    private void closeWindows() {
        command("log \"CLOSING WINDOWS\"");
        for(Stage s : Windows)
            s.close();
    }


    private boolean focusing = false;
    private void openWindows() {
        if(!focusing) {
            command("log \"OPENING WINDOWS\"");
            focusing = true;
            for(Stage s : Windows)
                s.requestFocus();
            new java.util.Timer().schedule( 
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        focusing = false;
                    }
                }, 
            1000
            );
        }
    }

    public void command(String command) {
        try {
            /*
            After careful consideration I have decided that a Command manager is not
            needed here beyond processing the fundamental Commands of the game
            */
            String[] args = command.split(" ");
            boolean update = true; // in case a command will want to stop the screen from updating
            switch (args[0]) {
                case "log":
                    console.log(command.substring(args[0].length() + 1));
                    break;
                case "att":
                    if (args[1].compareTo("p") == 0) {
                        Enemy todmg = enemies.get(Integer.parseInt(args[2]));
                        int dmg = hero.attack(todmg);
                        command("log " + hero.getName() + " attacked " + todmg.getName() + " for " + dmg + "dmg");
                        command("say " + hero.getName() + " attacks " + todmg.getName() + " for " + dmg + "dmg.");
                        command("say " + new String[]{"It had no effect...","It was not very effective..","It was quite effective.","It was very effective!","It was extremely effective!"}[(int)(TypeWeakness.calculateStrength(todmg.getType(), hero.getType())*2)]);
                        update();
                    } else {
                        Enemy fromdmg = enemies.get(Integer.parseInt(args[1]));
                        int dmg = fromdmg.attack(hero);
                        command("log " + fromdmg.getName() + " attacked " + hero.getName() + " for " + dmg + "dmg");
                        command("say " + fromdmg.getName() + " attacks " + hero.getName() + " for " + dmg + "dmg.");
                        command("say " + new String[]{"It had no effect...","It was not very effective..","It was quite effective.","It was very effective!","It was extremely effective!"}[(int)(TypeWeakness.calculateStrength(hero.getType(), fromdmg.getType())*2)]);
                    }
                    break;
                case "use":
                    Weapon temp = new Weapon(weapons.get(Integer.parseInt(args[1])));
                    weapons.set(Integer.parseInt(args[1]),new Weapon(hero.getWeapon()));
                    loadout.update();
                    command("log " + hero.getName() + " equiped " + temp.getName() + " instead of " + hero.getWeapon().getName());
                    command("say " + hero.getName() + " equips " + temp.getName() + " instead of " + hero.getWeapon().getName() + ".");
                    hero.setWeapon(temp);
                    update();
                    break;
                case "say":
                    utility.addText(command.substring(args[0].length() + 1));
                    break;
                case "closeWindows":
                    closeWindows();
                    break;
                case "openWindows":
                    openWindows();
                    break;
            }
            if (update)
                display.update();
        } catch (Exception e) {
            console.log(e.getMessage());
        }
    }



    public Comms(boolean cons) {
        this.Windows = new ArrayList<Stage>();
        console = new CONSOLE(cons);
    }

    public Comms() {
        this(false);
    }

    public boolean getWin() {
        return this.Win;
    }

    public boolean isWin() {
        return this.Win;
    }

    public boolean getLoose() {
        return this.Loose;
    }

    public boolean isLoose() {
        return this.Loose;
    }

    public Hero getHero() {
        return this.hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public Display getDisplay() {
        return this.display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    public Loadout getLoadout() {
        return this.loadout;
    }

    public void setLoadout(Loadout loadout) {
        this.loadout = loadout;
    }

    public Utility getUtility() {
        return this.utility;
    }

    public void setUtility(Utility utility) {
        this.utility = utility;
    }

    public CONSOLE getConsole() {
        return this.console;
    }

    public void setConsole(CONSOLE console) {
        this.console = console;
    }

    public ArrayList<Enemy> getEnemies() {
        return this.enemies;
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    public ArrayList<Weapon> getWeapons() {
        return this.weapons;
    }

    public void setWeapons(ArrayList<Weapon> weapons) {
        this.weapons = weapons;
    }

    public void addWindow(Stage s) {
        Windows.add(s);
    }

    @Override
    public String toString() {
        return "{" + " hero='" + getHero() + "'" + ", display='" + getDisplay() + "'" + ", loadout='" + getLoadout()
                + "'" + ", utility='" + getUtility() + "'" + ", console='" + getConsole() + "'" + ", enemies='"
                + getEnemies() + "'" + "}";
    }
}