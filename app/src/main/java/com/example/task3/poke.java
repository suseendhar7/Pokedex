package com.example.task3;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class poke {
    private Integer count;
    private Object previous;
    private List<Pokemon> results = null;
    private String next;

    public poke(Integer count, Object previous, List<Pokemon> results, String next) {
        this.count = count;
        this.previous = previous;
        this.results = results;
        this.next = next;
    }

    public Integer getCount() {
        return count;
    }

    public Object getPrevious() {
        return previous;
    }

    public List<Pokemon> getResults() {
        return results;
    }

    public String getNext() {
        return next;
    }
    public class Pokemon {
        @SerializedName("url")
        private String url;
        @SerializedName("name")
        private String name;

        public Pokemon() {

        }

        public Pokemon(String url, String name) {
            this.url = url;
            this.name = name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public String getName() {
            return name;
        }

    }
}
