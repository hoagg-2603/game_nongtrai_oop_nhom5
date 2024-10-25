package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class Entity extends Sprite {
    private Vector2 velocity;
    private Direction currentDirection;
    private Direction previousDirection;

    private Animation<TextureRegion> walkLeft;
    private Animation<TextureRegion> walkRight;
    private Animation<TextureRegion> walkUp;
    private Animation<TextureRegion> walkDown;

    private Array<TextureRegion> walkLeftFrames;
    private Array<TextureRegion> walkRightFrames;
    private Array<TextureRegion> walkUpFrames;
    private Array<TextureRegion> walkDownFrames;

    private Vector2 nextPosition;
    private Vector2 currentPosition;
    private State state = State.IDLE;
    private float frameTime;
    private Sprite frameSprite;
    private TextureRegion currentFrame;

    private Texture texture;
    private World world;
    private Body body;

    public static Rectangle boundingBox;
    private static final float PPM = 100;  // Pixels per meter, cần chỉnh theo độ phân giải

    public enum State {
        IDLE, WALKING;
    }

    public enum Direction {
        UP, RIGHT, DOWN, LEFT;
    }

    public Entity(World world) {
        this.world = world;
        this.nextPosition = new Vector2();
        this.currentPosition = new Vector2();
        this.boundingBox = new Rectangle();
        this.velocity = new Vector2(2.5f, 2.5f);
        frameTime = 0f;
        currentDirection = Direction.DOWN;
        texture = new Texture("light.png");

        // Khởi tạo các thành phần vật lý và sprite
        defineEntity();
        loadSprite();
        loadAnimations();
    }

    private void defineEntity() {
        // Tạo BodyDef
        BodyDef bdef = new BodyDef();
        bdef.position.set(currentPosition.x / PPM, currentPosition.y / PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;

        // Tạo body trong thế giới
        body = world.createBody(bdef);

        // Tạo hình dáng vật thể
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(16 / PPM, 16 / PPM);  // Kích thước hộp

        // FixtureDef để kết nối shape với body
        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        fdef.density = 1.0f;
        fdef.friction = 0.5f;
        fdef.restitution = 0.2f;

        // Tạo fixture
        body.createFixture(fdef);

        // Giải phóng bộ nhớ cho shape
        shape.dispose();
    }

    public void startingPosition(float x, float y) {
        this.currentPosition.set(x, y);
        this.nextPosition.set(x, y);
        body.setTransform(x / PPM, y / PPM, 0);
    }

    public void update(float delta) {
        setPosition(body.getPosition().x * PPM - getWidth() / 2, body.getPosition().y * PPM - getHeight() / 2);
        move(currentDirection, delta);
        frameTime = state == State.WALKING ? frameTime + delta : 0;
        boundingBox.set(getX() + 20, getY(), 24, 12);
    }

    private void loadSprite() {
        TextureRegion[][] textureFrames = TextureRegion.split(texture, 64, 64);
        frameSprite = new Sprite(textureFrames[0][0].getTexture(), 0, 0, 64, 64);
        currentFrame = textureFrames[0][0];
    }

    private void loadAnimations() {
        TextureRegion[][] textureFrames = TextureRegion.split(texture, 64, 64);
        walkDownFrames = new Array<TextureRegion>(9);
        walkUpFrames = new Array<TextureRegion>(9);
        walkLeftFrames = new Array<TextureRegion>(9);
        walkRightFrames = new Array<TextureRegion>(9);

        for (int i = 0; i < 8; i++) {
            walkDownFrames.insert(i, textureFrames[10][i + 1]);
        }
        for (int i = 0; i < 8; i++) {
            walkUpFrames.insert(i, textureFrames[8][i + 1]);
        }
        for (int i = 0; i < 9; i++) {
            walkLeftFrames.insert(i, textureFrames[9][i]);
        }
        for (int i = 0; i < 9; i++) {
            walkRightFrames.insert(i, textureFrames[11][i]);
        }

        walkDown = new Animation<TextureRegion>(.1f, walkDownFrames, Animation.PlayMode.LOOP);
        walkUp = new Animation<TextureRegion>(.1f, walkUpFrames, Animation.PlayMode.LOOP);
        walkLeft = new Animation<TextureRegion>(.1f, walkLeftFrames, Animation.PlayMode.LOOP);
        walkRight = new Animation<TextureRegion>(.1f, walkRightFrames, Animation.PlayMode.LOOP);
    }

    public void setDirection(Direction direction, float delta) {
        this.previousDirection = this.currentDirection;
        this.currentDirection = direction;
        switch (currentDirection) {
            case DOWN:
                currentFrame = walkDown.getKeyFrame(frameTime);
                break;
            case UP:
                currentFrame = walkUp.getKeyFrame(frameTime);
                break;
            case LEFT:
                currentFrame = walkLeft.getKeyFrame(frameTime);
                break;
            case RIGHT:
                currentFrame = walkRight.getKeyFrame(frameTime);
                break;
            default:
                break;
        }
    }

    public void move(Direction direction, float delta) {
        float speed = 100f;  // Tốc độ di chuyển của nhân vật
        switch (direction) {
            case DOWN:
                body.setLinearVelocity(0, -speed*delta);
                break;
            case UP:
                body.setLinearVelocity(0, speed*delta);
                break;
            case LEFT:
                body.setLinearVelocity(-speed*delta, 0);
                break;
            case RIGHT:
                body.setLinearVelocity(speed*delta, 0);
                break;
            default:
                body.setLinearVelocity(0, 0);  // Dừng lại khi không có hướng
                break;
        }
    }

    public void setState(State state) {
        this.state = state;
    }

    public Sprite getFrameSprite() {
        return frameSprite;
    }

    public TextureRegion getCurrentFrame() {
        return currentFrame;
    }

    public Direction getDirection() {
        return currentDirection;
    }

    public void setCurrentPosition(float x, float y) {
        frameSprite.setX(x);
        frameSprite.setY(y);
        this.currentPosition.x = x;
        this.currentPosition.y = y;
        this.nextPosition.x = x;
        this.nextPosition.y = y;
        body.setTransform(x / PPM, y / PPM, 0);
    }

    public void setCurrentToNext() {
        setCurrentPosition(nextPosition.x, nextPosition.y);
    }

    public static Rectangle getBoundingBox() {
        return boundingBox;
    }

    public static float getPlayerCenterX() {
        return boundingBox.x + boundingBox.width / 2;
    }

    public static float getPlayerCenterY() {
        return boundingBox.y + boundingBox.height / 2;
    }
}
