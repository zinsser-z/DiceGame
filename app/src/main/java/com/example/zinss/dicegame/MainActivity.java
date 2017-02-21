package com.example.zinss.dicegame;

import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ImageView> imageViews;
    private ArrayList<Integer> numbersOfDice;
    private ImageView diceImage1;
    private ImageView diceImage2;
    private ImageView diceImage3;
    private TextView scoreBoard;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //函数初始化
        final Random ran = new Random();
        imageViews = new ArrayList<>();
        numbersOfDice = new ArrayList<>();
        score = 0;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        diceImage1 = (ImageView) findViewById(R.id.diceImage1);
        diceImage2 = (ImageView) findViewById(R.id.diceImage2);
        diceImage3 = (ImageView) findViewById(R.id.diceImage3);
        scoreBoard = (TextView) findViewById(R.id.scoreBoard);

        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.changeButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageViews.clear();
                numbersOfDice.clear();
                imageViews.add(diceImage1);
                imageViews.add(diceImage2);
                imageViews.add(diceImage3);

                for(int i=0 ; i<3 ; i++)
                {
                    numbersOfDice.add(ran.nextInt(6)+1);
                    String filename = "die_"+String.valueOf(numbersOfDice.get(i))+".png";
                    //System.out.println(filename);
                    try{
                        AssetManager assetManager = getAssets();
                        InputStream inputStream = assetManager.open(filename);
                        Drawable drawable = Drawable.createFromStream(inputStream,null);
                        //填充imageview
                        imageViews.get(i).setImageDrawable(drawable);
                    }
                    catch (IOException e){
                        e.getStackTrace();
                    }
                }
                System.out.println(numbersOfDice.get(0));
                System.out.println(numbersOfDice.get(1));
                System.out.println(numbersOfDice.get(2));

                if (numbersOfDice.get(0)==numbersOfDice.get(1)
                        && numbersOfDice.get(1)==numbersOfDice.get(2))
                {//如果3个数字相同
                    score = score + 100 * numbersOfDice.get(0);
                }
                else if(numbersOfDice.get(0)==numbersOfDice.get(1)
                        ||numbersOfDice.get(1)==numbersOfDice.get(2)
                        ||numbersOfDice.get(0)==numbersOfDice.get(2))
                {//其中有两个一样的
                    score = score + 50;
                }
                scoreBoard.setText("Score:" + score);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
