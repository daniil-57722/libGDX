package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public class Tetris implements Screen {
GameClass game;
  OrthographicCamera camera;
Texture backToMenu;
  public Tetris(GameClass game) {
    this.game = game;
    backToMenu = new Texture("Menu/backToMenu.png");

    camera = new OrthographicCamera();
    camera.setToOrtho(false, 800, 480);
  }

  @Override
  public void show() {

  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(1, 1, 1, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    game.batch.begin();
    game.batch.draw(backToMenu, 650,100, 100, 50);
    game.batch.end();
    if (Gdx.input.isTouched()){
      Vector3 touchPos = new Vector3();
      touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
      camera.unproject(touchPos);
      if (touchPos.x>=650&&touchPos.x<=750&&touchPos.y>=100&&touchPos.y<=150){
        game.setScreen(new MainMenuScreen(game));
      }
    }
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
}
