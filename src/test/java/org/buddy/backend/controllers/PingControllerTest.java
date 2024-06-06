package org.buddy.backend.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PingControllerTest {

    @Autowired
    private PingController pingController;

    @Test
    public void WhenGet_ThenShouldReturnPong() {
        // Act
        String response = pingController.get();

        // Assert
        assertEquals("pong", response);
    }

}