package com.lutzseverino.discordbooks.discord.component;

import org.jetbrains.annotations.Nullable;

public interface Clickable extends Actionable {

    @Override default Type getType() {
        return Type.CLICKABLE;
    }

    Clickable setId(String id);

    @Nullable String getUrl();

    Clickable setUrl(String url);

    Style getStyle();

    Clickable setStyle(Style style);

    @Nullable String getDisplay();

    Clickable setDisplay(String display);

    @Nullable String getEmoji();

    Clickable setEmoji(String emoji);

    Clickable setDisabled(boolean disabled);

    enum Style {
        PRIMARY,
        SECONDARY,
        SUCCESS,
        DANGER,
        LINK,
        UNKNOWN,
    }

}
