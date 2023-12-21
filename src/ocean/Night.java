package ocean;

import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Night implements BackgroundStrategy {
    private final Pane oceanPane;
    private Image backgroundImage;
    private ImageView backgroundImageView;

    public Night(Pane oceanPane, Image backgroundImage) {
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

        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(-0.8);
        colorAdjust.setContrast(1);
        colorAdjust.setSaturation(2.0);
        backgroundImageView.setEffect(colorAdjust);

        oceanPane.getChildren().add(0, backgroundImageView);
    }

    @Override
    public ImageView getBackgroundImage() {
        return backgroundImageView;
    }
}
