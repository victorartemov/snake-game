package model;

import java.util.Random;

public class GameWorld {

    //класс самой игры. Содержит змейку, лягушку и карту 16 на 16 + не самую понятную логику тика игры

    private static final int WORLD_WIDTH = 16;
    private static final int WORLD_HEIGHT = 16;
    private static final int SCORE_INCREMENT = 10;
    private static final float TICK_INITIAL = 0.5f;
    private static final float TICK_DECREMENT = 0.05f;

    private Snake snake;
    private Frog frog;
    private boolean gameOver;
    private boolean isRunning;
    private int score;

    private boolean fields[][];
    private Random random;
    private float tickTime;
    private static float tick;

    public GameWorld() {
        this.snake = new Snake();

        //вот тут мы создаем для змейки отдельный поток
        Thread snakeThread = new Thread(snake);
        snakeThread.setDaemon(true); //загуглите если надо
        snakeThread.start();

        this.gameOver = false;
        this.isRunning = true;
        this.score = 0;
        this.fields = new boolean[WORLD_WIDTH][WORLD_HEIGHT];
        this.random = new Random();
        this.tickTime = 0;
        this.tick = TICK_INITIAL;
        placeFrog();
    }

    public static float getTick() {
        return tick;
    }

    public static void setTick(float tick) {
        GameWorld.tick = tick;
    }

    public float getTickTime() {
        return tickTime;
    }

    public void setTickTime(float tickTime) {
        this.tickTime = tickTime;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Frog getFrog() {
        return frog;
    }

    public void setFrog(Frog frog) {
        this.frog = frog;
    }

    public Snake getSnake() {
        return snake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean[][] getFields() {
        return fields;
    }

    public void setFields(boolean[][] fields) {
        this.fields = fields;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    //усовершенствованный метод размещения лягушки
    public void placeFrog() {
        for(int x = 0; x < WORLD_WIDTH; x++){
            for (int y = 0; y < WORLD_HEIGHT; y++){
                fields[x][y] = false;
            }
        }

        for(int i = 0; i < snake.getParts().size(); i++){
            SnakePart part = snake.getParts().get(i);
            fields[part.getX()][part.getY()] = true;
        }

        int frogX = random.nextInt(WORLD_WIDTH);
        int frogY = random.nextInt(WORLD_HEIGHT);

        while (true){
            if(fields[frogX][frogY] == false)
                break;
            frogX += 1;
            if(frogX >= WORLD_WIDTH){
                frogX = 0;
                frogY += 1;
                if(frogY >= WORLD_HEIGHT){
                    frogY = 0;
                }
            }
        }
        frog = new Frog(frogX, frogY);
    }

    //самый главный метод отображения игры
    public void update(float deltaTime){
        if(gameOver)
            return;

        if(isRunning) {

            tickTime += deltaTime;

            while (tickTime > tick) {
                tickTime -= tick;
                snake.move();
                if (snake.checkBitten()) {
                    gameOver = true;
                    return;
                }

                SnakePart head = snake.getParts().get(0);
                if (head.getX() == frog.getX() && head.getY() == frog.getY()) {
                    score += SCORE_INCREMENT;
                    snake.eat();

                    if (snake.getParts().size() == WORLD_HEIGHT * WORLD_WIDTH) {
                        gameOver = true;
                        return;
                    } else {
                        placeFrog();
                    }
                }
            }
        }
    }
}
