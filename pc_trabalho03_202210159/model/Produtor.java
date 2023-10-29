/* ***************************************************************
* Autor............: Christian Schettine Paiva Rocha
* Matricula........: 202210159
* Inicio...........: 22/10/2023
* Ultima alteracao.: 29/10/2023
* Nome.............: Produtor
* Funcao...........: implementa a thread que representa um produtor 
* em um problema de sincronização e concorrência, associado a um 
* cenário de produção-consumo. Nesse contexto, há um buffer compar-
* tilhado onde os produtores inserem itens.
*************************************************************** */
//package
package model;

//import
import controller.ControllerPrincipal;

public class Produtor extends Thread{
  
  //instancia do controller na classe
  ControllerPrincipal cPP;

  //variaveis de acesso privado
  private int speed;
  private int prato;
  
  //construtor do produtor
  public Produtor (ControllerPrincipal cPP){
    this.cPP = cPP;
  }

  /* ***************************************************************
 * Metodo: velocidadeProdutor
 * Funcao: recebe o valor int convertido do slider e com um switch,
 * define a velocidade do produtor
 * Parametros: nao recebe parametros
 * Retorno: nao retorna valores
 *************************************************************** */
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
            System.out.println("Valor invahlido");
            break;
    }
    return speed;
  }

 /* ***************************************************************
 * Metodo: produzir
 * Funcao: com a permissao do semaforo, responsavel por produzir e
 * ocupar um espaco do buffer
 * Parametros: nao recebe parametros
 * Retorno: nao retorna valores
 *************************************************************** */
  public void produzir(){
    try {
      //tempo de producao
      sleep(velocidadeProdutor()*100);
    } catch (InterruptedException e) {
    }
  }
 /* ***************************************************************
 * Metodo: insereItem
 * Funcao: eh o metodo de insercao na regiao critica. insere na mesa 
 * o prato 1 depois o 2 depois o 3
 * Parametros: nao recebe parametros
 * Retorno: nao retorna valores
 *************************************************************** */
  public void insereItem(){//
    try {
      sleep(velocidadeProdutor()*100);
      if (!cPP.getimgPrato1().isVisible()) {
        hannibalServe();
        cPP.getimgPrato1().setVisible(true);
      } else if  (!cPP.getimgPrato2().isVisible()) {
        hannibalServe();
        cPP.getimgPrato2().setVisible(true);
      } else if (!cPP.getimgPrato3().isVisible()) {
        hannibalServe();
        cPP.getimgPrato3().setVisible(true);
      }
    } catch (InterruptedException e) {
    }
  }

 /* ***************************************************************
 * Metodo: hannibalServe e hannibalCozinha
 * Funcao: controlam a alternancia entre as posicoes do cozinheiro
 * Parametros: nao recebe parametros
 * Retorno: nao retorna valores
 *************************************************************** */
  public void hannibalCozinha(){
    cPP.getimgHannibalCozinha().setVisible(true);
    cPP.getimgHannibalServe().setVisible(false);
  }
  public void hannibalServe() {
    cPP.getimgHannibalServe().setVisible(true);
    cPP.getimgHannibalCozinha().setVisible(false);
  }
  public void resetaPrato(){
    prato = 0;
  }
  public void run(){
  
    while(true){
      try {
        for (prato = 0 ;prato <= 2 ;prato++){
        //producao
        produzir();
        //animação de produção
        if (cPP.produtorTaPausado == false) {
          hannibalCozinha();
        }
        //////////////////////////////////////
        //INICIO DA REGIAO CRITICA
        Semaforo.vazio.acquire();
        Semaforo.mutex.acquire();
        if (cPP.produtorTaPausado == false && !(cPP.getimgPrato1().isVisible() && cPP.getimgPrato2().isVisible() && cPP.getimgPrato3().isVisible())) {
          insereItem();
        }
        Semaforo.mutex.release();
        Semaforo.cheio.release();
        //////////////////////////////////////
        //FIM DA REGIAO CRITICA
        }
      }catch (InterruptedException e){
        e.printStackTrace();
      }
    }
  }
}