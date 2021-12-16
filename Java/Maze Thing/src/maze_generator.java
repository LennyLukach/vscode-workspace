// Jisung Lee
// 12/14/21
// 12/15/21
// Generate maze given dimensions and solve it
// Honors Data Structures and Algorithms

import java.util.*;

public class maze_generator {
   private int dimensionX, dimensionY; // dimension of maze
   private int gridDimensionX, gridDimensionY; // dimension of output grid
   private char[][] grid; // output grid
   private Cell[][] cells; // 2d array of Cells
   private Random random = new Random(); // The random object

   /**
    * Initialize a maze with same x and y
    * @param aDimension - dimension
    */
   public maze_generator(int aDimension) { this(aDimension, aDimension); }

   /**
    * Generate maze given x and y
    * @param xDimension - x dimension
    * @param yDimension - y dimension
    */
   public maze_generator(int xDimension, int yDimension) {
      dimensionX = xDimension;
      dimensionY = yDimension;
      gridDimensionX = xDimension * 4 + 1;
      gridDimensionY = yDimension * 2 + 1;
      grid = new char[gridDimensionX][gridDimensionY];
      init();
      generateMaze();
   }

   /**
    * Initialize 2D as "maze"
    */
   private void init() {
      cells = new Cell[dimensionX][dimensionY];
      for (int x = 0; x < dimensionX; x++)
         for (int y = 0; y < dimensionY; y++)
            cells[x][y] = new Cell(x, y, false);
   }

   /**
    * Private class to represent a Cell in the 2D Array
    */
   private class Cell {
      // PROPERTIES
      // Coordinates
      int x, y;
      // Cells connected to current cell
      ArrayList<Cell> neighbors = new ArrayList<>();
      // Impassable cell (basically checking if it's a wall)
      boolean wall = true;
      // Cell is not taken by another cell
      boolean open = true;

      // SOLVING PROPERTIES
      // If already visited in algorithm
      boolean visited = false;
      // Previous cell
      Cell parent = null;
      // If used in last attempt (in algorithm)
      boolean inPath = false;
      // Distance travelled this far to get to cell
      double travelled;
      // Projected distance to end
      double projectedDist;

      /**
       * Construct cell with default isWall true
       * @param x - x coordinate
       * @param y - y coordinate
       */
      Cell(int x, int y) {
         this(x, y, true);
      }

      /**
       * Consturct cell with specified isWall
       * @param x - x coordinate
       * @param y - y coordinate
       * @param isWall - is it passable (a wall or not)
       */
      Cell(int x, int y, boolean isWall) {
         this.x = x;
         this.y = y;
         this.wall = isWall;
      }

      /**
       * Initialize neighbors of cell + other cells linked to it
       * @param other - Cell to compare to current cell
       */
      void addNeighbor(Cell other) {
         if (!this.neighbors.contains(other)) this.neighbors.add(other);
         if (!other.neighbors.contains(this)) other.neighbors.add(this);
      }

      /**
       * Used in this.updateGrid()
       * @return {@link Boolean} If Cell neighbors contains the cell below the neighbor
       */
      boolean isCellBelowNeighbor() {
         return this.neighbors.contains(new Cell(this.x, this.y + 1));
      }

      /**
       * Used in this.updateGrid()
       * @return {@link Boolean} If Cell neighbors contains the cell right of the neighbor
       */
      boolean isCellRightNeighbor() {
         return this.neighbors.contains(new Cell(this.x + 1, this.y));
      }

      /**
       * String representation
       */
      @Override
      public String toString() {
         return String.format("Cell(%s, %s)", x, y);
      }

      /**
       * Cell eqaulizer + check equality of Cells
       */
      @Override
      public boolean equals(Object other) {
         if (!(other instanceof Cell))
            return false;
         Cell otherCell = (Cell) other;
         return (this.x == otherCell.x && this.y == otherCell.y);
      }

      /**
       * Return a useful hash code designed to be unique (this.eqauls())
       */
      @Override
      public int hashCode() {
         return this.x + this.y * 256;
      }
   }

   /**
    * Generate maze from coordinates (0, 0) (Top Left)
    */
   private void generateMaze() {
      generateMaze(0, 0);
   }

   /**
    * Generate maze from coordinates x, y
    * @param x - x coordinate
    * @param y - y coordinate
    */
   private void generateMaze(int x, int y) {
      generateMaze(getCell(x, y));
   }

