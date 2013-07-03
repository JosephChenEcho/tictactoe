package com.JC.tictactoe;

import java.io.IOException;

public class mainmenu {
	private static int ticgrid[][];
	private final static int PLAYER1 = 1;
	private final static int PLAYER2 = 0;
	private static int CURRENTID;
	private static byte buffer[];
	private final static int MAX = 512;
	private static int N = 3;
	
	public static void main(String[] args) throws IOException{
		System.out.println("Welcome to TicTacToe");
		System.out.println("Please input N: ");
				
		buffer = new byte[MAX];		
		System.in.read(buffer);		
		N = Integer.parseInt(String.valueOf((char)buffer[0]));
		initgrid();
		
		while (!checkresult() && !checkdraw()){
			for(int i = 0;i<N;i++){
				for(int j = 0;j<N;j++){
					if (ticgrid[i][j] == -1){
						System.out.print(' ');
					}
					else{
						System.out.print(ticgrid[i][j]);
					}					
					System.out.print('|');
				}
				System.out.println();				
			}
			if(CURRENTID == 1){
				System.out.println("Player1 move :");
				selectgrid();
				CURRENTID = 0;
			}
			else{
				System.out.println("Player2 move :");
				selectgrid();
				CURRENTID = 1;
			}			
		}
		
		if (CURRENTID == 1){
			System.out.println("Winner is Player2");
		}
		else{
			System.out.println("Winner is Player1");
		}
		
		
	}
	
	private static void initgrid(){
		ticgrid = new int[N][N];
		CURRENTID = 1;
		
		for (int i = 0;i<N;i++){
			for (int j = 0;j<N;j++){
				ticgrid[i][j] = -1;
			}
		}
	}
	
	private static boolean checkresult(){
		boolean result = true;
/*		for(int i = 0;i<3;i++){
			if (ticgrid[0][i] == ticgrid[1][i] && ticgrid[1][i] == ticgrid[2][i] && ticgrid[0][i] != -1){
				
				result = true;
				break;
			}
			if (ticgrid[i][0] == ticgrid[i][1] && ticgrid[i][1] == ticgrid[i][2] && ticgrid[i][0] != -1){
				
				result = true;
				break;
			}			
		}
		
		if (ticgrid[0][0] == ticgrid[1][1] && ticgrid[1][1]== ticgrid[2][2] && ticgrid[0][0] != -1){
			result = true;			
		}
		
		if (ticgrid[0][2] == ticgrid[1][1] && ticgrid[1][1]==ticgrid[2][0] && ticgrid[0][2] != -1){
			result = true;
		}	*/	
		
		result = true;
		for (int i = 0;i<N-1;i++){
			if (ticgrid[0][i] != ticgrid[0][i+1] || ticgrid[0][i] == -1){
				result = false;
				break;
			}				
		}
		if (result){
			return result;
		}
		result = true;
		for (int i=0;i<N-1;i++){
			if (ticgrid[i][0] != ticgrid[i+1][0] || ticgrid[i][0] == -1){
				result = false;
				break;
			}	
		}
		if (result){
			return result;
		}
		result = true;
		for (int i=0;i<N-1;i++){
			if (ticgrid[i][i] != ticgrid[i+1][i+1] || ticgrid[i][i] == -1){
				result = false;
				break;
			}	
		}
		result = true;
		for (int i=0;i<N-1;i++){
			if (ticgrid[i][N-1-i] != ticgrid[i+1][N-2-i] || ticgrid[i][N-1-i] == -1){
				result = false;
				break;
			}	
		}		
		return result;
	}
	
	private static void selectgrid() throws IOException{
		int x;
		int y;
		char count;		
		do{
			System.out.println("Select x:");
			buffer = new byte[MAX];		
			System.in.read(buffer);
			count = (char)buffer[0];
			x = Integer.parseInt(String.valueOf(count));
			
			char debut = (char)buffer[0];
			
			System.out.println("Select y:");
			buffer = new byte[MAX];
			System.in.read(buffer);
			count = (char)buffer[0];
			y = Integer.parseInt(String.valueOf(count));
			
		}
		while (!occupy(x,y));
		ticgrid[y][x] = CURRENTID;
	}
	
	private static boolean occupy(int x, int y){
		boolean result = true;
		if (x<0)
			x=0;
		if (y<0)
			y=0;
		
		if (x>=N || y>=N){
			System.out.println("Over Bound!");
			return false;			
		}
		
		if (ticgrid[y][x] != -1){
			System.out.println("Already Choosen!");
			result = false;		
		}
		
		return result;
		
	}
	
	private static boolean checkdraw(){
		for(int i =0;i<N;i++){
			for(int j=0;j<N;j++){
				if(ticgrid[i][j] == -1){
					return false;
				}
			}
		}
		System.out.println("DRAW");
		return true;
	}
}
