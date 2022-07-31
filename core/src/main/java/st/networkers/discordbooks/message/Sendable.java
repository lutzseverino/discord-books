package st.networkers.discordbooks.message;

import java.util.List;
import java.util.Optional;

public interface Sendable {

    Optional<String> getText();

    Sendable setEmbeds(Embed... embeds);

    List<Embed> getEmbeds();

}
