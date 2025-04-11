package ar.com.mercadolibre.crud.dto.request;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductoRequestDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
}
