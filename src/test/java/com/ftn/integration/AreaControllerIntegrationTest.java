package com.ftn.integration;

import com.ftn.model.Area;
import com.ftn.model.Restaurant;
import com.ftn.repository.AreaDao;
import com.ftn.repository.RestaurantTypeDao;
import com.ftn.repository.UserDao;
import com.ftn.utils.TestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Alek on 3/1/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(profiles = "test")
public class AreaControllerIntegrationTest {

    private static final String BASE_URL = "/api/areas";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AreaDao areaDao;

    @Test
    @Transactional
    @WithMockUser(roles = "GUEST")
    public void getAllAreasForbidden() throws Exception {
        mockMvc.perform(get(BASE_URL)
                .with(user("akahriman@execom.eu")
                        .authorities(new SimpleGrantedAuthority("GUEST"))))
                .andExpect(status().isForbidden());
    }

    @Test
    @Transactional
    @WithMockUser(roles = "MANAGER")
    public void getAllAreas() throws Exception {
        mockMvc.perform(get(BASE_URL)
                .with(user("res@gmail.com")
                        .authorities(new SimpleGrantedAuthority("MANAGER"))))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void createRestaurant() throws Exception {
        final Area area = areaDao.findByName("Smoking");
        mockMvc.perform(post(BASE_URL)
                .with(user("res@gmail.com")
                        .authorities(new SimpleGrantedAuthority("MANAGER")))
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.json(area)))
                .andExpect(status().isCreated());
    }

    @Test
    @Transactional
    public void createRestaurantForbidden() throws Exception {
        final Area area = areaDao.findByName("Smoking");
        mockMvc.perform(post(BASE_URL)
                .with(user("akahriman@execom.eu")
                        .authorities(new SimpleGrantedAuthority("GUEST")))
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.json(area)))
                .andExpect(status().isForbidden());
    }
}
