package guru.springgramework.sfgpetclinic.controllers;

import guru.springgramework.sfgpetclinic.model.Owner;
import guru.springgramework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
class ownersControllerTest {

    Set<Owner> owners;

    @InjectMocks
    OwnersController controller;

    @Mock
    OwnerService ownerService;
    @Mock
    Model model;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        owners = new HashSet<>();
        owners.add(Owner.builder().id(1l).build());
        owners.add(Owner.builder().id(2l).build());
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void ownersList() throws Exception {
        String indexTest = "owners/index";
        owners = new HashSet<>();
        owners.add(Owner.builder().id(1l).build());
        owners.add(Owner.builder().id(2l).build());
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        when(ownerService.findAll()).thenReturn(owners);
        assertEquals(indexTest, controller.ownersList(model));


        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/index"))
                .andExpect(model().attribute("owners", hasSize(2)));
//        verify(ownerService, times(2)).findAll();
        verify(model, times(1)).addAttribute("owners", ownerService.findAll());

    }

    @Test
    void findOwners() throws Exception {
        String indexTest = "notImplemented";
        assertEquals(indexTest, controller.findOwners());
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(get("/owners/find")).andExpect(status().isOk())
                .andExpect(view().name("notImplemented"));
        verifyNoInteractions(ownerService);
    }
}