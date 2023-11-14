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
   * Parametros: 
   * Retorno: Sem retorno.
   *************************************************************** */
  public Escritor(int index, ControllerPrincipal control){
    this.id = index;
    this.control = control;
  }
  /* ***************************************************************
   * Metodo: run
   * Funcao: 
   * Parametros: Sem parametros.
   * Retorno: Sem retorno.
   *************************************************************** */
  @Override
  public void run() {
    while(true) {
      try {
        obtemDado();
        ControllerPrincipal.db.acquire();
        escreveBaseDeDados();
        ControllerPrincipal.db.release();
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
    Platform.runLater(() -> control.doPcProDisc(this.id));
    suspendeThread(control.getVelocidadeNoDiscord(this.id));
    testaPause();
  }

  /* ***************************************************************
   * Metodo: escreveBaseDeDados
   * Funcao: Anima a ida do Discord pro PC.
   * Parametros: Sem parametros.
   * Retorno: Sem retorno.
   *************************************************************** */
  private void escreveBaseDeDados(){
    Platform.runLater(() -> control.doDiscProPc(this.id));
    suspendeThread(control.getVelocidadeTrabalhando(this.id));
    testaPause();
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
   * Funcao: Modifica o boolean do pausa.
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