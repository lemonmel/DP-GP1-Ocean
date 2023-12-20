package ocean;

import javafx.application.Application;

import java.io.File;
import java.nio.file.Paths;

import javafx.animation.AnimationTimer;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;

public class MyOceanApp extends Application {
   public static double INIT_TANK_HT = 540;
   public static double INIT_TANK_WD = 960;

   public void start(Stage stage) {
      // String path = "background-music.mp3";
      // Media media = new Media(Paths.get(path).toUri().toString());
      // MediaPlayer mediaPlayer = new MediaPlayer(media);
      // mediaPlayer.setAutoPlay(true);

      Font.loadFont(getClass().getResourceAsStream("/resources/SFPixelate.ttf"), 14);

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
      scene.getStylesheets().add(getClass().getResource("/resources/styles.css").toExternalForm());
      stage.setScene(scene);
      stage.setTitle("Ocean");
      stage.show();

      AnimationTimer timer = new CreatureAnimation(Ocean.getInstance(), p);
      timer.start();
   }

   private MenuBar createMenuBar(OceanFacade facade) {
      Menu createCreatures = new Menu("> Add Creatures");
      MenuItem puffer = new MenuItem("Mackeral");
      puffer.setOnAction(e -> facade.addOceanCreature(1));
      MenuItem crab = new MenuItem("Crab");
      crab.setOnAction(e -> facade.addOceanCreature(2));
      MenuItem jellyfish = new MenuItem("Jellyfish");
      jellyfish.setOnAction(e -> facade.addOceanCreature(3));
      MenuItem anchovy = new MenuItem("Anchovy");
      anchovy.setOnAction(e -> facade.addOceanCreature(4));
      MenuItem turtle = new MenuItem("Turtle");
      turtle.setOnAction(e -> facade.addOceanCreature(5));
      MenuItem shark = new MenuItem("Shark");
      shark.setOnAction(e -> facade.addOceanCreature(6));
      createCreatures.getItems().addAll(puffer, crab, jellyfish, anchovy, turtle, shark);

      Menu changeTerrain = new Menu("> Change Terrain");
      Menu changeMode = new Menu("> Change Mode");
      Menu changeColour = new Menu("> Change Colour");
      Menu fish = new Menu("> Fishing");
      MenuBar bar = new MenuBar();
      bar.getMenus().addAll(createCreatures, changeTerrain, changeMode, changeColour, fish);
      return bar;
   }

   public static void main(String[] args) {
      Application.launch(args);
   }
}
