package com.lutzseverino.discordbooks.discord.component;

import org.jetbrains.annotations.Nullable;

public interface Actionable {

    @Nullable String getId();

    Actionable setId(String id);

    Actionable setDisabled(boolean disabled);

    boolean isDisabled();

    default Type getType() {
        return Type.UNKNOWN;
    }

    enum Type {
        CLICKABLE,
        SELECTABLE,
        UNKNOWN,
    }

}