   /**
    * Generate maze in 2D Array
    * @param startAt - Param for cell to start generation
    */
   private void generateMaze(Cell startAt) {
      if (startAt == null) return;
      startAt.open = false; // indicate cell closed for generation
      ArrayList<Cell> cells = new ArrayList<>();
      cells.add(startAt);

      while (!cells.isEmpty()) {
         // Temporary Placeholder Cell
         Cell cell;

         // this is to reduce but not completely eliminate the number of long twisting halls with short easy to detect branches which results in easy mazes
         if (random.nextInt(10) == 0) cell = cells.remove(random.nextInt(cells.size()));
         else cell = cells.remove(cells.size() - 1);

         // for collection
         ArrayList<Cell> neighbors = new ArrayList<>();

         // cells that could potentially be neighbors
         Cell[] potentialNeighbors = new Cell[] { getCell(cell.x + 1, cell.y), getCell(cell.x, cell.y + 1), getCell(cell.x - 1, cell.y), getCell(cell.x, cell.y - 1) };

         for (Cell other : potentialNeighbors) {
            // skip if outside, is a wall or is not opened
            if (other == null || other.wall || !other.open) continue;
            neighbors.add(other);
         }
         if (neighbors.isEmpty()) continue;
         // get random cell
         Cell selected = neighbors.get(random.nextInt(neighbors.size()));
         // add as neighbor
         selected.open = false; // indicate cell closed for generation
         cell.addNeighbor(selected);
         cells.add(cell);
         cells.add(selected);
      }
   }

   // used to get a Cell at x, y; returns null out of bounds
   /**
    * getFunction to get a Cell at (x, y) in matrix
    * @param x - x coordinate
    * @param y - y coordinate
    * @return {@link Cell} If bounds in range or {@link NullPointerException} if out of bounds
    */
   public Cell getCell(int x, int y) {
      try {
         return cells[x][y];
      } catch (ArrayIndexOutOfBoundsException e) {
         return null;
      }
   }

   /**
    * Deafult soling from top left cell to bottom right cell
    */
   public void solve() { this.solve(0, 0, dimensionX - 1, dimensionY - 1); }

   /**
    * Specified bounds
    */
   public void solve(int startX, int startY, int endX, int endY) {
      // re initialize cells for path finding
      for (Cell[] cellrow : this.cells) {
         for (Cell cell : cellrow) {
            cell.parent = null;
            cell.visited = false;
            cell.inPath = false;
            cell.travelled = 0;
            cell.projectedDist = -1;
         }
      }
      // cells still being considered
      ArrayList<Cell> openCells = new ArrayList<>();
      // cell being considered
      Cell endCell = getCell(endX, endY);
      if (endCell == null) return; // quit if end out of bounds
      { // anonymous block to delete start, because not used later
         Cell start = getCell(startX, startY);
         if (start == null) return; // quit if start out of bounds
         start.projectedDist = getProjectedDistance(start, 0, endCell);
         start.visited = true;
         openCells.add(start);
      }

      boolean solving = true;
      while (solving) {
         if (openCells.isEmpty()) return; // quit, no path

         // sort openCells according to least projected distance
         Collections.sort(openCells, new Comparator<Cell>() {
            @Override
            public int compare(Cell cell1, Cell cell2) {
               double diff = cell1.projectedDist - cell2.projectedDist;
               if (diff > 0)
                  return 1;
               else if (diff < 0)
                  return -1;
               else
                  return 0;
            }
         });

         Cell current = openCells.remove(0); // pop cell least projectedDist
         if (current == endCell) break; // at end

         for (Cell neighbor : current.neighbors) {
            double projDist = getProjectedDistance(neighbor, current.travelled + 1, endCell);
            if (!neighbor.visited || // not visited yet
                  projDist < neighbor.projectedDist) { // better path
               neighbor.parent = current;
               neighbor.visited = true;
               neighbor.projectedDist = projDist;
               neighbor.travelled = current.travelled + 1;
               if (!openCells.contains(neighbor))
                  openCells.add(neighbor);
            }
         }
      }

      // create path from end to beginning
      Cell backtracking = endCell;
      backtracking.inPath = true;
      while (backtracking.parent != null) {
         backtracking = backtracking.parent;
         backtracking.inPath = true;
      }
   }

   /**
    * Get projected distance of cell to end cell
    * @param current - Current cell
    * @param travelled - Travelled distance
    * @param end - End cell
    * @return {@link Integer} Projected Distance between cells
    */
   public double getProjectedDistance(Cell current, double travelled, Cell end) {
      return travelled + Math.abs(current.x - end.x) + Math.abs(current.y - current.x);
   }

