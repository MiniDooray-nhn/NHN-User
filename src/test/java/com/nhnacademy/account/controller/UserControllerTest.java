package com.nhnacademy.account.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.account.dto.DeleteResponse;
import com.nhnacademy.account.dto.UserModifyRequest;
import com.nhnacademy.account.dto.UserRegisterRequest;
import com.nhnacademy.account.dto.UserResponse;
import com.nhnacademy.account.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void Given_User_Id_When_Get_User_Then_Return_User_Response() throws Exception {
        String userId = "testUserId";
        UserResponse userResponse = new UserResponse(userId, 1, "Test User");

        Mockito.when(userService.getUserById(ArgumentMatchers.anyString())).thenReturn(userResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(userId))
                .andExpect(jsonPath("$.statusId").value(1))
                .andExpect(jsonPath("$.name").value("Test User"));
    }

    @Test
    void Given_Valid_User_Register_Request_When_Create_User_Then_Return_User_Response() throws Exception {
        UserRegisterRequest userRegisterRequest =
                new UserRegisterRequest("testUserId", "testPassword", "test@example.com", "Test User");
        UserResponse userResponse = new UserResponse("testUserId", 1, "Test User");

        Mockito.when(userService.createUser(ArgumentMatchers.any(UserRegisterRequest.class))).thenReturn(userResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRegisterRequest)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value("testUserId"))
                .andExpect(jsonPath("$.statusId").value(1))
                .andExpect(jsonPath("$.name").value("Test User"));
    }

    @Test
    void Given_Valid_User_Modify_Request_When_Modify_User_Then_Return_User_Response() throws Exception {
        String userId = "testUserId";
        UserModifyRequest userModifyRequest = new UserModifyRequest("newPassword", "new@example.com", "New Name");
        UserResponse userResponse = new UserResponse(userId, 1, "New Name");

        Mockito.when(
                        userService.modifyUserById(ArgumentMatchers.anyString(), ArgumentMatchers.any(UserModifyRequest.class)))
                .thenReturn(userResponse);

        mockMvc.perform(MockMvcRequestBuilders.put("/user/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userModifyRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(userId))
                .andExpect(jsonPath("$.statusId").value(1))
                .andExpect(jsonPath("$.name").value("New Name"));
    }

    @Test
    void Given_User_Id_When_Delete_User_Then_Return_Delete_Response() throws Exception {
        String userId = "testUserId";
        DeleteResponse deleteResponse = new DeleteResponse("User deleted successfully");

        Mockito.when(userService.deleteUserById(ArgumentMatchers.anyString())).thenReturn(deleteResponse);

        mockMvc.perform(MockMvcRequestBuilders.delete("/user/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("User deleted successfully"));
    }
}
