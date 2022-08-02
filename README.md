![java](https://img.shields.io/badge/java-%23ED8B00.svg?style=flat&logo=java&logoColor=white) ![total lines](https://img.shields.io/tokei/lines/github/frequential/discord-books) ![license](https://img.shields.io/github/license/frequential/discord-books)

<img alt="discord-books library showcase" align="right" src="https://user-images.githubusercontent.com/28309837/162641093-c8648d57-5afb-4b91-8a38-82b1473cc6d4.gif" height="450">

# Discord Books

A lightweight and implementable library that helps you create multi-page messages automatically on Discord.

## Summary

* [Installation](#installation)
    * [Spins](#spins)
* [Features](#features)
* [Usage](#usage)
    * [Creating the book](#creating-the-book)
    * [Adding pages](#adding-pages)
    * [Customizing buttons](#customizing-buttons)
    * [Adding component rows](#adding-component-rows)
    * [Specifying the owners](#specifying-the-owners)
    * [Adding the books](#adding-the-books)
    * [Sending the books](#sending-the-books)
    * [Listening to the button event](#listening-to-the-button-event)
    * [Handling errors](#handling-errors)

## Installation

Add the following to your `pom.xml` file:

```xml

<dependency>
    <groupId>st.networkers.discordbooks</groupId>
    <artifactId>discord-books-SPIN</artifactId>
    <version>1.0.0</version>
</dependency> 
```

Replace `-SPIN` with the appropriate version you're looking for. Remove it if you want every module.

### Spins

You can check all available spins on
the [GitHub Packages](https://github.com/frequential?tab=packages&repo_name=discord-books) tab.

## Features

* Create book-like messages of static content on Discord.
* Combine regular messages and embed messages at the same time.
* Customize navigation buttons or add extra ones.
* Add extra component rows.
* Control the order of buttons and rows
* Handle errors easily.

## Usage

Usage doesn't vary depending on the spin you're using.

### Creating the book

By calling the super constructor, you can set the books' name. You may also specify if you want the book to be available
for everyone and not only the owner.

```java
public class Example extends Book {
    public Example() {
        // "example" will be the name of the book.
        super("example");
    }
}
```

### Adding pages

Pages are added with the `#addPages(Sendable...)` method. `Sendables` are like Discord messages, they can contain text,
embeds, or both at the same time.

```java
public class Example extends JDABook {
    public Example() {
        super("example");

        addPages(
                new SendableImpl("A regular ol' text message."),
                new SendableImpl().addEmbeds(new EmbedImpl()
                                .setTitle("Embeds?")
                                .setDescription("No problemo."),
                        new EmbedImpl()
                                .setTitle("Another one, too!")
                                .setDescription("Messages can have multiple of these!")),
                new SendableImpl().setText("Text and embeds at the same time?")
                        .addEmbeds(
                                new EmbedImpl()
                                        .setTitle("I betcha")
                                        .setDescription("Need more convincing? Too bad, I've got no more pages left."))
        );
    }
}
```

### Customizing buttons

Navigation buttons can come in many styles, so you can create your own by calling `#setNextClickable(Clickable)`
and `#setPreviousClickable(Clickable)`.

After creating the `Clickables`, you'll have to add them manually by calling `#setClickables(Clickable...)`. This is
required, so you can have control over the order of the buttons. You may also add extra buttons to this row.

```java
public class Example extends JDABook {
    public Example() {
        super("example");

        addPages(
                new SendableImpl("A regular ol' text message."),
                new SendableImpl().addEmbeds(new EmbedImpl()
                                .setTitle("Embeds?")
                                .setDescription("No problemo."),
                        new EmbedImpl()
                                .setTitle("Another one, too!")
                                .setDescription("Messages can have multiple of these!")),
                new SendableImpl().setText("Text and embeds at the same time?")
                        .addEmbeds(
                                new EmbedImpl()
                                        .setTitle("I betcha")
                                        .setDescription("Need more convincing? Too bad, I've got no more pages left."))
        );

        // Customize the buttons...
        setNextClickable(new ClickableImpl(Clickable.Style.PRIMARY).setEmoji("➡️"));
        setPreviousClickable(new ClickableImpl(Clickable.Style.PRIMARY).setEmoji("⬅️"));


        // ...then add the buttons to the book, alongside any additional buttons you want to add.
        setClickables(
                getPreviousClickable(),
                new ClickableImpl(Clickable.Style.DANGER)
                        .setId("example-danger")
                        .setEmoji("🔥"),
                getNextClickable()
        );
    }
}
```

### Adding component rows

No rows will be displayed yet, to do that, you'll need to call the `#setActionableRows(ActionableRow...)` method. Here,
you'll have to add the row you populated before.

Again, this may be a hassle, but is the only way to ensure you get to decide the order of the rows.

```java
public class Example extends JDABook {
    public Example() {
        super("example");

        addPages(
                new SendableImpl("A regular ol' text message."),
                new SendableImpl().addEmbeds(new EmbedImpl()
                                .setTitle("Embeds?")
                                .setDescription("No problemo."),
                        new EmbedImpl()
                                .setTitle("Another one, too!")
                                .setDescription("Messages can have multiple of these!")),
                new SendableImpl().setText("Text and embeds at the same time?")
                        .addEmbeds(
                                new EmbedImpl()
                                        .setTitle("I betcha")
                                        .setDescription("Need more convincing? Too bad, I've got no more pages left."))
        );

        setNextClickable(new ClickableImpl(Clickable.Style.PRIMARY).setEmoji("➡️"));
        setPreviousClickable(new ClickableImpl(Clickable.Style.PRIMARY).setEmoji("⬅️"));

        setClickables(
                getPreviousClickable(),
                new ClickableImpl(Clickable.Style.DANGER)
                        .setId("example-danger")
                        .setEmoji("🔥"),
                getNextClickable()
        );

        // In this example, the navigation row will be the first row, then, we can add some extra ones.
        setActionableRows(
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
```

### Specifying the owners

The owners of the books may be specified at send time. [Check here](#sending-the-books)

### Adding the books

You can add books whenever you see fit, but you may want to add them instatly after running the bot.

```java
public class Main {
    public final DiscordBooks discordBooks = new DiscordBooks();

    public static void main(String[] args) {
        discordBooks.addBooks(new Example());
    }
}
```

### Sending the books

Wow! You're done! Sending is the easiest part.

You'll need to get the `DiscordBooks` instance, search the book and send it. The way you access the instance is up to
you, obviously.

To tell the library how to send the book, you'll need to create a `Receivable` object, which varies depending on what
library you're using. If you're using (e.g) JDA, that object will be a `JDAReceivable`, which takes a JDA text channel.

```java
Main.getInstance().discordBooks.getBook("example").send(new JDAReceivable(event.getChannel()));
```

There are many `#send` methods, you can specify the index to send and the owners of the book.

### Listening to the button event

Listening to the button event that will handle navigation varies depending on the library you are using, if you're using
JDA, adding the `JDABookButtonListener` will be enough.

```java
builder.addEventListeners(new JDABookButtonListener(discordBooks.getBooks()));
```

Each listener requires you to pass in the book list, thankfully, `DiscordBooks#getBooks()` returns the list of books you
added.

### Handling errors

The way of handling errors also depends on your library, if you're using JDA, you implement the `JDABookErrorListener`
class to handle errors.

```java
public class JDABookErrorHandlerImpl implements JDABookErrorHandler {
    @Override public void whenBookIsNull(ButtonInteractionEvent event) {
        event.reply("Book not found, it may no longer exist in the codebase.").queue();
    }

    @Override public void whenUserIsNotOwner(ButtonInteractionEvent event) {
        event.reply("You are not the author of this book.").queue();
    }
}
```

Once that's done, you'll need to pass an instance of your implementation to the listener that we registered in the
previous step.

## Credits

**All versions**: Idea and execution by [Jasper Lutz Severino](https://github.com/frequential).
\
**Up to 1.0.0**: General audit and help by [Alberto Mimbrero](https://github.com/mimbrero). 