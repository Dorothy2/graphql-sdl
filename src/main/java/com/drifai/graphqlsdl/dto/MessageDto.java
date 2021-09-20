package com.drifai.graphqlsdl.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageDto {
    private UUID id;
    private String text;
    private Integer luckyNumbers;
}
