import java.awt.*;

public class Test extends SimpleEngine {

    public static void main(String[] args) {
        Test engine = new Test();
        engine.setLogLevel(LevelLogger.DEBUG);
        engine.start();
    }

    public Test() {
        super("Test 1", 600, 500, 30.0f);
    }

    private static final float PLAYER_SPEED = 1.8f;
    private float x, y;

    private String lastMsg = null;

    @Override
    protected LevelLogger initLogger() {
        return new LevelLogger() {
            @Override
            protected void doDebug(String s) {
                lastMsg = "[DEBUG]: " + s;
            }

            @Override
            protected void doInfo(String s) {
                lastMsg = "[INFO]: " + s;
            }

            @Override
            protected void doError(String s) {
                lastMsg = "[ERROR]: " + s;
            }
        };
    }

    @Override
    protected void init() {
        log.info("INIT");
    }

    @Override
    protected void onShutdown() {
        log.info("SHUTDOWN");
    }

    @Override
    public void update() {
        float vx = 0, vy = 0;

        if(keyboard.W()){
            vy -= PLAYER_SPEED;
        }

        if(keyboard.S()){
            vy += PLAYER_SPEED;
        }

        if(keyboard.A()){
            vx -= PLAYER_SPEED;
        }

        if(keyboard.D()){
            vx += PLAYER_SPEED;
        }

        x += vx;
        y += vy;

        log.debug("Player(X: " + (((int)(x * 10)) / 10.0) + ", Y: " + (((int)(y * 10)) / 10.0) + ")");
    }

    @Override
    public void render(IPencil pencil) {
        pencil.setColor(Color.WHITE);
        pencil.fillScreen();

        pencil.setColor(Color.BLACK);
        pencil.fillRectangle(x, y, 32, 32);


        if(lastMsg != null) {
            pencil.drawString(lastMsg, 10, 20);
        }
    }
}
