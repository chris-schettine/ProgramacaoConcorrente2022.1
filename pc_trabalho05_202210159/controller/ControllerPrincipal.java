/* ***************************************************************
* Autor............: Christian Schettine Paiva Rocha
* Matricula........: 202210159
* Inicio...........: 25/11/2023
* Ultima alteracao.: 04/12/2023
* Nome.............: ControllerPrincipal
* Funcao...........: Controla toda a animacao com botoes, define
*                    todas as configuracoes iniciais e administra 
*                    as threads.
*************************************************************** */
package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import model.Carro;

public class ControllerPrincipal implements Initializable{

  //Elementos FXML dos carros
  @FXML
  public ImageView imagemCarro1;
  @FXML
  public Button Botao_Pause_Carro1;
  @FXML
  public Button Botao_Play_Carro1;
  @FXML
  public Slider sliderVelocidadeCarro1;

  //
  @FXML
  public ImageView imagemCarro2;
  @FXML
  public Button Botao_Pause_Carro2;
  @FXML
  public Button Botao_Play_Carro2;
  @FXML
  public Slider sliderVelocidadeCarro2;

  //
  @FXML
  public ImageView imagemCarro3;
  @FXML
  public Button Botao_Pause_Carro3;
  @FXML
  public Button Botao_Play_Carro3;
  @FXML
  public Slider sliderVelocidadeCarro3;

  //
  @FXML
  public ImageView imagemCarro4;
  @FXML
  public Button Botao_Pause_Carro4;
  @FXML
  public Button Botao_Play_Carro4;
  @FXML
  public Slider sliderVelocidadeCarro4;

  //
  @FXML
  public ImageView imagemCarro5;
  @FXML
  public Button Botao_Pause_Carro5;
  @FXML
  public Button Botao_Play_Carro5;
  @FXML
  public Slider sliderVelocidadeCarro5;

  //
  @FXML
  public ImageView imagemCarro6;
  @FXML
  public Button Botao_Pause_Carro6;
  @FXML
  public Button Botao_Play_Carro6;
  @FXML
  public Slider sliderVelocidadeCarro6;

  //
  @FXML
  public ImageView imagemCarro7;
  @FXML
  public Button Botao_Pause_Carro7;
  @FXML
  public Button Botao_Play_Carro7;
  @FXML
  public Slider sliderVelocidadeCarro7;

  //
  @FXML
  public ImageView imagemCarro8;
  @FXML
  public Button Botao_Pause_Carro8;
  @FXML
  public Button Botao_Play_Carro8;
  @FXML
  public Slider sliderVelocidadeCarro8;

  //Botao responsavel por resetar o programa
  @FXML
  public Button Botao_Reset;

  //Checkbox responsaveis por mostrar os trajetos
  @FXML
  private CheckBox CheckBoxGuia1;
  @FXML
  private CheckBox CheckBoxGuia2;
  @FXML
  private CheckBox CheckBoxGuia3;
  @FXML
  private CheckBox CheckBoxGuia4;
  @FXML
  private CheckBox CheckBoxGuia5;
  @FXML
  private CheckBox CheckBoxGuia6;
  @FXML
  private CheckBox CheckBoxGuia7;
  @FXML
  private CheckBox CheckBoxGuia8;

  //Imagem dessas guias
  @FXML
  private ImageView imagemGuia1;
  @FXML
  private ImageView imagemGuia2;
  @FXML
  private ImageView imagemGuia3;
  @FXML
  private ImageView imagemGuia4;
  @FXML
  private ImageView imagemGuia5;
  @FXML
  private ImageView imagemGuia6;
  @FXML
  private ImageView imagemGuia7;
  @FXML
  private ImageView imagemGuia8;

  //Vetor de carros, com tamanho 8
  public static final int N = 8;
  public static Carro[] carros = new Carro[N];

