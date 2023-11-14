/* ***************************************************************
* Autor............: Christian Schettine Paiva Rocha
* Matricula........: 202210159
* Inicio...........: 07/11/2023
* Ultima alteracao.: 14/11/2023
* Nome.............: Escritor
* Funcao...........: a classe Escritor tem a função de escrever 
dados em uma região compartilhada, enquanto evita interferências 
com outros escritores e leitores.
*************************************************************** */
//Declaracao de pacote
package model;

//Imports necessarios
import controller.ControllerPrincipal;
import javafx.application.Platform;

public class Escritor extends Thread {
  private volatile boolean pausada = false; //Flag usada para pausar/retomar
  private static ControllerPrincipal control;
  private final int id; //Id correspondente a cada escritor

  /* ***************************************************************
   * Metodo: Escritor
   * Funcao: Construtor do objeto Escritor.
   * Parametros: id do Escritor e Controlador responsável
   * Retorno: Sem retorno.
   *************************************************************** */
  public Escritor(int index, ControllerPrincipal control) {
    this.id = index;
    this.control = control;
  }
  /* ***************************************************************
   * Metodo: run
   * Funcao: Permitir a execução de ambas regiões crítica e não crítica
   * da Thread escritora.
   * Parametros: Sem parametros.
   * Retorno: Sem retorno.
   * 
   * A minha ideia de implementacao se baseia numa startup de entrete-
   * nimento chamada Xilften (Escritores) e os universitarios que a as-
   * sinam (Leitores). Nesse caso, os escritores seriam os devs dessa
   * startup, que impedem o acesso ao streaming enquanto manipulam os
   * conteudos da plataforma. Por ser uma startup, a comunicacao eh fe-
   * ita por Discord, onde eles sabem das novidades para postar na Xilften
   * e das informações erradas ou bugs no sistema que necessitam corre-
   * cao. Durante a manutencao, eles manipulam o banco de dados e im-
   * pedem o acesso dos assinantes.
   *************************************************************** */
  @Override
  public void run() {
    while(true) {
      try {
        obtemDado();
        ControllerPrincipal.db.acquire();
        escreveBaseDeDados();
        ControllerPrincipal.db.release();
        voltaProDiscord();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  /* ***************************************************************
   * Metodo: obtemDado
   * Funcao: Anima a ida do PC pro Discord.
   * Parametros: Sem parametros.
   * Retorno: Sem retorno.
   *************************************************************** */
  private void obtemDado() {
    suspendeThread(control.getVelocidadeNoDiscord(this.id));
    testaPause();
  }

  /* ***************************************************************
   * Metodo: escreveBaseDeDados
   * Funcao: Anima a ida do Discord pro PC.
   * Parametros: Sem parametros.
   * Retorno: Sem retorno.
   *************************************************************** */
  private void escreveBaseDeDados() {
    Platform.runLater(() -> control.doDiscProPc(this.id));
    suspendeThread(control.getVelocidadeTrabalhando(this.id));
    testaPause();
  }

  private void voltaProDiscord() {
    Platform.runLater(() -> control.doPcProDisc(this.id));
  }

  /* ***************************************************************
   * Metodo: suspendeThread
   * Funcao: Faz o processo dormir.
   * Parametros: Valor int.
   * Retorno: Sem retorno.
   *************************************************************** */
  public void suspendeThread(int time){
    try {
      sleep((long) time*1000);
    } catch (InterruptedException e) {
    }
  }
  
  /* ***************************************************************
   * Metodo: testaPause
   * Funcao: pausa o processo.
   * Parametros: Sem parametro.
   * Retorno: Sem retorno.
   *************************************************************** */
  private void testaPause(){
    while (pausada && !Thread.interrupted()){
      suspendeThread(1);
    }
  }

  /* ***************************************************************
   * Metodo: pausar
   * Funcao: Modifica o boolean da pausa.
   * Parametros: Sem parametro.
   * Retorno: Sem retorno.
   *************************************************************** */
  public void pause(){
    pausada = true;
  }

  /* ***************************************************************
   * Metodo: retomar
   * Funcao: Modifica o boolean do retoma.
   * Parametros: Sem parametro.
   * Retorno: Sem retorno.
   *************************************************************** */
  public void retomar(){
    pausada = false;
  }
}