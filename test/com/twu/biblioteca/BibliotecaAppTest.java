package com.twu.biblioteca;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


public class BibliotecaAppTest {
    BibliotecaApp app;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Before
    public void setUp() throws Exception {
        app = new BibliotecaApp();
    }

    @After
    public void restoreStreams() {
        System.setOut(System.out);
    }

    @Test
    public void shouldReturnAWelcomeMessage() {
        app.welcomeMessage();
        assertEquals("Welcome to the main menu, choose an option:", outContent.toString().trim());
    }

    @Test
    public void shouldPrintAMainMenuOptionsAndReceiveInputOptionAndPrintTheResultOfTheChoice() {
        String message = "1.Book List\n2.Quit";
        String result = "book\nbook\nbook\nbook\nbook\nbook";

        String input = "1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        app.mainMenu();

        assertThat(outContent.toString().trim(), CoreMatchers.containsString(message));
        assertThat(outContent.toString().trim(), CoreMatchers.containsString(result));
    }

    @Test
    public void shouldReceiveInputFromUser() {
        String input = "1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        app.inputUser();

        assertThat(outContent.toString().trim(), CoreMatchers.containsString("Choose your option: "));
        assertThat(outContent.toString().trim(), CoreMatchers.containsString("you choose: 1"));
    }
}
