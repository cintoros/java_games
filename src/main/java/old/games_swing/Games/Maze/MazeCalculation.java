package old.games_swing.Games.Maze;

import old.games_swing.GameLogic.ArrayCopy;
import java.util.Random;

/**
 * calculation of a new Labyrinth
 *
 * @author Elias
 */
public class MazeCalculation {

    private int xIndex, yIndex, lenght, height;

    /**
     * sets the variables
     */
    public MazeCalculation() {
        this.yIndex = 0;
        this.xIndex = 0;
    }

    /**
     * gives back an arry of boolenans true for there is an way, false means
     * there is an wall.
     *
     * @param lenght
     * @param height
     * @return gives back an new Maze
     */
    public boolean[][] newMaze(int lenght, int height) {
        boolean[][] mazeWands = new boolean[lenght][height];
        this.lenght = lenght;
        this.height = height;
        mazeWands = startPosition(ArrayCopy.clone2DArrayBoolean(mazeWands), 0, 0);
        int Zaehler = 0;
        while (!mazeWands[lenght - 1][height - 1]) {
            int a[] = getStartPoint(mazeWands);
            mazeWands = startPosition(ArrayCopy.clone2DArrayBoolean(mazeWands), a[0], a[1]);
            Zaehler++;
            if (Zaehler > lenght * height / 2) {
                addEndError(mazeWands);//adds the end point if non way exists ot it
                break;
            }
        }
        while (!enoughVariants(mazeWands)) {
            int a[] = getStartPoint(mazeWands);
            mazeWands = startPosition(ArrayCopy.clone2DArrayBoolean(mazeWands), a[0], a[1]);
            if (Zaehler > lenght * height / 2) {//if it is calculating to long stop it.
                break;
            }
        }
        return ArrayCopy.clone2DArrayBoolean(mazeWands);
    }

    private boolean[][] addEndError(boolean[][] mazeWands) {// connects the end point with an other path
        int startx = lenght - 1, starty = height - 1;
        while (true) {
            mazeWands[startx][starty] = true;
            starty--;
            if (mazeWands[startx][starty]) {
                break;
            }
        }
        return ArrayCopy.clone2DArrayBoolean(mazeWands);
    }

    private boolean[][] startPosition(boolean[][] mazeWands, int startx, int starty) {//calculates from an startpoint an way until it can no longer proceed then it gives the maze back
        int[] diagonal = new int[2];
        Random zufall = new Random();
        xIndex = startx;//startposition
        yIndex = starty;
        mazeWands[xIndex][yIndex] = true;
        for (int index = 0; index < (lenght * height); index++) {
            if (index % 10 == 0) {//alle 100 Schritte soll überprüft werden ob es noch weitergeht
                if (nextMove(0, mazeWands) == 0 && nextMove(1, mazeWands) == 0 && nextMove(2, mazeWands) == 0 && nextMove(3, mazeWands) == 0) {
                    break;
                }
            }//calculating
            int a = zufall.nextInt(4);
            int b = nextMove(a, mazeWands);
            if (diagonal[0] != a && b != 0) {
                diagonal[0] = diagonal[1];
                diagonal[1] = a;
                if (a <= 1) {
                    xIndex += b;
                } else {
                    yIndex += b;
                }
                int d[] = {xIndex, yIndex};
                mazeWands[xIndex][yIndex] = true;
            }
        }
        return ArrayCopy.clone2DArrayBoolean(mazeWands);
    }

