package com.dczajkowski.survey;

import static com.dczajkowski.helpers.Helpers.tap;

public class Ad {
    private String text;
    private String link;
    private int clicked = 0;

    public Ad(String text, String link) {
        this.text = text;
        this.link = link;
    }

    public String getText() {
        return text;
    }

    public String getLink() {
        return link;
    }

    public int getClicked() {
        return clicked;
    }

    public Ad withIncrementedClickCount() {
        return tap(this, t -> clicked += 1);
    }
}
