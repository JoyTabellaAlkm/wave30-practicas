package ar.com.mercadolibre.crud.dto.response;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductoResponseDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private String status; // INSERTED o UPDATED
}
