package ru.kdv.sliderPuzzle;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.List;

public class Solver {
    private SearchNode lastNode;
    private final MinPQ<SearchNode> priorityQueue;
    private boolean isSolvable;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null)
            throw new IllegalArgumentException();
        isSolvable = false;
        lastNode = null;
        priorityQueue = new MinPQ<>();
        priorityQueue.insert(new SearchNode(initial, 0, null));
        while (!isSolvable) {
            SearchNode currentNode = priorityQueue.delMin();
            Board board = currentNode.getBoard();
            if (board.isGoal()) {
                isSolvable = true;
                lastNode = currentNode;
                break;
            }
            if (board.hamming() == 2 && board.twin().isGoal()) {
                isSolvable = false;
                break;
            }
            int moves = currentNode.getMoves();
            Board previousBoard = moves > 0 ? currentNode.getPrevious().getBoard() : null;
            for (Board neighbor : board.neighbors()) {
                if (previousBoard != null && neighbor.equals(previousBoard)) {
                    continue;
                }
                priorityQueue.insert(new SearchNode(neighbor, currentNode.getMoves() + 1, currentNode));
            }

        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return isSolvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (!isSolvable) return -1;
        return lastNode.getMoves();
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (!isSolvable())
            return null;
        List<Board> solutionBoard = new ArrayList<>();
        solutionBoard.add(lastNode.getBoard());
        while (lastNode.getPrevious() != null) {
            solutionBoard.add(lastNode.getPrevious().getBoard());
            lastNode = lastNode.getPrevious();
        }
        return solutionBoard;
    }

    // test client (see below)
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

    private class SearchNode implements Comparable<SearchNode> {
        private final Board board;
        private final int moves;
        private final SearchNode previous;

        public SearchNode(Board board, int move, SearchNode previous) {
            this.board = board;
            this.moves = move;
            this.previous = previous;
        }

        @Override
        public int compareTo(SearchNode o) {
            return priority() - o.priority();
        }

        public int priority() {
            return board.manhattan() + moves;
        }

        public Board getBoard() {
            return board;
        }

        public int getMoves() {
            return moves;
        }

        public SearchNode getPrevious() {
            return previous;
        }
    }
}
