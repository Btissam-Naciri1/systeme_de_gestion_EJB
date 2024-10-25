package com.example.ejbexam.Bean;
import com.example.ejbexam.entity.CD;

import com.example.ejbexam.service.CDService;
import jakarta.ejb.EJB;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;
import java.util.List;


@ManagedBean
@RequestScoped
public class CDBean {
    @EJB
    private CDService cdService;

    private Long selectedCDId; // For borrowing/returning CDs

    public List<CD> getCds() {
        return cdService.listAvailableCDs();
    }

    public void borrowCD() {
        cdService.borrowCD(selectedCDId);
        // Add success message or redirect
    }

    public void returnCD() {
        cdService.returnCD(selectedCDId);
        // Add success message or redirect
    }

    // Getters and Setters
    public Long getSelectedCDId() {
        return selectedCDId;
    }

    public void setSelectedCDId(Long selectedCDId) {
        this.selectedCDId = selectedCDId;
    }
}
