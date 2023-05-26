package uz.najottalim.javan6.dto;

import jakarta.persistence.Column;

public interface CustomerDtoForMostValuable {
    Long getId();
    String getName();
    Integer getTier();
    Double getPayedPrice();
}
