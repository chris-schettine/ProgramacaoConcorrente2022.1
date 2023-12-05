/* ***************************************************************
* Autor............: Christian Schettine Paiva Rocha
* Matricula........: 202210159
* Inicio...........: 25/11/2023
* Ultima alteracao.: 04/12/2023
* Nome.............: Escritor
* Funcao...........: a classe Carro tem a funcao de construir e ma-
* nipular seus movimentos, implementando pause/retomada, velocidade
* individual e ainda a funcao reset para as threads.
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
  private int[] velCarro = new int[8];
  private static Semaphore[] semaforo = new Semaphore[75];
  
  //Seta/reseta os semaforos usados
  public static void setSemaforo(int valor) {
    for(int i = 0; i < 25; i++){
      semaforo[i] = new Semaphore(valor);
    }
  }

  //Construtor da classe Carro com controller, percurso e coordenadas iniciais
  public Carro(ControllerPrincipal cP, String perc, double coordInX, double coordInY){
    this.cP = cP;
    this.percurso = perc;
    this.coordInX = coordInX;
    this.coordInY = coordInY;

    for(int i = 0; i < 25; i++){
      semaforo[i] = new Semaphore(1);
    }
  }

  public int velocidadeCarro(int id) {
    velCarro[id] = cP.getVelocidade(id);   
    switch(velCarro[id]){
        case 0: 
        velCarro[id] = 11;
          break;
        case 1: 
        velCarro[id] = 7;
            break;
        case 2: 
        velCarro[id] = 4;
          break;
        default:
          System.out.println("Velocidade inválida");
          break;
    }
    return velCarro[id];
  }
  
  //Funcoes que movimentam os carros nas 4 direcoes
  //Movimenta pra esquerda
  public void moveLeft(int limite, ImageView imagemCarro, int id){
    while(this.coordInX >= limite){
      try {
        testaPause();
        this.coordInX--;
        Platform.runLater(()->imagemCarro.setLayoutX(this.coordInX));
        sleep(velocidadeCarro(id));
      } catch (InterruptedException e) {
      }
    }
  }
   //Movimenta pra cima
   public void moveUp(int limite, ImageView imagemCarro, int id){
    while(this.coordInY >= limite){
      try {
        testaPause();
        this.coordInY--;
        Platform.runLater(()->imagemCarro.setLayoutY(this.coordInY));
        sleep(velocidadeCarro(id));
      } catch (InterruptedException e) {
      }
    }
  }
  //Movimenta pra direita
  public void moveRight(int limite, ImageView imagemCarro, int id){
    while(this.coordInX <= limite){
      try {
        testaPause();
        this.coordInX++;
        Platform.runLater(()->imagemCarro.setLayoutX(this.coordInX));
        sleep(velocidadeCarro(id));
      } catch (InterruptedException e) {
      }
    }
  }
  //Movimenta pra baixo
  public void moveDown(int limite, ImageView imagemCarro, int id){
    while(this.coordInY <= limite){
      try {
        testaPause();
        this.coordInY++;
        Platform.runLater(()->imagemCarro.setLayoutY(this.coordInY));
        sleep(velocidadeCarro(id));
      } catch (InterruptedException e) {
      }
    }
  }

  //Descricao dos movimentos de cada percurso e seu respectivo carro
  public void percurso01SA() throws InterruptedException{
    ImageView imagemCarro1 = cP.getImagemCarro(0);
    while(true){
      Platform.runLater(() -> imagemCarro1.setRotate(0));
      moveLeft(418, imagemCarro1, 0);
      try {
        semaforo[16].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      moveLeft(377, imagemCarro1, 0);
      try {
        semaforo[9].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      Platform.runLater(() -> imagemCarro1.setRotate(-270));
      moveUp(109, imagemCarro1, 0);
      semaforo[16].release();
      semaforo[9].release();
      try {
        semaforo[0].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
        try {
        semaforo[5].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      moveUp(74, imagemCarro1, 0);
      Platform.runLater(() -> imagemCarro1.setRotate(0));
      moveLeft(206, imagemCarro1, 0);
      semaforo[0].release();
      try {
        semaforo[1].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      moveLeft(165, imagemCarro1, 0);
      Platform.runLater(() -> imagemCarro1.setRotate(-90));
      moveDown(277, imagemCarro1, 0);
      semaforo[5].release();
      moveDown(457, imagemCarro1, 0);
      semaforo[1].release();
      try {
        semaforo[2].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      moveDown(491, imagemCarro1, 0);
      Platform.runLater(() -> imagemCarro1.setRotate(180));
      moveRight(266, imagemCarro1, 0);
      semaforo[2].release();
      try {
        semaforo[8].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      Platform.runLater(() -> imagemCarro1.setRotate(-270));
      moveUp(325, imagemCarro1, 0);
      semaforo[8].release();
      Platform.runLater(() -> imagemCarro1.setRotate(180));
      moveRight(344, imagemCarro1, 0);
      try {
        semaforo[10].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      moveRight(376, imagemCarro1, 0);
      Platform.runLater(() -> imagemCarro1.setRotate(-270));
      moveUp(239, imagemCarro1, 0);
      semaforo[10].release();
      try {
        semaforo[4].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      Platform.runLater(() -> imagemCarro1.setRotate(180));
      moveRight(448, imagemCarro1, 0);
      semaforo[4].release();
      try {
        semaforo[6].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      moveRight(485, imagemCarro1, 0);
      Platform.runLater(() -> imagemCarro1.setRotate(-90));
      moveDown(325, imagemCarro1, 0);
      semaforo[6].release();
      Platform.runLater(() -> imagemCarro1.setRotate(180));
      moveRight(556, imagemCarro1, 0);
      try {
        semaforo[11].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      moveRight(591, imagemCarro1, 0);
      Platform.runLater(() -> imagemCarro1.setRotate(-90));
      moveDown(457, imagemCarro1, 0);
      semaforo[11].release();
      try {
        semaforo[2].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      moveDown(491, imagemCarro1, 0);
      Platform.runLater(() -> imagemCarro1.setRotate(180));
      moveRight(668, imagemCarro1, 0);
      semaforo[2].release();
      try {
        semaforo[3].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      moveRight(694, imagemCarro1, 0);
      Platform.runLater(() -> imagemCarro1.setRotate(-270));
      moveUp(277, imagemCarro1, 0);
      try {
        semaforo[5].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      moveUp(109, imagemCarro1, 0);
      semaforo[3].release();
      try {
        semaforo[0].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      moveUp(74, imagemCarro1, 0);
      Platform.runLater(() -> imagemCarro1.setRotate(0));
      moveLeft(484, imagemCarro1, 0);
      semaforo[0].release();
      semaforo[5].release();
      try {
        semaforo[7].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      Platform.runLater(() -> imagemCarro1.setRotate(-90));
      moveDown(157, imagemCarro1, 0);
      semaforo[7].release();
      Platform.runLater(() -> imagemCarro1.setRotate(0));
      moveLeft(429, imagemCarro1, 0);
    }
  }
  public void percurso05SA() throws InterruptedException {
    ImageView imagemCarro2 = cP.getImagemCarro(1);
    try {
      semaforo[0].acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
      try {
      semaforo[5].acquire();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      while (true) {
      Platform.runLater(() -> imagemCarro2.setRotate(0));
      moveLeft(206, imagemCarro2, 1);
      semaforo[0].release();
      try {
        semaforo[1].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      moveLeft(165, imagemCarro2, 1);
      Platform.runLater(() -> imagemCarro2.setRotate(-90));
      moveDown(277, imagemCarro2, 1);
      semaforo[5].release();
      moveDown(457, imagemCarro2, 1);
      semaforo[1].release();
      try {
        semaforo[2].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      moveDown(491, imagemCarro2, 1);
      Platform.runLater(() -> imagemCarro2.setRotate(180));
      moveRight(668, imagemCarro2, 1);
      semaforo[2].release();
      try {
        semaforo[3].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      moveRight(701, imagemCarro2, 1);
      Platform.runLater(() -> imagemCarro2.setRotate(-270));
      moveUp(277, imagemCarro2, 1);
      try {
        semaforo[5].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      moveUp(109, imagemCarro2, 1);
      try {
        semaforo[0].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      moveUp(76, imagemCarro2, 1);
      semaforo[3].release();
      Platform.runLater(() -> imagemCarro2.setRotate(0));
      moveLeft(224, imagemCarro2, 1);
    }
  }
  public void percurso07SH() throws InterruptedException{
    ImageView imagemCarro3 = cP.getImagemCarro(2);
    while(true){
      moveLeft(631, imagemCarro3, 2);
      try {
        semaforo[22].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      moveLeft(555, imagemCarro3, 2);
      semaforo[22].release();
      moveLeft(524, imagemCarro3, 2);
      try {
        semaforo[19].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      moveLeft(523, imagemCarro3, 2);
      try {
        semaforo[4].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      moveLeft(522, imagemCarro3, 2);
      try {
        semaforo[20].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      moveLeft(457, imagemCarro3, 2);
      semaforo[20].release();
      moveLeft(418, imagemCarro3, 2);
      try {
        semaforo[23].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      moveLeft(359, imagemCarro3, 2);
      semaforo[19].release();
      moveLeft(344, imagemCarro3, 2);
      semaforo[4].release();
      semaforo[23].release();
      moveLeft(310, imagemCarro3, 2);
      try {
        semaforo[21].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      moveLeft(237, imagemCarro3, 2);
      semaforo[21].release();
      moveLeft(206, imagemCarro3, 2);
      try {
        semaforo[5].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      moveLeft(172, imagemCarro3, 2);
      Platform.runLater(() -> imagemCarro3.setRotate(-270));
      try {
        semaforo[18].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      moveUp(76, imagemCarro3, 2);
      Platform.runLater(() -> imagemCarro3.setRotate(180));
      moveRight(704, imagemCarro3, 2);
      Platform.runLater(() -> imagemCarro3.setRotate(-90));
      moveDown(243, imagemCarro3, 2);
      semaforo[5].release();
      semaforo[18].release();
      Platform.runLater(() -> imagemCarro3.setRotate(0));
      moveLeft(642, imagemCarro3, 2);
    }
  }
  public void percurso12SA() throws InterruptedException{
    ImageView imagemCarro4 = cP.getImagemCarro(3);
    while(true){
      Platform.runLater(() -> imagemCarro4.setRotate(-90));
      moveDown(206, imagemCarro4, 3);
      try {
        semaforo[22].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      moveDown(208, imagemCarro4, 3);
      try {
        semaforo[20].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      moveDown(277, imagemCarro4, 3);
      semaforo[20].release();
      semaforo[22].release();
      moveDown(292, imagemCarro4, 3);
      try {
        semaforo[8].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      moveDown(457, imagemCarro4, 3);
      semaforo[8].release();
      moveDown(379, imagemCarro4, 3);
      try {
        semaforo[14].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      try {
        semaforo[15].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
        moveDown(457, imagemCarro4, 3);
      try {
        semaforo[2].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      moveDown(494, imagemCarro4, 3);
      Platform.runLater(() -> imagemCarro4.setRotate(180));
      moveRight(591, imagemCarro4, 3);
      semaforo[2].release();
      Platform.runLater(() -> imagemCarro4.setRotate(-270));
      moveUp(379, imagemCarro4, 3);
      semaforo[14].release();
      semaforo[15].release();
      moveUp(362, imagemCarro4, 3);
      try {
        semaforo[7].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      moveUp(277, imagemCarro4, 3);
      try {
        semaforo[21].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      moveUp(276, imagemCarro4, 3);
      try {
        semaforo[23].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      moveUp(207, imagemCarro4, 3);
      semaforo[23].release();
      semaforo[21].release();
      semaforo[7].release();
      moveUp(193, imagemCarro4, 3);
      try {
        semaforo[6].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      moveUp(109, imagemCarro4, 3);
      semaforo[6].release();
      try {
        semaforo[0].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      moveUp(108, imagemCarro4, 3);
      try {
        semaforo[18].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      moveUp(77, imagemCarro4, 3);
      Platform.runLater(() -> imagemCarro4.setRotate(0));
      moveLeft(380, imagemCarro4, 3);
      semaforo[0].release();
      semaforo[18].release();
      Platform.runLater(() -> imagemCarro4.setRotate(-90));
      moveDown(184, imagemCarro4, 3);
    }
  }
  public void percurso13SH() throws InterruptedException{
    ImageView imagemCarro5 = cP.getImagemCarro(4);
    while(true){
      Platform.runLater(() -> imagemCarro5.setRotate(-90));//
      moveDown(292, imagemCarro5, 4);
      try {
        semaforo[11].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      moveDown(457, imagemCarro5, 4);
      try {
        semaforo[2].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      moveDown(494, imagemCarro5, 4);
      semaforo[11].release();
      Platform.runLater(() -> imagemCarro5.setRotate(0));//
      moveLeft(273, imagemCarro5, 4);
      semaforo[2].release();
      Platform.runLater(() -> imagemCarro5.setRotate(-270));//
      moveUp(362, imagemCarro5, 4);
      try {
        semaforo[10].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      moveUp(210, imagemCarro5, 4);
      semaforo[10].release();
      moveUp(278, imagemCarro5, 4);
      try {
        semaforo[9].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      moveUp(198, imagemCarro5, 4);
      try {
        semaforo[17].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      moveUp(126, imagemCarro5, 4);
      semaforo[9].release();
      moveUp(109, imagemCarro5, 4);
      moveUp(77, imagemCarro5, 4);
      Platform.runLater(() -> imagemCarro5.setRotate(180));
      moveRight(488, imagemCarro5, 4);
      semaforo[0].release();
      semaforo[17].release();
      Platform.runLater(() -> imagemCarro5.setRotate(-90));//
      moveDown(184, imagemCarro5, 4);
    }
  }
  public void percurso18SA() throws InterruptedException{
    ImageView imagemCarro6 = cP.getImagemCarro(5);
    while(true){
      Platform.runLater(() -> imagemCarro6.setRotate(0));
      moveLeft(526, imagemCarro6, 5);
      try {
        semaforo[12].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      moveLeft(377, imagemCarro6, 5);
      Platform.runLater(() -> imagemCarro6.setRotate(-90));
      moveDown(362, imagemCarro6, 5);
      semaforo[12].release();
      moveDown(379, imagemCarro6, 5);
      try {
        semaforo[14].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      moveDown(457, imagemCarro6, 5);
      try {
        semaforo[2].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      moveDown(490, imagemCarro6, 5);
      semaforo[14].release();
      Platform.runLater(() -> imagemCarro6.setRotate(180));
      moveRight(694, imagemCarro6, 5);
      semaforo[2].release();
      try {
        semaforo[3].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
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
      try {
        semaforo[3].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      moveRight(694, imagemCarro7, 6);
      Platform.runLater(() -> imagemCarro7.setRotate(-90));
      moveDown(326, imagemCarro7, 6);
      semaforo[3].release();
      Platform.runLater(() -> imagemCarro7.setRotate(0));
      moveLeft(632, imagemCarro7, 6);
      try {
        semaforo[11].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      moveLeft(591, imagemCarro7, 6);
      Platform.runLater(() -> imagemCarro7.setRotate(-90));
      moveDown(409, imagemCarro7, 6);
      semaforo[11].release();
      Platform.runLater(() -> imagemCarro7.setRotate(0));
      moveLeft(487, imagemCarro7, 6);
      try {
        semaforo[15].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      Platform.runLater(() -> imagemCarro7.setRotate(-90));
      moveDown(457, imagemCarro7, 6);
      try {
        semaforo[14].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      moveDown(490, imagemCarro7, 6);
      Platform.runLater(() -> imagemCarro7.setRotate(0));
      moveLeft(419, imagemCarro7, 6);
      try {
        semaforo[8].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      moveLeft(380, imagemCarro7, 6);
      Platform.runLater(() -> imagemCarro7.setRotate(-270));
      moveUp(413, imagemCarro7, 6);
      semaforo[14].release();
      semaforo[15].release();
      semaforo[8].release();
      Platform.runLater(() -> imagemCarro7.setRotate(0));
      moveLeft(274, imagemCarro7, 6);
      Platform.runLater(() -> imagemCarro7.setRotate(-270));
      moveUp(326, imagemCarro7, 6);
      Platform.runLater(() -> imagemCarro7.setRotate(0));
      moveLeft(206, imagemCarro7, 6);
      try {
        semaforo[1].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
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
      try {
        semaforo[9].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      try {
        semaforo[16].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      moveRight(376, imagemCarro7, 6);
      Platform.runLater(() -> imagemCarro7.setRotate(-270));
      moveUp(109, imagemCarro7, 6);
      moveUp(74, imagemCarro7, 6);
      semaforo[9].release();
      semaforo[16].release();
      Platform.runLater(() -> imagemCarro7.setRotate(180));
      moveRight(454, imagemCarro7, 6);
      try {
        semaforo[6].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
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
      try {
        semaforo[0].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      moveUp(75, imagemCarro8, 7);
      Platform.runLater(() -> imagemCarro8.setRotate(180));
      moveRight(594, imagemCarro8, 7);
      semaforo[0].release();
      Platform.runLater(() -> imagemCarro8.setRotate(-270));
      moveDown(240, imagemCarro8, 7);
      try {
        semaforo[19].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      Platform.runLater(() -> imagemCarro8.setRotate(180));
      moveRight(665, imagemCarro8, 7);
      try {
        semaforo[3].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      moveRight(700, imagemCarro8, 7);
      semaforo[19].release();
      Platform.runLater(() -> imagemCarro8.setRotate(-270));
      moveDown(328, imagemCarro8, 7);
      semaforo[3].release();
      Platform.runLater(() -> imagemCarro8.setRotate(0));
      moveLeft(634, imagemCarro8, 7);
      try {
        semaforo[11].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      moveLeft(595, imagemCarro8, 7);
      Platform.runLater(() -> imagemCarro8.setRotate(-270));
      moveDown(461, imagemCarro8, 7);
      try {
        semaforo[2].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      moveDown(492, imagemCarro8, 7);
      semaforo[11].release();
      Platform.runLater(() -> imagemCarro8.setRotate(0));
      moveLeft(310, imagemCarro8, 7);
      try {
        semaforo[8].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      moveLeft(274, imagemCarro8, 7);
      semaforo[2].release();
      Platform.runLater(() -> imagemCarro8.setRotate(-90));
      moveUp(327, imagemCarro8, 7);
      semaforo[8].release();
      Platform.runLater(() -> imagemCarro8.setRotate(0));
      moveLeft(204, imagemCarro8, 7);
      try {
        semaforo[1].acquire();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      moveLeft(168, imagemCarro8, 7);
      Platform.runLater(() -> imagemCarro8.setRotate(-90));
      moveUp(243, imagemCarro8, 7);
      semaforo[1].release();
      Platform.runLater(() -> imagemCarro8.setRotate(180));
      moveRight(217, imagemCarro8, 7);
    }
  }

  //Metodo que conecta cada thread e cada carro a seu percurso informado pelo construtor
  public void startMovimento() throws InterruptedException{
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
      startMovimento();
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