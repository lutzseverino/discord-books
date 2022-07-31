package st.networkers.discordbooks.jda.message;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.emoji.EmojiUnion;
import net.dv8tion.jda.api.interactions.components.ActionComponent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.selections.SelectMenu;
import net.dv8tion.jda.api.interactions.components.selections.SelectOption;
import org.jetbrains.annotations.NotNull;
import st.networkers.discordbooks.message.Embed;
import st.networkers.discordbooks.message.Sendable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class JDAMessage implements Sendable {
    private final Message message;
    private final List<Embed> embeds = new ArrayList<>();
    private final List<List<Actionable>> actionables = new ArrayList<>();

    public JDAMessage(Message message) {
        this.message = message;

        message.getEmbeds().forEach(embed -> embeds.add(new JDAEmbed(embed)));

        message.getActionRows().forEach(actionRow ->
                actionables.add(actionRow
                        .getActionComponents()
                        .stream()
                        .map(JDAComponent::new)
                        .collect(Collectors.toList())
                )
        );
    }

    @Override
    public Optional<String> getText() {
        return message.getContentRaw().isEmpty() ? Optional.empty() : Optional.of(message.getContentRaw());
    }

    @Override public List<Embed> getEmbeds() {
        return this.embeds;
    }

    @Override public List<List<Actionable>> getActionables() {
        return this.actionables;
    }

    public static class JDAComponent implements Sendable.Actionable {
        private final ActionComponent component;

        public JDAComponent(ActionComponent component) {
            this.component = component;
        }

        @Override public Optional<String> getId() {
            return Optional.ofNullable(component.getId());
        }

        @Override public boolean isDisabled() {
            return component.isDisabled();
        }

        @NotNull private Optional<String> getEmoji(EmojiUnion emoji) {
            String formatted;

            if (emoji != null) try {
                emoji.asUnicode();
                formatted = emoji.asUnicode().getFormatted();
            } catch (IllegalStateException e) {
                try {
                    emoji.asCustom();
                    formatted = emoji.asCustom().getFormatted();
                } catch (IllegalStateException e2) {
                    throw new IllegalArgumentException("Unknown couldn't be parsed as unicode nor as custom");
                }
            }
            else return Optional.empty();

            return Optional.of(formatted);
        }

        class JDAButton implements Actionable.Clickable {

            private final Button button;

            public JDAButton(Button button) {
                this.button = button;
            }

            @Override public Style getStyle() {
                switch (button.getStyle()) {
                    case PRIMARY:
                        return Style.PRIMARY;
                    case SECONDARY:
                        return Style.SECONDARY;
                    case SUCCESS:
                        return Style.SUCCESS;
                    case DANGER:
                        return Style.DANGER;
                    case LINK:
                        return Style.LINK;
                    default:
                        return Style.UNKNOWN;
                }
            }

            @Override public String getDisplay() {
                return button.getLabel();
            }

            @Override public Optional<String> getUrl() {
                return Optional.ofNullable(button.getUrl());
            }

            @Override public Optional<String> getEmoji() {
                return JDAComponent.this.getEmoji(button.getEmoji());
            }
        }

        class JDAMenu implements Actionable.Selectable {
            private final SelectMenu menu;

            JDAMenu(SelectMenu menu) {
                this.menu = menu;
            }

            @Override public List<Selectable.Option> getOptions() {
                return menu.getOptions().stream().map(JDAMenuOption::new).collect(Collectors.toList());
            }

            class JDAMenuOption implements Actionable.Selectable.Option {
                private final SelectOption option;

                JDAMenuOption(SelectOption option) {
                    this.option = option;
                }

                @Override public String getDisplay() {
                    return option.getLabel();
                }

                @Override public String getValue() {
                    return option.getValue();
                }

                @Override public String getDescription() {
                    return option.getDescription();
                }

                @Override public boolean isDefault() {
                    return option.isDefault();
                }

                @Override public Optional<String> getEmoji() {
                    return JDAComponent.this.getEmoji(option.getEmoji());
                }
            }
        }
    }

}