   /**
    * Draw the maze
    */
   public void updateGrid() {
      char backChar = '⬜', wallChar = '⬛', cellChar = '⬜', pathChar = '⭐';
      // fill background
      for (int x = 0; x < gridDimensionX; x++) {
         for (int y = 0; y < gridDimensionY; y++) {
            grid[x][y] = backChar;
         }
      }
      // build walls
      for (int x = 0; x < gridDimensionX; x++) {
         for (int y = 0; y < gridDimensionY; y++) {
            if (x % 4 == 0 || y % 2 == 0)
               grid[x][y] = wallChar;
         }
      }
      // individual cell representation
      for (int x = 0; x < dimensionX; x++) {
         for (int y = 0; y < dimensionY; y++) {
            Cell current = getCell(x, y);
            int gridX = x * 4 + 2, gridY = y * 2 + 1;
            if (current.inPath) {
               grid[gridX][gridY] = pathChar;
               if (current.isCellBelowNeighbor())
                  if (getCell(x, y + 1).inPath) {
                     grid[gridX][gridY + 1] = pathChar;
                     grid[gridX + 1][gridY + 1] = backChar;
                     grid[gridX - 1][gridY + 1] = backChar;
                  } else {
                     grid[gridX][gridY + 1] = cellChar;
                     grid[gridX + 1][gridY + 1] = backChar;
                     grid[gridX - 1][gridY + 1] = backChar;
                  }
               if (current.isCellRightNeighbor())
                  if (getCell(x + 1, y).inPath) {
                     grid[gridX + 2][gridY] = pathChar;
                     grid[gridX + 1][gridY] = pathChar;
                     grid[gridX + 3][gridY] = pathChar;
                  } else {
                     grid[gridX + 2][gridY] = cellChar;
                     grid[gridX + 1][gridY] = cellChar;
                     grid[gridX + 3][gridY] = cellChar;
                  }
            } else {
               grid[gridX][gridY] = cellChar;
               if (current.isCellBelowNeighbor()) {
                  grid[gridX][gridY + 1] = cellChar;
                  grid[gridX + 1][gridY + 1] = backChar;
                  grid[gridX - 1][gridY + 1] = backChar;
               }
               if (current.isCellRightNeighbor()) {
                  grid[gridX + 2][gridY] = cellChar;
                  grid[gridX + 1][gridY] = cellChar;
                  grid[gridX + 3][gridY] = cellChar;
               }
            }
         }
      }
   }

   /**
    * Printing String representation
    */
   public void draw() { System.out.print(this); }

   /**
    * String representation
    * @return {@link String} String representation of grid
    */
   @Override
   public String toString() {
      updateGrid();
      String output = "";
      for (int y = 0; y < gridDimensionY; y++) {
         for (int x = 0; x < gridDimensionX; x++) {
            if (x == gridDimensionX - 2 && y == gridDimensionY - 2) output += "🌀";
            else if (x == 1 && y == 1) output += "📍";
            else output += grid[x][y];
         }
         output += "\n";
      }
      return output;
   }

   /**
    * Main Function
    * @param args
    */
   public static void main(String[] args) {
      // Initialize input
      Scanner in = new Scanner(System.in);

      // Input procedure
      System.out.println("What are the dimensions for your maze?");
      int dimension = in.nextInt();

      // New maze
      maze_generator maze = new maze_generator(dimension);

      // Directions
      System.out.println(
            "Welcome to Maze Generator! The maze will randomly generate (with a guranteed solution) a maze that starts at the bottom right corner to the top left corner! Do your best and good luck!");
      System.out.println(
            "BTW. The start is marked with a blue circle/cyclone, while the end is marked with a pin.\n\n");

      // Solution Predestined
      System.out.println("Would you like the maze to automatically show a solution? (respond with 'yes')");
      String verify = in.nextLine();
      if (verify.equals("yes")) maze.solve();

      // Print maze
      System.out.println("Here is your maze!\n");
      maze.draw();

      // Rdy for solution if not predestined
      if (!verify.equals("yes")) {
         System.out.println("\n\nReady for the solution? (respond with 'yes')");
         String solution = in.nextLine();
         if (solution.equals("yes")) {
            maze.solve();
            maze.draw();
         }
      }

      // Close Scanner
      in.close();
   }
}