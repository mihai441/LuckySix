package com.luckysix.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.luckysix.Numbers.number;
import com.luckysix.Saved.PreferenceMenu;
import com.luckysix.Textures.texturelocation;
import com.luckysix.Numbers.urna;
import com.luckysix.Textures.sprites;


import java.util.ArrayList;

import static com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.*;


/**
 * Created by AsX on 7/8/2017.
 */

public class PickState extends state {

    //Texturi
    private Texture background;
    private ArrayList<Texture> textures;
    private Texture randomButton;
    private Texture button;
    private Texture arrowUp;
    private Texture arrowDown;

    //Bounds
    private Rectangle buttonbounds;
    private Rectangle randomButtonbounds;
    private Rectangle upbuttonbounds;
    private Rectangle downbuttonbounds;
    private ArrayList<texturelocation> texturelocation;
    private ArrayList<Rectangle> textureBounds;
    private ArrayList<Rectangle> textureBounds2;


    //Integers
    private float counter=0;
    private boolean delay;
    private final int nrAlese=6;
    private int cont = 0;
    protected static int miza=1;


    //Prefs
    private PreferenceMenu preferenceMenu;
    private int credit;

    //Numere
    public static ArrayList<number> alese;
    protected urna urna;


    //Rezolutie
    private OrthographicCamera cam;
    private final float GAME_HEIGHT = 1080;
    float ratio = (float) Gdx.graphics.getWidth()/(float)Gdx.graphics.getHeight();
    private final float GAME_WIDTH = GAME_HEIGHT * ratio;

    //Font
    BitmapFont font;
    BitmapFont fontmiza;
    FreeTypeFontGenerator generator;
    FreeTypeFontParameter parameter;


    public PickState(GameStateManager gsm) {
        super(gsm);

        //Preferences
        preferenceMenu = new PreferenceMenu();
        credit = preferenceMenu.getCredit();

        //Font
        generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/creditfont.ttf"));
        parameter = new FreeTypeFontParameter();
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 3;
        parameter.size = 80;
        font = generator.generateFont(parameter);
        parameter.size = 60;
        fontmiza = generator.generateFont(parameter);

        //Camera
        cam = new OrthographicCamera(GAME_WIDTH,GAME_HEIGHT);
        cam.translate(cam.viewportWidth/2,cam.viewportHeight/2);

        //Numere
        alese = new ArrayList<number>();
        urna=new urna();

        //Texturi
        background = new Texture("backgrounds.jpg");
        button=new Texture("button.png");
        randomButton = new Texture("randombutton.png");
        arrowUp = new Texture("uparrow.png");
        arrowDown = new Texture("downarrow.png");
        textures = new ArrayList<Texture>();
        initiateTextures();

        //Bounds
        textureBounds = new ArrayList<Rectangle>();
        textureBounds2 = new ArrayList<Rectangle>();
        texturelocation = new ArrayList<texturelocation>();
        initTextureLocation();
        initbounds();

    }

    void initTextureLocation(){
        for(int i=950;i>-100;i-=150)
            for(int j=90;j<1140;j+=150) {
                texturelocation.add(new texturelocation(j, i));
            }
    }


    private void initbounds(){
        for(int i=0;i<49;i++)
            textureBounds.add(new Rectangle(sprites.getManualX(texturelocation.get(i).getX(), GAME_WIDTH),sprites.getManualY(texturelocation.get(i).getY(), GAME_HEIGHT),(100 * GAME_WIDTH) / 1920,(100 * GAME_HEIGHT) / 1080));

        for(int i=0;i<6;i++){
            textureBounds2.add(new Rectangle(sprites.getManualX(1200 + (105 * i), GAME_WIDTH), sprites.getManualY(650, GAME_HEIGHT), (85 * GAME_WIDTH) / 1920, (85 * GAME_HEIGHT) / 1080));

        }

        buttonbounds = new Rectangle(sprites.getManualX(1455,GAME_WIDTH),sprites.getManualY(220,GAME_HEIGHT), (110 * GAME_WIDTH) / 1920, (110 * GAME_HEIGHT) / 1080);

        randomButtonbounds = new Rectangle(sprites.getManualX(1435,GAME_WIDTH),sprites.getManualY(475,GAME_HEIGHT), (160 * GAME_WIDTH) / 1920, (65 * GAME_HEIGHT) / 1080);

        downbuttonbounds = new Rectangle(sprites.getManualX(1475,GAME_WIDTH),sprites.getManualY(780,GAME_HEIGHT), (75 * GAME_WIDTH) / 1920, (50 * GAME_HEIGHT) / 1080);
        upbuttonbounds = new Rectangle(sprites.getManualX(1475,GAME_WIDTH),sprites.getManualY(905,GAME_HEIGHT), (75 * GAME_WIDTH) / 1920, (50 * GAME_HEIGHT) / 1080);


    }
    private boolean alesecontains(int x){
        for(int i=0;i<alese.size();i++)
            if(x == alese.get(i).getNumar())
                return true;
        return false;
    }

