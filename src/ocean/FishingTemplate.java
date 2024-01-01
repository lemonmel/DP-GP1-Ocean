package ocean;

import javafx.scene.control.ChoiceDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class FishingTemplate {
    protected String fishType;
    protected Fish fish;

    public void setFish(Fish fish) {
        this.fish = fish;
    }

    public final void performFishing() {
       // chooseMethod();
        preparation();
        throwFishingEquip();
        waitingForFish();
        pullFishingEquip();
        showFish();
    }

    // public void chooseMethod(Pane pane) {
    //     ChoiceDialog<String> choiceDialog = new ChoiceDialog<>("Fishing Rod", "Fishing Rod", "Fishing Net");
    //     choiceDialog.setTitle("Choose Fishing Method");
    //     choiceDialog.setHeaderText("Select your fishing method:");
    //     choiceDialog.setContentText("Choose:");

    //     String result = choiceDialog.showAndWait().orElse("Fishing Rod");

    //     ImageView imageView = new ImageView();
    //     imageView.setPreserveRatio(true);
    //     imageView.setFitHeight(100); // Adjust as needed

    //     if ("Fishing Rod".equals(result)) {
    //         imageView.setImage(new Image("/src/images/fisherman-2.gif"));
    //     } else {
    //         imageView.setImage(new Image("/src/images/fishingnet.gif"));
    //     }

    //     pane.getChildren().add(imageView);
    // }

    protected void preparation() { //concrete method
        System.out.println("Start fishing for " + fishType);
        // Display message to user in JavaFX
    }

    protected abstract void throwFishingEquip(); //abstract method

    protected void waitingForFish() {   //concrete method
        System.out.println("Waiting...");
        // Display waiting message to user in JavaFX
    }

    protected abstract void pullFishingEquip(); //abstract method

    protected void showFish() { //concrete method
        System.out.println("You have caught " + fishType + " fish!");
        // Display caught fish message to user in JavaFX
    }
}