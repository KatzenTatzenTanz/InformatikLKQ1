package Structs;

import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Consumable implements Usable {
    private String name;
    private int heal;

    @Override
    public Node toUI() {
        VBox r = new VBox();
        r.getChildren().add(new Text("Name: " + name));
        r.getChildren().add(new Text("Heal: " + heal));
        return r;
    }

    @Override
    public void use() {
        
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Node getEditor() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
