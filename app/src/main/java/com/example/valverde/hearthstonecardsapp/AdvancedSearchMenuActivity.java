package com.example.valverde.hearthstonecardsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AdvancedSearchMenuActivity extends AppCompatActivity {
    @BindView(R.id.backToMainMemuButton) Button mainMenuButton;
    @BindView(R.id.searchByNameButton) Button searchByNameButton;
    @BindView(R.id.searchByClassButton) Button searchByClassButton;
    @BindView(R.id.searchByTypeButton) Button searchByTypeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advanced_search_menu);
        ButterKnife.bind(this);
        mainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        searchByNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSpecificAdvancedSearchActivity("byName");
            }
        });

        searchByClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSpecificAdvancedSearchActivity("byClass");
            }
        });

        searchByTypeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSpecificAdvancedSearchActivity("byType");
            }
        });
    }

    private void startSpecificAdvancedSearchActivity(String param) {
        Intent intent = new Intent(getApplicationContext(), SpecificAdvancedSearchActivity.class);
        intent.putExtra("searchMode", param);
        startActivity(intent);
    }
}