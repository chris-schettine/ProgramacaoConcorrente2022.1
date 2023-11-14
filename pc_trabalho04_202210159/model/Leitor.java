/* ***************************************************************
* Autor............: Christian Schettine Paiva Rocha
* Matricula........: 202210159
* Inicio...........: XX/XX/2023
* Ultima alteracao.: XX/XX/2023
* Nome.............: Leitor
* Funcao...........: 
*************************************************************** */
package model;

import controller.ControllerPrincipal;
import javafx.application.Platform;

public class Leitor extends Thread{
  private volatile boolean pausada = false;
  private static ControllerPrincipal control;
  private final int id;

  /* ***************************************************************
   * Metodo: Leitor
   * Funcao: Construtor do objeto Leitor.
   * Parametros: 
   * Retorno: Sem retorno.
   *************************************************************** */
  public Leitor(int index, ControllerPrincipal control){
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
      try{
        ControllerPrincipal.mutex.acquire();
        ControllerPrincipal.readerCount++;
        if (ControllerPrincipal.readerCount == 1) {
          ControllerPrincipal.db.acquire();
        }
        ControllerPrincipal.mutex.release();
        leBaseDeDados();
        ControllerPrincipal.mutex.acquire();
        ControllerPrincipal.readerCount--;
        if (ControllerPrincipal.readerCount == 0) {
          ControllerPrincipal.db.release();
        }
        ControllerPrincipal.mutex.release();
        utilizaDadoLido();
    } catch (Exception e) {
      e.printStackTrace();
    }
    }
  }

  /* ***************************************************************
   * Metodo: leBaseDeDados
   * Funcao: Anima a ida pro prato.
   * Parametros: Sem parametros.
   * Retorno: Sem retorno.
   *************************************************************** */
  private void leBaseDeDados() {
    Platform.runLater(() -> control.daTvProPapo(this.id));
    Platform.runLater(() -> control.xilftenOff());
    suspendeThread(control.getVelocidadeConversando(this.id));
    testaPause();
  }

  /* ***************************************************************
   * Metodo: utilizaDadoLido
   * Funcao: Anima a idea para a TV.
   * Parametros: Sem parametros.
   * Retorno: Sem retorno.
   *************************************************************** */
  private void utilizaDadoLido(){
    Platform.runLater(() -> control.doPapoPraTv(this.id));
    Platform.runLater(() -> control.xilftenOn(this.id));
    suspendeThread(control.getVelocidadeAssistindo(this.id));
    testaPause();
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