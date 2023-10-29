/* ***************************************************************
* Autor............: Christian Schettine Paiva Rocha
* Matricula........: 202210159
* Inicio...........: 22/10/2023
* Ultima alteracao.: 29/10/2023
* Nome.............: Semaforo
* Funcao...........: Esta classe representa um controlador de semáforos.
* Ela gerencia o acesso a uma seção crítica, controlando a entrada e saída
* de processos por meio do uso de semáforos. Além disso, esta classe mantém
* a contagem de recursos disponíveis (vazios e cheios) usando semáforos, 
* garantindo a sincronização adequada entre processos em um ambiente 
* multithread.
*************************************************************** */

//package
package model;

//imports
import java.util.concurrent.Semaphore;

public class Semaforo {
  //tamanho do buffer, que sera 3
  private static int buffer = 3;
  //controla o acesso a regiao critica
  static Semaphore mutex = new Semaphore(1);
  //conta as regioes vazias do buffer 
  static Semaphore vazio = new Semaphore(buffer);
  //conta as regioes ocupadas do buffer 
  static Semaphore cheio = new Semaphore(0);
  
  //metodo que instancia novo semaforo mutex
  public static void setMutex(int valor) {
    mutex = new Semaphore(valor);
  }
  //metodo que instancia novo semaforo vazio
  public static void setVazio(){
    buffer = 3;
    vazio = new Semaphore(buffer);
  }
  //metodo que instancia novo semaforo cheio
  public static void setCheio(int i) {
    cheio = new Semaphore(i);
  }
}