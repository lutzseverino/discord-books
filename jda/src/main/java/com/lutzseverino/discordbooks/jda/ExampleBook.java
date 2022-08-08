package com.lutzseverino.discordbooks.jda;

import com.lutzseverino.discordbooks.book.Book;
import com.lutzseverino.discordbooks.discord.component.ActionableRow;
import com.lutzseverino.discordbooks.discord.component.Clickable;
import com.lutzseverino.discordbooks.discord.component.impl.ClickableImpl;
import com.lutzseverino.discordbooks.discord.component.impl.SelectableImpl;
import com.lutzseverino.discordbooks.discord.message.impl.EmbedImpl;
import com.lutzseverino.discordbooks.discord.message.impl.SendableImpl;

public class ExampleBook extends Book {

    public ExampleBook() {
        super("example");

        addPages(
                // First page, has regular text and a button of its own.
                new SendableImpl("A regular ol' text message.")
                        .addActionableRows(ActionableRow.of(
                                new ClickableImpl(Clickable.Style.PRIMARY)
                                        .setId("example-page-button")
                                        .setDisplay("Pages can have buttons of their own!"))),
                // Second page, has two embeds.
                new SendableImpl().addEmbeds(
                        new EmbedImpl()
                                .setTitle("Embeds?")
                                .setDescription("No problemo."),
                        new EmbedImpl()
                                .setTitle("Another one, too!")
                                .setDescription("Messages can have multiple of these!")
                ),
                // Third page, has regular text and an embed.
                new SendableImpl().setText("Text and embeds at the same time?")
                        .addEmbeds(
                                new EmbedImpl()
                                        .setTitle("I betcha")
                                        .setDescription("Need more convincing? Too bad, I've got no more pages left."))
        );

        setNextClickable(clickable -> clickable.setEmoji("➡"));
        setPreviousClickable(clickable -> clickable.setEmoji("⬅️"));

        addClickables(
                getPreviousClickable(),
                new ClickableImpl(Clickable.Style.DANGER)
                        .setId("example-danger")
                        .setEmoji("🔥"),
                getNextClickable()
        );

        addActionableRows(
                getClickableRow(),
                ActionableRow.of(
                        new ClickableImpl(Clickable.Style.SECONDARY)
                                .setId("example-secondary")
                                .setDisplay("More rows?"),
                        new ClickableImpl(Clickable.Style.SUCCESS)
                                .setId("example-success")
                                .setDisplay("Easy task!")
                ),
                ActionableRow.of(
                        new SelectableImpl("select", new SelectableImpl.OptionImpl("Menus too!", "Amazing")
                                .setDefault(true)
                        )
                )
        );
    }
}
