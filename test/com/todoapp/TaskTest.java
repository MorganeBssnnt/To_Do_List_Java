package com.todoapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    void testTaskCreation() {
        Task task = new Task("Acheter du lait", "Aller au supermarché");
        assertEquals("Acheter du lait", task.getTitle());
    }
}
