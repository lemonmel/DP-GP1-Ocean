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
import javafx.scene.text.Font;

public class MyOceanApp extends Application {
   Pane p;
   Image backgroundImage;

   public void start(Stage stage) {
      Font.loadFont(getClass().getResourceAsStream("/resources/SFPixelate.ttf"), 14);
      backgroundImage = new Image("/images/sea-background-2.gif");
      OceanFacade oceanFacade = new OceanFacade();
      p = oceanFacade.getOceanPane();

      MenuBar bar = createMenuBar(oceanFacade);
      p.getChildren().add(bar);

      Scene scene = new Scene(p, 710, 770); // pane, initial width, initial height
      scene.getStylesheets().add(getClass().getResource("/resources/styles.css").toExternalForm());
      stage.setScene(scene);
      stage.setTitle("Ocean");
      stage.show();

      oceanFacade.setDaytimeStrategy(backgroundImage);
      oceanFacade.setSand();

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
      sand.setOnAction(e -> facade.setSand());
      MenuItem grass = new MenuItem("Grass");
      grass.setOnAction(e -> facade.setGrass());
      MenuItem rock = new MenuItem("Rocky");
      rock.setOnAction(e -> facade.setRock());
      changeTerrain.getItems().addAll(sand, grass, rock);

      Menu changeMode = new Menu("> Change Mode");
      MenuItem day = new MenuItem("Day Mode");
      day.setOnAction(e -> facade.setDaytimeStrategy(backgroundImage));
      MenuItem night = new MenuItem("Night Mode");
      night.setOnAction(e -> facade.setNighttimeStrategy(backgroundImage));
      changeMode.getItems().addAll(day, night);

      Menu changeColour = createChangeColourMenu(facade);

      Menu fish = new Menu("> Fishing");
      MenuItem rod = new MenuItem("Rod for Mackerel/Anchovy");
      rod.setOnAction(e -> facade.fishByRod());
      MenuItem net = new MenuItem("Net for Jellyfish/Crab");
      net.setOnAction(e -> facade.fishByNet());
      fish.getItems().addAll(rod, net);

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
