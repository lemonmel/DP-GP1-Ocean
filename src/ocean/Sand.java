package ocean;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class Sand implements TerrainStrategy {
    private final Pane oceanPane;
    private final HBox container;

    public Sand(Pane oceanPane) {
        this.oceanPane = oceanPane;
        this.container = new HBox();
        //this.oceanPane.getChildren().add(container);

    }

    public void applyFloor(ImageView imageBackImage) {
        // if (!container.getChildren().isEmpty() && container.getChildren().get(0) instanceof ImageView) {
        //     container.getChildren().remove(0);
        // }
        // if (!oceanPane.getChildren().isEmpty() && oceanPane.getChildren().get(1) instanceof ImageView) {
        //     oceanPane.getChildren().remove(1);
        // }
        oceanPane.getChildren().removeIf(node -> node instanceof ImageView);
        //oceanPane.getChildren().add(0, imageBackImage);
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
