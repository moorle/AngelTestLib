package com.moorle.angeltestlib.model;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.moorle.angeltestlib.util.IoUtils;

import java.util.List;

/**
 * Created by lanshon on 12/18/14.
 */
public class AngelModel {
    private static AngelModel sInstance = new AngelModel();

    private List<Item> mData;

    public static AngelModel getInstance() {
        return sInstance;
    }

    private AngelModel() {
    }

    public void loadData(Context context) {
        String localJson = IoUtils.readJson(context.getResources().getAssets(), "choice_question.json");
        Gson gson = new Gson();
        mData = gson.fromJson(localJson, new TypeToken<List<Item>>() {
        }.getType());
    }

    public List<Item> getData() {
        return mData;
    }
}
