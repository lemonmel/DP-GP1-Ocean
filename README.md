# Design Pattern - GP1 - Ocean
<img width="400" alt="Screenshot 2024-07-16 at 4 00 55 PM" src="https://github.com/user-attachments/assets/1fac873c-a37b-45f5-bb08-b700e0f9e820">


### To run:
> The following steps are from https://openjfx.io/openjfx-docs/, under JavaFX and VisualStudioCode > Non-modular from IDE


1. Download all required JavaFX libraries (https://gluonhq.com/products/javafx/).<br/>
2. In VSCode, import all JavaFX jar files to ‘Referenced Libraries’ under ‘Java Projects’. If there is no Java Projects under the left panel, go to ‘extensions’ on the left and search for ‘Extension Pack for Java’ to download. <br/>
3. After importing, the settings.json under .vscode will be configured automatically. We have to configure ‘launch.json’. <br/>
4. Configure ‘launch.json’ with code below:
```
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
```
5. Run the main method in ocean/MyOceanApp class.
