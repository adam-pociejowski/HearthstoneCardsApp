package com.example.valverde.hearthstonecardsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.valverde.hearthstonecardsapp.api.model.Card;
import com.example.valverde.hearthstonecardsapp.database.DatabaseHelper;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CardPresentationActivity extends AppCompatActivity {
    @BindView(R.id.cardNameField) TextView nameField;
    @BindView(R.id.cardIdField) TextView idField;
    @BindView(R.id.cardTypeField) TextView typeField;
    @BindView(R.id.cardRarityField) TextView rarityField;
    @BindView(R.id.cardManaCostField) TextView manaCostField;
    @BindView(R.id.cardClassField) TextView classField;
    @BindView(R.id.addToFavoriteButton) Button addToFavoriteButton;
    @BindView(R.id.backToMainMemuButton) Button mainMenuButton;
    private boolean isFavorite = false;
    private DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_presentation);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        final Card card = (Card) intent.getSerializableExtra("card");
        db = new DatabaseHelper(getApplicationContext());
        isFavorite = db.checkIfIsFavorite(card.getName());
        setTextInTextViews(card);
        setFavoriteButtonText();

        mainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

        addToFavoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFavorite) {
                    db.deleteFavoriteCard(card.getName());
                    isFavorite = false;
                }
                else {
                    db.addFavoriteCard(card);
                    isFavorite = true;
                }
                setFavoriteButtonText();
            }
        });
    }


    void setFavoriteButtonText() {
        if (isFavorite)
            addToFavoriteButton.setText(getString(R.string.deleteFromFavorites));
        else
            addToFavoriteButton.setText(getString(R.string.addToFavorites));
    }

    void setTextInTextViews(Card card) {
        nameField.setText(card.getName());
        idField.setText(card.getCardId());
        typeField.setText(card.getType());

        String rarity = card.getRarity();
        if (rarity == null)
            rarity = "Free";
        rarityField.setText(rarity);
        String manaCost = Integer.toString(card.getCost());
        manaCostField.setText(manaCost);

        String className = card.getPlayerClass();
        if (className == null)
            className = "All";
        classField.setText(className);
    }
}