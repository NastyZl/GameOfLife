package game;

import java.util.Scanner;

public class Game {
    private final int width;
    private final int height;
    private final int[][] grid;

    public Game(int width, int height, int numAliveCells){
        this.width = width;
        this.height = height;
        this.grid = new int[width][height];
        initializeGrid(numAliveCells);
    }

    public static void main(String[] args) {
        System.out.println("Enter the grid dimensions separated by a space (width, height)");
        Scanner scanner = new Scanner(System.in);
        int width = scanner.nextInt();
        int height = scanner.nextInt();
        System.out.println("Enter the number of \"live\" cells");
        int numAliveCells = scanner.nextInt();
        Game game = new Game(width, height, numAliveCells);
        game.printGrid(game.grid);
        game.nextGeneration(game.grid, width, height);
    }

    private void initializeGrid(int numAliveCells) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the coordinates " + numAliveCells + " of the 'live' cells:");
        for (int i = 0; i < numAliveCells; i++) {
            System.out.print("Coordinates of the " + (i + 1) + " cells (row[0-" + (width - 1) + "] column[0-" + (height - 1) + "]): ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            if (isValidCell(row, col)) {
                grid[row][col] = 1;
            } else {
                System.out.println("Incorrect coordinates, try again.");
                i--;
            }
        }
        scanner.close();
    }

    private boolean isValidCell(int row, int col) {
        return row >= 0 && row < width && col >= 0 && col < height;
    }

    private void printGrid(int[][] grid) {
        System.out.println("Current generation:");
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (grid[i][j] == 0){
                    System.out.print(".");
                } else {
                    System.out.print("*");
                }
            }
            System.out.println();
        }
    }
    private void nextGeneration(int[][] grid, int width, int height) {
        int[][] future = new int[width][height];
        for (int l = 0; l < width; l++) {
            for (int m = 0; m < height; m++) {
                int aliveNeighbours = 0;
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        if ((l + i >= 0 && l + i < width) && (m + j >= 0 && m + j < height)) {
                            aliveNeighbours += grid[l + i][m + j];
                        }
                    }
                }
                aliveNeighbours -= grid[l][m];
                if ((grid[l][m] == 1) && (aliveNeighbours < 2)){
                    future[l][m] = 0;
                }  else if ((grid[l][m] == 1) && (aliveNeighbours > 3)){
                    future[l][m] = 0;
                } else if ((grid[l][m] == 0) && (aliveNeighbours == 3)){
                    future[l][m] = 1;
                } else {
                    future[l][m] = grid[l][m];
                }
            }
        }
        System.out.println("Next Generation");
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (future[i][j] == 0){
                    System.out.print(".");
                } else{
                    System.out.print("*");
                }
            }
            System.out.println();
        }
    }
}