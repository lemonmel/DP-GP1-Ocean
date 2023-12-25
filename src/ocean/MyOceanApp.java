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
   public static double INIT_TANK_HT = 1000;
   public static double INIT_TANK_WD = 710;

   private List<OceanCreature> oceanCreatures = new ArrayList<>();

   public void start(Stage stage) {
      // String path = "background-music.mp3";
      // Media media = new Media(Paths.get(path).toUri().toString());
      // MediaPlayer mediaPlayer = new MediaPlayer(media);
      // mediaPlayer.setAutoPlay(true);

      Font.loadFont(getClass().getResourceAsStream("/resources/SFPixelate.ttf"), 14);

      OceanFacade oceanFacade = new OceanFacade();
      Pane p = oceanFacade.getOceanPane();

      // Load the image for the background
      Image backgroundImage = new Image("/images/sea-background-2.gif");
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
      puffer.setOnAction(e -> addOceanCreature(facade, 1));
      MenuItem crab = new MenuItem("Crab");
      crab.setOnAction(e -> addOceanCreature(facade, 2));
      MenuItem jellyfish = new MenuItem("Jellyfish");
      jellyfish.setOnAction(e -> addOceanCreature(facade, 3));
      MenuItem anchovy = new MenuItem("Anchovy");
      anchovy.setOnAction(e -> addOceanCreature(facade, 4));
      MenuItem turtle = new MenuItem("Turtle");
      turtle.setOnAction(e -> addOceanCreature(facade, 5));
      MenuItem shark = new MenuItem("Shark");
      shark.setOnAction(e -> addOceanCreature(facade, 6));
      createCreatures.getItems().addAll(puffer, crab, jellyfish, anchovy, turtle, shark);

      Menu changeTerrain = new Menu("> Change Terrain");
      Menu changeMode = new Menu("> Change Mode");
      Menu changeColour = createChangeColourMenu();
      Menu fish = new Menu("> Fishing");
      MenuBar bar = new MenuBar();
      bar.getMenus().addAll(createCreatures, changeTerrain, changeMode, changeColour, fish);
      return bar;
   }

   private void addOceanCreature(OceanFacade facade, int choice) {
      var oceanCreature = facade.addOceanCreature(choice);
      oceanCreatures.add(oceanCreature);
   }

   private Menu createChangeColourMenu() {
      Menu changeColourMenu = new Menu("> Change Colour");
      List<Menu> creatureMenus = createOceanCreatureMenus();

      Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.PURPLE};
      for (Menu creatureMenu : creatureMenus) {
         Menu addColor = new Menu("Add Color");
         MenuItem undoColor = new MenuItem("Undo Color");

         for (Color color : colors) {
            Rectangle colorBox = createColorBox(color);
            MenuItem colorMenuItem = new MenuItem("", colorBox);
            colorMenuItem.setOnAction(e -> {
               Command changeColorCommand = new ChangeColorCommand(oceanCreatures, e, color);
               changeColorCommand.execute();
            });
            addColor.getItems().add(colorMenuItem);
         }

         undoColor.setOnAction(e -> {
            // Handle the action to undo color
         });

         changeColourMenu.getItems().add(creatureMenu);
         creatureMenu.getItems().addAll(addColor, undoColor);
      }
      return changeColourMenu;
   }

   private static List<Menu> createOceanCreatureMenus() {
      List<Menu> creatureMenus = new ArrayList<>();
      Menu pufferMenu = new Menu("Mackeral");
      creatureMenus.add(pufferMenu);
      Menu crabMenu = new Menu("Crab");
      creatureMenus.add(crabMenu);
      Menu jellyfishMenu = new Menu("Jellyfish");
      creatureMenus.add(jellyfishMenu);
      Menu anchovyMenu = new Menu("Anchovy");
      creatureMenus.add(anchovyMenu);
      Menu turtleMenu = new Menu("Turtle");
      creatureMenus.add(turtleMenu);
      Menu sharkMenu = new Menu("Shark");
      creatureMenus.add(sharkMenu);
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
