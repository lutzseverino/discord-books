package st.networkers.discordbooks.jda.discord.message;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import st.networkers.discordbooks.discord.message.Embed;

import java.util.List;
import java.util.stream.Collectors;

public class JDAEmbed {
    private final Embed embed;

    public JDAEmbed(Embed embed) {
        this.embed = embed;
    }

    public static MessageEmbed buildMessageEmbed(Embed embed) {
        EmbedBuilder builder = new EmbedBuilder()
                .setTitle(embed.getTitle())
                .setDescription(embed.getDescription())
                .setTimestamp(embed.getTimestamp())
                .setColor(embed.getColor())
                .setFooter(embed.getFooter())
                .setImage(embed.getImage())
                .setThumbnail(embed.getThumbnail());

        Embed.Author author = embed.getAuthor();

        if (author != null) {
            builder.setAuthor(author.getName(), author.getUrl(), author.getIconUrl());
        }

        embed.getFields().forEach(field -> builder.addField(field.getName(), field.getValue(), field.isInline()));

        return builder.build();
    }

    public static List<MessageEmbed> buildMessageEmbeds(List<Embed> embeds) {
        return embeds.stream().map(JDAEmbed::buildMessageEmbed).collect(Collectors.toList());
    }

    public static class JDAAuthor {
        private final Embed.Author author;

        public JDAAuthor(Embed.Author author) {
            this.author = author;
        }

        public static MessageEmbed.AuthorInfo buildAuthorInfo(Embed.Author author) {
            return new MessageEmbed.AuthorInfo(author.getName(), author.getUrl(), author.getIconUrl(), null);
        }

        public MessageEmbed.AuthorInfo buildAuthorInfo() {
            return buildAuthorInfo(this.author);
        }
    }

    public static class JDAField {
        private final Embed.Field field;

        public JDAField(Embed.Field field) {
            this.field = field;
        }

        public static MessageEmbed.Field buildField(Embed.Field field) {
            return new MessageEmbed.Field(field.getName(), field.getValue(), field.isInline());
        }

        public MessageEmbed.Field buildField() {
            return buildField(this.field);
        }
    }
}
