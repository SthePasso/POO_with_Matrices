/*Requisitos:
1. Atributos:
	-a. Array bidimensional mat do tipo inteiro;
	-b. valor numLinhas do tipo inteiro;
	-c. valor numColunas do tipo inteiro;
2. Métodos get/set
3. Métodos construtores:
	-a. Matriz(int) - inicia um objeto com uma matriz quadrada, cuja ordem é informada no parâmetro inteiro recebido;
	-b. Marriz(int,int) - inicia um objeto com uma matriz, cujas dimensôes são informadas nos parâmetros inteiros recebidos referentes ao número de linhas e de colunas;
4. Métodos:
	-a. iniciarComNumero(int) - inicia a matriz com o número inteiro informado como parâmetro;
	+b. iniciaRamdom() - iniciar a matriz com números randômicos;
	+c. iniciaRandom(int) - iniciar a matriz com números randômicos, recebendo como parâmetro um numero inteiro (os numeros randomicos serão gerados entre 0 e o número fornecido)
	+d. iniciaPelaMatriz(Matriz) - iniciar a matriz a partir de outra matriz recebida como parâmetro (copia os elementos da matriz recebida para a própria matriz);
	+e. copiaParaMatriz(Matriz) - copiar os elementos da matriz para outra matriz recebida como parâmetro;
	-f. imprime() - imprime os elementos da matriz no console;*/

/***************************************************SOLI DEO GLORIA**************************************************************/

import java.util.Scanner;
import java.util.Random;

class Matriz {
	private int[][] mat;
	private int numLinhas;
	private int numColunas;
	
	Matriz (int parametro){
		this.setNumLinhas(parametro);
		this.setNumColunas(parametro);
		this.mat = new int[this.getNumLinhas()][this.getNumColunas()];
	}
	Matriz (int i, int j){
		this.setNumLinhas(i);
		this.setNumColunas(j);
		this.mat = new int[this.getNumLinhas()][this.getNumColunas()];
	}
	public void setNumLinhas(int nLinhas){
		this.numLinhas = nLinhas;		
	}
	public int getNumLinhas(){
		return this.numLinhas;
	}
	public void setNumColunas(int nColunas){
		this.numColunas = nColunas;		
	}
	public int getNumColunas(){
		return this.numColunas;
	}
	public void setElemento(int numLinhas, int numColunas, int valor){// analiza-lo
		this.mat[numLinhas][numColunas] = valor;		
	}
	public int getElemento(int numLinhas, int numColunas){
		return this.mat[numLinhas][numColunas];// o this é para referenciar atributos da classe
	}
	public void iniciarComNumero(int valor){
		int i, j;
		for (i = 0; i<this.getNumLinhas(); i++){
			for (j = 0; j<this.getNumColunas(); j++){
				this.setElemento(i,j,valor);// i,j,valor sao parametros
			}
		}
	}
	public void iniciaRandom(){
		Random aleatorio = new Random();
		int i, j;
		for(i = 0; i<this.getNumLinhas(); i++){
			for(j = 0; j<this.getNumColunas(); j++){
				this.setElemento(i,j,aleatorio.nextInt());
			}
		}
	}
	public void iniciaRandom(int parametro){// quando passa o parametro a classe Random da numeros entre 0 e o parametro
		Random aleatorio = new Random();
		int i, j;
		for (i = 0; i<this.getNumLinhas(); i++){
			for (j = 0; j<this.getNumColunas(); j++){
				this.setElemento(i,j,aleatorio.nextInt(parametro));
			}
		}
	}
	public void iniciaPelaMatriz(Matriz matOutra){
		int i, j;
		for (i = 0; i<this.getNumLinhas(); i++){
			for (j = 0; j<this.getNumColunas(); j++){
				this.setElemento(i,j,matOutra.getElemento(i,j));
			}
		}
	}
	public void copiaParaMatriz(Matriz matOutra){
		int i, j;
		for (i = 0; i<this.getNumLinhas(); i++){
			for (j = 0; j<this.getNumColunas(); j++){
				matOutra.setElemento(i,j,this.getElemento(i,j));
			}
		}
	}
	public void imprime(){
		int i,j, elemento = 0;
		for (i = 0; i<this.getNumLinhas(); i++){
			for (j = 0; j<this.getNumColunas(); j++){
				elemento = this.getElemento(i,j);
				if (elemento>(-10) && elemento<10){
					System.out.print("0"+elemento+" ");
				} else { System.out.print(elemento+" ");}
			}
			System.out.print("\n");
		}		
	}
	public void determinanteMatN(Matriz matMinha){
		int i,j, determinante = 0;
		for (i = 0;i < this.getNumLinhas(); i = i+2){
			for (j = 0;j <this.getNumColunas(); j= j+2){
				determinante = this.getElemento(i,j)+this.getElemento(i+1,j);
			}		
		}
	}
	