  //Vetor de imagens, sliders e variaveis usadas pelo programa
  private Slider[] velocidadeDoCarro;
  private ImageView[] imagemCarro;
  private String[] caminhos;
  private double[] coordenadasIniciaisX;
  private double[] coordenadasIniciaisY;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    start();
  }

  /* ***************************************************************
   * Metodo: getters de velocidade e da imagem do carro 
   * Funcao: Verifica o valor atual do slider/carro correspondente e
   * o passa para as Threads
   * Parametros: Sem parametros.
   * Retorno: retorna um valor numerico de tipo inteiro.
  *************************************************************** */

  public int getVelocidade(int id){
    return (int) velocidadeDoCarro[id].getValue();
  }

  public ImageView getImagemCarro(int id){
    return imagemCarro[id];
  }

  /* ***************************************************************
   * Metodo: pausa e retoma
   * Funcao: Pausar as Threads ao evento de click
   * Parametros: Sem parametros
   * Retorno: Sem retorno.
   *************************************************************** */

  @FXML
  void OnClickBotao_Pause_Carro1(ActionEvent event) {
    carros[0].pause();
  }
  @FXML
  void OnClickBotao_Play_Carro1(ActionEvent event) {
    carros[0].retomar();
  }
  @FXML
  void OnClickBotao_Pause_Carro2(ActionEvent event) {
    carros[1].pause();
  }
  @FXML
  void OnClickBotao_Play_Carro2(ActionEvent event) {
    carros[1].retomar();
  }
  @FXML
  void OnClickBotao_Pause_Carro3(ActionEvent event) {
    carros[2].pause();
  }
  @FXML
  void OnClickBotao_Play_Carro3(ActionEvent event) {
    carros[2].retomar();
  }
  @FXML
  void OnClickBotao_Pause_Carro4(ActionEvent event) {
    carros[3].pause();
  }
  @FXML
  void OnClickBotao_Play_Carro4(ActionEvent event) {
    carros[3].retomar();
  }
  @FXML
  void OnClickBotao_Pause_Carro5(ActionEvent event) {
    carros[4].pause();
  }
  @FXML
  void OnClickBotao_Play_Carro5(ActionEvent event) {
    carros[4].retomar();
  }
  @FXML
  void OnClickBotao_Pause_Carro6(ActionEvent event) {
    carros[5].pause();
  }
  @FXML
  void OnClickBotao_Play_Carro6(ActionEvent event) {
    carros[5].retomar();
  }
  @FXML
  void OnClickBotao_Pause_Carro7(ActionEvent event) {
    carros[6].pause();
  }
  @FXML
  void OnClickBotao_Play_Carro7(ActionEvent event) {
    carros[6].retomar();
  }
  @FXML
  void OnClickBotao_Pause_Carro8(ActionEvent event) {
    carros[7].pause();
  }
  @FXML
  void OnClickBotao_Play_Carro8(ActionEvent event) {
    carros[7].retomar();
  }

  /* ***************************************************************
   * Metodo: handleCheckBox
   * Funcao: Mostrar as guias dos trajetos
   * Parametros: Sem parametros
   * Retorno: Sem retorno.
   *************************************************************** */
  @FXML
  public void handleCheckBox1() {
    if (CheckBoxGuia1.isSelected()) {
      imagemGuia1.setVisible(true);
    } else {
      imagemGuia1.setVisible(false);
    }
  }
  @FXML
  public void handleCheckBox2() {
    if (CheckBoxGuia2.isSelected()) {
      imagemGuia2.setVisible(true);
    } else {
      imagemGuia2.setVisible(false);
    }
  }
  @FXML
  public void handleCheckBox3() {
    if (CheckBoxGuia3.isSelected()) {
      imagemGuia3.setVisible(true);
    } else {
      imagemGuia3.setVisible(false);
    }
  }
  @FXML
  public void handleCheckBox4() {
    if (CheckBoxGuia4.isSelected()) {
      imagemGuia4.setVisible(true);
    } else {
      imagemGuia4.setVisible(false);
    }
  }
  @FXML
  public void handleCheckBox5() {
    if (CheckBoxGuia5.isSelected()) {
      imagemGuia5.setVisible(true);
    } else {
      imagemGuia5.setVisible(false);
    }
  }
  @FXML
  public void handleCheckBox6() {
    if (CheckBoxGuia6.isSelected()) {
      imagemGuia6.setVisible(true);
    } else {
      imagemGuia6.setVisible(false);
    }
  }
  @FXML
  public void handleCheckBox7() {
    if (CheckBoxGuia7.isSelected()) {
      imagemGuia7.setVisible(true);
    } else {
      imagemGuia7.setVisible(false);
    }
  }
  @FXML
  public void handleCheckBox8() {
    if (CheckBoxGuia8.isSelected()) {
      imagemGuia8.setVisible(true);
    } else {
      imagemGuia8.setVisible(false);
    }
  }
  
  /* ***************************************************************
   * Metodo: OnClickBotao_Reset
   * Funcao: Resetar o programa ao evento de click
   * Parametros: Sem parametros
   * Retorno: Sem retorno.
   *************************************************************** */

  @FXML
  void OnClickBotao_Reset(ActionEvent event) {
    resetaThreads();
    start();
  }

  /* ***************************************************************
   * Metodo: resetaThreads
   * Funcao: Interromper todas as Threads para o reset
   * Parametros: Sem parametros
   * Retorno: Sem retorno.
   *************************************************************** */

  public void resetaThreads() {
    for (int x = 0; x < N; x++) {
      carros[x].pause();
    }
    for (int y = 0; y < N; y++) {
      carros[y].interrupt();
    }
  }

  /* ***************************************************************
   * Metodo: start
   * Funcao: startar as threads.
   * Parametros: Sem parametros.
   * Retorno: Sem retorno.
  *************************************************************** */

  public void start() {

    imagemGuia1.setVisible(false);
    imagemGuia2.setVisible(false);
    imagemGuia3.setVisible(false);
    imagemGuia4.setVisible(false);
    imagemGuia5.setVisible(false);
    imagemGuia6.setVisible(false);
    imagemGuia7.setVisible(false);
    imagemGuia8.setVisible(false);

    velocidadeDoCarro = new Slider[]{sliderVelocidadeCarro1, sliderVelocidadeCarro2, sliderVelocidadeCarro3, sliderVelocidadeCarro4, sliderVelocidadeCarro5, sliderVelocidadeCarro6, sliderVelocidadeCarro7, sliderVelocidadeCarro8};
    imagemCarro = new ImageView[]{imagemCarro1, imagemCarro2, imagemCarro3, imagemCarro4, imagemCarro5, imagemCarro6, imagemCarro7, imagemCarro8};
    caminhos = new String[]{"P01SA", "P05SA", "P07SH", "P12SA", "P13SH", "P18SA", "P20SH", "P21SH"};
    coordenadasIniciaisX = new double[]{429.0, 224.0, 642.0, 488.0, 380.0, 537.0, 537.0, 217.0};
    coordenadasIniciaisY = new double[]{157.0, 76.0, 243.0, 184.0, 184.0, 240.0, 157.0, 240.0};


    for(int i=0; i<N ;i++) {
      velocidadeDoCarro[i].setValue(5);
      velocidadeDoCarro[i].setMin(0);
      velocidadeDoCarro[i].setMax(2);
      velocidadeDoCarro[i].setMajorTickUnit(1);
      velocidadeDoCarro[i].setMinorTickCount(0);
      velocidadeDoCarro[i].setSnapToTicks(true);

      imagemCarro[i].setLayoutX(coordenadasIniciaisX[i]);
      imagemCarro[i].setLayoutY(coordenadasIniciaisY[i]);

      carros[i] = new Carro(this, caminhos[i], coordenadasIniciaisX[i], coordenadasIniciaisY[i]);
    }

    Carro.setSemaforo(1);

    carros[0].start();
    carros[1].start();
    carros[2].start();
    carros[3].start();
    carros[4].start();
    carros[5].start();
    carros[6].start();
    carros[7].start();
  }
}
