package st.networkers.discordbooks.message;

import java.util.List;
import java.util.Optional;

public interface Sendable {

    Optional<String> getText();

    List<Embed> getEmbeds();

    List<List<Actionable>> getActionables();

    interface Actionable {

        Optional<String> getId();

        boolean isDisabled();

        interface Clickable {

            Style getStyle();

            String getDisplay();

            Optional<String> getUrl();

            Optional<String> getEmoji();

            enum Style {
                PRIMARY,
                SECONDARY,
                SUCCESS,
                DANGER,
                LINK,
                UNKNOWN,
            }
        }

        interface Selectable {

            List<Selectable.Option> getOptions();

            interface Option {
                String getDisplay();

                String getValue();

                String getDescription();

                boolean isDefault();

                Optional<String> getEmoji();
            }
        }
    }

}
