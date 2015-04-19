package maze.cli;

import java.util.Scanner;

import maze.logic.*;

public class Client 
{
	public static void imprimeHeroi(Heroi heroi)
	{
		if (heroi.vivo)
			if(heroi.armado)
				System.out.print('A');
			else if(heroi.protegido)
				System.out.print('P');
			else
				System.out.print('H');
		else System.out.print('M');
	}
	
	public static void imprimeEspada()
	{
		System.out.print('E');
	}
	
	public static void imprimeDragao(Dragao dragao, Espada sword)
	{
		if(dragao.vivo)
			if (dragao.posicao.equals(sword.posicao))
				System.out.print('F');
			else if(dragao.dormir)
				System.out.print('d');
			else System.out.print('D');
	}
	
	public static void imprimeEscudo()
	{
		System.out.print('S');
	}
	
	public static void imprimeDardo()
	{
		System.out.print('»');
	}
	
	public static void desenhaLabirinto(Jogo jogo) 
	{
		for (int height = 0; height < jogo.dimensaoLabirinto; height++) 
		{
			for (int width = 0; width < jogo.dimensaoLabirinto; width++)
			{
				int drake = jogo.verificaDragao(width, height);
				int dardinho = jogo.verificaDardo(width, height);

				if (height == jogo.hero.cor.y && width == jogo.hero.cor.x )
					imprimeHeroi(jogo.hero);
				else if (drake != -1)
					imprimeDragao((jogo.dragoes.get(drake)), jogo.sword);
				else if(dardinho != -1)
					imprimeDardo();
				else if (height == jogo.sword.posicao.y && width == jogo.sword.posicao.x && !jogo.sword.recolhida)
					imprimeEspada();
				else if (height == jogo.shield.posicao.y && width == jogo.shield.posicao.x && !jogo.shield.recolhida)
					imprimeEscudo();
				else if(jogo.labirinto.Saida.equals(new Coordenada(width, height)))
					System.out.print(' ');
				else
					imprimeTabuleiro(height, width, jogo.labirinto);

				System.out.print(' ');

			}

			System.out.print('\n');
		}

		System.out.println("w - Mover para cima");
		System.out.println("a - Mover para a esquerda");
		System.out.println("s - Mover para baixo");
		System.out.println("d - Mover para a direita");

		if(jogo.hero.numDardos > 0)
			System.out.println("e - Disparar dardos");

	}
	
	public static boolean mensagemInicial(Jogo jogo)
	{
		jogo.dimensaoLabirinto = 0;
		
		@SuppressWarnings("resource")
		Scanner in2 = new Scanner(System.in);
		

		System.out.println("Deseja criar um labirinto? ^^ ");
		System.out.println(" y - sim ");
		System.out.println(" n - não");
		char res = in2.next().charAt(0);

		in2.reset();
		System.out.println("\nComo pretende que o Dragao se movimente:");
		System.out.println(" 1 - Dragao parado ");
		System.out.println(" 2 - Dragao com movimento aleatorio ");
		System.out.println(" 3 - Dragao com movimento aleatorio e possibilidade de adormecer");
		int temp = in2.nextInt();

		if(temp == 1)
			jogo.dragonMoves = 0;
		else if (temp == 2)
			jogo.dragonMoves = 5;
		else if (temp == 3)
			jogo.dragonMoves = 6;

		
		in2.reset();
		if(res == 'y') 
		{
			System.out.println("\nQuantos dragoes deseja enfrentar? Por definição, o jogo será iniciado com um Dragão.");
			temp = in2.nextInt();
			
			if(temp > Jogo.MAX_DRAGONS)
				jogo.numDragoes = Jogo.MAX_DRAGONS;
			else jogo.numDragoes = temp;

			while(jogo.dimensaoLabirinto == 0)
			{
				System.out.println("\nInsira a dimensao do labirinto (NxN) pretendida. Aconselha-se uma dimensao igual ou superior a 7!");
				System.out.println("\nBoa Sorte Sweet! ^^ ");
				@SuppressWarnings("resource")
				Scanner in = new Scanner(System.in);
				int n = in.nextInt();

				if (n >= 7)
					jogo.dimensaoLabirinto = n;
			}
			return true;
		}
		else  
		{
			System.out.println("\n\nBoa Sorte Sweet! ^^ ");
			return false;
		}
		
	}
	
	public static void mensagemFinal(Jogo jogo)
	{
		if (jogo.hero.vivo)
			if (jogo.dragoes.size() == 0)
				System.out.println("Terminou o jogo! Boa! Experimenta lutar tambem contra o dragao!");
			else System.out.println("Terminou o jogo abatendo tambem o dragao!!! Parabens! :)");
		else System.out.println("O Heroi foi abatido pelo dragao :( ...");
	}


	
	public static char direcaoDardo(Jogo jogo)
	{
		System.out.println("w - Disparar para cima");
		System.out.println("a - Disparar para a esquerda");
		System.out.println("s - Disparar para baixo");
		System.out.println("d - Disparar para a direita");
		System.out.println("e - Cancelar");

		return jogo.caraterPremido();
	}
	
	public static void imprimeTabuleiro(int height, int width, Labirinto labirinto)
	{
		if(height == labirinto.Saida.y && width == labirinto.Saida.x)
			System.out.print(' ');
		else System.out.print(labirinto.labirinto[height][width]);
	}
}
