/* ***************************************************************
* Autor............: Christian Schettine Paiva Rocha
* Matricula........: 202210159
* Inicio...........: 26/08/2023
* Ultima alteracao.: 01/09/2023
* Nome.............: ControllerPrincipal
* Funcao...........: Controla todos os movimentos dos trens
*************************************************************** */
//pacote da classe
package model;
//importacoes
import java.util.concurrent.Semaphore;

public class Semaforo {
  //tamanho do buffer, no caso sera 3, producao e insercao de 3 produtos enche o buffer
  private static int buffer = 3;
  //mutex realiza o controle do acesso a regiao critica
  static Semaphore mutex = new Semaphore(1);
  //vazio faz a contagem das regioes vazias do buffer 
  static Semaphore vazio = new Semaphore(buffer);
  //cheio faz a contagem das regioes ocupadas do buffer 
  static Semaphore cheio = new Semaphore(0);
  
  //metodo para instanciar um novo semaforo mutex
  public static void setMutex(int valor) {
    mutex = new Semaphore(valor);
  }
  //metodo para instanciar um novo semaforo vazio
  public static void setVazio(){
    buffer = 3;
    vazio = new Semaphore(buffer);
  }
  //metodo para intanciar um novo semaforo cheio
  public static void setCheio(int i) {
    cheio = new Semaphore(i);
  }
}