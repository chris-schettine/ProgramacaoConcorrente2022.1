/* ***************************************************************
* Autor............: Christian Schettine Paiva Rocha
* Matricula........: 202210159
* Inicio...........: 26/08/2023
* Ultima alteracao.: 01/09/2023
* Nome.............: ControllerPrincipal
* Funcao...........: Controla todos os movimentos dos trens
*************************************************************** */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.util.Duration;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

public class ControllerPrincipal implements Initializable {
  //Primeiramente, os elementos do FXML que serao manipulados no Controller sao declarados e nomeados
  @FXML
  private ImageView imageView_trem1;

  @FXML
  private ImageView imageView_trem2;

  @FXML
  private Button botao_reset;

  @FXML
  private Button botao_direita_trem1;

  @FXML
  private Button botao_esquerda_trem1;

  @FXML
  private Button botao_direita_trem2;

  @FXML
  private Button botao_esquerda_trem2;

  @FXML
  private Button botao_vel0_trem1;

  @FXML
  private Button botao_vel1_trem1;
  
  @FXML
  private Button botao_vel2_trem1;

  @FXML
  private Button botao_vel3_trem1;

  @FXML
  private Button botao_vel0_trem2;

  @FXML
  private Button botao_vel1_trem2;
  
  @FXML
  private Button botao_vel2_trem2;

  @FXML
  private Button botao_vel3_trem2;

  //Os Paths, que nesse programa serao os trilhos dos trens, sao criados
  //e declarados de forma global, para que sejam acessiveis por todos os metodos
  //e Event Handlers da classe Controller
  Path trilho1 = new Path(); //Dois de ida dos trens
  Path trilho2 = new Path();
  Path trilho3 = new Path(); //Dois de volta
  Path trilho4 = new Path();
  //Os PathTransitions, que nesse programa serao os 4 percursos possiveis, sao criados e declarados
  //de forma global
  PathTransition idaTrem1 = new PathTransition();
  PathTransition idaTrem2 = new PathTransition();
  PathTransition voltaTrem1 = new PathTransition();
  PathTransition voltaTrem2 = new PathTransition();

