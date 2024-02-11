package com.tobia.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.tobia.game.TowerDefense;
import com.tobia.game.buttons.Button;
import com.tobia.game.buttons.ButtonObserver;
import com.tobia.game.buttons.PlaceTowerButton;
import com.tobia.game.entities.Ant;
import com.tobia.game.entities.Defense;
import com.tobia.game.entities.EnemyObserver;
import com.tobia.game.entities.Beetle;
import com.tobia.game.entities.Cannon;
import com.tobia.game.entities.Enemy;
import com.tobia.game.entities.Wasp;
import com.tobia.game.maps.Map;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * PlayState is the state where the game is played. 
 * It contains the map, enemies, cannons and buttons.
 * 
 */
public class PlayState extends State implements EnemyObserver, ButtonObserver {
    private List<Enemy> enemies;
    private Map map;
    private List<Defense> towers;
    private List<Button> buttons;
    private Texture backGround;

    private float money;
    private float health;

    protected PlayState(Map map) {
        super();

        TowerDefense.font = new BitmapFont();
        mouse = new Vector3(0,0,0);

        this.map = map;

        // Set the player's money and health
        this.money = 0;
        this.health = 100;


        cam.setToOrtho(false, TowerDefense.WIDTH, TowerDefense.HEIGHT);
        backGround = map.getBackground();

        System.out.println(map.f(2));

        enemies = new ArrayList<>();
        Enemy ant1 = new Ant(1, map.f(1), this);
        enemies.add(ant1);


        towers = new ArrayList<>();
        Cannon cannon1 = new Cannon(500,200);
        towers.add(cannon1);
        towers.add(new Cannon(700, 600));

        buttons = new ArrayList<>();
        buttons.add(new PlaceTowerButton(200, 10, this));

    }

    public Enemy createRandomEnemy() {
        Random random = new Random();
        int randomNumber = random.nextInt(0,3);

        if (randomNumber == 1)
            return new Beetle(1,map.f(1), this);

        if (randomNumber == 2)
            return new Wasp(1,map.f(1), this);
        
        return new Ant(1, map.f(1), this);


    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()){
            Enemy enemy = createRandomEnemy();
            enemies.add(enemy);
        }
    }

    @Override
    public void justClicked() {
        gameStateManager.setOverlapping(new PlaceTowerState());
    }

    public void antDied(Enemy enemy){
        enemies.remove(enemy);
        money += enemy.getCashForKill();
        enemy.dispose();
    }


    @Override
    protected void update(float deltaTime) {

        handleInput();
        for (int i = 0; i< towers.size(); i++){
            towers.get(i).aim(enemies);
            towers.get(i).update(deltaTime);
        }

        for (int i = 0; i< enemies.size(); i++){
            enemies.get(i).update(deltaTime, map);
        }

        cam.update();
        mouse.set(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY(), 0);
        buttons.get(0).update(mouse);
    }

    @Override
    public void render(SpriteBatch spriteBatch) {

        spriteBatch.setProjectionMatrix(cam.combined);
        spriteBatch.begin();
        spriteBatch.draw(backGround, cam.position.x- (cam.viewportWidth/2), 0);

        for (Enemy ant: enemies){
           spriteBatch.draw(ant.getTexture(), ant.getPosition().x, ant.getPosition().y, ant.getHitbox().width / 2, ant.getHitbox().height / 2, ant.getHitbox().width, ant.getHitbox().height, 1, 1, ant.getRotation());
        }
        for (Defense cannon: towers){
            spriteBatch.draw(cannon.getTexture(), cannon.getPosition().x, cannon.getPosition().y, cannon.getTextureHitbox().width / 2, cannon.getHitbox().height / 4, cannon.getHitbox().width, cannon.getHitbox().height *2, 1, 1, cannon.getRotation());
        }
        for (Button button: buttons){
            spriteBatch.draw(button.getTexture(), button.getPosition().x, button.getPosition().y, button.getHitBox().width, button.getHitBox().height);

        }

        TowerDefense.font.draw(spriteBatch, ("Spenn: " + money), 500, 50);



        spriteBatch.end();
    }

    @Override
    public void dispose() {

        backGround.dispose();
        System.out.println("PLay state disposed");

    }

    public Map getMap(){
        return map;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public List<Defense> getTowers() {
        return towers;
    }

    public void addCannons(Defense tower){
        towers.add(tower);
    }


    public List<Button> getButtons() {
        return buttons;
    }

    public float getMoney() {
        return money;
    }

    public void pay(float payment){
        money -= payment;
    }

    @Override
    public void antReachedEnd(Enemy enemy) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'antReachedEnd'");
    }
}
