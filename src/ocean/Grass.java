package ocean;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Grass implements TerrainStrategy {
    private final Pane oceanPane;
    private ImageView imageview;

    public Grass(Pane oceanPane) {
        this.oceanPane = oceanPane;

    }

    public void applyFloor(ImageView imageBackImage) {
        List<Node> tempChildren = new ArrayList<>(oceanPane.getChildren());

        Image image = new Image("images/grass.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(oceanPane.getWidth());
        imageView.setFitHeight(oceanPane.getHeight() * 0.5);
        imageView.setLayoutY(oceanPane.getHeight() - imageView.getFitHeight());

        if (oceanPane.getChildren().size() > 2) {
            oceanPane.getChildren().set(1, imageView);
        } else {
            // If there are less than two children, simply add the new ImageView
            oceanPane.getChildren().add(1, imageView);
        }

        // Print information for debugging
        System.out.println("Children size: " + oceanPane.getChildren().size());
        System.out.println("Children contents: " + oceanPane.getChildren());

        // Add back the original children except for ImageView instances and duplicates
        tempChildren.stream()
                .filter(node -> !(node instanceof ImageView) && !oceanPane.getChildren().contains(node))
                .forEach(oceanPane.getChildren()::add);
    }
}
