package application;

import java.util.Locale;
import java.util.Scanner;

import boardgame.Position;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		Position pos = new Position(3, 5); // criando OO
		
		System.out.println(pos); //Imprimindo posição
	
		sc.close();
	}
}
