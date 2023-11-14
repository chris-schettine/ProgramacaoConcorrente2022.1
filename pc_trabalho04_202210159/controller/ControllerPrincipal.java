/* ***************************************************************
* Autor............: Christian Schettine Paiva Rocha
* Matricula........: 202210159
* Inicio...........: 07/11/2023
* Ultima alteracao.: 14/11/2023
* Nome.............: ControllerPrincipal
* Funcao...........: Controla toda a animacao com botoes, define
*                    todas as configuracoes iniciais e administra 
*                    as threads.
*************************************************************** */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Semaphore;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import model.Escritor;
import model.Leitor;

public class ControllerPrincipal implements Initializable {

  //Elementos FXML dos escritores
  @FXML
  public ImageView progNoDiscord1;
  @FXML
  public ImageView progTrabalha1;
  @FXML
  public Slider velProgNoDiscord1;
  @FXML
  public Slider velProgTrabalhando1;
  @FXML
  public Button PausaProg1;
  @FXML
  public Button RetomaProg1;

  //
  @FXML
  public ImageView progNoDiscord2;
  @FXML
  public ImageView progTrabalha2;
  @FXML
  public Slider velProgNoDiscord2;
  @FXML
  public Slider velProgTrabalhando2;
  @FXML
  public Button PausaProg2;
  @FXML
  public Button RetomaProg2;

  //
  @FXML
  public ImageView progNoDiscord3;
  @FXML
  public ImageView progTrabalha3;
  @FXML
  public Slider velProgNoDiscord3;
  @FXML
  public Slider velProgTrabalhando3;
  @FXML
  public Button PausaProg3;
  @FXML
  public Button RetomaProg3;

  //

  @FXML
  public ImageView progNoDiscord4;
  @FXML
  public ImageView progTrabalha4;
  @FXML
  public Slider velProgNoDiscord4;
  @FXML
  public Slider velProgTrabalhando4;
  @FXML
  public Button PausaProg4;
  @FXML
  public Button RetomaProg4;

  //

  @FXML
  public ImageView progNoDiscord5;
  @FXML
  public ImageView progTrabalha5;
  @FXML
  public Slider velProgNoDiscord5;
  @FXML
  public Slider velProgTrabalhando5;
  @FXML
  public Button PausaProg5;
  @FXML
  public Button RetomaProg5;

  //Elementos FXML dos leitores
  @FXML
  public ImageView assinanteConversando1;
  @FXML
  public ImageView assinanteAssistindo1;
  @FXML
  public Slider velAssinanteCome1;
  @FXML
  public Slider velAssinanteAssiste1;
  @FXML
  public Button PausaAssin1;
  @FXML
  public Button RetomaAssin1;

  //
  @FXML
  public ImageView assinanteConversando2;
  @FXML
  public ImageView assinanteAssistindo2;
  @FXML
  public Slider velAssinanteCome2;
  @FXML
  public Slider velAssinanteAssiste2;
  @FXML
  public Button PausaAssin2;
  @FXML
  public Button RetomaAssin2;

  //
  @FXML
  public ImageView assinanteConversando3;
  @FXML
  public ImageView assinanteAssistindo3;
  @FXML
  public Slider velAssinanteCome3;
  @FXML
  public Slider velAssinanteAssiste3;
  @FXML
  public Button PausaAssin3;
  @FXML
  public Button RetomaAssin3;

  //

  @FXML
  public ImageView assinanteConversando4;
  @FXML
  public ImageView assinanteAssistindo4;
  @FXML
  public Slider velAssinanteCome4;
  @FXML
  public Slider velAssinanteAssiste4;
  @FXML
  public Button PausaAssin4;
  @FXML
  public Button RetomaAssin4;

  //

  @FXML
  public ImageView assinanteConversando5;
  @FXML
  public ImageView assinanteAssistindo5;
  @FXML
  public Slider velAssinanteCome5;
  @FXML
  public Slider velAssinanteAssiste5;
  @FXML
  public Button PausaAssin5;
  @FXML
  public Button RetomaAssin5;

  @FXML
  public ImageView img_tv0;
  @FXML
  public ImageView img_tv1;
  @FXML
  public ImageView img_tv2;
  @FXML
  public ImageView img_tv3;
  @FXML
  public ImageView img_tv4;
  @FXML
  public ImageView img_tv5;

  //Botao responsavel por resetar o programa
  @FXML
  public Button Botao_Reset;

