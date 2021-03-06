/*
 * Copyright (C) 2010-2014 The MPDroid Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.namelessdev.mpdroid.tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.namelessdev.mpdroid.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class LibraryTabsUtil {

    public static final String TAB_ARTISTS = "artists";
    public static final String TAB_ALBUMS = "albums";
    public static final String TAB_PLAYLISTS = "playlists";
    public static final String TAB_STREAMS = "streams";
    public static final String TAB_FILES = "files";
    public static final String TAB_GENRES = "genres";

    public static final HashMap<String, Integer> TABS = new HashMap<String, Integer>();
    static {
        TABS.put(TAB_ARTISTS, R.string.artists);
        TABS.put(TAB_ALBUMS, R.string.albums);
        TABS.put(TAB_PLAYLISTS, R.string.playlists);
        TABS.put(TAB_STREAMS, R.string.streams);
        TABS.put(TAB_FILES, R.string.files);
        TABS.put(TAB_GENRES, R.string.genres);
    }

    private static final String LIBRARY_TABS_SETTINGS_KEY = "currentLibraryTabs";

    private static final String LIBRARY_TABS_DELIMITER = "|";

    private static String DEFAULT_LIBRARY_TABS = TAB_ARTISTS
            + LIBRARY_TABS_DELIMITER + TAB_ALBUMS
            + LIBRARY_TABS_DELIMITER + TAB_PLAYLISTS
            + LIBRARY_TABS_DELIMITER + TAB_STREAMS
            + LIBRARY_TABS_DELIMITER + TAB_FILES
            + LIBRARY_TABS_DELIMITER + TAB_GENRES;

    public static ArrayList<String> getAllLibraryTabs() {
        String CurrentSettings = DEFAULT_LIBRARY_TABS;
        return new ArrayList<String>(Arrays.asList(CurrentSettings.split("\\"
                + LIBRARY_TABS_DELIMITER)));
    }

    public static ArrayList<String> getCurrentLibraryTabs(Context context) {
        final SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(context);
        String currentSettings = settings.getString(LIBRARY_TABS_SETTINGS_KEY, "");
        if (currentSettings == "") {
            currentSettings = DEFAULT_LIBRARY_TABS;
            resetLibraryTabs(context);
        }
        return new ArrayList<String>(Arrays.asList(currentSettings.split("\\"
                + LIBRARY_TABS_DELIMITER)));
    }

    public static ArrayList<String> getTabsListFromString(String tabs) {
        return new ArrayList<String>(Arrays.asList(tabs.split("\\"
                + LIBRARY_TABS_DELIMITER)));
    }

    public static String getTabsStringFromList(ArrayList<String> tabs) {
        if (tabs == null || tabs.size() <= 0) {
            return "";
        } else {
            String s = tabs.get(0);
            for (int i = 1; i < tabs.size(); i++) {
                s += LIBRARY_TABS_DELIMITER + tabs.get(i);
            }
            return s;
        }
    }

    public static int getTabTitleResId(String tab) {
        return TABS.get(tab);
    }

    public static void saveCurrentLibraryTabs(Context context,
            ArrayList<String> tabs) {
        final SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(context);
        String currentSettings = getTabsStringFromList(tabs);
        settings.edit().putString(LIBRARY_TABS_SETTINGS_KEY, currentSettings).commit();
    }

    public static void resetLibraryTabs(Context context) {
        final SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(context);
        settings.edit().putString(LIBRARY_TABS_SETTINGS_KEY, DEFAULT_LIBRARY_TABS)
                .commit();
    }
}
