package com.lama.dsa.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.lama.dsa.app.Application;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = Application.class)
public class SmokeTest {

    @Autowired
    private Controller controller;
       
    @Test
    public void contexLoads() throws Exception {
        assertThat(controller).isNotNull();
    }
    
      
}