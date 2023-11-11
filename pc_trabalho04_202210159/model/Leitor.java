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
   * Parametros: Id do filosofo e o controlador da interface.
   * Retorno: Sem retorno.
   *************************************************************** */
  public Leitor(int index, ControllerPrincipal control){
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
      try{
        vaiComer();
        ControllerPrincipal.mutex.acquire();
        ControllerPrincipal.readerCount++;
        if (ControllerPrincipal.readerCount == 1) {
          ControllerPrincipal.db.acquire();
        }
        ControllerPrincipal.mutex.release();
          // Perform reading
        vaiPraXilften();
        ControllerPrincipal.mutex.acquire();
        ControllerPrincipal.readerCount--;
        if (ControllerPrincipal.readerCount == 0) {
          ControllerPrincipal.db.release();
        }
        ControllerPrincipal.mutex.release();
        vaiComer();
    } catch (Exception e) {
      e.printStackTrace();
    }
    }
  }

  /* ***************************************************************
   * Metodo: vaiComer
   * Funcao: Anima a ida pro prato.
   * Parametros: Sem parametros.
   * Retorno: Sem retorno.
   *************************************************************** */
  private void vaiComer() {
    Platform.runLater(() -> control.daTvProPrato(this.id));
    sleepTime(control.getVelocidadeComendo(this.id));
    pausando();
  }

  /* ***************************************************************
   * Metodo: vaiPraXilften
   * Funcao: Anima a idea para a TV.
   * Parametros: Sem parametros.
   * Retorno: Sem retorno.
   *************************************************************** */
  private void vaiPraXilften(){
    Platform.runLater(() -> control.doPratoPraTv(this.id));
    sleepTime(control.getVelocidadeAssistindo(this.id));
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