package ocean;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class Grass implements TerrainStrategy {
    private final Pane oceanPane;

    public Grass(Pane oceanPane) {
        this.oceanPane = oceanPane;

    }

    public void applyFloor(ImageView imageBackImage) {
        oceanPane.getChildren().removeIf(node -> node instanceof ImageView);
        Image image = new Image("images/grasses.png");
        ImageView imageview = new ImageView(image);
        imageview.setFitWidth(oceanPane.getWidth());
        imageview.setFitHeight(oceanPane.getHeight()* 1.2);

        imageview.setLayoutY(oceanPane.getHeight() - imageview.getFitHeight()); // bottom of screen//background
        //container.getChildren().add(0,imageview);
        oceanPane.getChildren().add(0, imageBackImage); 
        oceanPane.getChildren().add(1, imageview); 
        

    }
}