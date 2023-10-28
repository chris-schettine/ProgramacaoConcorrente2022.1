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
  private int prato = 0;
  
  //construtor do produtor
  public Produtor (ControllerPrincipal cPP){
    this.cPP = cPP;
  }

  //metodo que altera a velocidade
  public int velocidadeProdutor() {
    speed = cPP.getVelocidadeSliderProdutor();
    switch(speed) {
        case 0: 
            speed = 50;
            break;
        case 1: 
            speed = 25;
            break;
        case 2: 
            speed = 5;
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
  public void insereItem(int p){//insere na mesa o prato 1 depois o 2 depois o 3
    try {
      sleep(velocidadeProdutor()*100);
      switch(p) {
        case 0: 
            if(!cPP.getimgPrato1().isVisible())
              cPP.getimgPrato1().setVisible(true);
            break;
        case 1: 
            if(!cPP.getimgPrato2().isVisible())
            cPP.getimgPrato2().setVisible(true);
            break;
        case 2: 
            if(!cPP.getimgPrato3().isVisible())
            cPP.getimgPrato3().setVisible(true);
            break;
      } 
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
        if (cPP.produtorTaPausado == false) {
          hannibalCozinha();
        }
        //////////////////////////////////////
        //REGIAO CRITICA
        Semaforo.vazio.acquire();
        Semaforo.mutex.acquire();
        if (cPP.produtorTaPausado == false) {
          insereItem(prato);
        }
        Semaforo.mutex.release();
        Semaforo.cheio.release();
        //////////////////////////////////////
        //cozinheiro volta pro fogão
        if (cPP.produtorTaPausado == false) {
          hannibalServe();
        }
      }catch (InterruptedException e){
        e.printStackTrace();
      }
    }
  }
}