  //Vetores de imagens usadas pelo programa
  private ImageView [] programadorNoDiscord;
  private ImageView [] programadorTrabalhando;
  private ImageView [] assinanteConversando;
  private ImageView [] assinanteAssistindo;
  private ImageView [] seriesNaXilften;

  //Sliders de velocidade usados pelo programa
  private Slider[] velocidadeNoDiscord;
  private Slider[] velocidadeTrabalhando;
  private Slider[] velocidadeComendo;
  private Slider[] velocidadeAssistindo;

  //Vetores de leitores e escritores, com tamanho 5 cada
  public static final int N = 5;
  public static Escritor[] escritores = new Escritor[N];
  public static Leitor[] leitores = new Leitor[N];

  //Semaforos utilizados na regiao critica
  public static Semaphore mutex = new Semaphore(1);
  public static int readerCount = 0;
  public static Semaphore db = new Semaphore(1);

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    iniciar();
  }

  /* ***************************************************************
   * Metodo: iniciar
   * Funcao: iniciar e startar as threads.
   * Parametros: Sem parametros.
   * Retorno: Sem retorno.
   *************************************************************** */
  public void iniciar(){
    velocidadeNoDiscord = new Slider[]{velProgNoDiscord1, velProgNoDiscord2, velProgNoDiscord3, velProgNoDiscord4, velProgNoDiscord5};
    velocidadeTrabalhando = new Slider[] {velProgTrabalhando1, velProgTrabalhando2, velProgTrabalhando3, velProgTrabalhando4, velProgTrabalhando5};
    velocidadeComendo = new Slider[]{velAssinanteCome1, velAssinanteCome2, velAssinanteCome3, velAssinanteCome4, velAssinanteCome5};
    velocidadeAssistindo = new Slider[] {velAssinanteAssiste1, velAssinanteAssiste2, velAssinanteAssiste3, velAssinanteAssiste4, velAssinanteAssiste5};

    programadorNoDiscord = new ImageView[]{ progNoDiscord1, progNoDiscord2, progNoDiscord3, progNoDiscord4, progNoDiscord5};
    programadorTrabalhando = new ImageView[]{ progTrabalha1, progTrabalha2, progTrabalha3, progTrabalha4, progTrabalha5};
    assinanteConversando = new ImageView[] {assinanteConversando1, assinanteConversando2, assinanteConversando3, assinanteConversando4, assinanteConversando5};
    assinanteAssistindo = new ImageView[] {assinanteAssistindo1, assinanteAssistindo2, assinanteAssistindo3, assinanteAssistindo4, assinanteAssistindo5};
    seriesNaXilften = new ImageView[] {img_tv1, img_tv2, img_tv3, img_tv4, img_tv5};

    xilftenOff();

    readerCount = 0;
    mutex = new Semaphore(1);
    db = new Semaphore(1);

    //Laco que permite a criacao das 10 Threads e a definição das velocidades e imagens iniciais
    for(int i=0; i<N ;i++) {
      escritores[i] = new Escritor(i, this);
      leitores[i] = new Leitor(i, this);

      velocidadeNoDiscord[i].setValue(5);
      velocidadeTrabalhando[i].setValue(5);
      velocidadeComendo[i].setValue(5);
      velocidadeAssistindo[i].setValue(5);

      programadorNoDiscord[i].setVisible(true);
      programadorTrabalhando[i].setVisible(false);
      assinanteConversando[i].setVisible(true);
      assinanteAssistindo[i].setVisible(false);

      escritores[i].retomar();
      leitores[i].retomar();
    }
    //Iniciando a execucao das thrheads
    escritores[0].start();
    escritores[1].start();
    escritores[2].start();
    escritores[3].start();
    escritores[4].start();
    leitores[0].start();
    leitores[1].start();
    leitores[2].start();
    leitores[3].start();
    leitores[4].start();
  }

  /* ***************************************************************
   * Metodo: OnClickBotao_Reset
   * Funcao: Resetar o programa ao evento de click
   * Parametros: Sem parametros
   * Retorno: Sem retorno.
   *************************************************************** */
  @FXML
  void OnClickBotao_Reset(ActionEvent event) {
    interromperThreads();
    iniciar();
  }

  /* ***************************************************************
   * Metodo: interromperThreads
   * Funcao: Interromper todas as Threads para o reset
   * Parametros: Sem parametros
   * Retorno: Sem retorno.
   *************************************************************** */
  public void interromperThreads() {
    for (int i = 0; i < N; i++) {
      escritores[i].interrupt();
      leitores[i].interrupt();
    }
  }

  /* ***************************************************************
   * Metodo: pausa e retoma
   * Funcao: Pausar as Threads ao evento de click
   * Parametros: Sem parametros
   * Retorno: Sem retorno.
   *************************************************************** */
  @FXML
  void OnClickPausaProg1(ActionEvent event) {
    escritores[0].pause();

    PausaProg1.setStyle("-fx-background-color: #FF0000; -fx-text-fill: #FFFFFF;");
    RetomaProg1.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #000000;");
  }
  @FXML
  void OnClickRetomaProg1(ActionEvent event) {
    escritores[0].retomar();

    PausaProg1.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #000000;");
    RetomaProg1.setStyle("-fx-background-color: #1A6F1A; -fx-text-fill: #FFFFFF;");
  }
  @FXML
  void OnClickPausaProg2(ActionEvent event) {
    escritores[1].pause();

    PausaProg2.setStyle("-fx-background-color: #FF0000; -fx-text-fill: #FFFFFF;");
    RetomaProg2.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #000000;");
  }
  @FXML
  void OnClickRetomaProg2(ActionEvent event) {
    escritores[1].retomar();

    PausaProg2.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #000000;");
    RetomaProg2.setStyle("-fx-background-color: #1A6F1A; -fx-text-fill: #FFFFFF;");
  }
  @FXML
  void OnClickPausaProg3(ActionEvent event) {
    escritores[2].pause();

    PausaProg3.setStyle("-fx-background-color: #FF0000; -fx-text-fill: #FFFFFF;");
    RetomaProg3.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #000000;");
  }
  @FXML
  void OnClickRetomaProg3(ActionEvent event) {
    escritores[2].retomar();

    PausaProg3.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #000000;");
    RetomaProg3.setStyle("-fx-background-color: #1A6F1A; -fx-text-fill: #FFFFFF;");
  }
  @FXML
  void OnClickPausaProg4(ActionEvent event) {
    escritores[3].pause();

    PausaProg4.setStyle("-fx-background-color: #FF0000; -fx-text-fill: #FFFFFF;");
    RetomaProg4.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #000000;");
  }
  @FXML
  void OnClickRetomaProg4(ActionEvent event) {
    escritores[3].retomar();

    PausaProg4.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #000000;");
    RetomaProg4.setStyle("-fx-background-color: #1A6F1A; -fx-text-fill: #FFFFFF;");
  }
  @FXML
  void OnClickPausaProg5(ActionEvent event) {
    escritores[4].pause();

    PausaProg5.setStyle("-fx-background-color: #FF0000; -fx-text-fill: #FFFFFF;");
    RetomaProg5.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #000000;");
  }
  @FXML
  void OnClickRetomaProg5(ActionEvent event) {
    escritores[4].retomar();

    PausaProg5.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #000000;");
    RetomaProg5.setStyle("-fx-background-color: #1A6F1A; -fx-text-fill: #FFFFFF;");
  }

  @FXML
  void OnClickPausaAssin1(ActionEvent event) {
    leitores[0].pause();

    PausaAssin1.setStyle("-fx-background-color: #FF0000; -fx-text-fill: #FFFFFF;");
    RetomaAssin1.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #000000;");
  }
  @FXML
  void OnClickRetomaAssin1(ActionEvent event) {
    leitores[0].retomar();

    PausaAssin1.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #000000;");
    RetomaAssin1.setStyle("-fx-background-color: #1A6F1A; -fx-text-fill: #FFFFFF;");
  }
  @FXML
  void OnClickPausaAssin2(ActionEvent event) {
    leitores[1].pause();

    PausaAssin2.setStyle("-fx-background-color: #FF0000; -fx-text-fill: #FFFFFF;");
    RetomaAssin2.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #000000;");
  }
  @FXML
  void OnClickRetomaAssin2(ActionEvent event) {
    leitores[1].retomar();

    PausaAssin2.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #000000;");
    RetomaAssin2.setStyle("-fx-background-color: #1A6F1A; -fx-text-fill: #FFFFFF;");
  }
  @FXML
  void OnClickPausaAssin3(ActionEvent event) {
    leitores[2].pause();

    PausaAssin3.setStyle("-fx-background-color: #FF0000; -fx-text-fill: #FFFFFF;");
    RetomaAssin3.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #000000;");
  }
  @FXML
  void OnClickRetomaAssin3(ActionEvent event) {
    leitores[2].retomar();

    PausaAssin3.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #000000;");
    RetomaAssin3.setStyle("-fx-background-color: #1A6F1A; -fx-text-fill: #FFFFFF;");
  }
  @FXML
  void OnClickPausaAssin4(ActionEvent event) {
    leitores[3].pause();

    PausaAssin4.setStyle("-fx-background-color: #FF0000; -fx-text-fill: #FFFFFF;");
    RetomaAssin4.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #000000;");
  }
  @FXML
  void OnClickRetomaAssin4(ActionEvent event) {
    leitores[3].retomar();

    PausaAssin4.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #000000;");
    RetomaAssin4.setStyle("-fx-background-color: #1A6F1A; -fx-text-fill: #FFFFFF;");
  }
  @FXML
  void OnClickPausaAssin5(ActionEvent event) {
    leitores[4].pause();

    PausaAssin5.setStyle("-fx-background-color: #FF0000; -fx-text-fill: #FFFFFF;");
    RetomaAssin5.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #000000;");
  }
  @FXML
  void OnClickRetomaAssin5(ActionEvent event) {
    leitores[4].retomar();

    PausaAssin5.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #000000;");
    RetomaAssin5.setStyle("-fx-background-color: #1A6F1A; -fx-text-fill: #FFFFFF;");
  }

  /* ***************************************************************
   * Metodo: getters de velocidade
   * Funcao: Verifica o valor atual do slider e o passa para as Threads
   * Parametros: Sem parametros.
   * Retorno: retorna um valor numerico de tipo inteiro.
   *************************************************************** */

  public int getVelocidadeNoDiscord(int id){
    return (int) velocidadeNoDiscord[id].getValue();
  }

  public int getVelocidadeTrabalhando(int id){
    return (int) velocidadeTrabalhando[id].getValue();
  }

  public int getVelocidadeConversando(int id){
    return (int) velocidadeComendo[id].getValue();
  }

  public int getVelocidadeAssistindo(int id){
    return (int) velocidadeAssistindo[id].getValue();
  }

  /* ***************************************************************
   * Metodo: muda animacao
   * Funcao: Modifica a visibilidade das imagens.
   * Parametros: Id da thread.
   * Retorno: Sem retorno
   *************************************************************** */
  public void doDiscProPc(int id){
    programadorNoDiscord[id].setVisible(false);
    programadorTrabalhando[id].setVisible(true);
  }

  public void doPcProDisc(int id){
    programadorNoDiscord[id].setVisible(true);
    programadorTrabalhando[id].setVisible(false);
  }

    public void doPapoPraTv(int id){
    assinanteAssistindo[id].setVisible(true);
    assinanteConversando[id].setVisible(false);
  }

  public void daTvProPapo(int id){
    assinanteAssistindo[id].setVisible(false);
    assinanteConversando[id].setVisible(true);
  }

  public void xilftenOn (int serie){
    switch(serie){
      case 0:
        seriesNaXilften[0].setVisible(true);
        seriesNaXilften[1].setVisible(false);
        seriesNaXilften[2].setVisible(false);
        seriesNaXilften[3].setVisible(false);
        seriesNaXilften[4].setVisible(false);
      case 1:
        seriesNaXilften[0].setVisible(false);
        seriesNaXilften[1].setVisible(true);
        seriesNaXilften[2].setVisible(false);
        seriesNaXilften[3].setVisible(false);
        seriesNaXilften[4].setVisible(false);
      case 2:
        seriesNaXilften[0].setVisible(false);
        seriesNaXilften[1].setVisible(false);
        seriesNaXilften[2].setVisible(true);
        seriesNaXilften[3].setVisible(false);
        seriesNaXilften[4].setVisible(false);
      case 3:
        seriesNaXilften[0].setVisible(false);
        seriesNaXilften[1].setVisible(false);
        seriesNaXilften[2].setVisible(false);
        seriesNaXilften[3].setVisible(true);
        seriesNaXilften[4].setVisible(false);
      case 4:
        seriesNaXilften[0].setVisible(false);
        seriesNaXilften[1].setVisible(false);
        seriesNaXilften[2].setVisible(false);
        seriesNaXilften[3].setVisible(false);
        seriesNaXilften[4].setVisible(true);
    };
  }

  public void xilftenOff (){
    img_tv0.setVisible(true);
    img_tv1.setVisible(false);
    img_tv2.setVisible(false);
    img_tv3.setVisible(false);
    img_tv4.setVisible(false);
    img_tv5.setVisible(false);
  }
}