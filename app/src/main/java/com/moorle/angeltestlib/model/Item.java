package com.moorle.angeltestlib.model;

import java.util.List;

/**
 * Created by lanshon on 12/16/14.
 */
public class Item {
    public String title;
    public List<String> options;
    public String answer;

    public Item(String title, List<String> options, String answer) {
        this.title = title;
        this.options = options;
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Item{" +
                "title='" + title + '\'' +
                ", options=" + options +
                ", answer='" + answer + '\'' +
                '}';
    }

    public String optionsToString() {
        StringBuilder sb = new StringBuilder();
        for (String s : options) {
            sb.append(s).append("\n");
        }

        return sb.toString();
    }
}
