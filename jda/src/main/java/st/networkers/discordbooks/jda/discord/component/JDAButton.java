package st.networkers.discordbooks.jda.discord.component;

import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;
import st.networkers.discordbooks.discord.component.Clickable;

import java.util.Objects;

public class JDAButton {
    private final Clickable clickable;

    public JDAButton(Clickable clickable) {
        this.clickable = clickable;
    }

    public static Button buildButton(Clickable clickable) {
        return Button.of(
                translateButtonStyle(clickable.getStyle()),
                clickable.getId() != null ? clickable.getId() : Objects.requireNonNull(clickable.getUrl()),
                clickable.getDisplay(),
                clickable.getEmoji() != null ? Emoji.fromFormatted(clickable.getEmoji()) : null
        ).withDisabled(clickable.isDisabled());
    }

    private static ButtonStyle translateButtonStyle(Clickable.Style style) {
        switch (style) {
            case PRIMARY:
                return ButtonStyle.PRIMARY;
            case SECONDARY:
                return ButtonStyle.SECONDARY;
            case SUCCESS:
                return ButtonStyle.SUCCESS;
            case DANGER:
                return ButtonStyle.DANGER;
            case LINK:
                return ButtonStyle.LINK;
            default:
                return ButtonStyle.UNKNOWN;
        }
    }

    public Button buildButton() {
        return buildButton(this.clickable);
    }
}