	public void cronometro(){
		float cont, somaDeterminante = 0, somaDetBasica = 0, somaDetPro = 0;
		long ti, tf;
		int det=0, detBasica=0, detPro=0;

		ti = System.nanoTime() ;
		det = this.determinante();
		tf = System.nanoTime();
		somaDeterminante += tf-ti;
		System.out.println ("Há 3 naves disponíveis para viajar, veja o Determinante de alvos e a demora p/ detectar:");
		System.out.println ("\nDeterminante sem otimização: "+det); 
		System.out.printf ("Nave sem otimização: %.2f nanosegundos", somaDeterminante);
		System.out.println (" ");

		ti = System.nanoTime() ;
		detBasica = this.detBasica();
		tf = System.nanoTime();
		somaDetBasica += tf-ti;	
		System.out.println ("\nDeterminante com otimização:"+detBasica); 
		System.out.printf ("Nave basica: %.2f nanosegundos", somaDetBasica);
		System.out.println (" ");

		ti = System.nanoTime() ;
		detPro = this.detPro();
		tf = System.nanoTime();
		somaDetPro += tf-ti;
		System.out.println ("\nDeterminante turbinada:"+detPro); 
		System.out.printf ("Nave Turbinada, exclusividade Jedi: %.2f nanosegundos", somaDetPro);
		System.out.println (" ");

		
	}


///////////////////////////////////////////DETERMINANTE DO SEM OTIMIZAÇÃO//////////////////////////////////////////////////////////
	
	// caso matriz nao quadrada, retorna -1
	public int retorneOrdem(){
		int numL, numC, ordem;
		numL = this.getNumLinhas();
		numC = this.getNumColunas();
		ordem = -1;
		if(numL == numC){
			ordem = numL;
		}
		return ordem;
	}	
	public int determinante(){
		int ordem,det;
		ordem = this.retorneOrdem();
		det = 0;
		if(ordem > 0){
			switch (ordem) {
			    case 1:  det = this.detOrdem1(this);
				     break;
			    case 2:  det = this.detOrdem2(this);;
				     break;
			    default: det = this.detOrdemN(this);;
				     break;
			}
		}
		else{
			System.out.println("Matriz nao eh quadrada!! retornando 0");
		}
		return det;
	}
	private int detOrdem1(Matriz mat){
		return mat.getElemento(0,0);
	}
	private int detOrdem2(Matriz mat){
		int diagonalP, diagonalI;
		diagonalP = mat.getElemento(0,0) * mat.getElemento(1,1);		
		diagonalI = mat.getElemento(1,0) * mat.getElemento(0,1);		
		return (diagonalP - diagonalI);
	}
	private int detOrdemN(Matriz mat){
		int sinal,cofator,detTemp,resposta,contC,numL,numC;
		Matriz matmenor;
		numL = this.getNumLinhas();
		numC = this.getNumColunas();
		resposta = 0;
		for(contC = 0; contC < mat.getNumColunas(); contC++){
			cofator = mat.getElemento(0,contC);
			sinal = this.calculaSinal(0,contC);
			matmenor = new Matriz(numL-1,numC-1);
			this.copiaMatrizMaiorParaMenor(mat,matmenor,0,contC);
			detTemp = matmenor.determinante();
			resposta = resposta + (cofator * sinal * detTemp);
		}
		return (resposta);
	}
	private int calculaSinal(int indiceL, int indiceC){
		int sinal;
		sinal = -1;
		if( ((indiceL + indiceC)% 2) == 0 ){
			sinal = 1;
		}
		return sinal;		
	}
	public void copiaMatrizMaiorParaMenor(Matriz maior,Matriz menor,int isqn,int jsqn){
		int contAi,contAj,contBi,contBj,temp,numL,numC;
		numL = menor.getNumLinhas();
		numC = menor.getNumColunas();
		contAi = 0;
		for(contBi = 0; contBi < numL; contBi++){
			if(contAi == isqn){
				contAi++;
			}
			contAj = 0;
			for(contBj = 0; contBj < numC; contBj++){
				if(contAj == jsqn){
					contAj++;
				}
				temp = maior.getElemento(contAi,contAj);
				menor.setElemento(contBi,contBj,temp);
				contAj++;
			}
			contAi++;
		}
	}

///////////////////////////////////////////DETERMINANTE COM OTIMIZAÇÃO BASICA//////////////////////////////////////////////////////
	
