package st.networkers.discordbooks.component;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface Selectable extends Actionable {

    @Override default Type getType() {
        return Type.SELECTABLE;
    }

    List<MenuOption> getOptions();

    interface MenuOption {
        String getDisplay();

        String getValue();

        @Nullable String getDescription();

        boolean isDefault();

        @Nullable String getEmoji();
    }
}