    private int nextMove(int a, boolean[][] mazeWands) {//gives back if the next move is possible or not
        switch (a) {
            case 0: {//links
                if (xIndex > 0) {
                    if (mazeWands[xIndex - 1][yIndex]) {
                        break;
                    }
                    if (xIndex - 1 > 0) {
                        if (mazeWands[xIndex - 2][yIndex]) {
                            break;
                        }
                        if (yIndex > 0) {
                            if (mazeWands[xIndex - 2][yIndex - 1]) {
                                break;
                            }
                        }
                        if (yIndex < height - 1) {
                            if (mazeWands[xIndex - 2][yIndex + 1]) {
                                break;
                            }
                        }
                    }
                    if (yIndex > 0) {
                        if (mazeWands[xIndex - 1][yIndex - 1]) {
                            break;
                        }
                    }
                    if (yIndex < height - 1) {
                        if (mazeWands[xIndex - 1][yIndex + 1]) {
                            break;
                        }
                    }
                    return -1;
                }
                break;
            }
            case 1: {//rechts
                if (xIndex < lenght - 1) {
                    if (mazeWands[xIndex + 1][yIndex]) {
                        break;
                    }
                    if (xIndex + 1 < lenght - 1) {
                        if (mazeWands[xIndex + 2][yIndex]) {
                            break;
                        }
                        if (yIndex > 0) {
                            if (mazeWands[xIndex + 2][yIndex - 1]) {
                                break;
                            }
                        }
                        if (yIndex < height - 1) {
                            if (mazeWands[xIndex + 2][yIndex + 1]) {
                                break;
                            }
                        }
                    }
                    if (yIndex > 0) {
                        if (mazeWands[xIndex + 1][yIndex - 1]) {
                            break;
                        }
                    }
                    if (yIndex < height - 1) {
                        if (mazeWands[xIndex + 1][yIndex + 1]) {
                            break;
                        }
                    }
                    return +1;
                }
                break;
            }
            case 2: {//rauf
                if (yIndex > 0) {
                    if (mazeWands[xIndex][yIndex - 1]) {
                        break;
                    }
                    if (yIndex - 1 > 0) {
                        if (mazeWands[xIndex][yIndex - 2]) {
                            break;
                        }
                        if (xIndex > 0) {
                            if (mazeWands[xIndex - 1][yIndex - 2]) {
                                break;
                            }
                        }
                        if (xIndex < lenght - 1) {
                            if (mazeWands[xIndex + 1][yIndex - 2]) {
                                break;
                            }
                        }
                    }
                    if (xIndex > 0) {
                        if (mazeWands[xIndex - 1][yIndex - 1]) {
                            break;
                        }
                    }
                    if (xIndex < lenght - 1) {
                        if (mazeWands[xIndex + 1][yIndex - 1]) {
                            break;
                        }
                    }
                    return -1;
                }
                break;
            }
            case 3: {//runter
                if (yIndex < height - 1) {
                    if (mazeWands[xIndex][yIndex + 1]) {
                        break;
                    }
                    if (yIndex + 1 < height - 1) {
                        if (mazeWands[xIndex][yIndex + 2]) {
                            break;
                        }
                        if (xIndex > 0) {
                            if (mazeWands[xIndex - 1][yIndex + 2]) {
                                break;
                            }
                        }
                        if (xIndex < lenght - 1) {
                            if (mazeWands[xIndex + 1][yIndex + 2]) {
                                break;
                            }
                        }
                    }
                    if (xIndex > 0) {
                        if (mazeWands[xIndex - 1][yIndex + 1]) {
                            break;
                        }
                    }
                    if (xIndex < lenght - 1) {
                        if (mazeWands[xIndex + 1][yIndex + 1]) {;
                            break;
                        }
                    }
                    return +1;
                }
                break;
            }
        }
        return 0;
    }

    private int[] getStartPoint(boolean[][] maze) {//gets an point on the field that can be a startpoint of a new way
        Random zufall = new Random();
        while (true) {
            int a = zufall.nextInt(lenght);
            int b = zufall.nextInt(height);
            if (maze[a][b]) {
                int c[] = {a, b};
                return c;
            }
        }
    }

    private boolean enoughVariants(boolean[][] maze) {//gives back if there are enough possible ways to abuse the user
        int zaehler = 0;
        for (int i = 0; i < lenght; i++) {
            for (int index = 0; index < height; index++) {
                if (maze[i][index]) {
                    zaehler++;
                }
            }
        }
        return zaehler > lenght * height / 2;
    }
}
