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
   public static double INIT_TANK_HT = 1000;
   public static double INIT_TANK_WD = 710;
   Pane p;
   Image backgroundImage;

   public void start(Stage stage) {
      // String path = "background-music.mp3";
      // Media media = new Media(Paths.get(path).toUri().toString());
      // MediaPlayer mediaPlayer = new MediaPlayer(media);
      // mediaPlayer.setAutoPlay(true);

      Font.loadFont(getClass().getResourceAsStream("/resources/SFPixelate.ttf"), 14);
      backgroundImage = new Image("/images/sea-background-2.gif");
      OceanFacade oceanFacade = new OceanFacade();
      p = oceanFacade.getOceanPane();

      MenuBar bar = createMenuBar(oceanFacade);
      p.getChildren().add(bar);

      Scene scene = new Scene(p, INIT_TANK_WD, INIT_TANK_HT);
      scene.getStylesheets().add(getClass().getResource("/resources/styles.css").toExternalForm());
      stage.setScene(scene);
      stage.setTitle("Ocean");
      stage.show();

      oceanFacade.setDaytimeStrategy(p, backgroundImage);
      oceanFacade.setSand(p);

      AnimationTimer timer = new CreatureAnimation(Ocean.getInstance(), p);
      timer.start();
   }

   private MenuBar createMenuBar(OceanFacade facade) {
      Menu createCreatures = new Menu("> Add ");
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
      MenuItem sand = new MenuItem("Sandy");
      sand.setOnAction(e -> facade.setSand(p));
      MenuItem grass = new MenuItem("Grass");
      grass.setOnAction(e -> facade.setGrass(p));
      MenuItem rock = new MenuItem("Rocky");
      rock.setOnAction(e -> facade.setRock(p));
      changeTerrain.getItems().addAll(sand, grass, rock);

      Menu changeMode = new Menu("> Change Mode");
      MenuItem day = new MenuItem("Day Mode");
      day.setOnAction(e -> facade.setDaytimeStrategy(p, backgroundImage));
      MenuItem night = new MenuItem("Night Mode");
      night.setOnAction(e -> facade.setNighttimeStrategy(p, backgroundImage));
      changeMode.getItems().addAll(day, night);

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
