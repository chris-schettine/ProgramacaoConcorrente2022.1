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

public class Produtor extends Thread{
  private int speed;
  ControllerPrincipal cPP;
  
  //construtor do produtor
  public Produtor (ControllerPrincipal cPP){
    this.cPP = cPP;
  }

  //metodo que altera a velocidade
  public int velocidadeProdutor() {
    speed = cPP.getVelocidadeSliderProdutor();
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

  //metodo de producao
  public void produzir(){
    try {
      //tempo de producao
      sleep(velocidadeProdutor()*100);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
  //metodo de insercao na regiao critica
  public void insereItem(){//insere na mesa o prato 1 depois o 2 depois o 3
    try {
      sleep(velocidadeProdutor()*100);
      if(!cPP.getimgPrato1().isVisible())
        cPP.getimgPrato1().setVisible(true);
      else if(!cPP.getimgPrato2().isVisible())
        cPP.getimgPrato2().setVisible(true);
      else if(!cPP.getimgPrato3().isVisible())
        cPP.getimgPrato3().setVisible(true);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  //funcoes de ida e volta do cozinheiro
  public void hannibalCozinha(){
    cPP.getimgHannibalCozinha().setVisible(true);
    cPP.getimgHannibalServe().setVisible(false);
  }
  public void hannibalServe() {
    cPP.getimgHannibalServe().setVisible(true);
    cPP.getimgHannibalCozinha().setVisible(false);
  }

  public void run(){
  
    while(true){
      try {
        //producao
        produzir();
        //animação de produção
        hannibalCozinha();
        //////////////////////////////////////
        //REGIAO CRITICA
        Semaforo.vazio.acquire();
        Semaforo.mutex.acquire();
        insereItem();
        Semaforo.mutex.release();
        Semaforo.cheio.release();
        //////////////////////////////////////
        //cozinheiro volta pro fogão
        hannibalServe();
      }catch (InterruptedException e){
        e.printStackTrace();
      }
    }
  }
}