package com.pricing;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class PricingControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @WithMockUser
  @Test
  public void testGetPricing() throws Exception {
    mockMvc.perform(get("/pricing/getPricing")).andExpect(status().isOk());
  }

  @Test
  public void testGetPricingWithoutAuthentication() throws Exception {
    mockMvc.perform(get("/pricing/getPricing")).andExpect(status().isUnauthorized());
  }

  // PUT action not yet implemented, this is just to test the auth filter
  @WithMockUser(roles = "ADMIN")
  @Test
  public void testPut() throws Exception {
    mockMvc.perform(put("/pricing")).andExpect(status().isOk());
  }

  // PUT action not yet implemented, this is just to test the auth filter
  @WithMockUser(roles = "USER")
  @Test
  public void testPutWithoutAuthorization() throws Exception {
    mockMvc.perform(put("/pricing")).andExpect(status().isForbidden());
  }

}
