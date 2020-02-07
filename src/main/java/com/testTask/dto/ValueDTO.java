package com.testTask.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class ValueDTO {

    @Positive
    @NotNull(message = "Value can not be empty")
    private Float value;
}
