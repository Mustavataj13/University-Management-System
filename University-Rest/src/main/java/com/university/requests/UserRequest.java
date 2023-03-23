package com.university.requests;

import com.university.enums.Roles;
import lombok.Data;

@Data
public class UserRequest {

        private Integer userId;
        private String username;
        private String password;
        private String email;
        private int enabled;
        private String roles;

}
