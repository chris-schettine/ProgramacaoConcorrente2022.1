/* ***************************************************************
* Autor............: Christian Schettine Paiva Rocha
* Matricula........: 202210159
* Inicio...........: 07/11/2023
* Ultima alteracao.: 14/11/2023
* Nome.............: Escritor
* Funcao...........: a classe Escritor tem a função de escrever 
dados em uma região compartilhada, enquanto evita interferências 
com outros escritores e leitores.
*************************************************************** */
package model;

import java.util.concurrent.Semaphore;

import controller.ControllerPrincipal;
import javafx.application.Platform;
import javafx.scene.image.ImageView;

public class Carro extends Thread{
  private volatile boolean pausada = false;
  private double coordInX;
  private double coordInY;
  private String percurso = "";
  ControllerPrincipal cP;
  private int[] speedCarro = new int[8];
  private static Semaphore[] semaforo = new Semaphore[75];
  
  // Metodo para reinicializar semaforo
  public static void setSemaforo(int valor) {
    for(int i = 0; i < 75; i++){
      semaforo[i] = new Semaphore(valor);
    }
  }

  //Construtor
  public Carro(ControllerPrincipal cP, String perc, double coordInX, double coordInY){
    this.cP = cP;
    this.percurso = perc;
    this.coordInX = coordInX;
    this.coordInY = coordInY;

    for(int i = 0; i < 75; i++){
      semaforo[i] = new Semaphore(1);
    }
  }

  public int velocidadeCarro(int indice) {
    speedCarro[indice] = cP.getVelocidade(indice);   
    switch(speedCarro[indice]){
        case 0: 
        speedCarro[indice] = 11;
          break;
        case 1: 
        speedCarro[indice] = 7;
            break;
        case 2: 
        speedCarro[indice] = 4;
          break;
        default:
          System.out.println("Não foi possivel atribuir");
          break;
    }
    return speedCarro[indice];
  }
  
