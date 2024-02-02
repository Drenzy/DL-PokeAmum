package com.example.pokeamum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.security.PrivateKey;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    List<Player> players;
    TextView pPoint;
    TextView pPoint2;
    TextView roundCount;
    ImageView pCard;
    Button nextRound;
    Card card;

    Integer cardIndex = 0;
    Integer playIndex = 1;
    ImageView pic;
    LinearLayout item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        players = (List<Player>) getIntent().getSerializableExtra("Players");
        initGui();
        getInfo();

        //when the button is clicked
        Button myButton = findViewById(R.id.nxt_round);
        myButton.setOnClickListener(new View.OnClickListener() {
            int p1point = 0;
            int p2point = 0;
            @Override
            public void onClick(View v) {

                if(players.get(0).cards.get(cardIndex).hp > players.get(1).cards.get(cardIndex).hp){
                    Toast.makeText(GameActivity.this, "Player 1 Won!",
                            Toast.LENGTH_SHORT).show();
                    p1point++;
                    pPoint.setText("p1 points:" + p1point);
                }else if(players.get(0).cards.get(cardIndex).hp < players.get(1).cards.get(cardIndex).hp){
                    Toast.makeText(GameActivity.this, "Player 2 Won!",
                            Toast.LENGTH_SHORT).show();
                    p2point++;
                    pPoint2.setText("p2 points: " + p2point);
                }else {
                    Toast.makeText(GameActivity.this, "Draw!", Toast.LENGTH_SHORT).show();
                }

                players.get(0).cards.remove(0);
                players.get(1).cards.remove(0);
                for (int i = 0; i <= item.getChildCount(); i++) {
                    item.removeView(item.getChildAt(i));
                }

                getInfo();

                //Toast.makeText(GameActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void initGui(){
        pPoint = findViewById(R.id.player_point);
        pPoint2 = findViewById(R.id.player_point2);
        roundCount = findViewById(R.id.round_count);
        nextRound = findViewById(R.id.nxt_round);
    }
    private void getInfo() {

        Random rnd = new Random();


//        if(players.get(playIndex).cards.get(cardIndex).image == null){
//            String a = "its null";
//            Log.d("a",a);
//            return;
//        }
        //getting the cards on screen
        for (int i = 0; i < players.size(); i++){
            item = (LinearLayout) findViewById(R.id.t_t);
            ImageView pic = (ImageView) getLayoutInflater().inflate(R.layout.card, null);
            item.addView(pic);

            //Picasso.get().invalidate(players.get(i).cards.get(cardIndex).image+"/high.jpg");
            Picasso.get().load(
                    players.get(i).cards.get(cardIndex).image+"/high.jpg")
                    .networkPolicy(NetworkPolicy.NO_CACHE).memoryPolicy(MemoryPolicy.NO_CACHE)
                    .into(pic);

        }
    }
}