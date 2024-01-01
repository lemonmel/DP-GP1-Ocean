package ocean;

import javafx.application.Application;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
      for (int i = 0; i < OceanCreatureType.values().length; i++) {
         MenuItem menuItem = new MenuItem(OceanCreatureType.values()[i].name());
         int choice = i + 1;
         menuItem.setOnAction(e -> facade.addOceanCreature(choice));
         createCreatures.getItems().add(menuItem);
      }

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

      Menu changeColour = createChangeColourMenu(facade);

      Menu fish = new Menu("> Fishing");
      MenuBar bar = new MenuBar();
      bar.getMenus().addAll(createCreatures, changeTerrain, changeMode, changeColour, fish);
      return bar;
   }

   private Menu createChangeColourMenu(OceanFacade facade) {
      Menu changeColourMenu = new Menu("> Change Colour");
      List<Menu> creatureMenus = createOceanCreatureMenus();

      Color[] colors = { Color.RED, Color.ORANGE, Color.GREEN, Color.BLUE, Color.PURPLE };
      for (Menu creatureMenu : creatureMenus) {
         Menu addColor = new Menu("Add Color");
         MenuItem undoColor = new MenuItem("Undo Color");

         for (Color color : colors) {
            Rectangle colorBox = createColorBox(color);
            MenuItem colorMenuItem = new MenuItem("", colorBox);
            colorMenuItem.setOnAction(e -> facade.addOceanCreatureColor(e, color));
            addColor.getItems().add(colorMenuItem);
         }

         undoColor.setOnAction(e -> facade.undoOceanCreatureColor(e));

         changeColourMenu.getItems().add(creatureMenu);
         creatureMenu.getItems().addAll(addColor, undoColor);
      }
      return changeColourMenu;
   }

   private static List<Menu> createOceanCreatureMenus() {
      List<Menu> creatureMenus = new ArrayList<>();

      for (OceanCreatureType creatureType : OceanCreatureType.values()) {
         Menu menu = new Menu(creatureType.name());
         creatureMenus.add(menu);
      }

      return creatureMenus;
   }

   private Rectangle createColorBox(Color color) {
      Rectangle box = new Rectangle(20, 20); // Define the size of the colored box
      box.setFill(color); // Set the fill color of the rectangle
      return box;
   }

   public static void main(String[] args) {
      Application.launch(args);
   }
}
