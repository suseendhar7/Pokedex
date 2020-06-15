package com.example.task3;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class poke_info_modal_class {

    @SerializedName("abilities")
    List<ability> abilities = null;
    @SerializedName("base experience")
    int base_experience;
    @SerializedName("forms")
    List<poke.Pokemon> forms = null;
    @SerializedName("game_indices")
    List<game_indices> game_indices = null;
    @SerializedName("height")
    int height;
    @SerializedName("held_items")
    List<held_items> held_items = null;
    @SerializedName("id")
    int id;
    @SerializedName("is_default")
    boolean is_default;
    @SerializedName("location_area_encounters")
    String location_area_encounters;
    @SerializedName("moves")
    List<moves> moves = null;
    @SerializedName("name")
    String name;
    @SerializedName("order")
    int order;
    @SerializedName("species")
    poke.Pokemon species = null;
    @SerializedName("sprites")
    sprites sprites = null;
    @SerializedName("stats")
    List<stats> stats = null;
    @SerializedName("types")
    List<types> types = null;
    @SerializedName("weight")
    int weight;

    public poke_info_modal_class(List<ability> abilities, int base_experience, List<poke.Pokemon> forms,
                                 List<poke_info_modal_class.game_indices> game_indices, int height,
                                 List<poke_info_modal_class.held_items> held_items, int id,
                                 boolean is_default, String location_area_encounters,
                                 List<poke_info_modal_class.moves> moves, String name, int order,
                                 poke.Pokemon species, poke_info_modal_class.sprites sprites,
                                 List<poke_info_modal_class.stats> stats,
                                 List<poke_info_modal_class.types> types, int weight) {
        this.abilities = abilities;
        this.base_experience = base_experience;
        this.forms = forms;
        this.game_indices = game_indices;
        this.height = height;
        this.held_items = held_items;
        this.id = id;
        this.is_default = is_default;
        this.location_area_encounters = location_area_encounters;
        this.moves = moves;
        this.name = name;
        this.order = order;
        this.species = species;
        this.sprites = sprites;
        this.stats = stats;
        this.types = types;
        this.weight = weight;
    }

    public List<ability> getAbilities() {
        return abilities;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public List<poke_info_modal_class.stats> getStats() {
        return stats;
    }

    public List<poke_info_modal_class.types> getTypes() {
        return types;
    }


    //abilities class
    public class ability {

        @SerializedName("ability")
        ab ab = null;
        @SerializedName("is_hidden")
        boolean is_hidden;
        @SerializedName("slot")
        int slot;

        public ability(ability.ab ab, boolean is_hidden, int slot) {
            this.ab = ab;
            this.is_hidden = is_hidden;
            this.slot = slot;
        }

        public ability.ab getAb() {
            return ab;
        }

        public class ab {
            String name;
            String url;

            public ab(String name, String url) {
                this.name = name;
                this.url = url;
            }

            public String getName() {
                return name;
            }
        }
    }

    //game_indices class
    class game_indices {
        int game_index;
        poke.Pokemon version = null;

        public game_indices(int game_index, poke.Pokemon version) {
            this.game_index = game_index;
            this.version = version;
        }
    }

    //held_items class
    class held_items {
        poke.Pokemon item = null;
        List<version_details> version_details = null;
        class version_details {
            int rarity;
            poke.Pokemon version = null;

            public version_details(int rarity, poke.Pokemon version) {
                this.rarity = rarity;
                this.version = version;
            }
        }

        public held_items(poke.Pokemon item, List<held_items.version_details> version_details) {
            this.item = item;
            this.version_details = version_details;
        }
    }

    //moves class
    class moves {
        poke.Pokemon move = null;
        List<v_g_d> version_group_details = null;
        class v_g_d {
            int level_leaned_at;
            poke.Pokemon move_learn_method = null;
            poke.Pokemon version_group = null;

            public v_g_d(int level_leaned_at, poke.Pokemon move_learn_method, poke.Pokemon version_group) {
                this.level_leaned_at = level_leaned_at;
                this.move_learn_method = move_learn_method;
                this.version_group = version_group;
            }
        }

        public moves(poke.Pokemon move, List<v_g_d> version_group_details) {
            this.move = move;
            this.version_group_details = version_group_details;
        }
    }

    //class sprites
    class sprites {
        String back_default;
        String back_female;
        String back_shiny;
        String back_shiny_female;
        String front_default;
        String front_female;
        String front_shiny;
        String front_shiny_female;

        public sprites(String back_default, String back_female, String back_shiny,
                       String back_shiny_female, String front_default, String front_female,
                       String front_shiny, String front_shiny_female) {
            this.back_default = back_default;
            this.back_female = back_female;
            this.back_shiny = back_shiny;
            this.back_shiny_female = back_shiny_female;
            this.front_default = front_default;
            this.front_female = front_female;
            this.front_shiny = front_shiny;
            this.front_shiny_female = front_shiny_female;
        }
    }

    //class stats
    class stats {
        int base_stat;
        int effort;
        stat stat = null;

        public stats(int base_stat, int effort, stats.stat stat) {
            this.base_stat = base_stat;
            this.effort = effort;
            this.stat = stat;
        }

        public int getBase_stat() {
            return base_stat;
        }

        public poke_info_modal_class.stats.stat getStat() {
            return stat;
        }

        class stat {
            String name;
            String url;

            public stat(String name, String url) {
                this.name = name;
                this.url = url;
            }

            public String getName() {
                return name;
            }
        }
    }

    //class types
    class types {
        int slot;
        poke.Pokemon type = null;

        public poke.Pokemon getType() {
            return type;
        }

        public types(int slot, poke.Pokemon type) {
            this.slot = slot;
            this.type = type;
        }
    }

}
