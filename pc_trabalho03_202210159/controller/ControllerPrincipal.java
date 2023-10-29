/* ***************************************************************
* Autor............: Christian Schettine Paiva Rocha
* Matricula........: 202210159
* Inicio...........: 22/10/2023
* Ultima alteracao.: 29/10/2023
* Nome.............: ControllerPrincipal
* Funcao...........: Controla toda a animacao com botoes, define
* todas as configuracoes iniciais e administra as threads.
*************************************************************** */
//package
package controller;

//imports
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import model.Consumidor;
import model.Produtor;
import model.Semaforo;

public class ControllerPrincipal implements Initializable{

  //importacao dos elementos fxml que serao usados nos action handlers
  @FXML
  private Button Botao_Pause_Consumidor;
  @FXML
  private Button Botao_Pause_Produtor;
  @FXML
  private Button Botao_Retoma_Consumidor;
  @FXML
  private Button Botao_Retoma_Produtor;
  @FXML
  private Button Botao_Reset;
  @FXML
  private ImageView imgHannibalCozinha;
  @FXML
  private ImageView imgHannibalServe;
  @FXML
  private ImageView imgPrato1;
  @FXML
  private ImageView imgPrato2;
  @FXML
  private ImageView imgPrato3;
  @FXML
  private Slider sliderConsumidor;
  @FXML
  private Slider sliderProdutor;
  
  //instanciando threads no controle
  Produtor produtor;
  Consumidor consumidor;

  //variaveis usadas para pausar ou retomar a animação
  public boolean produtorTaPausado = false;
  public boolean consumidorTaPausado =false;
  
  //Getters e Setters
  public ImageView getimgPrato1() {
    return imgPrato1;
  }
  public void setimgPrato1(ImageView imgPrato1) {
    this.imgPrato1 = imgPrato1;
  }
  public ImageView getimgPrato2() {
    return imgPrato2;
  }
  public void setimgPrato2(ImageView imgPrato2) {
    this.imgPrato2 = imgPrato2;
  }
  public ImageView getimgPrato3() {
    return imgPrato3;
  }
  public void setimgPrato3(ImageView imgPrato3) {
    this.imgPrato3 = imgPrato3;
  }
  public ImageView getimgHannibalCozinha() {
    return imgHannibalCozinha;
  }
  public void setimgHannibalCozinha(ImageView imgHannibalCozinha) {
    this.imgHannibalCozinha = imgHannibalCozinha;
  }
  public ImageView getimgHannibalServe() {
    return imgHannibalServe;
  }
  public void setimgHannibalServe(ImageView imgHannibalServe) {
    this.imgHannibalServe = imgHannibalServe;
  }
  public int getVelocidadeSliderProdutor() {
    return doublePraInt(sliderProdutor.getValue());
  }
  public int getVelocidadeSliderConsumidor() {
    return doublePraInt(sliderConsumidor.getValue());
  }

  /* ***************************************************************
 * Evento: click
 * Funcao: pausa animacao do produtor e/ou consumidor
 * Parametros: nao recebe parametros
 * Retorno: nao retorna valores
 *************************************************************** */
  @FXML
  void OnClickBotao_Pause_Consumidor(ActionEvent event) {
    consumidorTaPausado = true;
    Botao_Pause_Consumidor.setStyle("-fx-background-color: #FF0000; -fx-text-fill: #FFFFFF;");
    Botao_Retoma_Consumidor.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #000000;");
  }

  @FXML
  void OnClickBotao_Pause_Produtor(ActionEvent event) {
    produtorTaPausado = true;
    Botao_Pause_Produtor.setStyle("-fx-background-color: #FF0000; -fx-text-fill: #FFFFFF;");
    Botao_Retoma_Produtor.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #000000;");
  }

  /* ***************************************************************
 * Evento: click
 * Funcao: retoma animacao do produtor e/ou consumidor
 * Parametros: nao recebe parametros
 * Retorno: nao retorna valores
 *************************************************************** */
  @FXML
  void OnClickBotao_Retoma_Consumidor(ActionEvent event) {
    consumidorTaPausado = false;
    Botao_Pause_Consumidor.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #000000;");
    Botao_Retoma_Consumidor.setStyle("-fx-background-color: #1A6F1A; -fx-text-fill: #FFFFFF;");
  }

