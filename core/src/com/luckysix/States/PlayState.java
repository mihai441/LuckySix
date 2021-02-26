package com.luckysix.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.luckysix.Numbers.Cota;
import com.luckysix.Numbers.number;
import com.luckysix.Saved.PreferenceMenu;
import com.luckysix.Textures.sprites;
import com.luckysix.Numbers.urna;

import java.util.ArrayList;

/**
 * Created by AsX on 7/4/2017.
 */

public class PlayState extends state {


    //Camera
    private OrthographicCamera cam;
    private final float GAME_HEIGHT = 1080;
    private float ratio = (float)Gdx.graphics.getWidth()/(float)Gdx.graphics.getHeight();
    private final float GAME_WIDTH = GAME_HEIGHT * ratio;


    //Numere
    private ArrayList<number> alese;
    private urna urna;
    private ArrayList<Boolean> numereIesite;
    private ArrayList<number> extrase;

    //Texturi
    private Texture background;
    private Texture lightRay;
    private Texture menu;
    private ArrayList<Texture> textures;

    //Integers
    private final int extraseSize=35;
    public int nrExtrasePanaAcum=0;
    public int marimetextura=84;
    private float soundPitch=1;
    public static float counter=0;
    private int miza;
    private int credit;

    private PreferenceMenu PreferenceMenu;

    //Sound
    private Sound sound;
    private Sound win;

    private boolean isFinished = false;

    private Rectangle menuBounds;



    protected PlayState(GameStateManager gsm) {
        super(gsm);
        for (number number : alese = PickState.alese) {

        }


        //Camera
        cam = new OrthographicCamera(GAME_WIDTH,GAME_HEIGHT);
        cam.translate(cam.viewportWidth/2,cam.viewportHeight/2);


        //Sunet
        sound = Gdx.audio.newSound(Gdx.files.internal("Sounds/bingo.mp3"));
        win = Gdx.audio.newSound(Gdx.files.internal("Sounds/win.wav"));


        PreferenceMenu = new PreferenceMenu();

        miza = PickState.miza;
        credit = PreferenceMenu.getCredit();
        PreferenceMenu.setCredit(credit-miza);

        //Bounds
        menuBounds = new Rectangle( sprites.getManualX(660, GAME_WIDTH), sprites.getManualY(585 , GAME_HEIGHT), (560 * GAME_WIDTH) / 1920, (490 * GAME_HEIGHT) / 1080);



       //Numere
        numereIesite = new ArrayList<Boolean>();
        urna=new urna();
        extrase = new ArrayList<number>();
        for(int i=0;i<6;i++) {
            numereIesite.add(false);
        }


        //Texturi
        background = new Texture("background.png");
        lightRay = new Texture("lightray.png");
        menu = new Texture("menu.png");
        initiateTextures();

        //urna.shuffle();
        extragereNumere();

    }

    public void verificaDacaExistaNumarul(){

        for(int i=0; i<6;i++) {
            if (nrExtrasePanaAcum >= 0) {
                if (alese.get(i).getNumar() == extrase.get(nrExtrasePanaAcum).getNumar()) {
                    numereIesite.set(i, true);
                    if(toateNumereleiesite()){
                        PreferenceMenu.setCredit(credit + miza* Cota.VerificaCota(nrExtrasePanaAcum));
                        win.play();
                    }
                    else {
                        long id = sound.play(1.0f);
                        sound.setPitch(id, soundPitch);
                        soundPitch += 0.1f;
                    }
                }

            }
        }

    }


    private boolean toateNumereleiesite(){
        for(int i=0;i<6;i++)
            if(numereIesite.get(i)==false)
                return false;
        return true;
    }

    public void initiateTextures() {
        textures = new ArrayList<Texture>();
        for (int i = 0; i < urna.getMarimeUrna(); i++)
            textures.add(urna.Numere.get(i).getSprite());
    }



    public void extragereNumere(){

        for(int i=0; i <extraseSize;i++) {
            urna.get(urna.Numere, extrase);
        }

    }



    @Override
    protected void handleinput() {
        if (Gdx.input.isTouched()) {
            Vector3 tmp = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            cam.unproject(tmp);
            if (isFinished)
            if (menuBounds.contains(tmp.x, tmp.y)) {
                gsm.push(new MenuState(gsm));
                dispose();
                System.out.println("gata ba");
            }

        }
    }

    @Override
    public void update(float dt) {
        counter+=dt;
        marimetextura++;
        if(counter>3.0f){
            if(nrExtrasePanaAcum<extraseSize) {
                verificaDacaExistaNumarul();
                nrExtrasePanaAcum++;
                counter = 0;
                marimetextura=84;
            }
            else {
                isFinished=true;
                }
            }
            handleinput();
        }


    @Override
    public void render(SpriteBatch sb) {
        cam.update();
        sb.begin();
        sb.setProjectionMatrix(cam.combined);
        sb.draw(background,0,0, GAME_WIDTH,GAME_HEIGHT);
        for(int i=0;i<nrExtrasePanaAcum;i++)
        sb.draw(textures.get(extrase.get(i).getNumar()),sprites.getX(i,GAME_WIDTH),sprites.getY(i,GAME_HEIGHT),(84*GAME_WIDTH)/1920,(84*GAME_HEIGHT)/1080);

        for(int i=0;i<alese.size();i++){
            if (numereIesite.get(i)){
                sb.draw(lightRay,sprites.getManualX(650+(100*i)-12,GAME_WIDTH),sprites.getManualY(437,GAME_HEIGHT),(110*GAME_WIDTH)/1920,(110*GAME_HEIGHT)/1080);
            }
            sb.draw(alese.get(i).getSprite(),sprites.getManualX(650+(100*i),GAME_WIDTH),sprites.getManualY(450,GAME_HEIGHT),(84*GAME_WIDTH)/1920,(84*GAME_HEIGHT)/1080);
        }

        if(nrExtrasePanaAcum<extraseSize) {
            sb.draw(textures.get(extrase.get(nrExtrasePanaAcum).getNumar()), sprites.getManualX(905 - ((marimetextura - 84) / 2), GAME_WIDTH), sprites.getManualY(792 - ((marimetextura - 84) / 2), GAME_HEIGHT), (marimetextura * GAME_WIDTH) / 1920, (marimetextura * GAME_HEIGHT) / 1080);
        }

        if(isFinished)
        sb.draw(menu, sprites.getManualX(660, GAME_WIDTH), sprites.getManualY(585 , GAME_HEIGHT), (560 * GAME_WIDTH) / 1920, (490 * GAME_HEIGHT) / 1080);

        sb.end();
    }

    @Override
    public void dispose() {

        menu.dispose();
        for(int i=0;i<nrExtrasePanaAcum;i++)
            textures.get(i).dispose();
        lightRay.dispose();
    }


}
