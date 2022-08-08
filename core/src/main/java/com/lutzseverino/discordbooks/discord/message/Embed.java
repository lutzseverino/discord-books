package com.lutzseverino.discordbooks.discord.message;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.lutzseverino.discordbooks.discord.message.impl.EmbedImpl;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.time.OffsetDateTime;
import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes(@JsonSubTypes.Type(value = EmbedImpl.class, name = "default"))
public interface Embed {

    @Nullable String getTitle();

    Embed setTitle(@Nullable String title);

    @Nullable String getDescription();

    Embed setDescription(@Nullable String description);

    @Nullable String getFooter();

    Embed setFooter(@Nullable String footer);

    @Nullable String getImage();

    Embed setImage(@Nullable String image);

    @Nullable String getThumbnail();

    Embed setThumbnail(@Nullable String thumbnail);

    @Nullable OffsetDateTime getTimestamp();

    Embed setTimestamp(@Nullable OffsetDateTime timestamp);

    Color getColor();

    Embed setColor(@Nullable Color color);

    @Nullable Embed.Author getAuthor();

    Embed setAuthor(@Nullable Embed.Author author);

    List<Embed.Field> getFields();

    Embed setFields(List<Embed.Field> fields);

    @JsonSetter Embed setFields(Embed.Field... fields);

    Embed addFields(List<Embed.Field> fields);

    Embed addFields(Embed.Field... field);

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
    @JsonSubTypes({
            @JsonSubTypes.Type(value = EmbedImpl.AuthorImpl.class, name = "default"),
    })
    interface Author {
        String getName();

        Author setName(String name);

        String getUrl();

        Author setUrl(String url);

        String getIconUrl();

        Author setIconUrl(String iconUrl);
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
    @JsonSubTypes({
            @JsonSubTypes.Type(value = EmbedImpl.FieldImpl.class, name = "default"),
    })
    interface Field {
        String getName();

        Field setName(String name);

        String getValue();

        Field setValue(String value);

        boolean isInline();

        Field setInline(boolean inline);
    }
}
