package com.lutzseverino.discordbooks.discord.component;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.lutzseverino.discordbooks.discord.component.impl.SelectableImpl;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = SelectableImpl.class, name = "default"),
})
public interface Selectable extends Actionable {

    @Override default Type getType() {
        return Type.SELECTABLE;
    }

    List<Option> getOptions();

    Selectable setOptions(List<Option> options);

    Selectable addOptions(Option... options);

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
    @JsonSubTypes({
            @JsonSubTypes.Type(value = SelectableImpl.OptionImpl.class, name = "default"),
    })
    interface Option {
        String getDisplay();

        Option setDisplay(String display);

        String getValue();

        Option setValue(String value);

        @Nullable String getDescription();

        Option setDescription(String description);

        @Nullable String getEmoji();

        Option setEmoji(String emoji);

        boolean isDefault();

        Option setDefault(boolean defaultOption);
    }
}
