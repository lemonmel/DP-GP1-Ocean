package ocean;

import javafx.application.Application;
import javafx.animation.AnimationTimer;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class MyOceanApp extends Application {
   public static double INIT_TANK_HT = 540;
   public static double INIT_TANK_WD = 960;

   public void start(Stage stage) {
      OceanFacade oceanFacade = new OceanFacade();
      Pane p = oceanFacade.getOceanPane();
      // Load the image for the background
      Image backgroundImage = new Image("/images/sea-background.jpg");
      BackgroundImage background = new BackgroundImage(
            backgroundImage,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            new BackgroundSize(
                  BackgroundSize.AUTO,
                  BackgroundSize.AUTO,
                  false,
                  false,
                  true,
                  false));

      p.setBackground(new Background(background));
      MenuBar bar = createMenuBar(oceanFacade);
      p.getChildren().add(bar);

      Scene scene = new Scene(p, INIT_TANK_WD, INIT_TANK_HT);
      stage.setScene(scene);
      stage.setTitle("Ocean");
      stage.show();

      AnimationTimer timer = new CreatureAnimation(Ocean.getInstance(), p);
      timer.start();
   }

   private MenuBar createMenuBar(OceanFacade facade) {
      Menu createCreatures = new Menu("Add Ocean Creatures");
      MenuItem puffer = new MenuItem("Puffer fish");
      puffer.setOnAction(e -> facade.addOceanCreature(1));
      MenuItem crab = new MenuItem("Crab");
      crab.setOnAction(e -> facade.addOceanCreature(2));
      MenuItem jellyfish = new MenuItem("Jellyfish");
      jellyfish.setOnAction(e -> facade.addOceanCreature(3));
      createCreatures.getItems().addAll(puffer, crab, jellyfish);

      MenuBar bar = new MenuBar();
      bar.getMenus().addAll(createCreatures);
      return bar;
   }

   public static void main(String[] args) {
      Application.launch(args);
   }
}
