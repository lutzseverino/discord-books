![java](https://img.shields.io/badge/java-%23ED8B00.svg?style=flat&logo=java&logoColor=white) ![total lines](https://img.shields.io/tokei/lines/github/frequential/discord-books) ![license](https://img.shields.io/github/license/frequential/discord-books)

# Discord Books

A lightweight and implementable library that helps you create multi-page messages automatically on Discord.

## Summary

* Installation
    * Spins
* Features
* Usage

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

* Create book-like messages on Discord.
* Create books with regular messages and embed messages at the same time.
* Customize buttons or add extra ones.
* Add extra component rows.
* Handle errors easily.

## Usage

Each spin has its own Book extension, to create a book, you'll need to create a class that extends the appropriate Book
subclass.

### Creating the book

By calling the super constructor, you can set the books' name. You may also specify if you want the book to be available
for everyone and not only the owner.

```java
public class Example extends JDABook {
    public Example() {
        // "example" will be the name of the book, and it'll NOT be available for everyone.
        super("example", false);
    }
}
```

### Adding pages

Pages are added with the `#addPages()` method. Each page can contain one `Sendable`, which will vary depending on the
spin you're using.

```java
public class Example extends JDABook {
    public Example() {
        super("example");

        addPages(
                // Add a page that contains two embed messages...
                new Page(new JDAMessageEmbed(
                        new EmbedBuilder().setTitle("Page 1").setDescription("This is an example of a page, you can put whatever you like in here").build(),
                        new EmbedBuilder().setTitle("Page 1, Embed 2").setDescription("Yes, a page can also have multiple embeds").build()
                )),
                // ...and a page that contains a single embed message...
                new Page(new JDAMessageEmbed(new EmbedBuilder().setTitle("Page 2").setDescription("This is the second page. All the previous' page content has been replaced.").build())),
                // ...and a page that contains a normal message.
                new Page(new JDAMessage("And this is the third page. Embeds aren't required, you know?"))
        );
    }
}
```

### Customizing buttons

Navigation buttons are created automatically, but you need to customize and add them in order to use them. You use
the  `#setPreviousButtonAppearance()` and `#setNextButtonAppearance()` methods to do this.

Finally, to add the buttons, we call `#addButtons()` with the buttons you want to add, including the buttons previously
customized with `#getPreviousButton()` and `#getNextButton()`.

```java
public class Example extends JDABook {
    public Example() {
        super("example");

        addPages(
                new Page(new JDAMessageEmbed(
                        new EmbedBuilder().setTitle("Page 1").setDescription("This is an example of a page, you can put whatever you like in here").build(),
                        new EmbedBuilder().setTitle("Page 1, Embed 2").setDescription("Yes, a page can also have multiple embeds").build()
                )),
                new Page(new JDAMessageEmbed(new EmbedBuilder().setTitle("Page 2").setDescription("This is the second page. All the previous' page content has been replaced.").build())),
                new Page(new JDAMessage("And this is the third page. Embeds aren't required, you know?"))
        );

        // Customize the buttons...
        setPreviousButtonAppearance(button -> button.withEmoji(Emoji.fromMarkdown("⬅️")));
        setNextButtonAppearance(button -> button.withEmoji(Emoji.fromMarkdown("➡️")));

        // ...then add the buttons to the book, alongside any additional buttons you want to add.
        addButtons(getPreviousButton(), getNextButton(), Button.danger("close", "Discard"));
    }
}
```

### Adding action rows

Additional button rows may be added via the `#addActionRows()` method.

```java
public class Example extends JDABook {
    public Example() {
        super("example");

        addPages(
                new Page(new JDAMessageEmbed(
                        new EmbedBuilder().setTitle("Page 1").setDescription("This is an example of a page, you can put whatever you like in here").build(),
                        new EmbedBuilder().setTitle("Page 1, Embed 2").setDescription("Yes, a page can also have multiple embeds").build()
                )),
                new Page(new JDAMessageEmbed(new EmbedBuilder().setTitle("Page 2").setDescription("This is the second page. All the previous' page content has been replaced.").build())),
                new Page(new JDAMessage("And this is the third page. Embeds aren't required, you know?"))
        );

        setPreviousButtonAppearance(button -> button.withEmoji(Emoji.fromMarkdown("⬅️")));
        setNextButtonAppearance(button -> button.withEmoji(Emoji.fromMarkdown("➡️")));

        addButtons(getPreviousButton(), getNextButton(), Button.danger("close", "Discard"));
        // These rows will be displayed below the buttons added by the #addButtons() method.
        addActionRows(
                ActionRow.of(
                        Button.secondary("action", "More action rows?"),
                        Button.secondary("problem", "No problem!")
                ),
                ActionRow.of(
                        Button.secondary("third", "A third one?"),
                        Button.secondary("point", "Nah, you get the point.")
                ),
                ActionRow.of(
                        Button.secondary("https://example.com", "Do you...?")
                )
        );
    }
}
```

### Specifying the owner

The owner of the book **may not be specified** if your bot is using slash command interactions, if so, the user that
fired the interaction will automatically be the owner of the book.

Otherwise, the owner needs to be specified at send time.

### Adding the books

You can add books whenever you see fit, but it most users will most likely want to add them inside your main method.

```java
public class Main {
    public final DiscordBooks discordBooks = new DiscordBooks();

    public static void main(String[] args) {
        discordBooks.addBooks(new Example());
    }
}
```

### Listening to the button event

Listening to the button event that will handle navigation varies depending on the library you are using, if you're using
JDA, adding the `JDABookButtonListener` will be enough.

Each listener requires you to pass in the book list, thankfully, `DiscordBooks#getBooks()` returns the list of books you
added.

### Handling errors

The way of handling errors also depends on your library, if you're using JDA, you implement the `JDABookErrorListener`
class to handle errors.

```java
public class JDABookErrorHandlerImpl implements JDABookErrorHandler {
    public void whenBookIsNull(ButtonInteractionEvent event) {
        event.reply("Book not found, it may no longer exist in the codebase.").queue();
    }

    public void whenUserIsNotOwner(ButtonInteractionEvent event) {
        event.reply("You are not the author of this book.").queue();
    }
}
```

Once that's done, you'll need to pass an instance of your implementation to the listener that we registered in the previous step.