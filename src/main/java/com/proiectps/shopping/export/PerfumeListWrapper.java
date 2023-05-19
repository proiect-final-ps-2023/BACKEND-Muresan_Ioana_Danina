package com.proiectps.shopping.export;

import com.proiectps.shopping.dto.PerfumeDTO;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "perfumes")
public class PerfumeListWrapper {
    private List<PerfumeDTO> perfumes;

    @XmlElement(name = "perfume")
    public List<PerfumeDTO> getPerfumes() {
        return perfumes;
    }

    public void setPerfumes(List<PerfumeDTO> perfumes) {
        this.perfumes = perfumes;
    }

}
