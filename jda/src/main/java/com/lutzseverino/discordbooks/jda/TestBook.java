package com.lutzseverino.discordbooks.jda;

import com.lutzseverino.discordbooks.book.Book;
import com.lutzseverino.discordbooks.discord.component.ActionableRow;
import com.lutzseverino.discordbooks.discord.component.Clickable;
import com.lutzseverino.discordbooks.discord.component.impl.ClickableImpl;
import com.lutzseverino.discordbooks.discord.component.impl.SelectableImpl;
import com.lutzseverino.discordbooks.discord.message.impl.EmbedImpl;
import com.lutzseverino.discordbooks.discord.message.impl.SendableImpl;

public class TestBook extends Book {

    public TestBook() {
        super("example");

        addPages(
                new SendableImpl("loler"),
                new SendableImpl("loler sequela?"),
                new SendableImpl().addEmbeds(new EmbedImpl()
                        .setTitle("El embedido.")
                        .setDescription("La descripción de este."))
        );

        setNextClickable(new ClickableImpl(Clickable.Style.PRIMARY).setDisplay("El botón del siguiente"));
        setPreviousClickable(new ClickableImpl(Clickable.Style.SECONDARY).setDisplay("El botón del anterior"));

        setClickables(getPreviousClickable(), getNextClickable());

        setActionableRows(
                getClickableRow(),
                ActionableRow.of(
                        new ClickableImpl(Clickable.Style.DANGER).setDisplay("Yo cuando el peligro")
                ),
                ActionableRow.of(
                        new SelectableImpl("select", new SelectableImpl.OptionImpl("momento", "La divertidumbre"))
                )
        );
    }
}
