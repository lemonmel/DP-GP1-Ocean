# Design Pattern - GP1 - Ocean

The following steps are from https://openjfx.io/openjfx-docs/, under JavaFX and VisualStudioCode > Non-modular from IDE

To run:
1.Download all required JavaFX libraries (https://gluonhq.com/products/javafx/).
2.In VSCode, import all JavaFX jar files to ‘Referenced Libraries’ under ‘Java Projects’ as shown in Figure 7.1a. If there is no Java Projects under the left panel, go to ‘extensions’ on the left and search for ‘Extension Pack for Java’ to download.

Figure 7.1.1a: Referenced Libraries

Figure 7.1.1b: Extensions Installation
3.After importing, the settings.json under .vscode will be configured automatically. We have to configure ‘launch.json’.

Figure 7.1.1c .vscode folder
4.Configure ‘launch.json’ with code below:
{
 // Use IntelliSense to learn about possible attributes.
 // Hover to view descriptions of existing attributes.
 // For more information, visit: https://go.microsoft.com/fwlink/?linkid=830387
 "version": "0.2.0",
 "configurations": [
     {
         "type": "java",
         "name": "Launch App",
         "request": "launch",
         "mainClass": "ocean.MyOceanApp",
         "vmArgs": "--module-path /Users/chinfang/Downloads/javafx-sdk-21.0.1/lib --add-modules javafx.controls,javafx.fxml", //change to your directory to JavaFX
     }
 ]
}
5.Run the main method in ocean/MyOceanApp class.
