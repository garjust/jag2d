package garjust.jag2d.core;

import garjust.jag2d.geometry.point.Point;
import garjust.jag2d.geometry.shape.Polygon;
import garjust.jag2d.geometry.vector.Vector;

import java.awt.Graphics2D;

/**
 * 
 * @author Justin Garbutt
 */
public class PolygonEntity extends Polygon implements Entity {

    public Point position;
    public Vector velocity;
    public float rotation_velocity;

    public PolygonEntity(final Polygon polygon, final Point position) {
        super(polygon.vertices());
        this.position = position;
        this.velocity = Vector.ZERO.copy();
        this.rotation_velocity = 0;
    }

    public PolygonEntity(final Polygon polygon, final Point position, final Vector velocity, final float velocity_rotation) {
        super(polygon.vertices());
        this.position = position;
        this.velocity = velocity;
        this.rotation_velocity = velocity_rotation;
    }

    public PolygonEntity(final PolygonEntity entity) {
        super(entity.vertices());
        this.position = entity.position;
        this.velocity = entity.velocity;
        this.rotation_velocity = entity.rotation_velocity;
    }

    @Override
    public Point position() {
        return position;
    }

    @Override
    public Vector velocity() {
        return velocity;
    }

    @Override
    public float rotation_velocity() {
        return rotation_velocity;
    }

    @Override
    public void translate(final float x, final float y) {
        super.translate(x, y);
        position.translate(x, y);
    }

    @Override
    public void draw(Graphics2D graphics) {
        super.draw(graphics);
        position.draw(graphics);
        velocity.draw(graphics, position);
    }

    @Override
    public void update(final float delta) {
        translate(velocity.x() * (delta / 1000), velocity.y() * (delta / 1000));
        if (rotation_velocity != 0) {
            rotate(rotation_velocity * (delta / 1000), position);
        }
    }

    @Override
    public boolean collide(final Entity entity) {
        return false;
    }
}
