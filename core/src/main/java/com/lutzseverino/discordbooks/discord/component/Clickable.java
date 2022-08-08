package com.lutzseverino.discordbooks.discord.component;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.lutzseverino.discordbooks.discord.component.impl.ClickableImpl;
import org.jetbrains.annotations.Nullable;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ClickableImpl.class, name = "default"),
})
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
