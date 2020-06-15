package com.example.task3;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Type_Modal_Class {

    @SerializedName("damage_relation")
    private List<dr> damage_relation = null;
    @SerializedName("game_indices")
    private List<gi> game_indices = null;
    @SerializedName("generation")
    private poke.Pokemon generation = null;
    @SerializedName("id")
    private int id;
    @SerializedName("move_damage_class")
    private poke.Pokemon md_class = null;
    @SerializedName("moves")
    private List<poke.Pokemon> moves = null;
    @SerializedName("name")
    private String name;
    @SerializedName("names")
    private List<nom> names = null;
    @SerializedName("pokemon")
    private List<p_mon> pokemon = null;

    public Type_Modal_Class(List<dr> damage_relation, List<gi> game_indices, poke.Pokemon generation,
                            int id, poke.Pokemon md_class, List<poke.Pokemon> moves, String name,
                            List<nom> names, List<p_mon> pokemon) {
        this.damage_relation = damage_relation;
        this.game_indices = game_indices;
        this.generation = generation;
        this.id = id;
        this.md_class = md_class;
        this.moves = moves;
        this.name = name;
        this.names = names;
        this.pokemon = pokemon;
    }

    public List<p_mon> getP_mon() {
        return pokemon;
    }

    public class dr {
        private List<poke.Pokemon> double_damage_from = null;
        private List<poke.Pokemon> double_damage_to = null;
        private List<poke.Pokemon> half_damage_from = null;
        private List<poke.Pokemon> half_damage_to = null;
        private List<poke.Pokemon> no_damage_from = null;
        private List<poke.Pokemon> no_damage_to = null;

        public dr(List<poke.Pokemon> dd_from, List<poke.Pokemon> dd_to,
                  List<poke.Pokemon> hd_from, List<poke.Pokemon> hd_to,
                  List<poke.Pokemon> nd_from, List<poke.Pokemon> nd_to) {
            this.double_damage_from = dd_from;
            this.double_damage_to = dd_to;
            this.half_damage_from = hd_from;
            this.half_damage_to = hd_to;
            this.no_damage_from = nd_from;
            this.no_damage_to = nd_to;
        }
    }

    public class gi {

        @SerializedName("game_index")
        private int game_index;
        @SerializedName("generation")
        private poke.Pokemon generation = null;

        public gi(int game_index, poke.Pokemon generation) {
            this.game_index = game_index;
            this.generation = generation;
        }
    }

    public class p_mon {
        @SerializedName("pokemon")
        private extra extra = null;
        @SerializedName("slot")
        private int slot;

        public p_mon.extra getExtra() {
            return extra;
        }

        public class extra {
           String name;
           String url;

           public extra(String name, String url) {
               this.name = name;
               this.url = url;
           }

           public String getName() {
               return name;
           }

           public String getUrl() {
               return url;
           }
       }
    }

    public class nom {
        @SerializedName("language")
        private full full = null;
        @SerializedName("slot")
        private String cName;

        public class full {
            String name;
            String url;

            public full(String name, String url) {
                this.name = name;
                this.url = url;
            }
        }
    }

}