    private void setDownButton(){

        if(miza>1)
        miza--;
        delay = true;

    }

    private void setUpButton(){
            miza++;
        delay = true;

    }


    private void setRandomButton(){

        alese.clear();
        while (alese.size()<nrAlese){
            int rand = MathUtils.random(0,48);
            if(!alesecontains(rand)){
                alese.add(new number(rand));
            }
        }
    }
    private void initiateTextures(){
        for(int i=0;i<49;i++)
            textures.add(urna.Numere.get(i).getSprite());
    }
    @Override
    protected void handleinput() {
        if(Gdx.input.isTouched())
        {
            Vector3 tmp=new Vector3(Gdx.input.getX(),Gdx.input.getY(),0);
            cam.unproject(tmp);

            for(int i=0;i<49;i++)
            if(textureBounds.get(i).contains(tmp.x,tmp.y))
            {
                if (alese.size()>=nrAlese){System.out.println("Prea multe numere");}

                else if(alesecontains(i)){}

                else {
                    alese.add(new number(i));
                    break;
                }
                    }

            if (randomButtonbounds.contains(tmp.x,tmp.y)){
                setRandomButton();
            }

            if (upbuttonbounds.contains(tmp.x,tmp.y)){
                setUpButton();
            }

            if (downbuttonbounds.contains(tmp.x,tmp.y)){
                setDownButton();
            }
            for( int i=0;i<6;i++)
                if(textureBounds2.get(i).contains(tmp.x,tmp.y))
                    if(alese.size()>i) {
                        alese.remove(i);
                        delay = true;
                        break;
                    }
                    if (buttonbounds.contains(tmp.x,tmp.y))
                        if(alese.size()==6) {
                            gsm.push(new PlayState(gsm));
                            dispose();
                        }
                      }

        }

    @Override
    public void update(float dt) {

        if(delay==false)
        handleinput();

        if (delay == true) {
            counter+=dt;
            if (counter > 0.5f) {
                    counter = 0;
                delay=false;
                }
            }

        }

    @Override
    public void render(SpriteBatch sb) {
        cam.update();
        sb.begin();
        sb.setProjectionMatrix(cam.combined);
        sb.draw(background,0,0, GAME_WIDTH,GAME_HEIGHT);
        cont = 0;
        for(int i=950;i>-100;i-=150)
            for(int j=90;j<1140;j+=150) {
                texturelocation.add(new texturelocation(j,i));
                sb.draw(textures.get(cont++), sprites.getManualX(j, GAME_WIDTH), sprites.getManualY(i, GAME_HEIGHT), (100 * GAME_WIDTH) / 1920, (100 * GAME_HEIGHT) / 1080);
            }
        for(int i=0;i<alese.size();i++) {
      //      System.out.println(alese.get(i).getNumar());
            sb.draw(alese.get(i).getSprite(), sprites.getManualX(1200 + (105 * i), GAME_WIDTH), sprites.getManualY(650, GAME_HEIGHT), (85 * GAME_WIDTH) / 1920, (85 * GAME_HEIGHT) / 1080);

        }

        sb.draw(randomButton,sprites.getManualX(1435,GAME_WIDTH),sprites.getManualY(475,GAME_HEIGHT), (160 * GAME_WIDTH) / 1920, (65 * GAME_HEIGHT) / 1080);
        sb.draw(arrowUp,sprites.getManualX(1475,GAME_WIDTH),sprites.getManualY(905,GAME_HEIGHT), (75 * GAME_WIDTH) / 1920, (50 * GAME_HEIGHT) / 1080);
        sb.draw(arrowDown,sprites.getManualX(1475,GAME_WIDTH),sprites.getManualY(780,GAME_HEIGHT), (75 * GAME_WIDTH) / 1920, (50 * GAME_HEIGHT) / 1080);

        if(alese.size()==6)
            sb.draw(button,sprites.getManualX(1455,GAME_WIDTH),sprites.getManualY(220,GAME_HEIGHT), (110 * GAME_WIDTH) / 1920, (110 * GAME_HEIGHT) / 1080);

        font.draw(sb,"Credit: " + Integer.toString(credit),sprites.getManualX(1370,GAME_WIDTH),sprites.getManualY(1050,GAME_HEIGHT));
        font.draw(sb,"Miza:",sprites.getManualX(1350,GAME_WIDTH),sprites.getManualY(900,GAME_HEIGHT));
        font.draw(sb, Integer.toString(miza),sprites.getManualX(1500,GAME_WIDTH),sprites.getManualY(900,GAME_HEIGHT));


        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        randomButton.dispose();
        button.dispose();
        for(int i=0;i<textures.size();i++)
            textures.get(i).dispose();
    }
}




