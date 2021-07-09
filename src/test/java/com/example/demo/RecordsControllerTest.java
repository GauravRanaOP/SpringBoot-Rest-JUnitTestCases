package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.demo.Records.RecordsBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;

import javassist.NotFoundException;

@WebMvcTest(RecordsController.class)

public class RecordsControllerTest {

	@Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    
    @MockBean
    RecordsRepo repo;
    
    Records RECORD_1 = new Records(1l, "Gaurav", "Rana", "110085", "1998-05-22", "2021-06-24");
    Records RECORD_2 = new Records(2l, "Sahil", "Joshi", "110089", "1997-12-04", "2021-05-13" );
    Records RECORD_3 = new Records(3l, "Sachin", "Rawat", "123456", "1996-11-26", "2020-11-25");
    
    
    //Get test case
    @Test
    public void getAllRecords_success() throws Exception {
        List<Records> records = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3));
        
        Mockito.when(repo.findAll()).thenReturn(records);
        
        mockMvc.perform(MockMvcRequestBuilders
                .get("/rec")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    
    //Get with ID test case
    @Test
    public void getRecordsById_success() throws Exception {
        Mockito.when(repo.findById(RECORD_1.getAid())).thenReturn(java.util.Optional.of(RECORD_1));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/rec/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.fname", is("Gaurav")));
    }
    
    //Post test case
    @Test
    public void createRecord_success() throws Exception {
        Records record = Records.builder()
                .fname("Umang")
                .lname("Sahu")
                .pincode("112233")
                .DOB("1999-03-07")
                .DateOfJoining("2021-06-15")
                .build();

        Mockito.when(repo.save(record)).thenReturn(record);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/rec")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(record));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.fname", is("Umang")));
        }
    
    
    //Put test case
    @Test
    public void updatePatientRecord_success() throws Exception {
        Records updatedRecord = Records.builder()
        		.aid(1l)
                .fname("Gaurav")
                .lname("Rana")
                .pincode("110085")
                .DOB("1998-05-22")
                .DateOfJoining("2021-06-24")
                .build();

        Mockito.when(repo.findById(RECORD_1.getAid())).thenReturn(java.util.Optional.of(RECORD_1));
        Mockito.when(repo.save(updatedRecord)).thenReturn(updatedRecord);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/rec")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(updatedRecord));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.fname", is("Gaurav")));
    }
    
    @Test
    public void updatePatientRecord_nullId() throws Exception {
        Records updatedRecord= Records.builder()
                .fname("Saurav")
                .lname("Rana")
                .pincode("110085")
                .DOB("1999-04-21")
                .DateOfJoining("2021-06-24")
                .build();

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/rec")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(updatedRecord));
    }

    @Test
    public void updatePatientRecord_recordNotFound() throws Exception {
        Records updatedRecord = Records.builder()
                .aid(5l)
                .fname("Saurav")
                .lname("Rana")
                .pincode("110085")
                .DOB("1999-04-21")
                .DateOfJoining("2021-06-24")
                .build();

        Mockito.when(repo.findById(updatedRecord.getAid())).thenReturn(null);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/rec")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(updatedRecord));
    }
    
    //Delete test case
    @Test
    public void deletePatientById_success() throws Exception {
        Mockito.when(repo.findById(RECORD_2.getAid())).thenReturn(java.util.Optional.of(RECORD_2));

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/rec/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    
}
