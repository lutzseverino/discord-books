package st.networkers.discordbooks.discord.message;

import java.awt.*;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface Embed {

    String getTitle();

    String getDescription();

    Optional<OffsetDateTime> getTimestamp();

    Color getColor();

    Optional<String> getFooter();

    Optional<String> getImage();

    Optional<String> getThumbnail();

    Optional<? extends Embed.Author> getAuthor();

    List<? extends Embed.Field> getFields();

    interface Author {
        String getName();

        String getUrl();

        String getIconUrl();
    }

    interface Field {
        String getName();

        String getValue();

        boolean isInline();
    }
}
