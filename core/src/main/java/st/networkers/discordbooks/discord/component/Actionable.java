package st.networkers.discordbooks.discord.component;

import org.jetbrains.annotations.Nullable;

public interface Actionable {

    @Nullable String getId();

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