	public int[] linhaParaInicio(){
		int i,j, posicaoZeroI=0, linhaMenor=0, linhaZero=0;
		int[] zeros = new int[this.getNumLinhas()];
		for (i=0;i<this.getNumLinhas();i++){
			for (j=0;j<this.getNumColunas();j++){
				if (this.getElemento(i,j) == 0){
					zeros[i]++;//quantos ZEROS na linha	
				}
			}
		}
		return zeros;
	}
	public int[] colunaParaInicio(){
		int i,j, posicaoZeroJ=0, colunaMenor=0, colunaZero=0;
		int[] zeros = new int[this.getNumLinhas()];
		for (i=0;i<this.getNumLinhas();i++){
			for (j=0;j<this.getNumColunas();j++){
				if (this.getElemento(j,i) == 0){//EURECA
					zeros[i]++;//quantos ZEROS na coluna	
				}
			}
		}
		return zeros;
	}
	public int detBasica(){
		int ordem,det;
		ordem = this.retorneOrdem();
		det = 0;
		if(ordem > 0){
			switch (ordem) {
			    case 1:  det = this.detOrdem1(this);
				     break;
			    case 2:  det = this.detOrdem2(this);;
				     break;
			    default: det = this.detOrdemNBasica(this);;
				     break;
			}
		}
		else{
			System.out.println("Matriz nao eh quadrada!! retornando 0");
		}
		return det;
	}
	private int detOrdemNBasica(Matriz mat){
		int sinal,cofator,detTemp,resposta,contL,contC,numL,numC, maiorI=0, maiorJ=0, posicaoI=0, posicaoJ=0;
		int[] iZero = new int[this.getNumLinhas()];
		int[] jZero = new int[this.getNumColunas()];//iniciei posiçaõI com ZERO
		Matriz matmenor;
		numL = this.getNumLinhas();
		numC = this.getNumColunas();
	        iZero = this.linhaParaInicio();
	        jZero = this.colunaParaInicio();
		resposta = 0;
	        for (contL=0;contL<iZero.length;contL++){
	        	if (iZero[contL]>maiorI){
	        		maiorI = iZero[contL];
	        		posicaoI = contL;
	        	}
	        }
	        for (contC=0;contC<jZero.length;contC++){
	        	if (jZero[contC]>maiorJ){
	        		maiorJ = jZero[contC];
	        		posicaoJ = contC;
	        	}
	        }
	        if (maiorI>maiorJ){
			    for(contC = 0; contC < numL; contC++){
					cofator = mat.getElemento(posicaoI,contC);//inicia com o i indicado pelo cofator
					if (cofator==0){
						resposta = resposta;
					} else{
						sinal = this.calculaSinal(posicaoI,contC);
						matmenor = new Matriz(numL-1,numC-1);
						this.copiaMatrizMaiorParaMenor(mat,matmenor,posicaoI,contC);
						detTemp = matmenor.determinante();
				   		resposta = resposta + (cofator * sinal * detTemp);
				   	}
		   		}
      		}else{
  				for(contC = 0; contC < numC; contC++){
					cofator = mat.getElemento(contC,posicaoJ);//inicia com o j indicado pelo cofator
					if (cofator==0){
  						resposta = resposta;
  					} else{
						sinal = this.calculaSinal(contC,posicaoJ);
						matmenor = new Matriz(numL-1,numC-1);
						this.copiaMatrizMaiorParaMenor(mat,matmenor,contC,posicaoJ);
						detTemp = matmenor.determinante();
					    resposta = resposta + (cofator * sinal * detTemp);
					}
			  	}
        	}		
		return (resposta);
	}

/////////////////////////////////////////////////DETERMINANTE PRO//////////////////////////////////////////////////////////////////

	//detNula: https://mundoeducacao.bol.uol.com.br/matematica/propriedades-dos-determinantes.htm
	//https://www.somatematica.com.br/emedio/determinantes/determinantes4.php
	// matChio() eteorema de Chio: https://mundoeducacao.bol.uol.com.br/matematica/regra-chio-nos-calculos-dos-determinantes.htm
	// encontraUm() e propriedades das determinantes: http://www.igm.mat.br/aplicativos/index.php?option=com_content&view=article&id=148%3Apropriedadesdosdetermnantes&catid=41%3Aconteudosal&Itemid=38
	// forcaUm() e teorema de Jacobi: https://www.somatematica.com.br/emedio/determinantes/determinantes4.php
	//pegar regras de determinante e aplicar aqui para fazer uma determinante mais rapidamente
	
