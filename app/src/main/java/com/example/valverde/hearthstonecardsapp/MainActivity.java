package com.example.valverde.hearthstonecardsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.allCardsButton) Button allCardsButton;
    @BindView(R.id.myFavoriteCardsButton) Button myFavoriteCardsButton;
    @BindView(R.id.advancedSearchButton) Button advancedSearchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        allCardsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CardsListActivity.class);
                intent.putExtra("mode", "allCards");
                startActivity(intent);
            }
        });

        myFavoriteCardsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CardsListActivity.class);
                intent.putExtra("mode", "favoriteCards");
                startActivity(intent);
            }
        });

        advancedSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdvancedSearchMenuActivity.class);
                startActivity(intent);
            }
        });
    }
}