  //funcoes que movem os carros horizontalmente e verticalmente na tela
  // carro indo para esquerda
  public void moveLeft(int limite, ImageView imagemCarro, int id){
    while(this.coordInX >= limite){
      try {
        testaPause();
        //tempo de espera varia para cada velocidade setada no slider de cada carro
        sleep(velocidadeCarro(id));
        this.coordInX--;
        //fazendo movimentacao do carro na tela atraves do metodo Platform.runLater
        Platform.runLater(()->imagemCarro.setLayoutX(this.coordInX));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
   // carro indo para cima
   public void moveUp(int limite, ImageView imagemCarro, int id){
    while(this.coordInY >= limite){
      try {
        testaPause();
        //tempo de espera varia para cada velocidade setada no slider de cada carro
        sleep(velocidadeCarro(id));
        this.coordInY--;
        //fazendo movimentacao do carro na tela atraves do metodo Platform.runLater
        Platform.runLater(()->imagemCarro.setLayoutY(this.coordInY));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
  // camcarroinhao indo para direita
  public void moveRight(int limite, ImageView imagemCarro, int id){
    while(this.coordInX <= limite){
      try {
        testaPause();
        //tempo de espera varia para cada velocidade setada no slider de cada carro
        sleep(velocidadeCarro(id));
        this.coordInX++;
        //fazendo movimentacao do ccarroaminhao na tela atraves do metodo Platform.runLater
        Platform.runLater(()->imagemCarro.setLayoutX(this.coordInX));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
  // camcarroinhao indo para baixo
  public void moveDown(int limite, ImageView imagemCarro, int id){
    while(this.coordInY <= limite){
      try {
        testaPause();
        //tempo de espera varia para cada velocidade setada no slider de cada carro
        sleep(velocidadeCarro(id));
        this.coordInY++;
        //fazendo movimentacao do ccarroaminhao na tela atraves do metodo Platform.runLater
        Platform.runLater(()->imagemCarro.setLayoutY(this.coordInY));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
  //PERCURSOS
  public void percurso01SA() throws InterruptedException{
    ImageView imagemCarro1 = cP.getImagemCarro(0);
    while(true){
      Platform.runLater(() -> imagemCarro1.setRotate(0));
      moveLeft(377, imagemCarro1, 0);
      semaforo[9].acquire();
      Platform.runLater(() -> imagemCarro1.setRotate(-270));
      moveUp(109, imagemCarro1, 0);
      semaforo[9].release();
      semaforo[0].acquire();
      semaforo[5].acquire();
      moveUp(74, imagemCarro1, 0);
      Platform.runLater(() -> imagemCarro1.setRotate(0));
      moveLeft(206, imagemCarro1, 0);
      semaforo[0].release();
      semaforo[1].acquire();
      moveLeft(165, imagemCarro1, 0);
      Platform.runLater(() -> imagemCarro1.setRotate(-90));
      moveDown(277, imagemCarro1, 0);
      semaforo[5].release();
      moveDown(457, imagemCarro1, 0);
      semaforo[1].release();
      semaforo[2].acquire();
      moveDown(491, imagemCarro1, 0);
      Platform.runLater(() -> imagemCarro1.setRotate(180));
      moveRight(266, imagemCarro1, 0);
      semaforo[2].release();
      semaforo[8].acquire();
      Platform.runLater(() -> imagemCarro1.setRotate(-270));
      moveUp(325, imagemCarro1, 0);
      semaforo[8].release();
      Platform.runLater(() -> imagemCarro1.setRotate(180));
      moveRight(344, imagemCarro1, 0);
      semaforo[10].acquire();
      moveRight(376, imagemCarro1, 0);
      Platform.runLater(() -> imagemCarro1.setRotate(-270));
      moveUp(239, imagemCarro1, 0);
      semaforo[10].release();
      semaforo[4].acquire();
      Platform.runLater(() -> imagemCarro1.setRotate(180));
      moveRight(448, imagemCarro1, 0);
      semaforo[4].release();
      semaforo[6].acquire();
      moveRight(485, imagemCarro1, 0);
      Platform.runLater(() -> imagemCarro1.setRotate(-90));
      moveDown(325, imagemCarro1, 0);
      semaforo[6].release();
      Platform.runLater(() -> imagemCarro1.setRotate(180));
      moveRight(556, imagemCarro1, 0);
      semaforo[11].acquire();
      moveRight(591, imagemCarro1, 0);
      Platform.runLater(() -> imagemCarro1.setRotate(-90));
      moveDown(491, imagemCarro1, 0);
      semaforo[11].release();
      semaforo[2].acquire();
      Platform.runLater(() -> imagemCarro1.setRotate(180));
      moveRight(668, imagemCarro1, 0);
      semaforo[2].release();
      semaforo[3].acquire();
      moveRight(694, imagemCarro1, 0);
      Platform.runLater(() -> imagemCarro1.setRotate(-270));
      moveUp(277, imagemCarro1, 0);
      semaforo[5].acquire();
      moveUp(109, imagemCarro1, 0);
      semaforo[3].release();
      semaforo[0].acquire();
      moveUp(74, imagemCarro1, 0);
      Platform.runLater(() -> imagemCarro1.setRotate(0));
      moveLeft(484, imagemCarro1, 0);
      semaforo[0].release();
      semaforo[5].release();
      semaforo[7].acquire();
      Platform.runLater(() -> imagemCarro1.setRotate(-90));
      moveDown(157, imagemCarro1, 0);
      semaforo[7].release();
      Platform.runLater(() -> imagemCarro1.setRotate(0));
      moveLeft(429, imagemCarro1, 0);
    }
  }
  public void percurso05SA() throws InterruptedException {
    ImageView imagemCarro2 = cP.getImagemCarro(1);
    semaforo[0].acquire();
    semaforo[5].acquire();
    while (true) {
      Platform.runLater(() -> imagemCarro2.setRotate(0));
      moveLeft(206, imagemCarro2, 1);
      semaforo[0].release();
      semaforo[1].acquire();
      moveLeft(165, imagemCarro2, 1);
      Platform.runLater(() -> imagemCarro2.setRotate(-90));
      moveDown(277, imagemCarro2, 1);
      semaforo[5].release();
      moveDown(457, imagemCarro2, 1);
      semaforo[1].release();
      semaforo[2].acquire();
      moveDown(491, imagemCarro2, 1);
      Platform.runLater(() -> imagemCarro2.setRotate(180));
      moveRight(668, imagemCarro2, 1);
      semaforo[2].release();
      semaforo[3].acquire();
      moveRight(701, imagemCarro2, 1);
      Platform.runLater(() -> imagemCarro2.setRotate(-270));
      moveUp(277, imagemCarro2, 1);
      semaforo[5].acquire();
      moveUp(109, imagemCarro2, 1);
      semaforo[0].acquire();
      moveUp(76, imagemCarro2, 1);
      semaforo[3].release();
      Platform.runLater(() -> imagemCarro2.setRotate(0));
      moveLeft(224, imagemCarro2, 1);
    }
  }
  public void percurso07SH() throws InterruptedException{
    ImageView imagemCarro3 = cP.getImagemCarro(2);
    while(true){
      moveLeft(523, imagemCarro3, 2);
      semaforo[4].acquire();
      moveLeft(344, imagemCarro3, 2);
      semaforo[4].release();
      moveLeft(206, imagemCarro3, 2);
      semaforo[5].acquire();
      moveLeft(172, imagemCarro3, 2);
      Platform.runLater(() -> imagemCarro3.setRotate(-270));
      moveUp(76, imagemCarro3, 2);
      Platform.runLater(() -> imagemCarro3.setRotate(180));
      moveRight(704, imagemCarro3, 2);
      Platform.runLater(() -> imagemCarro3.setRotate(-90));
      moveDown(243, imagemCarro3, 2);
      semaforo[5].release();
      Platform.runLater(() -> imagemCarro3.setRotate(0));
      moveLeft(642, imagemCarro3, 2);
    }
  }
  public void percurso12SA() throws InterruptedException{
    ImageView imagemCarro4 = cP.getImagemCarro(3);
    while(true){
      Platform.runLater(() -> imagemCarro4.setRotate(-90));
      moveDown(292, imagemCarro4, 3);
      semaforo[8].acquire();
      moveDown(457, imagemCarro4, 3);
      semaforo[2].acquire();
      semaforo[8].release();
      moveDown(494, imagemCarro4, 3);
      Platform.runLater(() -> imagemCarro4.setRotate(180));
      moveRight(591, imagemCarro4, 3);
      semaforo[2].release();
      Platform.runLater(() -> imagemCarro4.setRotate(-270));
      moveUp(362, imagemCarro4, 3);
      semaforo[7].acquire();
      moveUp(207, imagemCarro4, 3);
      semaforo[7].release();
      moveUp(193, imagemCarro4, 3);
      semaforo[6].acquire();
      moveUp(109, imagemCarro4, 3);
      semaforo[6].release();
      semaforo[0].acquire();
      moveUp(77, imagemCarro4, 3);
      Platform.runLater(() -> imagemCarro4.setRotate(0));
      moveLeft(380, imagemCarro4, 3);
      semaforo[0].release();
      Platform.runLater(() -> imagemCarro4.setRotate(-90));
      moveDown(184, imagemCarro4, 3);
    }
  }
  public void percurso13SH() throws InterruptedException{
    ImageView imagemCarro5 = cP.getImagemCarro(4);
    while(true){
      Platform.runLater(() -> imagemCarro5.setRotate(-90));//
      moveDown(292, imagemCarro5, 4);
      semaforo[11].acquire();
      moveDown(457, imagemCarro5, 4);
      semaforo[2].acquire();
      moveDown(494, imagemCarro5, 4);
      semaforo[11].release();
      Platform.runLater(() -> imagemCarro5.setRotate(0));//
      moveLeft(273, imagemCarro5, 4);
      semaforo[2].release();
      Platform.runLater(() -> imagemCarro5.setRotate(-270));//
      moveUp(362, imagemCarro5, 4);
      semaforo[10].acquire();
      moveUp(210, imagemCarro5, 4);
      semaforo[10].release();
      moveUp(278, imagemCarro5, 4);
      semaforo[9].acquire();
      moveUp(126, imagemCarro5, 4);
      semaforo[9].release();
      moveUp(109, imagemCarro5, 4);
      semaforo[0].acquire();
      moveUp(77, imagemCarro5, 4);
      Platform.runLater(() -> imagemCarro5.setRotate(180));
      moveRight(488, imagemCarro5, 4);
      semaforo[0].release();
      Platform.runLater(() -> imagemCarro5.setRotate(-90));//
      moveDown(184, imagemCarro5, 4);
    }
  }
  public void percurso18SA() throws InterruptedException{
    ImageView imagemCarro6 = cP.getImagemCarro(5);
    while(true){
      Platform.runLater(() -> imagemCarro6.setRotate(0));
      moveLeft(526, imagemCarro6, 5);
      semaforo[12].acquire();
      moveLeft(377, imagemCarro6, 5);
      Platform.runLater(() -> imagemCarro6.setRotate(-90));
      moveDown(362, imagemCarro6, 5);
      semaforo[12].release();
      moveDown(457, imagemCarro6, 5);
      semaforo[2].acquire();
      moveDown(490, imagemCarro6, 5);
      Platform.runLater(() -> imagemCarro6.setRotate(180));
      moveRight(694, imagemCarro6, 5);
      semaforo[2].release();
      semaforo[3].acquire();
      Platform.runLater(() -> imagemCarro6.setRotate(-270));
      moveUp(242, imagemCarro6, 5);
      semaforo[3].release();
      Platform.runLater(() -> imagemCarro6.setRotate(0));
      moveLeft(537, imagemCarro6, 5);
    }
  }
  public void percurso20SH() throws InterruptedException{
    ImageView imagemCarro7 = cP.getImagemCarro(6);
    while(true){
      Platform.runLater(() -> imagemCarro7.setRotate(180));
      moveRight(591, imagemCarro7, 6);
      Platform.runLater(() -> imagemCarro7.setRotate(-90));
      moveDown(240, imagemCarro7, 6);
      Platform.runLater(() -> imagemCarro7.setRotate(180));
      moveRight(668, imagemCarro7, 6);
      semaforo[3].acquire();
      moveRight(694, imagemCarro7, 6);
      Platform.runLater(() -> imagemCarro7.setRotate(-90));
      moveDown(326, imagemCarro7, 6);
      semaforo[3].release();
      Platform.runLater(() -> imagemCarro7.setRotate(0));
      moveLeft(632, imagemCarro7, 6);
      semaforo[11].acquire();
      moveLeft(591, imagemCarro7, 6);
      Platform.runLater(() -> imagemCarro7.setRotate(-90));
      moveDown(409, imagemCarro7, 6);
      semaforo[11].release();
      Platform.runLater(() -> imagemCarro7.setRotate(0));
      moveLeft(487, imagemCarro7, 6);
      Platform.runLater(() -> imagemCarro7.setRotate(-90));
      moveDown(457, imagemCarro7, 6);
      semaforo[2].acquire();
      moveDown(490, imagemCarro7, 6);
      Platform.runLater(() -> imagemCarro7.setRotate(0));
      moveLeft(419, imagemCarro7, 6);
      semaforo[8].acquire();
      moveLeft(380, imagemCarro7, 6);
      semaforo[2].release();
      Platform.runLater(() -> imagemCarro7.setRotate(-270));
      moveUp(413, imagemCarro7, 6);
      semaforo[8].release();
      Platform.runLater(() -> imagemCarro7.setRotate(0));
      moveLeft(274, imagemCarro7, 6);
      Platform.runLater(() -> imagemCarro7.setRotate(-270));
      moveUp(326, imagemCarro7, 6);
      Platform.runLater(() -> imagemCarro7.setRotate(0));
      moveLeft(206, imagemCarro7, 6);
      semaforo[1].acquire();
      moveLeft(167, imagemCarro7, 6);
      Platform.runLater(() -> imagemCarro7.setRotate(-270));
      moveUp(240, imagemCarro7, 6);
      semaforo[1].release();
      Platform.runLater(() -> imagemCarro7.setRotate(180));
      moveRight(268, imagemCarro7, 6);
      Platform.runLater(() -> imagemCarro7.setRotate(-270));
      moveUp(159, imagemCarro7, 6);
      Platform.runLater(() -> imagemCarro7.setRotate(180));
      moveRight(344, imagemCarro7, 6);
      semaforo[9].acquire();
      moveRight(376, imagemCarro7, 6);
      Platform.runLater(() -> imagemCarro7.setRotate(-270));
      moveUp(109, imagemCarro7, 6);
      semaforo[0].acquire();
      moveUp(74, imagemCarro7, 6);
      semaforo[9].release();
      Platform.runLater(() -> imagemCarro7.setRotate(180));
      moveRight(454, imagemCarro7, 6);
      semaforo[6].acquire();
      moveRight(484, imagemCarro7, 6);
      semaforo[0].release();
      Platform.runLater(() -> imagemCarro7.setRotate(-90));
      moveDown(157, imagemCarro7, 6);
      semaforo[6].release();
      Platform.runLater(() -> imagemCarro7.setRotate(180));
      moveRight(537, imagemCarro7, 6);
    }
  }
  public void percurso21SH() throws InterruptedException{
    ImageView imagemCarro8 = cP.getImagemCarro(7);
    while(true){
      Platform.runLater(() -> imagemCarro8.setRotate(180));
      moveRight(274, imagemCarro8, 7);
      Platform.runLater(() -> imagemCarro8.setRotate(-90));
      moveUp(113, imagemCarro8, 7);
      semaforo[0].acquire();
      moveUp(75, imagemCarro8, 7);
      Platform.runLater(() -> imagemCarro8.setRotate(180));
      moveRight(594, imagemCarro8, 7);
      semaforo[0].release();
      Platform.runLater(() -> imagemCarro8.setRotate(-270));
      moveDown(240, imagemCarro8, 7);
      Platform.runLater(() -> imagemCarro8.setRotate(180));
      moveRight(665, imagemCarro8, 7);
      semaforo[3].acquire();
      moveRight(700, imagemCarro8, 7);
      Platform.runLater(() -> imagemCarro8.setRotate(-270));
      moveDown(328, imagemCarro8, 7);
      semaforo[3].release();
      Platform.runLater(() -> imagemCarro8.setRotate(0));
      moveLeft(634, imagemCarro8, 7);
      semaforo[11].acquire();
      moveLeft(595, imagemCarro8, 7);
      Platform.runLater(() -> imagemCarro8.setRotate(-270));
      moveDown(461, imagemCarro8, 7);
      semaforo[2].acquire();
      moveDown(492, imagemCarro8, 7);
      semaforo[11].release();
      Platform.runLater(() -> imagemCarro8.setRotate(0));
      moveLeft(310, imagemCarro8, 7);
      semaforo[8].acquire();
      moveLeft(274, imagemCarro8, 7);
      semaforo[2].release();
      Platform.runLater(() -> imagemCarro8.setRotate(-90));
      moveUp(327, imagemCarro8, 7);
      semaforo[8].release();
      Platform.runLater(() -> imagemCarro8.setRotate(0));
      moveLeft(204, imagemCarro8, 7);
      semaforo[1].acquire();
      moveLeft(168, imagemCarro8, 7);
      Platform.runLater(() -> imagemCarro8.setRotate(-90));
      moveUp(243, imagemCarro8, 7);
      semaforo[1].release();
      Platform.runLater(() -> imagemCarro8.setRotate(180));
      moveRight(217, imagemCarro8, 7);
    }
  }

  //SELECAO DE PERCURSO DA INSTANCIA DA CLASSE
  public void movimentaVeiculo() throws InterruptedException{
    switch(this.percurso){
      case"P01SA":
        percurso01SA();
        break;
      case"P05SA":
        percurso05SA();
        break;
      case"P07SH":
        percurso07SH();
        break;
      case"P12SA":
        percurso12SA();
        break;
      case"P13SH":
        percurso13SH();
        break;
      case"P18SA":
        percurso18SA();
        break;
      case"P20SH":
        percurso20SH();
        break;
      case"P21SH":
        percurso21SH();
        break;
    }
  }

  public void run(){
    try {
      movimentaVeiculo();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
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