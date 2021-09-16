package com.drifai.graphqlsdl.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {
    private LocalDate birthDate;
    private OffsetTime workStartTime;
    private OffsetDateTime bornAt;
    // not SocialMediaLink
    private String profileLink;
}
