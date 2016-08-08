package fezzik.web.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * Basic integration tests for service demo application.
 *
 * @author Dave Syer
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringBootConfigurationTest {

    @Value("${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate rest;

    @Test
    public void testHome() throws Exception {
//        ResponseEntity<String> entity = rest.getForEntity(
//                "http://inigo:montoya@localhost:" + this.port + "/test", String.class);
//        assertEquals(HttpStatus.OK, entity.getStatusCode());
//        assertEquals("Fezzik Home", entity.getBody());
    }

}
