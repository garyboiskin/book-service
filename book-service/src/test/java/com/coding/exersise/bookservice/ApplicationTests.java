package com.coding.exersise.bookservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {
 
  @LocalServerPort
  private Integer port;
 
  @Autowired
  private TestRestTemplate testRestTemplate;
 
  @Test
  void accessApplication() {
    System.out.println(" App running on the port:" +port);
  }
}