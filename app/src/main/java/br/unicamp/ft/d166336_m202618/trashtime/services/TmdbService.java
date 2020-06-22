package br.unicamp.ft.d166336_m202618.trashtime.services;

import androidx.fragment.app.Fragment;

import org.json.JSONObject;

import br.unicamp.ft.d166336_m202618.trashtime.ui.search.SearchFragment;

public class TmdbService {

    private String url, token, language;
    private JsonReciver fragment;

    public TmdbService(String url, String token, String language, JsonReciver fragment) {
        this.url = url;
        this.token = token;
        this.language = language;
        this.fragment = fragment;
    }

    public void search(String query) {

        String url = this.url + "/search/tv/?api_key=" + token + "&language=" + language + "&query=" + query;

        new ReciveJson(fragment).execute(url);
    }

    public void loadData (String serie) {
        url = url + "/tv/" + serie + "?api_key=" + token + "&language=" + language;

        new ReciveJson(fragment).execute(url);
    }
}
