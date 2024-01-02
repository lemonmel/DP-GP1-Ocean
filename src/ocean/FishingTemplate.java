package ocean;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public abstract class FishingTemplate {
    Pane p = Ocean.getOceanPane();
    final Image fishermanImage = new Image("/images/fisherman-1.gif", 100, 100, true, true);
    final ImageView fishermanImageView = new ImageView(fishermanImage);
    double x, y;
    Line fishingLine;
    Circle fishingEquip;
    OceanCreature caughtOceanCreature;

    final void performFishing() {
        prepare();
        setFishingObject(x, y);
        throwFishingEquip();
        boolean caught = checkCaught();
        discard(caught);
    }

    void prepare() {
        // display fisherman
        fishermanImageView.setLayoutX((p.getWidth() - fishermanImage.getWidth()) / 2);
        fishermanImageView.setLayoutY(p.getHeight() * 0.16);
        p.getChildren().add(fishermanImageView);
        x = fishermanImageView.getLayoutX() + fishermanImage.getWidth() - 5;
        y = fishermanImageView.getLayoutY() + 5;

        fishingLine = new Line(x, y, x, y);
        fishingLine.setStroke(Color.WHITE); // Set the color of the fishing rod
        fishingLine.setStrokeWidth(3); // Set the width of the fishing rod line
        p.getChildren().add(fishingLine);
    }

    abstract void setFishingObject(double x, double y);

    void throwFishingEquip() {
        // animate fishing equipment
        double oceanStopPoint = p.getHeight() * 0.8;
        KeyValue circleYValue = new KeyValue(fishingEquip.centerYProperty(), oceanStopPoint);
        KeyValue lineEndYValue = new KeyValue(fishingLine.endYProperty(), oceanStopPoint);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(4), circleYValue, lineEndYValue);
        Timeline timeline = new Timeline(keyFrame);

        timeline.play();
    }

    abstract boolean checkCaught();

    void discard(boolean caught) {
        // discard everything and display caught result
        Timeline fishingTimeline = new Timeline(
                new KeyFrame(Duration.seconds(5), event -> {
                    int length = p.getChildren().size();
                    for (int i = length - 1; i >= length - 3; i--) {
                        p.getChildren().remove(i);
                        System.out.println(i + ": current index");
                    }
                }));

        fishingTimeline.setOnFinished(e -> {
            String text = "HMMMM, I wonder there are no creatures inside or I was just bad luck :(";
            String title = "Opps, try next time!";
            if (caught && Math.random() < 0.7) { // Not always we can caught a fish right
                text = "Yeah! Caught a " + caughtOceanCreature.getClass().getSimpleName() + "!";
                title = "Congratulations!";
                Ocean.getInstance().removeOceanCreature(caughtOceanCreature);
            }
            Alert alert = new Alert(Alert.AlertType.NONE, text, ButtonType.APPLY);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.getDialogPane().getStylesheets().add(
                    getClass().getResource("/resources/styles.css").toExternalForm());

            alert.show();
        });
        fishingTimeline.play();
    }

}
