![java language](https://img.shields.io/badge/java-%23ED8B00.svg?style=flat&logo=oracle&logoColor=white) ![license](https://img.shields.io/github/license/lutzseverino/discord-books) ![maven central version](https://img.shields.io/maven-central/v/com.lutzseverino.discordbooks/discord-books) ![gitHub last commit](https://img.shields.io/github/last-commit/lutzseverino/discord-books) ![total lines](https://img.shields.io/tokei/lines/github/lutzseverino/discord-books)

![discord-books](https://user-images.githubusercontent.com/28309837/236696025-3efb244f-6bc0-4bee-a515-94c9dfc9e44e.png)
![discord-books library showcase](https://user-images.githubusercontent.com/28309837/182843934-f5eb5cb9-3f76-44a9-82a2-20d1a1ee3648.gif)

Discord Books is a lightweight, implementable library that provides message pagination for Discord.

## Installation

Add the following to your `pom.xml` file:

```xml
<dependency>
    <groupId>com.lutzseverino.discordbooks</groupId>
    <artifactId>discord-books-SPIN</artifactId>
    <version>VERSION</version>
</dependency> 
```

Alternatively, for Gradle:

```gradle
implementation 'com.lutzseverino.discordbooks:discord-books-SPIN:VERSION'
```

Replace `SPIN` and `VERSION` with the appropriate values.

### Available spins

* core
* [jda](https://github.com/DV8FromTheWorld/JDA) (Java Discord API)

### Versions

This is the latest version of the library on Maven Central. You can compile the library yourself for newer, unreleased
versions.

![Maven Central](https://img.shields.io/maven-central/v/com.lutzseverino.discordbooks/discord-books)

## Features

* Create static books that don't expire as long as they exist in your source.
* Create on-the-go books that can contain variable content.
* Choose how books should be stored and retrieved.
* Customize all aspects of the action rows, add extra ones or change the order.
* Add action rows to individual pages.
* Handle errors easily.

## Wiki

Understand the library and learn how to use it by reading the [wiki](https://github.com/lutzseverino/discord-books/wiki).

## Credits

Idea and execution by [Jasper Lutz Severino](https://github.com/lutzseverino). General audit and help for all versions
below 1.0.0 by [Alberto Mimbrero](https://github.com/mimbrero).
