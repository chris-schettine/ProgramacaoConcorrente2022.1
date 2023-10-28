/* ***************************************************************
* Autor............: Christian Schettine Paiva Rocha
* Matricula........: 202210159
* Inicio...........: 26/08/2023
* Ultima alteracao.: 01/09/2023
* Nome.............: ControllerPrincipal
* Funcao...........: Controla todos os movimentos dos trens
*************************************************************** */
package model;

import controller.ControllerPrincipal;

public class Consumidor extends Thread{
  //atributos 
  private int speed;
  ControllerPrincipal cPC;
  
  //construtor do consumidor
  public Consumidor (ControllerPrincipal cPC){
    this.cPC = cPC;
  }

 //metodo que altera a velocidade
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

  //metodo de remocao da regiao critica
  public void removeItem(){//remove o prato 1 depois o 2 depois o 3
    try {
      sleep(velocidadeConsumidor()*100);
      if(cPC.getimgPrato1().isVisible())
        cPC.getimgPrato1().setVisible(false);
      else if(cPC.getimgPrato2().isVisible())
        cPC.getimgPrato2().setVisible(false);
      else if(cPC.getimgPrato3().isVisible())
        cPC.getimgPrato3().setVisible(false);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
  //metodo de consumo
  public void consome(){
    try {
      //tempo de consumo
      sleep(velocidadeConsumidor());
    }catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void run(){

    while(true){
      try {
        //////////////////////////////////////
        //REGIAO CRITICA
        Semaforo.cheio.acquire();
        Semaforo.mutex.acquire();
        //prato(s) some(m)
        removeItem();
        Semaforo.mutex.release();
        Semaforo.vazio.release();
        //////////////////////////////////////
        //consumo
        consome();
      }catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}