	public int detPro(){
		int ordem,det,i=0,j=0;
		ordem = this.retorneOrdem();
		det = 0;
		if(ordem > 0){
			switch (ordem) {
			    case 1:  det = this.detOrdem1(this);
				     break;
			    case 2:  det = this.detOrdem2(this);
				     break;
			    case 3: det = this.detOrdem3(this);
				    break;
			    default:{
					if (this.nulaLinhaColuna(this)||this.matTriangular(this)||this.matMultipla(this)) {
						det = 0;
					}else {						
						this.controleChio(this);//logica para colocar matChio nas Matriz
						det = this.detOrdemNBasica(this);;
					}
				     }
				     break;
			}
		}
		else{
			System.out.println("Matriz nao eh quadrada!! retornando 0");
		}
		return det;
	}
	private int detOrdem3(Matriz mat){
		int diagonalP, diagonalI;
		diagonalP = (mat.getElemento(0,0) * mat.getElemento(1,1) * mat.getElemento(2,2)) + (mat.getElemento(1,0) * mat.getElemento(2,1) * mat.getElemento(0,2)) + (mat.getElemento(2,0) * mat.getElemento(0,1) * mat.getElemento(1,2));		
		diagonalI = (mat.getElemento(2,0) * mat.getElemento(1,1) * mat.getElemento(0,2)) + (mat.getElemento(1,2) * mat.getElemento(2,1) * mat.getElemento(0,0)) + (mat.getElemento(2,2) * mat.getElemento(0,1) * mat.getElemento(1,0));		
		return (diagonalP - diagonalI);
	}
	public boolean nulaLinhaColuna(Matriz mat){
		int i=0,j=0, colunaNula=0, linhaNula=0;
		boolean detNula = false, valida = true, naoNula = true;
		while (i<this.getNumLinhas() && naoNula){	//&& pq se algum for falso ele sai	
			valida = true;			
			j=0;			
			while(j<this.getNumColunas() && valida){				
				if (this.getElemento(j,i) != 0){
					valida = false;
				} else {
					colunaNula++;
					if (colunaNula == this.getNumColunas()){ naoNula = false;}
				}
				j++;			
			}
			i++;
			colunaNula = 0;
		}
		while (i<this.getNumLinhas() && naoNula){		
			valida = true;			
			j=0;			
			while(j<this.getNumLinhas() && valida){				
				if (this.getElemento(i,j) != 0){
					valida = false;
				} else {
					linhaNula++;
					if (linhaNula == this.getNumLinhas()){ naoNula = false;}
				}
				j++;			
			}
			i++;
			linhaNula = 0;
		}
		detNula = !naoNula;		
		return detNula;
	}
	public boolean matMultipla(Matriz mat){
		int i= 0,j= 0,jPesquisa = 0, iMultipla = 0, jMultipla = 0, num = 0;
		int[]vetColuna = new int [this.getNumColunas()];
		int[]vetLinha = new int [this.getNumLinhas()];
		boolean detNula = false, valido = true;
		while (i<this.getNumLinhas() && !detNula){			
			while(j<this.getNumColunas() && valido && !detNula){
				vetLinha[j] = this.getElemento(i,j);
				if (vetLinha[j] != this.getElemento(i+1,j)){ valido = false;
				} else if (j == (1-getNumLinhas())) {detNula = true;}
				j++;
			}
			i++;
		}
		while (i<this.getNumLinhas() && !detNula){			
			while(j<this.getNumColunas() && valido && !detNula){
				vetColuna[j] = this.getElemento(j,i);
				if (vetColuna[j] % this.getElemento(j,i+1) == 0){ valido = false;
				} else if (j == (1-getNumColunas())) {detNula = true;}
				j++;
			}
			i++;
		}	
		return detNula;
	}
	public boolean matTriangular(Matriz mat){
		int i = 0,j = 0;
		boolean verifica = true, detNula = false;
		while (i<this.getNumLinhas() && verifica){	
			j=i+1;		
			while(j<this.getNumColunas() && verifica){
				if(this.getElemento(i,j) != 0){
					verifica = false; 
				}
				j++;
			}
			i++;
		}
		i=1;
		if(!verifica){
			verifica = true;
			while (i<this.getNumLinhas() && verifica){	
				j=0;		
				while(j<i && verifica){		
					if(this.getElemento(i,j) != 0){
						verifica = false; 
					}
					j++;		
				}
				i++;
			}
		}	
		if (verifica) {detNula = true;}
		return detNula;
	}

