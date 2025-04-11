package ar.com.mercadolibre.crud.controller;

import ar.com.mercadolibre.crud.dto.request.ProductoRequestDTO;
import ar.com.mercadolibre.crud.dto.response.ProductoDetalleResponseDTO;
import ar.com.mercadolibre.crud.dto.response.ProductoResponseDTO;
import ar.com.mercadolibre.crud.service.IProductoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final IProductoService productoService;

    public ProductoController(IProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping
    public ProductoResponseDTO crearProducto(@RequestBody ProductoRequestDTO productoDTO) {
        return productoService.guardarProducto(productoDTO);
    }

    @GetMapping("/{id}")
    public ProductoDetalleResponseDTO obtenerProducto(@PathVariable Long id) {
        return productoService.obtenerProductoPorId(id);
    }

    @GetMapping
    public List<ProductoRequestDTO> listarProductos() {
        return productoService.listarProductos();
    }
}
