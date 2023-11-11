/* ***************************************************************
* Autor............: Christian Schettine Paiva Rocha
* Matricula........: 202210159
* Inicio...........: XX/XX/2023
* Ultima alteracao.: XX/XX/2023
* Nome.............: Escritor
* Funcao...........: 
*************************************************************** */
package model;

import controller.ControllerPrincipal;
import javafx.application.Platform;

public class Escritor extends Thread{
  private volatile boolean pausada = false;
  private static ControllerPrincipal control;
  private final int id;

  /* ***************************************************************
   * Metodo: Escritor
   * Funcao: Construtor do objeto Escritor.
   * Parametros: Id do filosofo e o controlador da interface.
   * Retorno: Sem retorno.
   *************************************************************** */
  public Escritor(int index, ControllerPrincipal control){
    this.id = index;
    this.control = control;
  }
  /* ***************************************************************
   * Metodo: run
   * Funcao: Executar o ciclo de vida do filosofo.
   * Parametros: Sem parametros.
   * Retorno: Sem retorno.
   *************************************************************** */
  @Override
  public void run() {
    while(!Thread.interrupted()) {
      try {
        ControllerPrincipal.db.acquire();
        vaiTrabalhar();
        ControllerPrincipal.db.release();
        vaiProcrastinar();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  /* ***************************************************************
   * Metodo: vaiProcrastinar
   * Funcao: Anima a ida do PC pro Discord.
   * Parametros: Sem parametros.
   * Retorno: Sem retorno.
   *************************************************************** */
  private void vaiProcrastinar() {
    Platform.runLater(() -> control.doPcProDisc(this.id));
    sleepTime(control.getVelocidadeNoDiscord(this.id));
    pausando();
  }

  /* ***************************************************************
   * Metodo: vaiTrabalhar
   * Funcao: Anima a ida do Discord pro PC.
   * Parametros: Sem parametros.
   * Retorno: Sem retorno.
   *************************************************************** */
  private void vaiTrabalhar(){
    Platform.runLater(() -> control.doDiscProPc(this.id));
    sleepTime(control.getVelocidadeTrabalhando(this.id));
    pausando();
  }

  /* ***************************************************************
   * Metodo: sleepTime
   * Funcao: Faz o processo dormir.
   * Parametros: Valor int.
   * Retorno: Sem retorno.
   *************************************************************** */
  public void sleepTime(int time){
    try {
      sleep((long) time*1000);
    } catch (InterruptedException e) {
    }
  }
  
  /* ***************************************************************
   * Metodo: pausando
   * Funcao: pausa o processo.
   * Parametros: Sem parametro.
   * Retorno: Sem retorno.
   *************************************************************** */
  private void pausando(){
    while (pausada && !Thread.interrupted()){
      sleepTime(1);
    }
  }

  /* ***************************************************************
   * Metodo: pausar
   * Funcao: Modifica o boolean do pausa.
   * Parametros: Sem parametro.
   * Retorno: Sem retorno.
   *************************************************************** */
  public void pausar(){
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