package st.networkers.discordbooks.discord.component.impl;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import st.networkers.discordbooks.discord.component.Selectable;

import java.util.List;

public class SelectableImpl implements Selectable {
    private String id;
    private List<Option> options;
    private boolean disabled;

    public SelectableImpl(@NotNull Selectable selectable) {
        this.id = selectable.getId();
        this.options = selectable.getOptions();
        this.disabled = selectable.isDisabled();
    }

    @Override public @Nullable String getId() {
        return this.id;
    }

    @Override public Selectable setId(String id) {
        this.id = id;
        return this;
    }

    @Override public List<Option> getOptions() {
        return this.options;
    }

    @Override public Selectable setOptions(List<Option> options) {
        this.options = options;
        return this;
    }

    @Override public boolean isDisabled() {
        return this.disabled;
    }

    @Override public Selectable setDisabled(boolean disabled) {
        this.disabled = disabled;
        return this;
    }

    public static class OptionImpl implements Selectable.Option {
        private String display;
        private String value;
        private String description;
        private String emoji;
        private boolean defaultOption;

        public OptionImpl(Selectable.Option option) {
            this.display = option.getDisplay();
            this.value = option.getValue();
            this.description = option.getDescription();
            this.emoji = option.getEmoji();
            this.defaultOption = option.isDefault();
        }

        @Override public String getDisplay() {
            return null;
        }

        @Override public Option setDisplay(String display) {
            this.display = display;
            return this;
        }

        @Override public String getValue() {
            return null;
        }

        @Override public Option setValue(String value) {
            this.value = value;
            return this;
        }

        @Override public @Nullable String getDescription() {
            return null;
        }

        @Override public Option setDescription(String description) {
            this.description = description;
            return this;
        }

        @Override public @Nullable String getEmoji() {
            return null;
        }

        @Override public Option setEmoji(String emoji) {
            this.emoji = emoji;
            return this;
        }

        @Override public boolean isDefault() {
            return false;
        }

        @Override public Option setDefault(boolean defaultOption) {
            this.defaultOption = defaultOption;
            return this;
        }
    }
}
