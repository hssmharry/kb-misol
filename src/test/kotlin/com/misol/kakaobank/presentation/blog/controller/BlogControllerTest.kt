//package com.misol.kakaobank.presentation.blog.controller
//
//import com.fasterxml.jackson.databind.ObjectMapper
//import jakarta.transaction.Transactional
//import org.junit.jupiter.api.Test
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.http.MediaType
//import org.springframework.test.context.ActiveProfiles
//import org.springframework.test.web.servlet.MockMvc
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
//
//@AutoConfigureMockMvc
//@SpringBootTest
//@ActiveProfiles("local")
//class BlogControllerTest {
//
//    @Autowired
//    lateinit var mockMvc: MockMvc
//
//    @Autowired
//    lateinit var objectMapper: ObjectMapper
//
//    @Test
//    @Transactional
//    fun search() {
//        // Given
//        insertTodo("Title Junit Test Insert 01", "Description Junit Test Insert 01", false)
//        insertTodo("Title Junit Test Insert 02", "Description Junit Test Insert 02", true)
//        insertTodo("Title Junit Test Insert 03", "Description Junit Test Insert 03", false)
//        insertTodo("Title Junit Test Insert 04", "Description Junit Test Insert 04", true)
//        insertTodo("Title Junit Test Insert 05", "Description Junit Test Insert 05", false)
//
//        val url = "/api/todos"
//        val todoRequest = TodoRequest().apply {
//            this.title = "Title Junit Test Insert"
//            this.description = "Description Junit Test Insert"
//            this.completed = true
//        }
//
//        // When
//        val resultActions = mockMvc.perform(
//                MockMvcRequestBuilders.post(url)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(todoRequest))
//        )
//
//        // Then
//        resultActions
//                .andExpect(status().isOk)
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.length()", `is`(2)))
//                .andDo(print())
//    }
//
//    @Test
//    fun popular() {
//    }
//}