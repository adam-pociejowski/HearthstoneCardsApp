package com.example.valverde.hearthstonecardsapp.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
* Generated by jsonschema2pojo.org
*/
public class Card implements Serializable {

   @SerializedName("cardId")
   @Expose
   private String cardId;
   @SerializedName("name")
   @Expose
   private String name;
   @SerializedName("cardSet")
   @Expose
   private String cardSet;
   @SerializedName("type")
   @Expose
   private String type;
   @SerializedName("text")
   @Expose
   private String text;
   @SerializedName("locale")
   @Expose
   private String locale;
   @SerializedName("rarity")
   @Expose
   private String rarity;
   @SerializedName("artist")
   @Expose
   private String artist;
   @SerializedName("playerClass")
   @Expose
   private String playerClass;
   @SerializedName("img")
   @Expose
   private String img;
   @SerializedName("imgGold")
   @Expose
   private String imgGold;
   @SerializedName("mechanics")
   @Expose
   private List<Mechanic> mechanics = new ArrayList<Mechanic>();
   @SerializedName("cost")
   @Expose
   private int cost;
   @SerializedName("flavor")
   @Expose
   private String flavor;
   @SerializedName("collectible")
   @Expose
   private boolean collectible;
   @SerializedName("attack")
   @Expose
   private int attack;
   @SerializedName("health")
   @Expose
   private int health;
   @SerializedName("race")
   @Expose
   private String race;
   @SerializedName("durability")
   @Expose
   private int durability;
   @SerializedName("elite")
   @Expose
   private boolean elite;
   @SerializedName("howToGet")
   @Expose
   private String howToGet;
   @SerializedName("howToGetGold")
   @Expose
   private String howToGetGold;


   public String getCardId() {
       return cardId;
   }

   public void setCardId(String cardId) {
       this.cardId = cardId;
   }

   public String getName() {
       return name;
   }

   public void setName(String name) {
       this.name = name;
   }

   public String getCardSet() {
       return cardSet;
   }

   public void setCardSet(String cardSet) {
       this.cardSet = cardSet;
   }

   public String getType() {
       return type;
   }

   public void setType(String type) {
       this.type = type;
   }

   public String getText() {
       return text;
   }

   public void setText(String text) {
       this.text = text;
   }

   public String getLocale() {
       return locale;
   }

   public void setLocale(String locale) {
       this.locale = locale;
   }

   public String getRarity() {
       return rarity;
   }

   public void setRarity(String rarity) {
       this.rarity = rarity;
   }

   public String getArtist() {
       return artist;
   }

   public void setArtist(String artist) {
       this.artist = artist;
   }

   public String getPlayerClass() {
       return playerClass;
   }

   public void setPlayerClass(String playerClass) {
       this.playerClass = playerClass;
   }

   public String getImg() {
       return img;
   }

   public void setImg(String img) {
       this.img = img;
   }

   public String getImgGold() {
       return imgGold;
   }

   public void setImgGold(String imgGold) {
       this.imgGold = imgGold;
   }

   public List<Mechanic> getMechanics() {
       return mechanics;
   }

   public void setMechanics(List<Mechanic> mechanics) {
       this.mechanics = mechanics;
   }

   public int getCost() {
       return cost;
   }

   public void setCost(int cost) {
       this.cost = cost;
   }

   public String getFlavor() {
       return flavor;
   }

   public void setFlavor(String flavor) {
       this.flavor = flavor;
   }

   public boolean isCollectible() {
       return collectible;
   }

   public void setCollectible(boolean collectible) {
       this.collectible = collectible;
   }

   public int getAttack() {
       return attack;
   }

   public void setAttack(int attack) {
       this.attack = attack;
   }

   public int getHealth() {
       return health;
   }

   public void setHealth(int health) {
       this.health = health;
   }

   public String getRace() {
       return race;
   }

   public void setRace(String race) {
       this.race = race;
   }

   public int getDurability() {
       return durability;
   }

   public void setDurability(int durability) {
       this.durability = durability;
   }

   public boolean isElite() {
       return elite;
   }

   public void setElite(boolean elite) {
       this.elite = elite;
   }

   public String getHowToGet() {
       return howToGet;
   }


   public void setHowToGet(String howToGet) {
       this.howToGet = howToGet;
   }


   public String getHowToGetGold() {
       return howToGetGold;
   }


   public void setHowToGetGold(String howToGetGold) {
       this.howToGetGold = howToGetGold;
   }
}