  @FXML
  void OnClickBotao_Retoma_Produtor(ActionEvent event) {
    produtorTaPausado = false;
    Botao_Pause_Produtor.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #000000;");
    Botao_Retoma_Produtor.setStyle("-fx-background-color: #1A6F1A; -fx-text-fill: #FFFFFF;");
  }

  /* ***************************************************************
 * Metodo: doublePraInt
 * Funcao: recebe o valor double dos sliders de velocidade e retorna
 * em int
 * Parametros: recebe double do slider
 * Retorno: retorna valor arredondado para inteiro
 *************************************************************** */
  public int doublePraInt (double n){
    int IntValue = (int) Math.round(n);
  return IntValue;
  }

  /* ***************************************************************
 * Evento: click
 * Funcao: reseta toda a animacao
 * Parametros: nao recebe parametros
 * Retorno: nao retorna valores
 *************************************************************** */
  @FXML
  void OnClickBotao_Reset(ActionEvent event){
    //interrompe as threads
    produtor.interrupt();
    consumidor.interrupt();

    //instancia novas threads
    produtor = new Produtor(this);
    consumidor = new Consumidor(this);

    //tornando imagem inicial visivel e demais, invisiveis
    imgHannibalCozinha.setVisible(true);
    imgHannibalServe.setVisible(false);
    imgPrato1.setVisible(false);
    imgPrato2.setVisible(false);
    imgPrato3.setVisible(false);

    //estilo inicial dos botoes
    Botao_Pause_Consumidor.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #000000;");
    Botao_Retoma_Consumidor.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #000000;");
    Botao_Pause_Produtor.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #000000;");
    Botao_Retoma_Produtor.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #000000;");

    //configura marcador inicial do sliders de velocidade
    sliderProdutor.setValue(1);
    sliderConsumidor.setValue(0);

    //instancia novo semaforo
    Semaforo.setMutex(1);
    Semaforo.setVazio();
    Semaforo.setCheio(0);
    
    //funcao do produtor que reseta variavel usada na thread para percorrer todos os pratos
    produtor.resetaPrato();

    //reset das variaveis de pause, para voltar ao estado inicial de execucao
    produtorTaPausado = false;
    consumidorTaPausado = false;

    //inicia as threads
    produtor.start();
    consumidor.start();
  }

  /* ***************************************************************
 * Classe: initialize
 * Funcao: inicializa as configurações do slider, passa a referencia
 * para as threads e define os Image Views iniciais
 * Parametros: nao recebe parametros
 * Retorno: nao retorna valores
 *************************************************************** */
  @Override
  public void initialize(URL location, ResourceBundle resources) {

    //configuracoes do slider de velocidade do produtor
    sliderProdutor.setMin(0);
    sliderProdutor.setMax(2);
    sliderProdutor.setValue(1);
    //definindo unidade de marcacao principal do slider
    sliderProdutor.setMajorTickUnit(1);
    //definindo as unidades de marcacoes menores que compoem os espacos entre as marcacoes principais
    sliderProdutor.setMinorTickCount(0);
    sliderProdutor.setSnapToTicks(true);

    //configuracoes do slider de velocidade do consumidor
    sliderConsumidor.setMin(0);
    sliderConsumidor.setMax(2);
    sliderConsumidor.setValue(0);
    //definindo unidade de marcacao principal do slider
    sliderConsumidor.setMajorTickUnit(1);
    //definindo as unidades de marcacoes menores que compoem os espacos entre as marcacoes principais
    sliderConsumidor.setMinorTickCount(0);
    sliderConsumidor.setSnapToTicks(true);

    //passa a referencia do controller para as threads que o chamam.
    produtor = new Produtor(this);
    consumidor = new Consumidor(this);
    produtor.start();
    consumidor.start();

    //tornando imagem inicial visivel e demais, invisiveis
    imgHannibalCozinha.setVisible(true);
    imgHannibalServe.setVisible(false);
    imgPrato1.setVisible(false);
    imgPrato2.setVisible(false);
    imgPrato3.setVisible(false);
  }
}