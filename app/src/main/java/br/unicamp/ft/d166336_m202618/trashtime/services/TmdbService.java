package br.unicamp.ft.d166336_m202618.trashtime.services;

import org.json.JSONObject;

import br.unicamp.ft.d166336_m202618.trashtime.ui.search.SearchFragment;

public class TmdbService {

    private String url, token, language;
    private SearchFragment searchFragment;

    public TmdbService(String url, String token, String language, SearchFragment searchFragment) {
        this.url = url;
        this.token = token;
        this.language = language;
        this.searchFragment = searchFragment;
    }

    public void search(String query) {

        String url = this.url + "/search/tv/?api_key=" + token + "&language=" + language + "&query=" + query;

        new ReciveJson(searchFragment).execute(url);
    }

    public void loadData (String serie) {
        url = url + "/tv/" + serie + "?=" + token + "&language=" + language;

        new ReciveJson(searchFragment).execute(url);
    }
}
