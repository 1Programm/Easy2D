package com.programm.projects.easy2d.engine.simple.objects;

import com.programm.project.easy2d.engine.api.IPencil;
import com.programm.projects.easy2d.objects.api.GameObject;
import com.programm.projects.easy2d.objects.api.IObjectContext;
import com.programm.projects.easy2d.objects.api.components.AbstractComponent;
import com.programm.projects.easy2d.objects.api.components.IRenderableComponent;
import com.programm.projects.easy2d.objects.api.components.IUpdatableComponent;
import com.programm.projects.easy2d.objects.api.components.Mover;
import com.programm.projects.easy2d.objects.api.components.collision.IntersectionInfo;
import com.programm.projects.easy2d.objects.api.components.collision.Collider;
import com.programm.projects.easy2d.objects.api.components.gfx.ColoredShape;
import com.programm.projects.easy2d.objects.api.components.shape.Circle;
import com.programm.projects.easy2d.objects.api.components.shape.Rect;
import com.programm.projects.easy2d.objects.api.components.shape.Shape;
import com.programm.projects.easy2d.objects.api.utils.MathUtils;

import java.awt.*;

class Test extends SimpleObjectEngine {

    private static final float PLAYER_SPEED = 0.23f;

    private static class PlayerComponent extends AbstractComponent implements IUpdatableComponent, IRenderableComponent {

        private float vx, vy;

//        private final CollisionInfo info = new CollisionInfo();
//        private Collider rectCollider = new RectCollider(10, 10);
        private final IntersectionInfo info = new IntersectionInfo();
        private final Circle circle = new Circle(2.6f, 0, 0.5f);

        @Override
        public void init(IObjectContext ctx) {
            super.init(ctx);
//            rectCollider.init(ctx);
        }

        @Override
        public void update() {
            vx = 0;
            vy = 0;
            if(engine.keyboard().A()){
                vx -= PLAYER_SPEED;
            }
            if(engine.keyboard().D()){
                vx += PLAYER_SPEED;
            }
            if(engine.keyboard().W()){
                vy -= PLAYER_SPEED;
            }
            if(engine.keyboard().S()){
                vy += PLAYER_SPEED;
            }

            gameObject.get(Mover.class).move(vx, vy);

//            rectCollider.offset.set(vx * 20, vy * 20);
//            objectHandler.testCollision(info, rectCollider, null);

            info.reset();
//            objectHandler.testCollision(info, circle, gameObject.position);
            objectHandler.testAndSolveCollision(circle, gameObject.position, MathUtils.VEC2_UNIT, false, 1);


//            if(info.collision()){
//                System.out.println("YEAAAAA");
//            }

//            System.out.println(info);
        }

        @Override
        public void render(IPencil pen) {
            pen.setColor(Color.YELLOW);
//            pen.drawRectangle(gameObject.position.getX() * objectHandler.unitSize(), gameObject.position.getY() * objectHandler.unitSize(), 10, 10);

            pen.drawCircle((gameObject.position.getX() + circle.position.getX()) * objectHandler.unitSize(), (gameObject.position.getY() + circle.position.getY()) * objectHandler.unitSize(), circle.radius * objectHandler.unitSize());

//            float ow = 10;
//            float oh = 10;
//            float ox = gameObject.position.getX() + vx * 20;
//            float oy = gameObject.position.getY() + vy * 20;
//
//            pen.setColor(Color.RED);
//            pen.drawLine(gameObject.position.getX(), gameObject.position.getY(), ox, oy);
//
//            float x = ox - ow/2f;
//            float y = oy - oh/2f;
//            pen.setColor(Color.GREEN);
//            pen.drawRectangle(x, y, ow, oh);
        }
    }

    public Test(String title, int width, int height, float fps, boolean cached) {
        super(title, width, height, fps, cached);
    }

    @Override
    protected void init() {
//        addObject(new Player(10, 10, 20, 20));
//        addObject(new Player(278, 144, 20, 20));
//        addObject(new CirclePlayer(139, 139, 10, Color.BLUE));
//        addObject(new CirclePlayer(140, 125, 10, 20, Color.BLUE));
//        addObject(new CircleObject(100, 100, 32, Color.RED));
//        addObject(new Block(300, 100, 0, 0, 40, 70));

        unitSize(10);
        debugShowUnitRaster(true);

        GameObject o1 = new GameObject(5, 5, 2, 1);
//        Shape shape1 = new Circle();
        Shape shape2 = new Circle();
//        o1.add(new ColoredShape(Color.RED, shape1, true));
        o1.add(new ColoredShape(Color.BLUE, shape2, false));
        o1.add(new PlayerComponent());
        o1.add(new Collider(shape2));

        addObject(o1);


        GameObject o2 = new GameObject(6, 6, 2, 2);
        Shape shape3 = new Circle();
        o2.add(new ColoredShape(Color.BLACK, shape3));
        o2.add(new Collider(shape3));

        addObject(o2);


//        GameObject player = new GameObject(100, 100, 32, 32);
//        player.add(new PlayerComponent());
//        player.add(new CircleCollider());
////        player.add(new ColoredRect(Color.BLUE));
//        player.add(new ColoredCircle(Color.BLUE));
//        addObject(player);
//
//        GameObject block1 = new GameObject(150, 100, 60, 60);
//        block1.add(new CircleCollider().fixed(true));
//        block1.add(new ColoredCircle(Color.RED));
//        addObject(block1);
    }


    public static void main(String[] args) {
        Test test = new Test("Test Engine", 600, 500, 30, false);
        test.start();
    }

}
