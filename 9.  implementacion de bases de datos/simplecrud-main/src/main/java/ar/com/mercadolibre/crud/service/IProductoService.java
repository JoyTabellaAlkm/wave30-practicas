package ar.com.mercadolibre.crud.service;

import java.util.List;

import ar.com.mercadolibre.crud.dto.request.ProductoRequestDTO;
import ar.com.mercadolibre.crud.dto.response.ProductoDetalleResponseDTO;
import ar.com.mercadolibre.crud.dto.response.ProductoResponseDTO;

public interface IProductoService {
    ProductoResponseDTO guardarProducto(ProductoRequestDTO productoDTO);
    ProductoDetalleResponseDTO obtenerProductoPorId(Long id);
    List<ProductoRequestDTO> listarProductos();
}
