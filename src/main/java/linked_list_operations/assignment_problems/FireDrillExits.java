// The Fire Drill Exits
// A building floor plan is represented as a 2D grid of rooms.
// Each row of rooms is modeled as a doubly linked list.
// All rows are connected vertically via a column of sentinel (dummy) nodes.
// Fire drill exits are marked at specific positions; during a drill,
// the system must:
//   1. Mark certain rooms as exits using their (row, col) positions.
//   2. Find and print the shortest exit from a given starting room (row, col)
//      by scanning horizontally (left and right) in that row.
//   3. Print all exits in the grid row by row.
//
// The exit with the smallest column distance from the start is considered closest.
// If two exits are equidistant, prefer the one to the left.

public class FireDrillExits {

    static class Room {
        int row;
        int col;
        boolean isExit;
        Room left;   // doubly linked list in the row
        Room right;

        Room(int row, int col) {
            this.row = row;
            this.col = col;
            this.isExit = false;
            this.left = null;
            this.right = null;
        }
    }

    private int rows;
    private int cols;
    private Room[][] grid;
    private Room[] rowHeads; // head of each row's doubly linked list

    public FireDrillExits(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new Room[rows][cols];
        this.rowHeads = new Room[rows];

        // Build grid: each row is a doubly linked list
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                grid[r][c] = new Room(r, c);
                if (c > 0) {
                    grid[r][c].left = grid[r][c - 1];
                    grid[r][c - 1].right = grid[r][c];
                }
            }
            rowHeads[r] = grid[r][0];
        }
    }

    // Mark a room as an exit
    public void markExit(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            System.out.println("Invalid position: (" + row + ", " + col + ")");
            return;
        }
        grid[row][col].isExit = true;
        System.out.println("Exit marked at Room (" + row + ", " + col + ")");
    }

    // Find the nearest exit in the same row as the starting room.
    // Scan simultaneously left and right using two pointers.
    // Return the room with the smallest column distance; prefer left on a tie.
    public Room findNearestExit(int startRow, int startCol) {
        if (startRow < 0 || startRow >= rows || startCol < 0 || startCol >= cols) {
            System.out.println("Invalid start position.");
            return null;
        }

        Room start = grid[startRow][startCol];

        // If the start itself is an exit, return it
        if (start.isExit) {
            return start;
        }

        Room leftPtr = start.left;
        Room rightPtr = start.right;
        int leftDist = 1;
        int rightDist = 1;

        // Scan outward simultaneously
        while (leftPtr != null || rightPtr != null) {
            // Check left first (prefer left on tie)
            if (leftPtr != null) {
                if (leftPtr.isExit) {
                    return leftPtr;
                }
                leftPtr = leftPtr.left;
                leftDist++;
            }
            if (rightPtr != null) {
                if (rightPtr.isExit) {
                    return rightPtr;
                }
                rightPtr = rightPtr.right;
                rightDist++;
            }
        }

        return null; // no exit found in this row
    }

    // Print all exits in the grid row by row
    public void printAllExits() {
        System.out.println("All Fire Drill Exits:");
        boolean anyExit = false;
        for (int r = 0; r < rows; r++) {
            Room current = rowHeads[r];
            while (current != null) {
                if (current.isExit) {
                    System.out.println("  Exit at Room (" + current.row + ", " + current.col + ")");
                    anyExit = true;
                }
                current = current.right;
            }
        }
        if (!anyExit) {
            System.out.println("  No exits marked.");
        }
    }

    public static void main(String[] args) {
        // Build a 3x5 building grid (3 floors, 5 rooms per floor)
        FireDrillExits building = new FireDrillExits(3, 5);

        // Mark exits
        building.markExit(0, 0); // Row 0, leftmost room
        building.markExit(0, 4); // Row 0, rightmost room
        building.markExit(1, 2); // Row 1, middle
        building.markExit(2, 1); // Row 2

        System.out.println();
        building.printAllExits();

        System.out.println();

        // Find nearest exit from various starting positions
        int[] startRows = {0, 1, 2};
        int[] startCols = {2, 3, 3};
        for (int i = 0; i < startRows.length; i++) {
            int sr = startRows[i], sc = startCols[i];
            Room exit = building.findNearestExit(sr, sc);
            System.out.print("Nearest exit from Room (" + sr + ", " + sc + "): ");
            if (exit != null) {
                System.out.println("Room (" + exit.row + ", " + exit.col + ")");
            } else {
                System.out.println("No exit found in this row.");
            }
        }
    }
}
