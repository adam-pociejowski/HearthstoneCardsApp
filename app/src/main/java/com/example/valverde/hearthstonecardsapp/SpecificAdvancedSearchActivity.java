package com.example.valverde.hearthstonecardsapp;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SpecificAdvancedSearchActivity extends AppCompatActivity {
    @BindView(R.id.backToMainMemuButton) Button mainMenuButton;
    @BindView(R.id.advancedSearchButton) Button searchButton;
    @BindView(R.id.specificSearchTextView) TextView searchLabel;
    @BindView(R.id.specificSearchEditText) EditText searchEditText;
    @BindView(R.id.specificSearchSpinner) Spinner spinner;
    @BindView(R.id.advancedSpecificSearchLabel) TextView bigLabel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_advanced_search);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        final String searchMode = intent.getStringExtra("searchMode");
        setLayoutComponents(searchMode);
        setLabelsText(searchMode);

        mainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchKey = getSearchKey(searchMode);
                if (searchKey.equals("")) {
                    Toast.makeText(getApplicationContext(), "Field cannot be empty!",
                            Toast.LENGTH_LONG).show();
                }
                else {
                    Intent i = new Intent(getApplicationContext(), CardsListActivity.class);
                    i.putExtra("mode", searchMode);
                    i.putExtra("searchKey", searchKey);
                    startActivity(i);
                }
            }
        });
    }

    private String getSearchKey(String mode) {
        if (mode.equals("byName"))
            return searchEditText.getText().toString();
        else
            return spinner.getSelectedItem().toString();
    }

    void setLabelsText(String mode) {
        if (mode.equals("byName")) {
            searchLabel.setText(getString(R.string.cardNameField));
            bigLabel.setText(getString(R.string.searchByName));
        }
        else if (mode.equals("byClass")) {
            searchLabel.setText(getString(R.string.cardClassField));
            bigLabel.setText(getString(R.string.searchByClass));

        }
        else if (mode.equals("byType")) {
            searchLabel.setText(getString(R.string.cardTypeField));
            bigLabel.setText(getString(R.string.searchByType));
        }
    }

    void setLayoutComponents(String mode) {
        if (mode.equals("byName")) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ActionBar.LayoutParams.WRAP_CONTENT);
            params.weight = 0.0f;
            spinner.setLayoutParams(params);
            spinner.setVisibility(View.INVISIBLE);
        }
        else {
            ArrayAdapter<CharSequence> adapter;
            if (mode.equals("byClass")) {
                adapter = ArrayAdapter.createFromResource(this, R.array.classes,
                        android.R.layout.simple_spinner_item);
            }
            else {
                adapter = ArrayAdapter.createFromResource(this, R.array.types,
                        android.R.layout.simple_spinner_item);
            }
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {}

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {}
            });
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ActionBar.LayoutParams.WRAP_CONTENT);
            params.weight = 0.0f;
            searchEditText.setLayoutParams(params);
            searchEditText.setVisibility(View.INVISIBLE);
        }
    }
}