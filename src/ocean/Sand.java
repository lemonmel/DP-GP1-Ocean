package ocean;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Sand implements TerrainStrategy {
    private final Pane oceanPane;

    public Sand(Pane oceanPane) {
        this.oceanPane = oceanPane;

    }

    public void applyFloor(ImageView imageBackImage) {

        
        List<Node> tempChildren = new ArrayList<>(oceanPane.getChildren());
        tempChildren.remove(1);
        
        Image image = new Image("images/sand.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(oceanPane.getWidth());
        imageView.setFitHeight(oceanPane.getHeight() * 0.1);
        imageView.setLayoutY(oceanPane.getHeight() - imageView.getFitHeight());

        oceanPane.getChildren().set(1, imageView);

        // Print information for debugging
        System.out.println("Children size: " + oceanPane.getChildren().size());
        System.out.println("Children contents: " + oceanPane.getChildren());

        tempChildren.stream()
                .filter(node -> node instanceof ImageView && oceanPane.getChildren().indexOf(node) == 1)
                .forEach(oceanPane.getChildren()::add);
    }
}
