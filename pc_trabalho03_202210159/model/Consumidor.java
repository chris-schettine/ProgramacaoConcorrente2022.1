/* ***************************************************************
* Autor............: Christian Schettine Paiva Rocha
* Matricula........: 202210159
* Inicio...........: 22/10/2023
* Ultima alteracao.: 29/10/2023
* Nome.............: Consumidor
* Funcao...........: representa um consumidor em um problema de 
* sincronização e concorrência, onde há múltiplos produtores e 
* consumidores compartilhando um buffer.
*************************************************************** */
//package
package model;

//import
import controller.ControllerPrincipal;

public class Consumidor extends Thread{

  //instancia do controller na classe
  ControllerPrincipal cPC;

  //variavel de acesso privado 
  private int speed;
  
  //construtor do consumidor
  public Consumidor (ControllerPrincipal cPC){
    this.cPC = cPC;
  }

  /* ***************************************************************
 * Metodo: velocidadeConsumidor
 * Funcao: recebe o valor int convertido do slider e com um switch,
 * define a velocidade do consumidor
 * Parametros: nao recebe parametros
 * Retorno: nao retorna valores
 *************************************************************** */
  public int velocidadeConsumidor() {
    speed = cPC.getVelocidadeSliderConsumidor();
    switch(speed) {
        case 0: 
            speed = 8;
            break;
        case 1: 
            speed = 5;
            break;
        case 2: 
            speed = 2;
            break;
        default:
            System.out.println("Não foi possivel atribuir");
            break;
    }
    return speed;
  }

 /* ***************************************************************
 * Metodo: consome
 * Funcao: com a permissao do semaforo, responsavel por consumir e
 * desocupar um espaco do buffer
 * Parametros: nao recebe parametros
 * Retorno: nao retorna valores
 *************************************************************** */
  public void consome(){
    try {
      //tempo de consumo
      sleep(velocidadeConsumidor());
    }catch (InterruptedException e) {
    }
  }

 /* ***************************************************************
 * Metodo: removeItem
 * Funcao: eh o metodo de remocao na regiao critica. retira da mesa 
 * o prato 1 depois o 2 depois o 3
 * Parametros: nao recebe parametros
 * Retorno: nao retorna valores
 *************************************************************** */
  public void removeItem(){
    try {
      sleep(velocidadeConsumidor()*100);
      if(cPC.getimgPrato1().isVisible())
        cPC.getimgPrato1().setVisible(false);
      else if(cPC.getimgPrato2().isVisible())
        cPC.getimgPrato2().setVisible(false);
      else if(cPC.getimgPrato3().isVisible())
        cPC.getimgPrato3().setVisible(false);
    } catch (InterruptedException e) {
    }
  }

  public void run(){

    while(true){
      try {
        //////////////////////////////////////
        //INICIO DA REGIAO CRITICA
        Semaforo.cheio.acquire();
        Semaforo.mutex.acquire();
        //animacao onde  o(s) prato(s) some(m)
        if (cPC.consumidorTaPausado == false) {
          removeItem();
        }
        Semaforo.mutex.release();
        Semaforo.vazio.release();
        //////////////////////////////////////
        //FIM DA REGIAO CRITICA
        //consumo
        if (cPC.consumidorTaPausado == false) {
          consome();
        }
      }catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}