	//REGRA DE CHIÓ
	public void controleChio(Matriz mat){
		int cont=0;
		while (this.getElemento(0,0)==1 || cont>1) {
			this.matChio(this);
			if (this.encontraUm(this)){this.matChio(this);}
			else {this.forcaUm(this);this.encontraUm(this);}
			cont++;	
		}
	}
	public void matChio(Matriz mat){
		int i=0,j=0, elemento=0;// a matChio tem queser uma mat do tipo Matriz com todos os seus gets e sets
		Matriz matChio;		
		while (this.getElemento(0,0) == 1){			
			matChio = new Matriz(this.getNumLinhas()-1,this.getNumColunas()-1);			
			for (i=1; i<this.getNumLinhas(); i++){
				for (j=1; j<this.getNumColunas(); j++){//ele fala q a array não suporta, coisa assim
					elemento = (this.getElemento(i,j)-this.getElemento(0,j)*this.getElemento(i,0));
					matChio.setElemento(i-1,j-1,elemento);
				}
			}
			this.setNumLinhas(this.getNumLinhas()-1);
			this.setNumColunas(this.getNumColunas()-1);
			for (i=0; i<this.getNumLinhas(); i++){
					for (j=0; j<this.getNumColunas(); j++){
						this.setElemento(i,j,matChio.getElemento(i,j));
				}
			}
		} //quando sair do while transforme a this.Matriz igual a matChio que será mais eficiente. 
	}
	public boolean encontraUm(Matriz mat){
		int i=0,j=0,iP=0,jP=0,controle=0;
		int[] auxiliar;
		boolean achei = false;
		while (this.getElemento(0,0) != 1 && i<this.getNumLinhas()){
			while (this.getElemento(0,0) != 1 && j<this.getNumColunas()){
				if (this.getElemento(i,j) == 1){
					auxiliar = new int[this.getNumLinhas()];
					for (iP = 0;iP<this.getNumLinhas();iP++){
						auxiliar[iP] = this.getElemento(iP,j);
						this.setElemento(iP,j,this.getElemento(iP,0));
						this.setElemento(iP,0,auxiliar[iP]); 
						controle++;
					}
					if (this.getElemento(0,0) != 1){
						for (jP = 0;jP<this.getNumLinhas();jP++){
							auxiliar[jP] = this.getElemento(i,jP);
							this.setElemento(i,jP,this.getElemento(0,jP));
							this.setElemento(0,jP,auxiliar[jP]); 
							controle++;
						}
					}
					achei = true;
				}
				if(controle%2 != 0){//quando ter um numero "controle" impar de trocas o determinante tem q ser -1 
					for (iP = 0; iP<this.getNumLinhas();iP++){
						for(jP = 0; jP<this.getNumColunas();jP++){
							this.setElemento(iP,jP,(this.getElemento(iP,jP)*(-1))); 
						}
					}
				}
				j++;
			}
			i++;
		}
		return achei;
	}
	public boolean forcaUm(Matriz mat){
		int i=0,j=0,iP=0,jP=0,jControle=0, controle = 0;
		boolean achei = false;
		while (this.getElemento(0,0) != 1 && i<this.getNumLinhas()){
			while (this.getElemento(0,0) != 1 && j<this.getNumColunas()){
				controle = this.getElemento(i,j); 
				for (iP=0;iP<this.getNumColunas();iP++){
					for (jP=0;jP<this.getNumColunas();jP++){
						if (controle - (this.getElemento(iP,jP) * 2) == 1){
							for (jControle=0;jControle<this.getNumColunas();jControle++){
								controle = this.getElemento(i,jControle)-(this.getElemento(iP,jControle) * 2);
								this.setElemento(i,jControle,controle);
							}
							achei = true;
						}
					}
				}
				j++;
			}
			i++;
		}
		return achei;
	}
	public void fimEmotion(){
	
		System.out.printf("\n                                     /~/                          ");
		System.out.printf("\n                                    |oo )  Que a Força esteja	");
		System.out.printf("\n                                    _|=|__     com você!!	");
		System.out.printf("\n                    ___         #  // _   | 	");
		System.out.printf("\n                   / ()|         | /|[.]||| 	");
		System.out.printf("\n                 _|_____|_        V  [_] ||        ");
		System.out.printf("\n                | | === | |         |   |||	");
		System.out.printf("\n                |_|  O  |_|         |_ _|#		");
		System.out.printf("\n                 ||  O  ||          | | |	");
		System.out.printf("\n                 ||__*__||          | | |	");
		System.out.printf("\n                |~ [___] ~|         []|[]	");
		System.out.printf("\n                //  |=| //         | | | |    ");
		System.out.printf("\n________________[_]_[_]_[_]________[_]_[_]_________________________\n");
		//http://www.asciimation.co.nz/#
	}
}


/***************************************************SOLI DEO GLORIA**************************************************************/

