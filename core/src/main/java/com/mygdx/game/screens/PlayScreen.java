package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.FarmGame;
import com.mygdx.game.crops.Crop;
import com.mygdx.game.crops.Seeds;
import com.mygdx.game.sprites.Entity;
import com.mygdx.game.tools.*;
//import javafx.scene.control.Tab;


public class PlayScreen implements Screen{
    private FarmGame game;
    private World world;

    //Camera variables
    private OrthographicCamera cam;
    private Viewport gameView;
    private Vector3 tp;

    //Tiled map variables
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private MapLoader loader;
    private int mapWidth;
    private int mapHeight;
    private MapObject object;

    //Variables for player
    private Entity player;
    private TextureRegion currentPlayerFrame;
    private Sprite currentPlayerSprite;
    private PlayerController controller;
    private ShapeRenderer shapeRenderer;


    //Clock variables
    private GameTimeClock clock;
    private Timer_ timer;
    private int currentDays;

    //Tool variables
    public Items.ItemType currentType;
    public Items currentItem;
    public Texture bucketTexture;
    public Items bucket;
    public Items tomatoSeed;
    public Items potatoSeed;
    public Items carrotSeed;
    public Items cornSeed;
    public Items artSeed;
    public Items pepperSeed;
    public Items gourdSeed;
    public int intType;

    //Crop variables
    public Array<Crop> crops;
    public int numCrops;
    private TextureRegion[][] textureFrames;
    private TextureRegion mouseFrame;
    public Texture mouseCrop;
    public Array<Seeds> seeds;

    //Variables for score
    public static Integer money;
    private static Label timeLabel;
    private static Label timeStringLabel;
    private static Label scoreLabel;
    private static Label scoreStringLabel;
    private static Label coporateLabel;
    private static Label coporateScoreLabel;
    private static int coporateScore;
    private static Label daysLeftLabel;
    private static Label daysLeftNum;
    private Viewport viewport;
    public Stage stage;
    private String time;
    private Label lose;
    private Label win;

    //Inventory
    private Array<Items> items;
    private Texture box;
    private Texture border;


    //Other
    private boolean sleep;
    private Dialog bedMenu;
    private Skin skin;
    private static int daysLeft;
    private boolean showStage;
    private BitmapFont font;
    private Music music;