  @Override
  public void initialize (URL location, ResourceBundle resources) {
  botao_vel1_trem1.setStyle("-fx-background-color: #1A6F1A;"); // Seta o verde mais escuro, 
  botao_vel1_trem2.setStyle("-fx-background-color: #1A6F1A;"); // que sera a cor de fundo dos botoes
  botao_direita_trem1.setStyle("-fx-background-color: #1A6F1A;"); // selecionados, informando o sentido 
  botao_direita_trem2.setStyle("-fx-background-color: #1A6F1A;"); // e a velocidade atual de cada trem

  trilho1 = criaTrilho1ida(); //As funcoes que criam os trilhos sao chamadas, cada um com os parametros
  trilho2 = criaTrilho2ida(); //ja declarados no corpo de cada uma.
  trilho3 = criaTrilho1volta();
  trilho4 = criaTrilho2volta();

  idaTrem1 = criaPathTransition(imageView_trem1, trilho1); //As funcoes que criam os percursos sao chamadas,
  idaTrem2 = criaPathTransition(imageView_trem2, trilho2); //e como parametros são passados trilho e image_view
  voltaTrem1 = criaPathTransition(imageView_trem1, trilho3);
  voltaTrem2 = criaPathTransition(imageView_trem2, trilho4);

  idaTrem1.play(); //Inicia os percursos padrao, sendo ambos de ida.
  idaTrem2.play();
}

/* ***************************************************************
 * Evento: click
 * Funcao: restaura ao estado inicial dos trens
 * Parametros: nao recebe parametros
 * Retorno: nao retorna valores
 *************************************************************** */

@FXML
void onClickbotao_reset(ActionEvent event) throws IOException {
  idaTrem1.stop(); //Ao resetar, todos os 4 percursos sao parados
  idaTrem2.stop();
  voltaTrem1.stop();
  voltaTrem2.stop();

  idaTrem1.setRate(1); //As velocidades também sao resetadas
  idaTrem2.setRate(1);
  voltaTrem1.setRate(1);
  voltaTrem2.setRate(1);

  botao_vel0_trem1.setStyle("-fx-background-color: #2E8B57"); //As cores dos botoes de velocidade
  botao_vel1_trem1.setStyle("-fx-background-color: #1A6F1A;"); //voltam para o estado inicial
  botao_vel2_trem1.setStyle("-fx-background-color: #2E8B57");
  botao_vel3_trem1.setStyle("-fx-background-color: #2E8B57");

  botao_vel0_trem2.setStyle("-fx-background-color: #2E8B57"); //As cores dos botoes de velocidade
  botao_vel1_trem2.setStyle("-fx-background-color: #1A6F1A;"); //voltam para o estado inicial
  botao_vel2_trem2.setStyle("-fx-background-color: #2E8B57");
  botao_vel3_trem2.setStyle("-fx-background-color: #2E8B57");

  botao_direita_trem1.setStyle("-fx-background-color: #1A6F1A;"); //As cores dos botoes de sentido
  botao_esquerda_trem1.setStyle("-fx-background-color: #2E8B57"); //voltam para o estado inicial
  botao_direita_trem2.setStyle("-fx-background-color: #1A6F1A;");
  botao_esquerda_trem2.setStyle("-fx-background-color: #2E8B57");

  idaTrem1.play(); //Os percursos padrão são iniciados novamente
  idaTrem2.play();
}

/* ***************************************************************
 * Evento: click
 * Funcao: aumenta ou diminui a velocidade dos trens
 * Parametros: nao recebe parametros
 * Retorno: nao retorna valores
 *************************************************************** */

@FXML
void onClickbotao_vel0_trem1(ActionEvent event) throws IOException {
  idaTrem1.setRate(0.0); //Seta a velocidade do trem 1 para 0
  voltaTrem1.setRate(0.0);

  botao_vel0_trem1.setStyle("-fx-background-color: #1A6F1A;");
  botao_vel1_trem1.setStyle("-fx-background-color: #2E8B57");
  botao_vel2_trem1.setStyle("-fx-background-color: #2E8B57");
  botao_vel3_trem1.setStyle("-fx-background-color: #2E8B57");
}

@FXML
void onClickbotao_vel1_trem1(ActionEvent event) throws IOException {
  idaTrem1.setRate(1.0); //Seta a velocidade do trem 1 para 1, o Rate default
  voltaTrem1.setRate(1.0);

  botao_vel0_trem1.setStyle("-fx-background-color: #2E8B57");
  botao_vel1_trem1.setStyle("-fx-background-color: #1A6F1A;");
  botao_vel2_trem1.setStyle("-fx-background-color: #2E8B57");
  botao_vel3_trem1.setStyle("-fx-background-color: #2E8B57");
}

@FXML
void onClickbotao_vel2_trem1(ActionEvent event) throws IOException {
  idaTrem1.setRate(2.0); //Seta a velocidade do trem 1 para 2
  voltaTrem1.setRate(2.0);

  botao_vel0_trem1.setStyle("-fx-background-color: #2E8B57");
  botao_vel1_trem1.setStyle("-fx-background-color: #2E8B57");
  botao_vel2_trem1.setStyle("-fx-background-color: #1A6F1A;");
  botao_vel3_trem1.setStyle("-fx-background-color: #2E8B57");
}

@FXML
void onClickbotao_vel3_trem1(ActionEvent event) throws IOException {
  idaTrem1.setRate(3.0); //Seta a velocidade do trem 1 para 3
  voltaTrem1.setRate(3.0);

  botao_vel0_trem1.setStyle("-fx-background-color: #2E8B57");
  botao_vel1_trem1.setStyle("-fx-background-color: #2E8B57");
  botao_vel2_trem1.setStyle("-fx-background-color: #2E8B57");
  botao_vel3_trem1.setStyle("-fx-background-color: #1A6F1A;");
}

@FXML
void onClickbotao_vel0_trem2(ActionEvent event) throws IOException {
  idaTrem2.setRate(0.0); //Seta a velocidade do trem 2 para 0
  voltaTrem2.setRate(0.0);

  botao_vel0_trem2.setStyle("-fx-background-color: #1A6F1A;");
  botao_vel1_trem2.setStyle("-fx-background-color: #2E8B57");
  botao_vel2_trem2.setStyle("-fx-background-color: #2E8B57");
  botao_vel3_trem2.setStyle("-fx-background-color: #2E8B57");
}

@FXML
void onClickbotao_vel1_trem2(ActionEvent event) throws IOException {
  idaTrem2.setRate(1.0); //Seta a velocidade do trem 2 para 1
  voltaTrem2.setRate(1.0);

  botao_vel0_trem2.setStyle("-fx-background-color: #2E8B57");
  botao_vel1_trem2.setStyle("-fx-background-color: #1A6F1A;");
  botao_vel2_trem2.setStyle("-fx-background-color: #2E8B57");
  botao_vel3_trem2.setStyle("-fx-background-color: #2E8B57");
}

@FXML
void onClickbotao_vel2_trem2(ActionEvent event) throws IOException {
  idaTrem2.setRate(2.0); //Seta a velocidade do trem 2 para 2
  voltaTrem2.setRate(2.0);

  botao_vel0_trem2.setStyle("-fx-background-color: #2E8B57");
  botao_vel1_trem2.setStyle("-fx-background-color: #2E8B57");
  botao_vel2_trem2.setStyle("-fx-background-color: #1A6F1A;");
  botao_vel3_trem2.setStyle("-fx-background-color: #2E8B57");
}

@FXML
void onClickbotao_vel3_trem2(ActionEvent event) throws IOException {
  idaTrem2.setRate(3.0); //Seta a velocidade do trem 2 para 3
  voltaTrem2.setRate(3.0);

  botao_vel0_trem2.setStyle("-fx-background-color: #2E8B57");
  botao_vel1_trem2.setStyle("-fx-background-color: #2E8B57");
  botao_vel2_trem2.setStyle("-fx-background-color: #2E8B57");
  botao_vel3_trem2.setStyle("-fx-background-color: #1A6F1A;");
}

/* ***************************************************************
 * Evento: click
 * Funcao: troca a posicao dos trens
 * Parametros: nao recebe parametros
 * Retorno: nao retorna valores
 *************************************************************** */
@FXML
void onClickbotao_direita_trem1(ActionEvent event) throws IOException {
  voltaTrem1.stop(); //Seta o sentido do trem 1 para a direita, parando o sentido contrario e iniciando
  idaTrem1.play();   //o sentido desejado

  botao_direita_trem1.setStyle("-fx-background-color: #1A6F1A;");
  botao_esquerda_trem1.setStyle("-fx-background-color: #2E8B57");
}

@FXML
void onClickbotao_direita_trem2(ActionEvent event) throws IOException {
  voltaTrem2.stop(); //Seta o sentido do trem 2 para a direita, parando o sentido contrario e iniciando
  idaTrem2.play();   //o sentido desejado

  botao_direita_trem2.setStyle("-fx-background-color: #1A6F1A;");
  botao_esquerda_trem2.setStyle("-fx-background-color: #2E8B57");
}

@FXML
void onClickbotao_esquerda_trem1(ActionEvent event) throws IOException {
  idaTrem1.stop();   //Seta o sentido do trem 1 para a esquerda, parando o sentido contrario e iniciando
  voltaTrem1.play(); //o sentido desejado

  botao_esquerda_trem1.setStyle("-fx-background-color: #1A6F1A;");
  botao_direita_trem1.setStyle("-fx-background-color: #2E8B57");
}

@FXML
void onClickbotao_esquerda_trem2(ActionEvent event) throws IOException {
  idaTrem2.stop();   //Seta o sentido do trem 2 para a esquerda, parando o sentido contrario e iniciando
  voltaTrem2.play(); //o sentido desejado

  botao_esquerda_trem2.setStyle("-fx-background-color: #1A6F1A;");
  botao_direita_trem2.setStyle("-fx-background-color: #2E8B57");
}

/* ***************************************************************
 * Metodo: criaTrilho[X][sentido]
 * Funcao: cria os caminhos para o trem percorrer
 * Parametros: sem parametros
 * Retorno: retorna os caminhos, ou trilhos
 *************************************************************** */
private Path criaTrilho1ida() {
  Path path = new Path();
  path.getElements().add(new MoveTo(-2, 19));
  path.getElements().add(new LineTo(114, 19));
  path.getElements().add(new LineTo(231, 89));
  path.getElements().add(new LineTo(312, 89));
  path.getElements().add(new LineTo(432, 19));
  path.getElements().add(new LineTo(568, 19));
  path.getElements().add(new LineTo(683, 89));
  path.getElements().add(new LineTo(774, 89));
  path.getElements().add(new LineTo(891, 19));
  path.getElements().add(new LineTo(1052, 19));

  return path;
}
private Path criaTrilho2ida() {
  Path path = new Path();
  path.getElements().add(new MoveTo(-2, 15));
  path.getElements().add(new LineTo(114, 15));
  path.getElements().add(new LineTo(231, -56));
  path.getElements().add(new LineTo(312, -56));
  path.getElements().add(new LineTo(432, 15));
  path.getElements().add(new LineTo(568, 15));
  path.getElements().add(new LineTo(683, -56));
  path.getElements().add(new LineTo(774, -56));
  path.getElements().add(new LineTo(891, 15));
  path.getElements().add(new LineTo(1052, 15));

  return path;
}
private Path criaTrilho1volta() {
  Path path = new Path();
  path.getElements().add(new MoveTo(1052, 19));
  path.getElements().add(new LineTo(891, 19));
  path.getElements().add(new LineTo(774, 89));
  path.getElements().add(new LineTo(683, 89));
  path.getElements().add(new LineTo(568, 19));
  path.getElements().add(new LineTo(432, 19));
  path.getElements().add(new LineTo(312, 89));
  path.getElements().add(new LineTo(231, 89));
  path.getElements().add(new LineTo(114, 19));
  path.getElements().add(new LineTo(-2, 19));

  return path;
}
private Path criaTrilho2volta() {
  Path path = new Path();
  path.getElements().add(new MoveTo(1002, 15));
  path.getElements().add(new LineTo(891, 15));
  path.getElements().add(new LineTo(774, -56));
  path.getElements().add(new LineTo(683, -56));
  path.getElements().add(new LineTo(568, 15));
  path.getElements().add(new LineTo(432, 15));
  path.getElements().add(new LineTo(312, -56));
  path.getElements().add(new LineTo(231, -56));
  path.getElements().add(new LineTo(114, 15));
  path.getElements().add(new LineTo(48, 15));

  return path; 
}

/* ***************************************************************
 * Metodo: criaPathTransition
 * Funcao: define a animacao dos trens
 * Parametros: o trem e o trilho
 * Retorno: retorna o percurso
 *************************************************************** */
public PathTransition criaPathTransition(Node tremA, Path trilhoA) {
  PathTransition pathTransition = new PathTransition();
  pathTransition.setPath(trilhoA);
  pathTransition.setNode(tremA);

  pathTransition.setDuration(Duration.seconds(5));
  pathTransition.setCycleCount(PathTransition.INDEFINITE);
  pathTransition.setAutoReverse(false);
  pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);

  return pathTransition;
}
}
