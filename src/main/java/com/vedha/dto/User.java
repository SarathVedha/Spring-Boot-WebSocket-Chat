package com.vedha.dto;

import com.vedha.util.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "User", description = "User details")
public class User {

    private String nickName;

    private String fullName;

    private Status status;
}
