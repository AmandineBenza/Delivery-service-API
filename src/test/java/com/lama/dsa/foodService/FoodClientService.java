package com.lama.dsa.foodService;


import com.lama.dsa.app.Application;
import com.lama.dsa.service.food.FoodService;
import com.lama.dsa.service.food.IFoodService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@RunWith(SpringRunner.class) //is used to provide a bridge between Spring Boot test features and JUnit.
@SpringBootTest(classes = {FoodService.class})
@ContextConfiguration(classes = {Application.class})
@AutoConfigureMockMvc
public class FoodClientService {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IFoodService foodService;

    // write test cases here


    @Test
    public void whenFindByNameClientService() throws Exception {

       when(foodService.getAll()).thenReturn(null);
       /*MvcResult result = this.mockMvc.perform(get("/FOOD")).andDo(print()).andExpect(status().isOk())
                .andExpect(status().isBadRequest())
                .andReturn();

        //String content = result.getResponse().getContentType();
        //System.out.println(content);
        //assertEquals(foodService, null);*/



    }




/*        //given
        Client client1 = new Client("alex");
        entityManager.persist(client1);
        entityManager.flush();

        //when
        Client found = (Client) clientRepository.findByName(client1.getName());

        //then
        //assertThat(found.getName()).isEqualTo(client1.getName());
        //assertEquals.

    } */

}