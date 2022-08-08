package com.lutzseverino.discordbooks.discord.message.impl;

import com.lutzseverino.discordbooks.discord.message.Embed;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class EmbedImpl implements Embed {
    private String title;
    private String description;
    private String footer;
    private String image;
    private String thumbnail;
    private OffsetDateTime timestamp;
    private Color color;
    private Embed.Author author;
    private List<Embed.Field> fields = new ArrayList<>();

    public EmbedImpl() {
    }

    public EmbedImpl(Embed embed) {
        this.title = embed.getTitle();
        this.description = embed.getDescription();
        this.footer = embed.getFooter();
        this.image = embed.getImage();
        this.thumbnail = embed.getThumbnail();
        this.timestamp = embed.getTimestamp();
        this.color = embed.getColor();
        this.author = embed.getAuthor();
        this.fields = embed.getFields();
    }

    @Override public @Nullable String getTitle() {
        return this.title;
    }

    @Override public Embed setTitle(@Nullable String title) {
        this.title = title;
        return this;
    }

    @Override public @Nullable String getDescription() {
        return this.description;
    }

    @Override public Embed setDescription(@Nullable String description) {
        this.description = description;
        return this;
    }

    @Override public @Nullable OffsetDateTime getTimestamp() {
        return this.timestamp;
    }

    @Override public Embed setTimestamp(@Nullable OffsetDateTime timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    @Override public Color getColor() {
        return this.color;
    }

    @Override public Embed setColor(@Nullable Color color) {
        this.color = color;
        return this;
    }

    @Override public @Nullable String getFooter() {
        return this.footer;
    }

    @Override public Embed setFooter(@Nullable String footer) {
        this.footer = footer;
        return this;
    }

    @Override public @Nullable String getImage() {
        return this.image;
    }

    @Override public Embed setImage(@Nullable String image) {
        this.image = image;
        return this;
    }

    @Override public @Nullable String getThumbnail() {
        return this.thumbnail;
    }

    @Override public Embed setThumbnail(@Nullable String thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }

    @Override public @Nullable Embed.Author getAuthor() {
        return this.author;
    }

    @Override public Embed setAuthor(@Nullable Author author) {
        this.author = author;
        return this;
    }

    @Override public List<Field> getFields() {
        return this.fields;
    }

    @Override public Embed setFields(List<Field> fields) {
        this.fields = fields;
        return this;
    }

    @Override public Embed setFields(Field... fields) {
        return setFields(List.of(fields));
    }

    @Override public Embed addFields(List<Field> fields) {
        this.fields.addAll(fields);
        return this;
    }

    @Override public Embed addFields(Field... field) {
        return addFields(List.of(field));
    }

    public static class AuthorImpl implements Embed.Author {
        private String name;
        private String url;
        private String iconUrl;

        public AuthorImpl(Embed.Author author) {
            this.name = author.getName();
            this.url = author.getUrl();
            this.iconUrl = author.getIconUrl();
        }

        @Override public String getName() {
            return this.name;
        }

        @Override public Author setName(String name) {
            this.name = name;
            return this;
        }

        @Override public String getUrl() {
            return this.url;
        }

        @Override public Author setUrl(String url) {
            this.url = url;
            return this;
        }

        @Override public String getIconUrl() {
            return this.iconUrl;
        }

        @Override public Author setIconUrl(String iconUrl) {
            this.iconUrl = iconUrl;
            return this;
        }
    }

    public static class FieldImpl implements Embed.Field {
        private String name;
        private String value;
        private boolean inline;

        @Override public String getName() {
            return this.name;
        }

        @Override public Field setName(String name) {
            this.name = name;
            return this;
        }

        @Override public String getValue() {
            return this.value;
        }

        @Override public Field setValue(String value) {
            this.value = value;
            return this;
        }

        @Override public boolean isInline() {
            return this.inline;
        }

        @Override public Field setInline(boolean inline) {
            this.inline = inline;
            return this;
        }
    }
}
