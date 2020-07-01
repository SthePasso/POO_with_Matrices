import java.util.Scanner;
import java.util.Random;

public class Main{
	public static void main(String[] args) {
		int numeros = 20, ordem = 0;
		String resposta;
		Matriz matA;

		Scanner leia = new Scanner(System.in);		

		System.out.println ("\n*************************Bem vindo a Matriz Intergaláctica!!**************************\n");
		System.out.print ("Qual a ORDEM desejada da visão para o Mapa Galaxical em Matriz? ");
		ordem = leia.nextInt();
		if (ordem<1){
			System.out.println ("Não existem matrizes desta ordem em nossa galáxia!");
		} else if (ordem<5) {
			System.out.println ("Os aprendizes para Jedi acabaram de calcular o determinante:");
		} else if (ordem<10){
			System.out.println ("Darth Vader está tramando algo contra A Força, mas os Jedi venceram:");
		} else if (ordem>=10){
			System.out.println ("Darck Vader atacou o sistema mas Lock Skiwalker está trabalhando para te ajudar!!");
		}
		if (ordem>0){
			matA = new Matriz (ordem);
			matA.iniciaRandom(numeros);
			matA.imprime();
			matA.cronometro();
			matA.fimEmotion();
		}
	}
}
