package old.maze_fx.maze.controller;

import shared.ArrayUtil;
import old.maze_fx.maze.models.Direction;
import java.awt.Point;
import java.util.Random;

/**
 * calculation of a new Labyrinth
 *
 * @author Elias
 */
public class MazeCalculation {

    private int lenght, height;
    private final Point indexPoint;

    /**
     * sets the variables
     */
    public MazeCalculation() {
        indexPoint = new Point();
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
        boolean[][] maze = new boolean[lenght][height];
        this.lenght = lenght;
        this.height = height;
        maze = startPosition(ArrayUtil.deepCopyBooleanMatrix(maze), new Point());
        int fsNum = lenght * height / 2;
        for (int count = 0; !enoughVariants(maze) || !maze[lenght - 1][height - 1]; count++) {
            Point p = getStartPoint(maze);
            maze = startPosition(ArrayUtil.deepCopyBooleanMatrix(maze), p);
            if (count > fsNum) {//if it is calculating to long stop it.
                addEndError(maze);
                break;
            }
        }
        return ArrayUtil.deepCopyBooleanMatrix(maze);
    }

    /**
     * adds the end point if non way exists to it and connects the end point
     * with an other path
     *
     * @param mazeWands the maze
     * @return the maze
     */
    private boolean[][] addEndError(boolean[][] mazeWands) {
        int startx = lenght - 1, starty = height - 1;
        while (!mazeWands[startx][starty]) {
            mazeWands[startx][starty] = true;
            starty--;
        }
        return ArrayUtil.deepCopyBooleanMatrix(mazeWands);
    }

    /**
     * calculates from an startpoint an way until it can no longer proceed then
     * it gives the maze back
     *
     * @param mazeWands the maze
     * @param startPoint the startpoint
     * @return the maze
     */
    private boolean[][] startPosition(boolean[][] mazeWands, Point startPoint) {
        int[] diagonal = new int[2];
        Random zufall = new Random();
        indexPoint.setLocation(startPoint);
        mazeWands[indexPoint.x][indexPoint.y] = true;
        for (int index = 0; index < (lenght * height); index++) {
            if (index % 10 == 0) {//alle 100 Schritte soll überprüft werden ob es noch weitergeht
                if (isNextMoveNotPos(mazeWands)) {
                    break;
                }
            }//calculating
            int a = zufall.nextInt(4);
            int b = nextMove(toDir(a), mazeWands);
            if (diagonal[0] != a && b != 0) {
                diagonal[0] = diagonal[1];
                diagonal[1] = a;
                if (a <= 1) {
                    indexPoint.x += b;
                } else {
                    indexPoint.y += b;
                }
                mazeWands[indexPoint.x][indexPoint.y] = true;
            }
        }
        return ArrayUtil.deepCopyBooleanMatrix(mazeWands);
    }

    private Direction toDir(int i) {
        if (i > 3) {
            i = i - 4;
        }
        switch (i) {
            case 0: {
                return Direction.Left;
            }
            case 1: {
                return Direction.Right;
            }
            case 2: {
                return Direction.Up;
            }
            case 3: {
                return Direction.Down;
            }
            default: {
                return Direction.Left;
            }
        }
    }

    private boolean isNextMoveNotPos(boolean[][] maze) {
        return (nextMove(Direction.Left, maze) == 0 && nextMove(Direction.Down, maze) == 0 && nextMove(Direction.Right, maze) == 0 && nextMove(Direction.Up, maze) == 0);
    }

    private int nextMove(Direction d, boolean[][] mazeWands) {//gives back if the next move is possible or not
        int xIndex = indexPoint.x;
        int yIndex = indexPoint.y;
        switch (d) {
            case Left: {//links
                if (xIndex > 0) {
                    if (!mazeWands[xIndex - 1][yIndex]) {
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
                }
                break;
            }
            case Right: {//rechts
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
            case Up: {//rauf
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
            case Down: {//runter
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

    private int switchLeft(boolean[][] mazeWands) {
        int xIndex = indexPoint.x;
        int yIndex = indexPoint.y;
        while (true) {
            if (xIndex > 0) {
                if (!mazeWands[xIndex - 1][yIndex]) {
                    if (xIndex - 1 > 0) {
//                        boolean[] b = mazeWands[xIndex - 2];
//                        if (b[yIndex]) {
//                            break;
//                        }
//                        if (yIndex > 0) {
//                            if (b[yIndex - 1]) {
//                                break;
//                            }
//                        }
//                        if (yIndex < height - 1) {
//                            if (b[yIndex + 1]) {
//                                break;
//                            }
//                        }
                        int max = getMax(yIndex, height, 1);
//                        int min=getMin
                        

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
            }
            break;
        }
        return 0;
    }

    private int getMax(int index, int maxGre, int maxDif) {
        int i;
        for (i = 0; i < maxDif; i++) {
            if (index >= maxGre - 1) {
                return i;
            }
        }
        return i;
    }

    private int getMin() {
        return 0;
    }

    private boolean isPos(int yStar, int yEnd, boolean[] b) {
        for (int i = yStar - 1; i < yEnd; i++) {
            if (b[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * gives back an random startpoint (way) from where an new way can be
     * generated
     *
     * @param maze the maze to select a point from
     * @return
     */
    private Point getStartPoint(boolean[][] maze) {
        Random zufall = new Random();
        Point p = new Point();
        while (true) {
            p.x = zufall.nextInt(lenght);
            p.y = zufall.nextInt(height);
            if (maze[p.x][p.y]) {
                return p;
            }
        }
    }

    /**
     * gives back if there are enough possible ways for the maze
     *
     * @param maze the maze
     * @return if there are enough(true) else (false)
     */
    private boolean enoughVariants(boolean[][] maze) {
        int count = 0;
        for (int xIndex = 0; xIndex < lenght; xIndex++) {
            for (int yIndex = 0; yIndex < height; yIndex++) {
                if (maze[xIndex][yIndex]) {
                    count++;
                }
            }
        }
        return count > (lenght * height / 2);
    }
}
