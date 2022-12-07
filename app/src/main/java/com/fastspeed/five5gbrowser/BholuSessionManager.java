package com.fastspeed.five5gbrowser;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class BholuSessionManager {
    private final Context contextBholu;
    private SharedPreferences prefBholu;
    private SharedPreferences.Editor editorBholu;

    @SuppressLint("CommitPrefEdits")
    public BholuSessionManager(Context context) {
        this.contextBholu = context;
        this.prefBholu = context.getSharedPreferences(BholuConst.PREF_NAMEBholu, MODE_PRIVATE);
        this.editorBholu = this.prefBholu.edit();
    }

    public void saveStringValueBholu(String key, String value) {
        editorBholu.putString(key, value);
        editorBholu.apply();
    }

    public String getStringValueBholu(String key) {
        return prefBholu.getString(key, "");
    }

    public void saveBooleanValueBholu(String key, boolean value) {
        editorBholu.putBoolean(key, value);
        editorBholu.apply();
    }

    public boolean getBooleanValueBholu(String key) {
        return prefBholu.getBoolean(key, false);
    }

    public int toggleBookmarkBholu(String id) {
        ArrayList<String> fav = getBookamrksBholu();
        if (fav != null) {
            if (fav.contains(id)) {
                fav.remove(id);
                editorBholu.putString(BholuConst.BOOKMARKBholu, new Gson().toJson(fav));
                editorBholu.apply();
                return 0;
            } else {
                fav.add(id);
                editorBholu.putString(BholuConst.BOOKMARKBholu, new Gson().toJson(fav));
                editorBholu.apply();
                return 1;
            }
        } else {
            fav = new ArrayList<>();
            fav.add(id);
            editorBholu.putString(BholuConst.BOOKMARKBholu, new Gson().toJson(fav));
            editorBholu.apply();
            return 1;
        }
    }

    public ArrayList<String> getBookamrksBholu() {
        String userStringBholu = prefBholu.getString(BholuConst.BOOKMARKBholu, "");
        if (!userStringBholu.isEmpty()) {
            return new Gson().fromJson(userStringBholu, new TypeToken<ArrayList<String>>() {
            }.getType());
        }
        return new ArrayList<>();
    }

    public void addToHistoryBholu(String id) {
        ArrayList<String> fav = getHistoryBholu();
        if (fav != null) {
            fav.add(id);
        } else {
            fav = new ArrayList<>();
            fav.add(id);

        }
        editorBholu.putString(BholuConst.HISTORYBholu, new Gson().toJson(fav));
        editorBholu.apply();
    }

    public void removefromHistoryBholu(String id) {
        ArrayList<String> fav = getHistoryBholu();
        if (fav != null) {
            if (fav.contains(id)) {
                fav.remove(id);
                Toast.makeText(contextBholu, "Removed", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(contextBholu, "Bookmarked", Toast.LENGTH_SHORT).show();
            }
        } else {
            fav = new ArrayList<>();

        }
        editorBholu.putString(BholuConst.HISTORYBholu, new Gson().toJson(fav));
        editorBholu.apply();
    }

    public ArrayList<String> getHistoryBholu() {
        String userStringBholu = prefBholu.getString(BholuConst.HISTORYBholu, "");
        if (!userStringBholu.isEmpty()) {
            return new Gson().fromJson(userStringBholu, new TypeToken<ArrayList<String>>() {
            }.getType());
        }
        return new ArrayList<>();
    }


    public void addToSearchBholu(String id) {
        ArrayList<String> fav = getSearchBholu();
        if (fav != null) {

            fav.add(id);

        } else {
            fav = new ArrayList<>();
            fav.add(id);

        }
        editorBholu.putString(BholuConst.SEARCHBholu, new Gson().toJson(fav));
        editorBholu.apply();
    }

    public void removefromSearchBholu(String id) {
        ArrayList<String> fav = getSearchBholu();
        if (fav != null) {
            if (fav.contains(id)) {
                fav.remove(id);
                Toast.makeText(contextBholu, "Removed", Toast.LENGTH_SHORT).show();
            }
        } else {
            fav = new ArrayList<>();

        }
        editorBholu.putString(BholuConst.SEARCHBholu, new Gson().toJson(fav));
        editorBholu.apply();
    }

    public ArrayList<String> getSearchBholu() {
        String userStringBholu = prefBholu.getString(BholuConst.SEARCHBholu, "");
        if (userStringBholu != null && !userStringBholu.isEmpty()) {
            return new Gson().fromJson(userStringBholu, new TypeToken<ArrayList<String>>() {
            }.getType());
        }
        return new ArrayList<>();
    }



}
