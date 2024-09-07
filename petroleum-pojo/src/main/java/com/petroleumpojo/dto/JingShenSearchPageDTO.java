package com.petroleumpojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class JingShenSearchPageDTO {
    private Integer pageIndex;
    private Integer pageSize;
    private String WellName;
    private String PrimaryWellType;
    private String WellType;
}
