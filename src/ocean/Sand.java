package ocean;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Sand implements TerrainStrategy {
    private final Pane oceanPane;

    public Sand(Pane oceanPane) {
        this.oceanPane = oceanPane;

    }

    public void applyFloor(ImageView imageBackImage) {
        oceanPane.getChildren().removeIf(node -> node instanceof ImageView);
        // Add the sand image to the container
        Image image = new Image("images/sand.png");
        ImageView imageview = new ImageView(image);
        imageview.setFitWidth(oceanPane.getWidth());
        imageview.setFitHeight(oceanPane.getHeight() * 0.2);
        imageview.setLayoutY(oceanPane.getHeight() - imageview.getFitHeight());
        // Add the seaweed
        oceanPane.getChildren().add(0, imageBackImage); 
        oceanPane.getChildren().add(1, imageview); 
    }
}
