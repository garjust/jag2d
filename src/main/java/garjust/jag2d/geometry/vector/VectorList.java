package garjust.jag2d.geometry.vector;

import java.util.ArrayList;

public class VectorList extends ArrayList<Vector> {

    private static final long serialVersionUID = 1L;

    public VectorList() {
        super();
    }

    public VectorList(int size) {
        super(size);
    }

    public VectorList(VectorList vectors) {
        super(vectors.size());
        for (Vector vector : vectors) {
            add(vector);
        }
    }

    public int[][] getCoordinateMatrix() {
        int[][] matrix = new int[2][size()];
        int i = 0;
        for (Vector vector : this) {
            Vector drawable = vector.snap();
            matrix[0][i] = (int) drawable.x();
            matrix[1][i] = (int) drawable.y();
            i++;
        }
        return matrix;
    }
}
