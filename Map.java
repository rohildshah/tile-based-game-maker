import java.util.ArrayList;

public class Map {

    static ArrayList<ArrayList<String>> obstacles = new ArrayList<>(new ArrayList<>());

    private int length = 0;
    private int width = 0;

    /**
     * Returns obstacles on map.
     *
     * @return obstacles
     */
    public ArrayList<ArrayList<String>> getObstacles() { return obstacles; }

    /**
     * Returns local 'length' field.
     *
     * @return length
     */
    public int getLength() { return length; }

    /**
     * Returns local 'width' field.
     *
     * @return width
     */
    public int getWidth() { return width; }

    /**
     * Modifies given row starting at given column with given objects.
     * Grows
     *
     * @param row row at which to insert objects
     * @param startCol column at which to begin insertion of objects
     * @param objects specified objects to insert
     */

    public void editRow(int row, int startCol, String objects) {
        if (row+1 > length) {
            for (int i = row+1; i > length; i--) {
                obstacles.add(new ArrayList<>());
                for (int j = 0; j < width; j++) {
                    obstacles.get(obstacles.size()-1).add(".");
                }

            }
        }


        ArrayList<String> existingRow = obstacles.get(row);
        int endCol = startCol+objects.length();

        if (endCol+1 > width) {
            for (int i = endCol; i > width; i--) {
                for (ArrayList<String> arrayList : obstacles) {
                    arrayList.add(".");
                }
            }
        }


        for (int i = startCol; i < endCol; i++) {
            existingRow.set(i, objects.substring(i - startCol, i - startCol + 1));
        }

        obstacles.set(row, existingRow);

        length = obstacles.size();
        width = obstacles.get(0).size();

    }

    public void editCol(int col, int startRow, String objects) {

        int endRow = startRow + objects.length();
        if (endRow > length) {
            for (int i = endRow; i > length; i--) {
                obstacles.add(new ArrayList<>());
                for (int j = 0; j < width; j++) {
                    obstacles.get(obstacles.size()-1).add(".");
                }
            }
        }

        if (col+1 > width) {
            for (int i = col+1; i > width; i--) {
                for (ArrayList<String> arrayList : obstacles) {
                    arrayList.add(".");
                }

            }
        }

        for (int i = startRow; i < endRow; i++) {
            obstacles.get(i).set(col, objects.substring(i - startRow, i - startRow + 1));
        }

        length = obstacles.size();
        width = obstacles.get(0).size();

    }

    public void printAll() {
        System.out.print("   ");

        for (int i = 0; i < width; i++) {
            if (i % 10 == 0) {
                System.out.print(i/10 + "  ");
            } else {
                System.out.print(i % 10 + "  ");
            }
        }

        System.out.print("\n");


        for (int i = 0; i < length; i++) {
            if (i % 10 == 0) {
                System.out.print(i / 10 + " ");
            } else {
                System.out.print(i % 10 + " ");
            }


            System.out.println(obstacles.get(i));

        }
        System.out.println("\n");

    }

}
