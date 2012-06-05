package garjust.jag2d.collision.spatialpartition;

import garjust.jag2d.collision.Collidable;
import garjust.jag2d.collision.util.CollidableList;
import garjust.jag2d.geometry.point.Point;
import garjust.jag2d.geometry.shape.Rectangle;
import garjust.jag2d.window.WindowDimension;

import java.util.ArrayList;

public class BoundingBoxQuadrantSystem implements QuadrantSystem {

    private final BoundingBoxQuadrant[][] quadrants;
    private final int x_quadrants;
    private final int y_quadrants;
    private final CollidableList entities;

    public BoundingBoxQuadrantSystem(final WindowDimension dimensions, final int x_quadrants, final int y_quadrants) {
        this.quadrants = new BoundingBoxQuadrant[y_quadrants][x_quadrants];
        this.x_quadrants = x_quadrants;
        this.y_quadrants = y_quadrants;
        this.entities = new CollidableList();
        int x_size = dimensions.width() / x_quadrants;
        int y_size = dimensions.height() / y_quadrants;
        for (int y = 0; y < y_quadrants; y++) {
            for (int x = 0; x < x_quadrants; x++) {
                quadrants[y][x] = new BoundingBoxQuadrant(new Point(x * x_size, y * y_size), new Point((x + 1) * x_size, (y + 1) * y_size));
            }
        }
    }

    /**
     * TODO remove duplicate references in returned list, objects in multiple
     * quadrants will be referenced multiple times
     * 
     * @param collidable
     * @return
     */
    @Override
    public CollidableList find(final Collidable collidable) {
        final CollidableList matches = new CollidableList();
        for (int y = 0; y < y_quadrants; y++) {
            for (int x = 0; x < x_quadrants; x++) {
                if (quadrants[y][x].contains(collidable)) {
                    matches.addAll(quadrants[y][x].retrieve(collidable));
                }
            }
        }
        return matches;
    }

    public ArrayList<Rectangle> findQuadrantRectangles(final Collidable collidable) {
        final ArrayList<Rectangle> list = new ArrayList<Rectangle>();
        for (int y = 0; y < y_quadrants; y++) {
            for (int x = 0; x < x_quadrants; x++) {
                if (quadrants[y][x].contains(collidable)) {
                    // list.add(quadrants[y][x].bound());
                }
            }
        }
        return list;
    }

    @Override
    public boolean add(final Collidable collidable) {
        entities.add(collidable);
        for (int y = 0; y < y_quadrants; y++) {
            for (int x = 0; x < x_quadrants; x++) {
                quadrants[y][x].belongs(collidable);
            }
        }
        return true;
    }

    @Override
    public boolean remove(final Collidable collidable) {
        for (int y = 0; y < y_quadrants; y++) {
            for (int x = 0; x < x_quadrants; x++) {
                quadrants[y][x].remove(collidable);
            }
        }
        return true;
    }

    @Override
    public void update() {
        for (int i = 0; i < entities.size(); i++) {
            for (int y = 0; y < y_quadrants; y++) {
                for (int x = 0; x < x_quadrants; x++) {
                    quadrants[y][x].belongs(entities.get(i));
                }
            }
        }
    }
}
