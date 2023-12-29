package ocean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Fisherman {
    private Pane oceanPane;
    private ImageView fishermanView;
    private Pane displayPane;

    public Fisherman(Pane oceanPane) {
        this.oceanPane = oceanPane;
        this.fishermanView = new ImageView(new Image(getClass().getResourceAsStream("/images/fisherman-1.gif")));
        this.fishermanView.setPreserveRatio(true);
        this.fishermanView.setFitHeight(100); // Adjust the size as needed
        this.displayPane = displayPane;
    }

    public void startFishing() {
        positionFisherman();
        oceanPane.getChildren().add(fishermanView);
        chooseMethod(displayPane);
    }

    private void positionFisherman() {
        // Assuming you want to center the fisherman in the Pane
        double relativeX = oceanPane.getWidth() * 0.4;
        double relativeY = oceanPane.getHeight() * 0.35;

        // Adjust for the size of the fisherman image
        double offsetX = fishermanView.getBoundsInParent().getWidth() / 2;
        double offsetY = fishermanView.getBoundsInParent().getHeight() / 2;

        fishermanView.setLayoutX(relativeX - offsetX);
        fishermanView.setLayoutY(relativeY - offsetY);
    }

    public void chooseMethod(Pane pane) {
        ChoiceDialog<String> choiceDialog = new ChoiceDialog<>("Fishing Rod", "Fishing Rod", "Fishing Net");
        choiceDialog.setTitle("Choose Fishing Method");
        choiceDialog.setHeaderText("Select your fishing method:");
        choiceDialog.setContentText("Choose:");

        String result = choiceDialog.showAndWait().orElse("Fishing Rod");

        ImageView imageView = new ImageView();
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(100); // Adjust as needed

        if ("Fishing Rod".equals(result)) {
            imageView.setImage(new Image("/src/images/fisherman-2.gif"));
        } else {
            imageView.setImage(new Image("/src/images/fishingnet.gif"));
        }

        pane.getChildren().add(imageView);
    }
}

