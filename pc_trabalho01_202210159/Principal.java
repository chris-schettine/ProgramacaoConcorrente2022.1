/* ***************************************************************
 * Autor............: Christian Schettine Paiva Rocha
 * Matricula........: 202210159
 * Inicio...........: 26/08/2023
 * Ultima alteracao.: 01/09/2023
 * Nome.............: Principal
 * Funcao...........: Este codigo serve para inicializar a interface grafica.
 *************************************************************** */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import controller.ControllerPrincipal;

public class Principal extends Application {
  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("fxml/TelaPrincipal.fxml")); //Carrega o FXML
    Scene scene = new Scene(root); //Cria a cena
    primaryStage.setTitle("Animacao Trens"); //Declara o titulo
    primaryStage.setScene(scene); //Seta a cena
    primaryStage.setResizable(false); //Impede de redimensionar a tela
    primaryStage.show(); //Exibe a tela

    primaryStage.setOnCloseRequest(Event -> { //Event Handler que fecha a tela ao clicar no "X"
      System.exit(0);
    });
  }

  public static void main(String[] args) {
    launch(args); //Lanca os argumentos
  }
}
