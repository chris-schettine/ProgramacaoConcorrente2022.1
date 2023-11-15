/* ***************************************************************
* Autor............: Christian Schettine Paiva Rocha
* Matricula........: 202210159
* Inicio...........: 07/11/2023
* Ultima alteracao.: 14/11/2023
* Nome.............: Leitor
* Funcao...........: a classe Leitor tem a função de ler dados 
de uma região compartilhada.
*************************************************************** */
//Declaracao de pacote
package model;

//Imports necessarios
import controller.ControllerPrincipal;
import javafx.application.Platform;

public class Leitor extends Thread {
  private volatile boolean pausada = false; //Flag usada para pausar/retomar
  private static ControllerPrincipal control;
  private final int id; //Id correspondente a cada leitor

  /* ***************************************************************
   * Metodo: Leitor
   * Funcao: Construtor do objeto Leitor.
   * Parametros: id do Leitor e Controlador responsável
   * Retorno: Sem retorno.
   *************************************************************** */
  public Leitor(int id, ControllerPrincipal control) {
    this.id = id;
    this.control = control;
  }
  /* ***************************************************************
   * Metodo: run
   * Funcao: Permitir a execução de ambas regiões crítica e não crítica
   * da Thread leitora.
   * Parametros: Sem parametros.
   * Retorno: Sem retorno.
   * 
   * A minha ideia de implementacao se baseia numa startup de entrete-
   * nimento chamada Xilften (Escritores) e os universitarios que a as-
   * sinam (Leitores). Nesse caso, os leitores seriam os universitarios 
   * de uma republica, que assistem series e filmes juntos quando os 
   * devs nao estao dando manutencao no sistema da Xilften. Durante a
   * conversa/lanche esses universitarios compartilham entre si os lanca-
   * mentos desse streaming e, depois, juntos ou separados eles assistem
   * as producoes postadas na plataforma.
   *************************************************************** */
  @Override
  public void run() {
    while(!Thread.interrupted()) {
      try{
        ControllerPrincipal.mutex.acquire();
      try {
        ControllerPrincipal.readerCount++;
      if (ControllerPrincipal.readerCount == 1) {
        ControllerPrincipal.db.acquire();
      }
      } finally {
        ControllerPrincipal.mutex.release();
      }
      try {
        leBaseDeDados();
      } finally {
        ControllerPrincipal.mutex.acquire();
      try {
        ControllerPrincipal.readerCount--;
        if (ControllerPrincipal.readerCount == 0) {
          ControllerPrincipal.db.release();
        }
      } finally {
        ControllerPrincipal.mutex.release();
      }
      }
      utilizaDadoLido();
    } catch (Exception e) {
      e.printStackTrace();
    }
    }
  }

  /* ***************************************************************
   * Metodo: leBaseDeDados
   * Funcao: Anima a ida pro papo.
   * Parametros: Sem parametros.
   * Retorno: Sem retorno.
   *************************************************************** */
  private void leBaseDeDados() {
    Platform.runLater(() -> control.doPapoPraTv(this.id));
    Platform.runLater(() -> control.xilftenOn(this.id));
    suspendeThread(control.getVelocidadeAssistindo(this.id)); 
    testaPause();                                             
  }                                                           

  /* ***************************************************************
   * Metodo: utilizaDadoLido
   * Funcao: Anima a ida para a TV.
   * Parametros: Sem parametros.
   * Retorno: Sem retorno.
   *************************************************************** */
  private void utilizaDadoLido(){
    Platform.runLater(() -> control.daTvProPapo(this.id));
    Platform.runLater(() -> control.xilftenOff());
    suspendeThread(control.getVelocidadeConversando(this.id));
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