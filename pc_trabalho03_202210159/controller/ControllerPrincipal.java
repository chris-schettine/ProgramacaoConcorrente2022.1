/* ***************************************************************
* Autor............: Christian Schettine Paiva Rocha
* Matricula........: 202210159
* Inicio...........: 26/08/2023
* Ultima alteracao.: 01/09/2023
* Nome.............: ControllerPrincipal
* Funcao...........: Controla todos os movimentos dos trens
*************************************************************** */
//pacote da classe
package controller;
//importacoes
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

  @FXML
  private Button Botao_Pause_Consumidor;
  @FXML
  private Button Botao_Pause_Produtor;
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

  //atributos auxiliares de velocidade do produtor e consumidor 
  private int velocidadeSliderProdutor = 1;
  private int velocidadeSliderConsumidor;
  
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
    return velocidadeSliderProdutor;
  }
  public int getVelocidadeSliderConsumidor() {
    return velocidadeSliderConsumidor;
  }

  @FXML
  void OnClickBotao_Pause_Consumidor(ActionEvent event) {
    if (Botao_Pause_Consumidor.getText().equals("Pausar")){
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      Botao_Pause_Consumidor.setText("Retomar");
    } 
    else{
      try {
        Thread.sleep(0);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      Botao_Pause_Consumidor.setText("Pausar");
    }
  }

  @FXML
  void OnClickBotao_Pause_Produtor(ActionEvent event) {
    if (Botao_Pause_Consumidor.getText().equals("Pausar")){
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      Botao_Pause_Consumidor.setText("Retomar");
    } 
    else{
      try {
        Thread.sleep(0);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      Botao_Pause_Consumidor.setText("Pausar");
    }
  }
  
  @FXML
  void OnClickBotao_Reset(ActionEvent event){
    //encerrando as threads
    produtor.interrupt();
    consumidor.interrupt();

    //instanciando novas threads
    produtor = new Produtor(this);
    consumidor = new Consumidor(this);

    //restaurando os botoes de pausar
    Botao_Pause_Consumidor.setText("Pausar");
    Botao_Pause_Produtor.setText("Pausar");

    //tornando imagens dos produtos invisiveis
    imgHannibalCozinha.setVisible(false);
    imgHannibalServe.setVisible(false);

    //setando marcador inicial do sliders
    sliderProdutor.setValue(1);
    sliderConsumidor.setValue(0);

    //instanciando um novo semaforo
    Semaforo.setMutex(1);
    Semaforo.setVazio();
    Semaforo.setCheio(0);

    //iniciando as threads
    produtor.start();
    consumidor.start();
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    /*Subdividindo as barras de velocidades em tres partes, 
    setando as bolinhas de selecao para ocupar as tres partes da divisao.*/
    sliderProdutor.setMin(0);
    sliderProdutor.setMax(2);
    sliderProdutor.setValue(1);
    //definindo unidade de marcacao principal do slider
    sliderProdutor.setMajorTickUnit(1);
    //definindo as unidades de marcacoes menores que compoem os espacos entre as marcacoes principais
    sliderProdutor.setMinorTickCount(0);
    sliderProdutor.setSnapToTicks(true);

    sliderConsumidor.setMin(0);
    sliderConsumidor.setMax(2);
    sliderConsumidor.setValue(0);
    //definindo unidade de marcacao principal do slider
    sliderConsumidor.setMajorTickUnit(1);
    //definindo as unidades de marcacoes menores que compoem os espacos entre as marcacoes principais
    sliderConsumidor.setMinorTickCount(0);
    sliderConsumidor.setSnapToTicks(true);

    //Passando a referencia desse controlle para as threads que o chamam.
    produtor = new Produtor(this);
    consumidor = new Consumidor(this);
    produtor.start();
    consumidor.start();

    //Deixando os Image Views inicialmente invisiveis.
    imgPrato1.setVisible(false);
    imgPrato2.setVisible(false);
    imgPrato3.setVisible(false);
    imgHannibalCozinha.setVisible(false);
    imgHannibalServe.setVisible(false);

    //definindo listener para mudanca de velocidade do caminhao A em tempo real e atribuindo o novo valor ao atributo de velocidade do produtor
    sliderProdutor.valueProperty().addListener(new ChangeListener<Number>() {
      @Override
      public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue){
        //passando o novo valor para o atributo de velocidade do produtor
        velocidadeSliderProdutor = newValue.intValue();
      }
    });
    //definindo listener para mudanca de velocidade do caminhao B em tempo real e atribuindo o novo valor ao atributo de velocidade do consumidor
    sliderConsumidor.valueProperty().addListener(new ChangeListener<Number>() {
      @Override
      public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue){
        //passando o novo valor para o atributo de velocidade do consumidor
        velocidadeSliderConsumidor = newValue.intValue();
      }
    });
  }

}