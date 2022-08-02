package com.lutzseverino.discordbooks.discord.component.impl;

import com.lutzseverino.discordbooks.discord.component.Clickable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ClickableImpl implements Clickable {
    private String id;
    private String url;
    private Style style;
    private String display;
    private String emoji;
    private boolean disabled;

    public ClickableImpl(Style style) {
        this.style = style;
    }

    public ClickableImpl(@NotNull Clickable clickable) {
        this.id = clickable.getId();
        this.url = clickable.getUrl();
        this.style = clickable.getStyle();
        this.display = clickable.getDisplay();
        this.emoji = clickable.getEmoji();
        this.disabled = clickable.isDisabled();
    }

    @Override public @Nullable String getId() {
        return this.id;
    }

    @Override public Clickable setId(String id) {
        this.id = id;
        return this;
    }

    @Override public @Nullable String getUrl() {
        return this.url;
    }

    @Override public Clickable setUrl(String url) {
        this.url = url;
        return this;
    }

    @Override public Style getStyle() {
        return this.style;
    }

    @Override public Clickable setStyle(Style style) {
        this.style = style;
        return this;
    }

    @Override public @Nullable String getDisplay() {
        return this.display;
    }

    @Override public Clickable setDisplay(String display) {
        this.display = display;
        return this;
    }

    @Override public @Nullable String getEmoji() {
        return this.emoji;
    }

    @Override public Clickable setEmoji(String emoji) {
        this.emoji = emoji;
        return this;
    }

    @Override public boolean isDisabled() {
        return this.disabled;
    }

    @Override public Clickable setDisabled(boolean disabled) {
        this.disabled = disabled;
        return this;
    }
}
