package com.example.task3;

public class colours {

    public static int getColorByType(String type) {
        switch (type) {

            case "Normal":
                return R.color.normal;


            case "Dragon":
                return R.color.dragon;


            case "Psychic":
                return R.color.psychic;


            case "Electric":
                return R.color.electric;


            case "Ground":
                return R.color.ground;


            case "Grass":
                return R.color.grass;

            case "Poison":
                return R.color.poison;

            case "Steel":
                return R.color.steel;


            case "Fairy":
                return R.color.fairy;


            case "Fire":
                return R.color.fire;


            case "Fight":
                return R.color.flight;


            case "Bug":
                return R.color.bug;


            case "Ghost":
                return R.color.ghost;


            case "Dark":
                return R.color.dark;


            case "Ice":
                return R.color.ice;


            case "Water":
                return R.color.water;
            default:
                return R.color.blue;
        }
    }

    public static int getColourByStat(String string) {
        switch (string) {
            case "Hp":
                return R.color.hp;
            case "Attack":
            case "Special-attack":
                return R.color.attack;
            case "Defense":
            case "Special-defense":
                return R.color.steel;
            case "Speed":
                return R.color.water;
            default:
                return R.color.blue;
        }
    }

}
