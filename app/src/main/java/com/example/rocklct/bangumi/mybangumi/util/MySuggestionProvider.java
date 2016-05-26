package com.example.rocklct.bangumi.mybangumi.util;

import android.content.SearchRecentSuggestionsProvider;

/**
 * Created by rocklct on 2016/5/26.
 */
public class MySuggestionProvider extends SearchRecentSuggestionsProvider {
    public final static String AUTHORITY = "com.example.rocklct.bangumi.util.MySuggestionProvider";
    public final static int MODE = DATABASE_MODE_QUERIES;

    public MySuggestionProvider(){
        setupSuggestions(AUTHORITY,MODE);
    }
}
