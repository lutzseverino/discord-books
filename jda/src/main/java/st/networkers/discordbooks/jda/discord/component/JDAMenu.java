package st.networkers.discordbooks.jda.discord.component;

import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.interactions.components.selections.SelectMenu;
import net.dv8tion.jda.api.interactions.components.selections.SelectOption;
import st.networkers.discordbooks.discord.component.Selectable;

import java.util.Objects;
import java.util.stream.Collectors;

public class JDAMenu {
    private final Selectable menu;

    public JDAMenu(Selectable menu) {
        this.menu = menu;
    }

    public static SelectMenu buildSelectMenu(Selectable selectable) {
        return SelectMenu.create(Objects.requireNonNull(selectable.getId()))
                .addOptions(
                        selectable.getOptions().stream()
                                .map(JDAOption::buildSelectOption)
                                .collect(Collectors.toList())
                ).build();
    }

    public SelectMenu buildSelectMenu() {
        return buildSelectMenu(this.menu);
    }

    public static class JDAOption {

        private final Selectable.Option option;

        JDAOption(Selectable.Option option) {
            this.option = option;
        }

        public static SelectOption buildSelectOption(Selectable.Option option) {
            return SelectOption.of(option.getDisplay(), option.getValue())
                    .withDefault(option.isDefault())
                    .withEmoji(option.getEmoji() != null ? Emoji.fromFormatted(option.getEmoji()) : null)
                    .withDescription(option.getDescription());
        }

        public SelectOption buildSelectOption() {
            return buildSelectOption(this.option);
        }
    }
}
