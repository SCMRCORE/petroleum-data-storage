package com.petroleumpojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WanGongDTO {
    private Integer OnlyKey;
    private String WellName;
    private String url;
    private String fileName;
    private String uploadTime;
}
