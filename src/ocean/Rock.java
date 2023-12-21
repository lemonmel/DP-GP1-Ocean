package ocean;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Rock implements TerrainStrategy {
    private final Pane oceanPane;

    public Rock(Pane oceanPane) {
        this.oceanPane = oceanPane;

    }

    public void applyFloor(ImageView imageBackImage) {
        oceanPane.getChildren().removeIf(node -> node instanceof ImageView);

        // Add the first rock image
        Image image1 = new Image("images/rock.png");
        ImageView imageView1 = new ImageView(image1);
        imageView1.setFitWidth(oceanPane.getWidth() * 0.5);
        imageView1.setFitHeight(oceanPane.getHeight() * 0.2);
        imageView1.setLayoutY(oceanPane.getHeight() - imageView1.getFitHeight());

        // Add the second rock image beside the first one
        Image image2 = new Image("images/rock.png");
        ImageView imageView2 = new ImageView(image2);
        imageView2.setFitWidth(oceanPane.getWidth() * 0.5);
        imageView2.setFitHeight(oceanPane.getHeight() * 0.2);
        imageView2.setLayoutY(oceanPane.getHeight() - imageView2.getFitHeight());
        imageView2.setLayoutX(oceanPane.getWidth() * 0.5); // Adjust the X position for the second image
        oceanPane.getChildren().add(0, imageBackImage);

        oceanPane.getChildren().addAll(imageView1, imageView2);
    }
}