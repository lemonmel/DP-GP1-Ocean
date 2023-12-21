package ocean;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class Grass implements TerrainStrategy {
    private final Pane oceanPane;
    private final HBox container;

    public Grass(Pane oceanPane) {
        this.oceanPane = oceanPane;
        this.container = new HBox();
       // this.oceanPane.getChildren().add(container);
    }

    public void applyFloor(ImageView imageBackImage) {
        // if (!oceanPane.getChildren().isEmpty() && oceanPane.getChildren().get(0) instanceof ImageView) {
        //     oceanPane.getChildren().remove(0);
        //     //container.getChildren().clear();
        // }
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