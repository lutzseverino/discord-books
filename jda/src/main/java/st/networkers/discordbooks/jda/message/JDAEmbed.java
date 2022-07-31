package st.networkers.discordbooks.jda.message;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import st.networkers.discordbooks.message.Embed;

import java.awt.*;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class JDAEmbed implements Embed {
    private final MessageEmbed embed;

    public JDAEmbed(MessageEmbed embed) {
        this.embed = embed;
    }

    public JDAEmbed(EmbedBuilder embed) {
        this.embed = embed.build();
    }

    public static MessageEmbed toMessageEmbed(Embed embed) {
        EmbedBuilder builder = new EmbedBuilder()
                .setTitle(embed.getTitle())
                .setDescription(embed.getDescription())
                .setTimestamp(embed.getTimestamp().orElse(null))
                .setColor(embed.getColor())
                .setFooter(embed.getFooter().orElse(null))
                .setImage(embed.getImage().orElse(null))
                .setThumbnail(embed.getThumbnail().orElse(null));

        embed.getAuthor().ifPresent(author -> builder.setAuthor(author.getName(), author.getUrl(), author.getIconUrl()));
        embed.getFields().forEach(field -> builder.addField(field.getName(), field.getValue(), field.isInline()));

        return builder.build();
    }

    public static List<MessageEmbed> toMessageEmbeds(List<Embed> embeds) {
        return embeds.stream().map(JDAEmbed::toMessageEmbed).collect(Collectors.toList());
    }

    @Override public String getTitle() {
        return embed.getTitle();
    }

    @Override public String getDescription() {
        return embed.getDescription();
    }

    @Override public Optional<OffsetDateTime> getTimestamp() {
        return embed.getTimestamp() != null ? Optional.of(embed.getTimestamp()) : Optional.empty();
    }

    @Override public Color getColor() {
        return embed.getColor();
    }

    @Override public Optional<String> getFooter() {
        return embed.getFooter() != null ? Optional.ofNullable(embed.getFooter().getText()) : Optional.empty();
    }

    @Override public Optional<String> getImage() {
        return embed.getImage() != null ? Optional.ofNullable(embed.getImage().getUrl()) : Optional.empty();
    }

    @Override public Optional<String> getThumbnail() {
        return embed.getThumbnail() != null ? Optional.ofNullable(embed.getThumbnail().getUrl()) : Optional.empty();
    }

    @Override public Optional<JDAEmbed.JDAAuthor> getAuthor() {
        return embed.getAuthor() != null ? Optional.of(new JDAAuthor(embed.getAuthor())) : Optional.empty();
    }

    @Override public List<JDAEmbed.JDAField> getFields() {
        return embed.getFields().stream().map(JDAField::new).collect(Collectors.toList());
    }

    class JDAAuthor implements Embed.Author {
        private final String name;
        private final String url;
        private final String iconUrl;

        public JDAAuthor(String name, String url, String iconUrl) {
            this.name = name;
            this.url = url;
            this.iconUrl = iconUrl;
        }

        public JDAAuthor(MessageEmbed.AuthorInfo author) {
            this(author.getName(), author.getUrl(), author.getIconUrl());
        }

        @Override public String getName() {
            return name;
        }

        @Override public String getUrl() {
            return url;
        }

        @Override public String getIconUrl() {
            return iconUrl;
        }
    }

    class JDAField implements Embed.Field {
        private final String name;
        private final String value;
        private final boolean inline;

        public JDAField(String name, String value, boolean inline) {
            this.name = name;
            this.value = value;
            this.inline = inline;
        }

        public JDAField(MessageEmbed.Field field) {
            this(field.getName(), field.getValue(), field.isInline());
        }

        @Override public String getName() {
            return name;
        }

        @Override public String getValue() {
            return value;
        }

        @Override public boolean isInline() {
            return inline;
        }
    }
}
