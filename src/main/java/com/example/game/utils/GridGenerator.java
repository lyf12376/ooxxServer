package com.example.game.utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GridGenerator {

    private static final Random random = new Random();

    public static char[][] generate(int size) {
        char[][] grid = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = '.';
            }
        }
        if (solve(grid, 0, 0)) {
            return grid;
        } else {
            return null;
        }
    }

    private static boolean solve(char[][] grid, int row, int col) {
        int size = grid.length;
        if (row == size) {
            return isUnique(grid);
        }
        if (col == size) {
            return solve(grid, row + 1, 0);
        }

        List<Character> chars = new ArrayList<>();
        chars.add('X');
        chars.add('O');
        Collections.shuffle(chars, random); // 随机打乱字符顺序

        for (char ch : chars) {
            if (isValid(grid, row, col, ch)) {
                grid[row][col] = ch;
                if (solve(grid, row, col + 1)) {
                    return true;
                }
                grid[row][col] = '.';
            }
        }
        return false;
    }

    private static boolean isValid(char[][] grid, int row, int col, char ch) {
        int size = grid.length;
        // Check row
        if (col >= 2 && grid[row][col - 1] == ch && grid[row][col - 2] == ch) {
            return false;
        }
        // Check column
        if (row >= 2 && grid[row - 1][col] == ch && grid[row - 2][col] == ch) {
            return false;
        }
        // Check row count
        int rowCount = 0;
        for (int i = 0; i < size; i++) {
            if (grid[row][i] == ch) {
                rowCount++;
            }
        }
        if (rowCount >= 3) {
            return false;
        }
        // Check column count
        int colCount = 0;
        for (int i = 0; i < size; i++) {
            if (grid[i][col] == ch) {
                colCount++;
            }
        }
        if (colCount >= 3) {
            return false;
        }
        return true;
    }

    private static boolean isUnique(char[][] grid) {
        int size = grid.length;
        List<String> rows = new ArrayList<>();
        List<String> cols = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            StringBuilder row = new StringBuilder();
            StringBuilder col = new StringBuilder();
            for (int j = 0; j < size; j++) {
                row.append(grid[i][j]);
                col.append(grid[j][i]);
            }
            rows.add(row.toString());
            cols.add(col.toString());
        }
        return rows.size() == new ArrayList<>(rows).size() && cols.size() == new ArrayList<>(cols).size();
    }

    public static void printGrid(char[][] grid) {
        if (grid != null) {
            for (char[] row : grid) {
                for (char cell : row) {
                    System.out.print(cell + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("No solution found");
        }
    }

    public static String generateGrid() {
        int size = (int) (4 + Math.random() * 4); // 随机生成4到7之间的整数
        while (true) {
            char[][] grid = generate(size);
            if (grid != null) {
                StringBuilder sb = new StringBuilder();
                for (char[] row : grid) {
                    for (char cell : row) {
                        sb.append(cell);
                    }
                }
                return sb.toString();
            }
        }
    }
}
