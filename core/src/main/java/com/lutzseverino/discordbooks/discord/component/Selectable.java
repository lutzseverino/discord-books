package com.lutzseverino.discordbooks.discord.component;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface Selectable extends Actionable {

    @Override default Type getType() {
        return Type.SELECTABLE;
    }

    List<Option> getOptions();

    Selectable setOptions(List<Option> options);

    Selectable addOptions(Option... options);

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
