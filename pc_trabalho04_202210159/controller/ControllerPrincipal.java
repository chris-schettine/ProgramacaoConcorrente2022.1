/* ***************************************************************
* Autor............: Christian Schettine Paiva Rocha
* Matricula........: 202210159
* Inicio...........: 22/10/2023
* Ultima alteracao.: 29/10/2023
* Nome.............: ControllerPrincipal
* Funcao...........: Controla toda a animacao com botoes, define
* todas as configuracoes iniciais e administra as threads.
*************************************************************** */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Semaphore;
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
  public Button pausaProg1;
  @FXML
  public Button retomaProg1;

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
  public Button pausaProg2;
  @FXML
  public Button retomaProg2;

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
  public Button pausaProg3;
  @FXML
  public Button retomaProg3;

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
  public Button pausaProg4;
  @FXML
  public Button retomaProg4;

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
  public Button pausaProg5;
  @FXML
  public Button retomaProg5;

  //Elementos FXML dos leitores
  @FXML
  public ImageView assinanteComendo1;
  @FXML
  public ImageView assinanteAssistindo1;
  @FXML
  public Slider velAssinanteCome1;
  @FXML
  public Slider velAssinanteAssiste1;
  @FXML
  public Button pausaAssin1;
  @FXML
  public Button retomaAssin1;

  //
  @FXML
  public ImageView assinanteComendo2;
  @FXML
  public ImageView assinanteAssistindo2;
  @FXML
  public Slider velAssinanteCome2;
  @FXML
  public Slider velAssinanteAssiste2;
  @FXML
  public Button pausaAssin2;
  @FXML
  public Button retomaAssin2;

  //
  @FXML
  public ImageView assinanteComendo3;
  @FXML
  public ImageView assinanteAssistindo3;
  @FXML
  public Slider velAssinanteCome3;
  @FXML
  public Slider velAssinanteAssiste3;
  @FXML
  public Button pausaAssin3;
  @FXML
  public Button retomaAssin3;

  //

  @FXML
  public ImageView assinanteComendo4;
  @FXML
  public ImageView assinanteAssistindo4;
  @FXML
  public Slider velAssinanteCome4;
  @FXML
  public Slider velAssinanteAssiste4;
  @FXML
  public Button pausaAssin4;
  @FXML
  public Button retomaAssin4;

  //

  @FXML
  public ImageView assinanteComendo5;
  @FXML
  public ImageView assinanteAssistindo5;
  @FXML
  public Slider velAssinanteCome5;
  @FXML
  public Slider velAssinanteAssiste5;
  @FXML
  public Button pausaAssin5;
  @FXML
  public Button retomaAssin5;

  @FXML
  public Button botao_Reset;

  private ImageView [] programadorNoDiscord;
  private ImageView [] programadorTrabalhando;
  private ImageView [] assinanteComendo;
  private ImageView [] assinanteAssistindo;

  private Slider[] velocidadeNoDiscord;
  private Slider[] velocidadeTrabalhando;
  private Slider[] velocidadeComendo;
  private Slider[] velocidadeAssistindo;

  public static final int N = 5;
  public static Escritor[] escritores = new Escritor[N];
  public static Leitor[] leitores = new Leitor[N];
  public  static Semaphore mutex;
  public static int readerCount;
  public static Semaphore db;

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
    assinanteComendo = new ImageView[] {assinanteComendo1, assinanteComendo2, assinanteComendo3, assinanteComendo4, assinanteComendo5};
    assinanteAssistindo = new ImageView[] {assinanteAssistindo1, assinanteAssistindo2, assinanteAssistindo3, assinanteAssistindo4, assinanteAssistindo5};

    readerCount = 0;
    mutex = new Semaphore(1);
    db = new Semaphore(1);

    for(int i=0; i<N ;i++) {
      escritores[i] = new Escritor(i, this);
      leitores[i] = new Leitor(i, this);

      velocidadeNoDiscord[i].setValue(5);
      velocidadeTrabalhando[i].setValue(5);
      velocidadeComendo[i].setValue(5);
      velocidadeAssistindo[i].setValue(5);
    }
    //iniciando a execucao das thrheads
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
   * Metodo: resetar
   * Funcao: Chama as subrotinas que configuram o reset da aplicacao
   * Parametros: Sem parametros
   * Retorno: Sem retorno.
   *************************************************************** */
  @FXML
  public void resetar(){
    interromperThreads();
    iniciar();
  }

  /* ***************************************************************
   * Metodo: interromperThreads
   * Funcao: Interrompe as threads.
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
   * Funcao: Pausa o processo.
   * Parametros: Sem parametros
   * Retorno: Sem retorno.
   *************************************************************** */
  @FXML
  public void pausaProg1() {
    escritores[0].pausar();
  }
  @FXML
  public void retomaProg1() {
    escritores[0].retomar();
  }
  @FXML
  public void pausaProg2() {
    escritores[1].pausar();
  }
  @FXML
  public void retomaProg2() {
    escritores[1].retomar();
  }
  @FXML
  public void pausaProg3() {
    escritores[2].pausar();
  }
  @FXML
  public void retomaProg3() {
    escritores[2].retomar();
  }
  @FXML
  public void pausaProg4() {
    escritores[3].pausar();
  }
  @FXML
  public void retomaProg4() {
    escritores[3].retomar();
  }
  @FXML
  public void pausaProg5() {
    escritores[4].pausar();
  }
  @FXML
  public void retomaProg5() {
    escritores[4].retomar();
  }

  @FXML
  public void pausaAssin1() {
    escritores[0].pausar();
  }
  @FXML
  public void retomaAssin1() {
    escritores[0].retomar();
  }
  @FXML
  public void pausaAssin2() {
    escritores[1].pausar();
  }
  @FXML
  public void retomaAssin2() {
    escritores[1].retomar();
  }
  @FXML
  public void pausaAssin3() {
    escritores[2].pausar();
  }
  @FXML
  public void retomaAssin3() {
    escritores[2].retomar();
  }
  @FXML
  public void pausaAssin4() {
    escritores[3].pausar();
  }
  @FXML
  public void retomaAssin4() {
    escritores[3].retomar();
  }
  @FXML
  public void pausaAssin5() {
    escritores[4].pausar();
  }
  @FXML
  public void retomaAssin5() {
    escritores[4].retomar();
  }

  /* ***************************************************************
   * Metodo: getters de velocidade
   * Funcao: Verifica o valor atual do slider.
   * Parametros: Sem parametros.
   * Retorno: retorna um valor numerico de tipo inteiro.
   *************************************************************** */

  public int getVelocidadeNoDiscord(int id){
    return (int) velocidadeNoDiscord[id].getValue();
  }

  public int getVelocidadeTrabalhando(int id){
    return (int) velocidadeTrabalhando[id].getValue();
  }

  public int getVelocidadeComendo(int id){
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
    programadorNoDiscord[id].setVisible(false);
    programadorTrabalhando[id].setVisible(true);
  }

    public void doPratoPraTv(int id){
    assinanteAssistindo[id].setVisible(true);
    assinanteComendo[id].setVisible(false);
  }

  public void daTvProPrato(int id){
    assinanteAssistindo[id].setVisible(false);
    assinanteComendo[id].setVisible(true);
  }
}