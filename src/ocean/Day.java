package ocean;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Day implements BackgroundStrategy {
    private final Pane oceanPane;
    private Image backgroundImage;
    private ImageView backgroundImageView;

    public Day(Pane oceanPane, Image backgroundImage) {
        this.oceanPane = oceanPane;
        this.backgroundImage = backgroundImage;
    }


    @Override
    public void applyBackground() {
        if (!oceanPane.getChildren().isEmpty() && oceanPane.getChildren().get(0) instanceof ImageView) {
            oceanPane.getChildren().remove(0);
        }
        backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitWidth(oceanPane.getWidth());
        backgroundImageView.setFitHeight(oceanPane.getHeight());
        oceanPane.getChildren().add(0, backgroundImageView);
    }

    @Override
    public ImageView getBackgroundImage() {
        return backgroundImageView;
    }
}
