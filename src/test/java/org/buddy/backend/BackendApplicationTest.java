package org.buddy.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class BackendApplicationTest {

    @Autowired
    private BackendApplication backendApplication;

    @Test
    public void contextLoads() {
        assertNotNull(backendApplication);
    }

}
