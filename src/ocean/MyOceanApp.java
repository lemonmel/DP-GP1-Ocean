package ocean;

import javafx.animation.*;
import javafx.application.Application;

import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.Iterator;



public class MyOceanApp extends Application {
   public static double INIT_TANK_HT = 1000;
   public static double INIT_TANK_WD = 710;
   Pane p;
   Image backgroundImage;
   double fishermanX; // Declare fishermanX as a class-level field
   double fishermanY; // Declare fishermanY as a class-level field
   Image fishermanImage;

   FishingRod fishingRod;

   public void start(Stage stage) {
      // String path = "background-music.mp3";
      // Media media = new Media(Paths.get(path).toUri().toString());
      // MediaPlayer mediaPlayer = new MediaPlayer(media);
      // mediaPlayer.setAutoPlay(true);

      Font.loadFont(getClass().getResourceAsStream("/resources/SFPixelate.ttf"), 14);
      backgroundImage = new Image("/images/sea-background-2.gif");
      OceanFacade oceanFacade = new OceanFacade();
      p = oceanFacade.getOceanPane();

      // Load the image of the fisherman
      fishermanImage = new Image("/images/fisherman-1.gif",100,100, true, true);
      // Create an ImageView for the fisherman
      ImageView fishermanImageView = new ImageView(fishermanImage);

      // Position the fisherman at the middle top
      double windowWidth = INIT_TANK_WD;
      fishermanX = (windowWidth - fishermanImage.getWidth()) / 2;
      fishermanY = 0.19 * INIT_TANK_HT; // Adjust the ratio as needed
      fishermanImageView.setLayoutX(fishermanX);
      fishermanImageView.setLayoutY(fishermanY);

      //fishing Rod
      fishingRod = new FishingRod(INIT_TANK_WD / 2 + 35, INIT_TANK_HT * 0.23);

      MenuBar bar = createMenuBar(oceanFacade);
      p.getChildren().add(bar);

      //add fisherman image
      p.getChildren().addAll(fishermanImageView);

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
      MenuItem fishingButton = new MenuItem("Start Fishing");
      fishingButton.setOnAction(e -> startFishing(Ocean.getInstance()));
      fish.getItems().add(fishingButton);

      MenuBar bar = new MenuBar();
      bar.getMenus().addAll(createCreatures, changeTerrain, changeMode, changeColour, fish);
      return bar;
   }

   private void startFishing(Ocean ocean) {
      System.out.println("Fishing Called");
      fishingRod.drop();

      // Create a circle for the fishing rod
      Circle fishingRodCircle = new Circle(fishingRod.getX(), fishingRod.getY(), 10);
      fishingRodCircle.setFill(Color.web("#D20117")); // Set the fill color of the fishing rod

      // Create a line for the fishing rod
      Line fishingRodLine = new Line(fishingRod.getX(), fishingRod.getY(), fishingRod.getX(), fishingRod.getY());
      fishingRodLine.setStroke(Color.WHITE); // Set the color of the fishing rod
      fishingRodLine.setStrokeWidth(2); // Set the width of the fishing rod line

      // Add the fishing rod circle and line to the Pane
      p.getChildren().addAll(fishingRodLine, fishingRodCircle);

      // Create a timeline to update the fishing rod position
      Timeline fishingTimeline = new Timeline(
              new KeyFrame(Duration.millis(16), event -> {
                 fishingRod.updateRod();
                 fishingRodCircle.setCenterY(fishingRod.getY() + 5); // Update the circle's position
                 fishingRodLine.setEndY(fishingRod.getY() + 5); // Update the line's end position

                 // Iterate through all fish in the ocean
                 Iterator<OceanCreature> iterator = ocean.oceanCreatures.iterator();
                 while (iterator.hasNext()) {
                    OceanCreature oceanCreature = iterator.next();

                    if (fishingRod.isCatching(oceanCreature)) {
                       // Caught a fish
                       System.out.println("Caught a " + oceanCreature.getClass().getSimpleName());

                       // Remove the caught fish from the ocean
                       iterator.remove();
                       p.getChildren().remove(oceanCreature.getView());

                       // Show success notification
                       showSuccessNotification(oceanCreature);
                    }
                 }

                 // Check if the fishing rod has reached the bottom
                 if (fishingRod.getY() >= INIT_TANK_HT) {
                    // Reset the fishing rod position and state
                    fishingRod.isDropped = false;
                    fishingRod.y = INIT_TANK_HT * 0.23;

                    // Remove the fishing rod circle from the Pane
                    p.getChildren().removeAll(fishingRodCircle, fishingRodLine);

                 }
              })
      );

      fishingTimeline.setCycleCount(Timeline.INDEFINITE); // Repeat indefinitely
      fishingTimeline.play();
   }
   private void showSuccessNotification(OceanCreature oceanCreature) {
      Platform.runLater(() -> {
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setTitle("Success");
         alert.setHeaderText(null);

         // Create an ImageView for the caught fish
         ImageView fishImageView = new ImageView(oceanCreature.getImage());
         fishImageView.setFitWidth(100); // Adjust the size as needed
         fishImageView.setFitHeight(100);

         // Create a VBox to hold the fish image and text
         VBox vbox = new VBox(10);
         vbox.setAlignment(Pos.CENTER);
         vbox.getChildren().addAll(fishImageView, new Text(oceanCreature.getClass().getSimpleName() + " caught!"));

         alert.getDialogPane().setContent(vbox);
         alert.showAndWait();
      });
   }


   private class FishingRod{
      private double x;
      private double y;
      private double width;
      private double height;
      private boolean isDropped;

      private Line fishingLine;

      public FishingRod(double x, double y) {
         this.x = x;
         this.y = y;
         this.width = 10;
         this.height = 10;
         this.isDropped = false;
      }

      public void updateRod() {
         if (isDropped) {
            y += 5;
            if (y > INIT_TANK_HT) {
               isDropped = false;
               y = 0.23 * INIT_TANK_HT;
            }
         }
      }

      public boolean isCatching(OceanCreature oceanCreature) {
         Bounds fishBounds = oceanCreature.getView().getBoundsInParent();

         double rodLeft = x;
         double rodRight = x + width;
         double rodTop = y;
         double rodBottom = y + height;

         double fishLeft = fishBounds.getMinX();
         double fishRight = fishBounds.getMaxX();
         double fishTop = fishBounds.getMinY();
         double fishBottom = fishBounds.getMaxY();

         boolean xOverlap = rodRight > fishLeft && rodLeft < fishRight;
         boolean yOverlap = rodBottom > fishTop && rodTop < fishBottom;

         return isDropped && xOverlap && yOverlap;
      }



      public void drop() {
         isDropped = true;
      }

      public double getX() {
         return x;
      }

      public double getY() {
         return y;
      }

   }


   public static void main(String[] args) {
      Application.launch(args);
   }
}
