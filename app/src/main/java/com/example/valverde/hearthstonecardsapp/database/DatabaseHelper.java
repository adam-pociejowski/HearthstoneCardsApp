package com.example.valverde.hearthstonecardsapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.valverde.hearthstonecardsapp.api.model.Card;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "hearthstoneCardsAppDB.db";
    private static final int VERSION = 1;
    private static final String TABLE_NAME = "favoriteCards";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "CARD_ID";
    private static final String COL_3 = "NAME";
    private static final String COL_4 = "TYPE";
    private static final String COL_5 = "MANA_COST";
    private static final String COL_6 = "RARITY";
    private static final String COL_7 = "CLASS";
    public static final String SQL_CREATE_QUERY = "CREATE TABLE "+TABLE_NAME+
            " ("+COL_1+" INTEGER PRIMARY KEY AUTOINCREMENT," +
            COL_2+" TEXT,"+
            COL_3+" TEXT,"+
            COL_4+" TEXT,"+
            COL_5 +" INTEGER, "+
            COL_6+" TEXT, "+
            COL_7+" TEXT)";
    public static final String SQL_DROP_QUERY = "DROP TABLE IF EXISTS "+TABLE_NAME;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_QUERY);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DROP_QUERY);
        onCreate(sqLiteDatabase);
    }


    public boolean checkIfIsFavorite(String cardName) {
        SQLiteDatabase db = getReadableDatabase();
        String SQL = "select * from "+TABLE_NAME+" where "+COL_3+"=\'"+cardName+"\'";
        Cursor c = db.rawQuery(SQL, null);
        if (c.getCount() <= 0){
            c.close();
            return false;
        }
        c.close();
        return true;
    }

    public void deleteFavoriteCard(String cardName) {
        SQLiteDatabase db = getWritableDatabase();
        String SQL = "delete from "+TABLE_NAME+" where "+COL_3+"=\'"+cardName+"\'";
        db.execSQL(SQL);
    }

    public boolean addFavoriteCard(Card card) {
        if (!checkIfIsFavorite(card.getName())) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_2, card.getCardId());
            contentValues.put(COL_3, card.getName());
            contentValues.put(COL_4, card.getType());
            contentValues.put(COL_5, card.getCost());
            contentValues.put(COL_6, card.getRarity());
            contentValues.put(COL_7, card.getPlayerClass());
            long result = db.insert(TABLE_NAME, null, contentValues);
            if (result == -1) return false;
            return true;
        }
        return false;

    }

    public List<Card> getFavoriteCards() {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = { COL_1, COL_2, COL_3, COL_4, COL_5, COL_6, COL_7 };
        Cursor c = db.query(TABLE_NAME, projection, null, null, null, null, null);

        List<Card> favoriteCards = new ArrayList<>();
        if (c.moveToFirst()) {
            do {
                String id =c.getString(1);
                String name = c.getString(2);
                String type = c.getString(3);
                int cost = c.getInt(4);
                String rarity = c.getString(5);
                String playerClass = c.getString(6);
                Card card = new Card();
                card.setName(name);
                card.setCardId(id);
                card.setType(type);
                card.setCost(cost);
                card.setRarity(rarity);
                card.setPlayerClass(playerClass);

                favoriteCards.add(card);
            } while (c.moveToNext());
            c.close();
        }
        return favoriteCards;
    }
}