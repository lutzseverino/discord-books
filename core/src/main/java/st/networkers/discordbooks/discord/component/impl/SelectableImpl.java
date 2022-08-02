package st.networkers.discordbooks.discord.component.impl;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import st.networkers.discordbooks.discord.component.Selectable;

import java.util.List;

public class SelectableImpl implements Selectable {
    private String id;
    private List<Option> options;
    private boolean disabled;

    public SelectableImpl(String id, List<Option> options) {
        this.id = id;
        this.options = options;
    }

    public SelectableImpl(String id, Option... options) {
        this.id = id;
        this.options = List.of(options);
    }

    public SelectableImpl(@NotNull Selectable selectable) {
        this.id = selectable.getId();
        this.options = selectable.getOptions();
        this.disabled = selectable.isDisabled();
    }

    @Override public String getId() {
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

    @Override public Selectable addOptions(Option... options) {
        this.options.addAll(List.of(options));
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

        public OptionImpl(String display, String value) {
            this.display = display;
            this.value = value;
        }

        public OptionImpl(Selectable.Option option) {
            this.display = option.getDisplay();
            this.value = option.getValue();
            this.description = option.getDescription();
            this.emoji = option.getEmoji();
            this.defaultOption = option.isDefault();
        }

        @Override public String getDisplay() {
            return this.display;
        }

        @Override public Option setDisplay(String display) {
            this.display = display;
            return this;
        }

        @Override public String getValue() {
            return this.value;
        }

        @Override public Option setValue(String value) {
            this.value = value;
            return this;
        }

        @Override public @Nullable String getDescription() {
            return this.description;
        }

        @Override public Option setDescription(String description) {
            this.description = description;
            return this;
        }

        @Override public @Nullable String getEmoji() {
            return this.emoji;
        }

        @Override public Option setEmoji(String emoji) {
            this.emoji = emoji;
            return this;
        }

        @Override public boolean isDefault() {
            return this.defaultOption;
        }

        @Override public Option setDefault(boolean defaultOption) {
            this.defaultOption = defaultOption;
            return this;
        }
    }
}
