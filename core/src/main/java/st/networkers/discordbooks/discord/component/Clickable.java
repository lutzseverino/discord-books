package st.networkers.discordbooks.discord.component;

import org.jetbrains.annotations.Nullable;

public interface Clickable extends Actionable {

    @Override default Type getType() {
        return Type.CLICKABLE;
    }

    Style getStyle();

    Clickable setStyle(Style style);

    @Nullable String getDisplay();

    Clickable setDisplay(String display);

    @Nullable String getUrl();

    Clickable setUrl(String url);

    @Nullable String getEmoji();

    Clickable setEmoji(String emoji);

    Clickable setDisabled(boolean disabled);

    Clickable setId(String id);

    enum Style {
        PRIMARY,
        SECONDARY,
        SUCCESS,
        DANGER,
        LINK,
        UNKNOWN,
    }

}
