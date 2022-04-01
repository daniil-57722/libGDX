package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Image;


public class MainMenuScreen  implements Screen, InputProcessor {
    GameClass game;
    OrthographicCamera camera;
    Texture btnStartTexture;
    Texture btnScoreTexture;
    Texture btnSound;
    Preferences preferences;
    boolean sound;
    long lastTouch;
    Texture logo;

    public MainMenuScreen(GameClass gam) {
        preferences = Gdx.app.getPreferences("MyPrefs");
        sound = preferences.getBoolean("sound");
        this.game = gam;
        btnScoreTexture = new Texture("scoreBtn.png");
        btnStartTexture = new Texture("startBtn.png");
        logo = new Texture("Tetris.png");
        if(sound){
            btnSound = new Texture("soundOn.png");}
        else{
            btnSound = new Texture("soundOff.png");
        }
        camera = new OrthographicCamera();

        camera.setToOrtho(false, 800, 480);
    }

    @Override
    public void show() {
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.9f, 0.9f, 0.9f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(logo,240, 340, 320, 130);
        game.batch.draw(btnSound, 690, 20, 90,40);
        game.batch.draw(btnStartTexture, 100, 280, 600, 70);
        game.batch.draw(btnScoreTexture, 100,180,600,80);
        game.batch.end();
        if (Gdx.input.isTouched()){
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            if (touchPos.x>=150&&touchPos.x<=650&&touchPos.y>=280&&touchPos.y<=380){
                game.setScreen(new Tetris(game));
            }
            else if (touchPos.x>=150&&touchPos.x<=650&&touchPos.y>=180&&touchPos.y<=260){
                game.setScreen(new ScoreScreen(game));
            }
            else if (touchPos.x>690&&touchPos.x<=780&&touchPos.y>20&&touchPos.y<60&&
                    System.currentTimeMillis()-lastTouch >=300){
                game.batch.begin();
                lastTouch = 0;
                if (sound){
                    preferences.putBoolean("sound", false);
                    preferences.flush();
                    btnSound = new Texture("soundOff.png");
                    sound = preferences.getBoolean("sound");
                } else{
                    preferences.putBoolean("sound", true);
                    preferences.flush();
                    btnSound = new Texture("soundOn.png");
                    sound = preferences.getBoolean("sound");
                }
                lastTouch = System.currentTimeMillis();
                Gdx.gl.glClearColor(1, 1, 1, 0.8f);
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                game.batch.draw(btnSound, 700, 500, 90,90);
                game.batch.draw(btnStartTexture, 100, 300, 600, 70);
                game.batch.draw(btnScoreTexture, 100,200,600,80);
                game.batch.end();
            }
        }
        camera.update();

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

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}