package com.example.valverde.hearthstonecardsapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.valverde.hearthstonecardsapp.api.model.Card;
import com.example.valverde.hearthstonecardsapp.api.remote.HearthstoneAPI;
import com.example.valverde.hearthstonecardsapp.database.DatabaseHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CardsListActivity extends AppCompatActivity {
    @BindView(R.id.cardsListView) ListView listView;
    @BindView(R.id.cardIdLabel) TextView idLabel;
    @BindView(R.id.cardNameLabel) TextView nameLabel;
    @BindView(R.id.cardCostLabel) TextView costLabel;
    private MyListViewAdapter myListAdapter;
    private List<Card> cards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards_list);
        ButterKnife.bind(this);
        cards = new ArrayList<>();
        Intent i = getIntent();
        String mode = i.getStringExtra("mode");
        nameLabel.setText(getString(R.string.gettingCardsLabel));

        if (mode.equals("allCards")) {
            Map<String, String> queryMap = new HashMap<>();
            setDefaultRequestParams(queryMap);
            HearthstoneAPI.RetrofitFactory.getInstance().getAllCards(queryMap).enqueue(new Callback<List<Card>>() {
                @Override
                public void onResponse(Call<List<Card>> call, Response<List<Card>> response) {
                    cards = response.body();
                    if (cards == null)
                        showFailureMessage();
                    else
                        addListView();
                }

                @Override
                public void onFailure(Call<List<Card>> call, Throwable t) {
                    Log.e("API onFailure Callback", "Error: "+t.toString());
                    showFailureMessage();
                }
            });
        }
        else if (mode.equals("favoriteCards")) {
            DatabaseHelper db = new DatabaseHelper(getApplicationContext());
            cards = db.getFavoriteCards();
            addListView();
        }
        else { /* Specific search results */
            final String searchKey = i.getStringExtra("searchKey");
            Map<String, String> queryMap = new HashMap<>();
            setDefaultRequestParams(queryMap);
            if (mode.equals("byName")) {
                HearthstoneAPI.RetrofitFactory.getInstance().getCardByName(searchKey, queryMap).enqueue(new Callback<List<Card>>() {
                    @Override
                    public void onResponse(Call<List<Card>> call, Response<List<Card>> response) {
                        cards = response.body();
                        if (cards == null) {
                            String alert = "No result found with name: \""+searchKey+"\"";
                            nameLabel.setText(alert);
                        }
                        else {
                            addListView();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Card>> call, Throwable t) {
                        Log.e("API onFailure Callback", "Error: "+t.toString());
                        showFailureMessage();
                    }
                });
            }
            else if (mode.equals("byClass")) {
                HearthstoneAPI.RetrofitFactory.getInstance().getCardByClass(searchKey, queryMap).enqueue(new Callback<List<Card>>() {
                    @Override
                    public void onResponse(Call<List<Card>> call, Response<List<Card>> response) {
                        cards = response.body();
                        if (cards == null)
                            showFailureMessage();
                        else
                            addListView();
                    }

                    @Override
                    public void onFailure(Call<List<Card>> call, Throwable t) {
                        Log.e("API onFailure Callback", "Error: "+t.toString());
                        showFailureMessage();
                    }
                });
            }
            else if (mode.equals("byType")) {
                HearthstoneAPI.RetrofitFactory.getInstance().getCardByType(searchKey, queryMap).enqueue(new Callback<List<Card>>() {
                    @Override
                    public void onResponse(Call<List<Card>> call, Response<List<Card>> response) {
                        cards = response.body();
                        if (cards == null)
                            showFailureMessage();
                        else
                            addListView();
                    }

                    @Override
                    public void onFailure(Call<List<Card>> call, Throwable t) {
                        Log.e("API onFailure Callback", "Error: "+t.toString());
                        showFailureMessage();
                    }
                });
            }
        }
    }

    void showFailureMessage() {
        Toast.makeText(getApplicationContext(),
                getString(R.string.checkInternetAlert), Toast.LENGTH_LONG).show();
        String alert = "No results found..";
        nameLabel.setText(alert);
    }

    void setLabelsText() {
        idLabel.setText(getString(R.string.idField));
        nameLabel.setText(getString(R.string.nameField));
        costLabel.setText(getString(R.string.costField));
    }

    void addListView() {
        myListAdapter = new MyListViewAdapter(this);
        listView.setAdapter(myListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Card card = cards.get(i);
                Intent intent = new Intent(getApplicationContext(), CardPresentationActivity.class);
                intent.putExtra("card", card);
                startActivity(intent);
            }
        });
        setLabelsText();
    }

    private void setDefaultRequestParams(Map<String, String> queryMap) {
        queryMap.put("locale", "enGB");
        queryMap.put("collectible", "true");
    }


    class MyListViewAdapter extends BaseAdapter {
        private Context context;

        public MyListViewAdapter(Context context) { this.context = context; }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View customView  = layoutInflater.inflate(R.layout.cards_listview_item, parent, false);
            TextView idField = (TextView) customView.findViewById(R.id.cardNumberField);
            TextView nameField = (TextView) customView.findViewById(R.id.cardNameField);
            TextView costField = (TextView) customView.findViewById(R.id.manaCostField);
            Card card = cards.get(position);
            String id = Integer.toString(position + 1);
            idField.setText(id);
            nameField.setText(card.getName());
            String manaCost = Integer.toString(card.getCost());
            costField.setText(manaCost);
            return customView;
        }

        @Override
        public int getCount() { return cards.size(); }

        @Override
        public Object getItem(int position) { return cards.get(position); }

        @Override
        public long getItemId(int position) { return position; }
    }
}