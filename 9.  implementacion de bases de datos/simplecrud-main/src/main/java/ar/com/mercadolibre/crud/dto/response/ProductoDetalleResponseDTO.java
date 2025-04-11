package ar.com.mercadolibre.crud.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductoDetalleResponseDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
}