    public PlayScreen(FarmGame game) {
        this.game = game;
        font = new BitmapFont();
        font.getData().setScale(.75f);
        cam = new OrthographicCamera();
        gameView = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), cam);
        cam.setToOrtho(false, gameView.getWorldWidth(), gameView.getWorldHeight());
        
        //load the map
        map = new TmxMapLoader().load("farm.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        renderer.setView(cam);


        seeds = new Array<Seeds>();
        loader = new MapLoader(this);

        // chiều rộng thực tế của bản đồ tính theo pixel
        mapWidth = map.getProperties().get("width", Integer.class) * 32;
        // chiều cao thực tế của bản đồ.
        mapHeight = map.getProperties().get("height", Integer.class) * 32;
        //World là môi trường vật lý nơi các vật thể tương tác với nhau. Trọng lực -9.8f mô phỏng gia tốc trọng trường như trong thực tế.
        world = new World(new Vector2(0, -9.8f), true);
        // Tạo entity là các nhân vật trong game
        player = new Entity(world);
        //Thiết lập vị trí bắt đầu cho player dựa trên tọa độ lấy từ phương thức getPlayerSpawn() của loader.
        player.startingPosition(loader.getPlayerSpawn().x, loader.getPlayerSpawn().y);
        // Lấy sprite (hình ảnh) hiện tại của player và gán nó cho biến currentPlayerSprite.
        currentPlayerSprite = player.getFrameSprite();
        //Tạo một đối tượng PlayerController mới để xử lý đầu vào và điều khiển player.
        controller = new PlayerController(this, player);
        //Khởi tạo một ShapeRenderer để vẽ các hình dạng cơ bản trong trò chơi.
        shapeRenderer = new ShapeRenderer();

        crops = new Array<Crop>();

        // Độ zoom nhỏ hơn 1.0f sẽ làm camera lùi ra xa, trong khi lớn hơn 1.0f sẽ phóng to vào cảnh.
        cam.zoom = .5f;
        // Timer_ có thể đếm thời gian, quản lý sự kiện theo thời gian hoặc điều khiển thời gian trong trò chơi.
        timer = new Timer_();
        //Khởi động bộ đếm thời gian với các tham số cụ thể.
        timer.StartNew(60, true, true);
        //Thiết lập thời gian bắt đầu cho bộ đếm thời gian.
        timer.setStartTime(0,12,0,0);
        //GameTimeClock sử dụng timer để theo dõi và hiển thị thời gian trong trò chơi.
        clock = new GameTimeClock(timer);
        currentDays = 0;
        numCrops = 0;
        intType = 0;
        daysLeft = 30;

        time = timer.getFormattedTimeofDay();
        mouseCrop = new Texture("farming/tilesets/plants.png");
        textureFrames = TextureRegion.split(mouseCrop, 32, 64);
        tp = new Vector3();
        bucketTexture = new Texture("bucket.png");
        bucket = new Items(bucketTexture, Items.ItemType.TOOL, Items.Item.BUCKET);
        tomatoSeed = new Items(textureFrames[5][0], Items.ItemType.SEED, Items.Item.TOMATO);
        potatoSeed = new Items(textureFrames[5][1], Items.ItemType.SEED, Items.Item.POTATO);
        carrotSeed = new Items(textureFrames[5][2], Items.ItemType.SEED, Items.Item.CARROT);
        cornSeed = new Items(textureFrames[5][6], Items.ItemType.SEED, Items.Item.CORN);
        gourdSeed = new Items(textureFrames[5][5], Items.ItemType.SEED, Items.Item.GOURD);
        artSeed = new Items(textureFrames[5][3], Items.ItemType.SEED, Items.Item.ARTICHOKE);
        pepperSeed = new Items(textureFrames[5][4], Items.ItemType.SEED, Items.Item.PEPPER);

        items = new Array<Items>(9);
        items.add(bucket);
        items.add(tomatoSeed);
        items.add(potatoSeed);
        items.add(carrotSeed);
        items.add(cornSeed);
        items.add(gourdSeed);
        items.add(artSeed);
        items.add(pepperSeed);
        setMouseCrop(bucket);



        //Score label
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
        stage = new Stage(viewport, game.batch);
        Table loseTable = new Table();
        Table winTable = new Table();
        Table table = new Table();
        winTable.center();
        winTable.setFillParent(true);
        loseTable.center();
        loseTable.setFillParent(true);
        table.top();
        table.setFillParent(true);
        money = 100;
        coporateScore = 100;

        timeLabel = new Label(time, new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timeStringLabel = new Label("Time", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel = new Label(String.format("$%d", money), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreStringLabel = new Label("Money", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        coporateLabel = new Label("Evil Coporate Money", new Label.LabelStyle(new BitmapFont(), Color.RED));
        coporateScoreLabel = new Label(String.format("$%d", coporateScore), new Label.LabelStyle(new BitmapFont(), Color.RED));
        daysLeftLabel = new Label("Days Left", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        daysLeftNum = new Label(String.format("%d", daysLeft), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        lose = new Label("You lose! The evil corporate company wins!", new Label.LabelStyle(new BitmapFont(), Color.RED));
        win = new Label("You win! The evil corporate doesn't get your land!", new Label.LabelStyle(new BitmapFont(), Color.GREEN));
        win.setVisible(false);
        win.setFontScale(3);
        lose.setVisible(false);
        lose.setFontScale(3);
        timeStringLabel.setFontScale(2);
        timeLabel.setFontScale(2);
        scoreStringLabel.setFontScale(2);
        scoreLabel.setFontScale(2);
        coporateScoreLabel.setFontScale(2);
        coporateLabel.setFontScale(2);
        daysLeftLabel.setFontScale(2);
        daysLeftNum.setFontScale(2);
        table.add(scoreStringLabel).padRight(100f);
        table.add(coporateLabel).padRight(100f);
        table.add(timeStringLabel).padRight(100f);
        table.add(daysLeftLabel);
        table.row();
        table.add(scoreLabel).padRight(100f);
        table.add(coporateScoreLabel).padRight(100f);
        table.add(timeLabel).padRight(100f);
        table.add(daysLeftNum);
        loseTable.add(lose);
        winTable.add(win);
        stage.addActor(table);
        stage.addActor(loseTable);
        stage.addActor(winTable);

        box = new Texture("box.png");
        border = new Texture("border.png");


        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        bedMenu = new Dialog("",skin, "dialog") {
            protected void result(Object object) {
                if (object.equals(true)) {
                    sleep = true;
                }
                Timer.schedule(new Timer.Task(){
                    @Override
                    public void run() {
                        bedMenu.remove();
                        showStage = false;
                    }
                }, 0);

            }
        };
        bedMenu.text("Would you like to sleep?");
        bedMenu.button("Yes", true);
        bedMenu.button("No",false);

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(controller);
        inputMultiplexer.addProcessor(stage);
        Gdx.input.setInputProcessor(inputMultiplexer);
        for(int i = 0; i < 20; i++)
            addSeeds(carrotSeed.getItem());

        music = FarmGame.manager.get("music.mp3", Music.class);
        music.setLooping(true);
        music.setVolume(.2f);
        music.play();

    }

    public void setMouseCrop(Items item){
        mouseFrame = item.getTextureRegion();
        currentItem = item;
        currentType = item.getType();

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(daysLeft == 0){
            if(coporateScore > money) {
                lose.setVisible(true);
                Timer.schedule(new Timer.Task(){
                    @Override
                    public void run() {
                        game.setScreen(new MainMenu(game));
                        music.stop();
                        dispose();
                    }
                }, 7);
            }
            if(coporateScore < money) {
                win.setVisible(true);
                Timer.schedule(new Timer.Task(){
                    @Override
                    public void run() {
                        game.setScreen(new MainMenu(game));
                        music.stop();
                        dispose();
                    }
                }, 7);
            }

        }

        //dieu chinh vi tri camera
        //Cạnh phải của camera không vượt quá ranh giới bên phải của bản đồ:
        //Cạnh trái của camera không vượt quá ranh giới bên trái của bản đồ:
        if (currentPlayerSprite.getX() + (cam.viewportWidth / 2 * cam.zoom) < mapWidth &&
            currentPlayerSprite.getX() - (cam.viewportWidth / 2 * cam.zoom) > 0)
            cam.position.x = player.getPlayerCenterX();

        //Cạnh trên của camera không vượt quá ranh giới trên của bản đồ:
        //Cạnh dưới của camera không vượt quá ranh giới dưới của bản đồ:
        if (currentPlayerSprite.getY() + cam.viewportHeight / 2 < mapHeight &&
            currentPlayerSprite.getY() - cam.viewportHeight / 2 > 0)
            cam.position.y = player.getPlayerCenterY();

        /*
         * isCollision(player.getBoundingBox()): Kiểm tra xem bounding box (hộp bao quanh) của player có xảy ra va chạm với bất kỳ vật thể nào khác trong trò chơi hay không.
         *  điều kiện if này sẽ đúng khi không có va chạm xảy ra.
         * player.setCurrentToNext();: Cập nhật vị trí hiện tại của player thành vị trí kế tiếp mà họ muốn di chuyển tới.
        */
        if (!isCollision(player.getBoundingBox()))
            player.setCurrentToNext();


        if ((object = isTeleport(player.getBoundingBox())) != null) {
            map.dispose();
            map = new TmxMapLoader().load(object.getName() + ".tmx");
            renderer.setMap(map);
            mapWidth = map.getProperties().get("width", Integer.class) * 32;
            mapHeight = map.getProperties().get("height", Integer.class) * 32;
            for (MapObject spawn : map.getLayers().get("Player_Spawn").getObjects()) {
                Rectangle rectangle = ((RectangleMapObject) spawn).getRectangle();
                if (spawn.getName().equals(object.getName())) {
                    player.setCurrentPosition(rectangle.x, rectangle.y);
                    cam.position.set(rectangle.x, rectangle.y, 0);
                }
            }
        }
        if (isBed(player.getBoundingBox())) {
            if(showStage == false) {
                showStage = true;
                bedMenu.show(stage);
            }
            if (sleep == true) {

                timer.setDaysPassed(currentDays + 1);
                timer.setStartTime(currentDays + 1, 7, 0, 0);
                for (MapObject spawn : map.getLayers().get("Player_Spawn").getObjects()) {
                    Rectangle rectangle = ((RectangleMapObject) spawn).getRectangle();
                    player.setCurrentPosition(rectangle.x, rectangle.y);
                    cam.position.set(rectangle.x, rectangle.y, 0);
                }
            }

        }
        sleep = false;
        cam.update();
        player.update(delta);

        renderer.render();
        clock.act(delta);
        if(timer.getDaysPast() != currentDays){
            for (Crop crops : crops)
                crops.addDay();
            currentDays = timer.getDaysPast();
            daysLeft--;
            daysLeftNum.setText(String.format("%d", daysLeft));
            coporateScore += 50;
            coporateScoreLabel.setText(String.format("$%d", coporateScore));
        }

        time = timer.getFormattedTimeofDay();
        timeLabel.setText(time);
        controller.update(delta);
        game.batch.setProjectionMatrix(cam.combined);
        currentPlayerFrame = player.getCurrentFrame();
        renderer.setView(cam);
        //renderer.render(bottom);
        game.batch.begin();


        for(int i = 0; i < numCrops; i++) {
            if (crops.get(i).isWatered())
                game.batch.setColor(Color.BROWN);
            game.batch.draw(textureFrames[4][1], crops.get(i).getFrameSprite().getX(), crops.get(i).getFrameSprite().getY() - 6);
            game.batch.setColor(Color.WHITE);
            game.batch.draw(crops.get(i).getCurrentFrame(), crops.get(i).getFrameSprite().getX(), crops.get(i).getFrameSprite().getY());
            if (crops.get(i).isDead()) {
                crops.removeIndex(i);
                numCrops--;
            }
        }

        for(int i = 0; i < seeds.size; i++){
            game.batch.draw(seeds.get(i).getTexture(), seeds.get(i).getBoundingRect().x, seeds.get(i).getBoundingRect().y);
        }

        // rendering the player and the inventory items on the game screen
        game.batch.draw(currentPlayerFrame, currentPlayerSprite.getX(), currentPlayerSprite.getY());
        //game.batch.draw(mouseFrame, Math.round((coords.x - 16)/32)*32, Math.round((coords.y - 16)/32)*32);
        //game.batch.draw(mouseFrame, coords.x - 16, coords.y -16);
        for(int i = 0; i < 9; i++) {
            game.batch.draw(box, (cam.position.x + 32 * i) - (cam.viewportWidth / 2 * (cam.zoom / 2)), cam.position.y - (cam.viewportHeight / 2 * cam.zoom));
            if(i < items.size) {
                game.batch.draw(items.get(i).getTextureRegion(), (cam.position.x + 32 * i) - (cam.viewportWidth / 2 * (cam.zoom / 2)), cam.position.y - (cam.viewportHeight / 2 * cam.zoom));
                if(items.get(i).getType() == Items.ItemType.SEED)
                    font.draw(game.batch, String.format("%d", items.get(i).getNum()), (cam.position.x + 32 * i) - (cam.viewportWidth / 2 * (cam.zoom / 2)-6), cam.position.y - (cam.viewportHeight / 2 * cam.zoom)+12);
                if(items.get(i).getItem() == currentItem.getItem())
                    game.batch.draw(border, (cam.position.x + 32 * i) - (cam.viewportWidth / 2 * (cam.zoom / 2)), cam.position.y - (cam.viewportHeight / 2 * cam.zoom));
            }
        }
        //quản lý việc vẽ các yếu tố đồ họa như hình hộp, ánh sáng môi trường, và cập nhật giao diện người dùng trong trò chơi.
        game.batch.end();
        //renderer.render(top);
        shapeRenderer.setProjectionMatrix(cam.combined);
        Gdx.gl20.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(clock.getAmbientLighting());
        Matrix4 mat = cam.combined.cpy();
        shapeRenderer.setProjectionMatrix(mat);
        mat.setToOrtho2D(0, 0, Gdx.graphics.getWidth(),
            Gdx.graphics.getHeight());
        shapeRenderer.rect(cam.position.x - gameView.getWorldWidth()/2, cam.position.y - gameView.getWorldHeight()/2, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        shapeRenderer.end();
        Gdx.gl20.glDisable(GL20.GL_BLEND);
        game.batch.setProjectionMatrix(mat);
        shapeRenderer.setColor(Color.WHITE);
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        gameView.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        bucketTexture.dispose();
        skin.dispose();
        font.dispose();
        map.dispose();
        mouseCrop.dispose();
        box.dispose();
        border.dispose();
    }
    
    //import map từ file tmx
    public TiledMap getMap() {
        return map;
    }


    public boolean isCollision(Rectangle boundingBox){
        // lấy lớp bản đồ có tên "Collision" từ đối tượng map. Lớp này chứa các đối tượng mà bạn đã định nghĩa để xử lý va chạm
        MapLayer objectLayer = map.getLayers().get("Collision");
        //Phương thức này gọi checkCollision, truyền vào boundingBox của đối tượng cần kiểm tra (trong trường hợp này là nhân vật) và objectLayer chứa các đối tượng va chạm. Phương thức checkCollision sẽ trả về true nếu có va chạm xảy ra và false nếu không.
        return checkCollision(boundingBox, objectLayer);
    }


    public MapObject isTeleport(Rectangle boundingBox) {
        // Chỉ xử lý các đối tượng là RectangleMapObject. Nếu có các loại đối tượng khác (ví dụ: PolygonMapObject, CircleMapObject), chúng sẽ bị bỏ qua. Điều này có thể là đúng nếu bạn chỉ sử dụng hình chữ nhật cho teleport.
        MapLayer objectLayer = map.getLayers().get("Teleport");
        for(MapObject object: objectLayer.getObjects()){
            if(object instanceof RectangleMapObject){
                Rectangle rectangle = ((RectangleMapObject) object). getRectangle();
                if(boundingBox.overlaps(rectangle))
                    return object;
            }
        }
        return null;
    }

    public boolean isBed(Rectangle boundingBox) {
        if(map.getProperties().get("name").equals("farmhouse")){
            MapLayer objectLayer = map.getLayers().get("Bed");
            return checkCollision(boundingBox, objectLayer);
        }
        else
            return false;
    }

    //: Phương thức checkCollision kiểm tra xem boundingBox (hộp bao quanh) của một đối tượng nào đó (ví dụ: nhân vật người chơi) có giao nhau với bất kỳ đối tượng nào trong objectLayer (lớp đối tượng trên bản đồ) hay không.
    public boolean checkCollision(Rectangle boundingBox, MapLayer objectLayer) {
        //Vòng lặp qua tất cả các đối tượng trong objectLayer:
        for (MapObject object : objectLayer.getObjects()) {
            //Chỉ xử lý những đối tượng là RectangleMapObject. Điều này giả định rằng tất cả các đối tượng va chạm đều được định nghĩa dưới dạng hình chữ nhật trong bản đồ.
            if (object instanceof RectangleMapObject) {
                //Lấy hình chữ nhật bao quanh đối tượng.
                Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
                //Lấy hình chữ nhật của đối tượng và kiểm tra xem boundingBox có giao nhau với hình chữ nhật đó không. Nếu có, trả về true ngay lập tức.
                if (boundingBox.overlaps(rectangle))
                    return true;
            }

        }
        return false;
    }

    public void buySeed(Seeds seed){
        if(seed.getPrice() <= money) {
            money -= seed.getPrice();
            addSeeds(seed.getItem());
        }
        scoreLabel.setText(String.format("$%d", money));
    }

    public void addSeeds(Items.Item item) {
        switch(item) {
            case GOURD:
                gourdSeed.add();
                break;
            case TOMATO:
                tomatoSeed.add();
                break;
            case PEPPER:
                pepperSeed.add();
                break;
            case CORN:
                cornSeed.add();
                break;
            case CARROT:
                carrotSeed.add();
                break;
            case POTATO:
                potatoSeed.add();
                break;
            case ARTICHOKE:
                artSeed.add();
        }
    }

    public void removeSeeds(Items.Item item) {
        switch(item) {
            case GOURD:
                gourdSeed.remove();
                break;
            case TOMATO:
                tomatoSeed.remove();
                break;
            case PEPPER:
                pepperSeed.remove();
                break;
            case CORN:
                cornSeed.remove();
                break;
            case CARROT:
                carrotSeed.remove();
                break;
            case POTATO:
                potatoSeed.remove();
                break;
            case ARTICHOKE:
                artSeed.remove();
        }
    }



    public OrthographicCamera getCam() {
        return cam;
    }

    public void addCrop(Crop crop){
        crops.add(crop);
    }

    public Array<Crop> getCrops() {
        return crops;
    }

    public void addMoney(int price){
        money += price;
        scoreLabel.setText(String.format("$%d", money));
    }

    public Array<Items> getItems() {
        return items;
    }

    public FarmGame getGame() {
        return game;
    }

    public Timer_ getTimer() {
        return timer;
    }

    public Array<Seeds> getSeeds() {
        return seeds;
    }

    public int getCurrentDays() {
        return currentDays;
    }
}
