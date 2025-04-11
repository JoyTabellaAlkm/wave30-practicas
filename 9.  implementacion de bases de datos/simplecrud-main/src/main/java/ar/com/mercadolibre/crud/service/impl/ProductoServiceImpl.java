package ar.com.mercadolibre.crud.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import ar.com.mercadolibre.crud.dto.response.ProductoDetalleResponseDTO;
import ar.com.mercadolibre.crud.repository.ProductoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import ar.com.mercadolibre.crud.dto.request.ProductoRequestDTO;
import ar.com.mercadolibre.crud.dto.response.ProductoResponseDTO;
import ar.com.mercadolibre.crud.exception.NotFoundException;
import ar.com.mercadolibre.crud.model.Producto;
import ar.com.mercadolibre.crud.service.IProductoService;

@Service
public class ProductoServiceImpl implements IProductoService {

    private final ProductoRepository productoRepository;
    private final ModelMapper modelMapper;

    public ProductoServiceImpl(ProductoRepository productoRepository, ModelMapper modelMapper) {
        this.productoRepository = productoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductoResponseDTO guardarProducto(ProductoRequestDTO productoDTO) {
        // ModelMapper -> Deserializar
        Producto producto = modelMapper.map(productoDTO, Producto.class);
        String status = "";

        // Producto -> id -> PK -> pisa
        //Update
        if(producto.getId()!= null && productoRepository.existsById(producto.getId())){
            productoRepository.save(producto);
            status  = "UPDATED";
        } else{ // SE INSERTA
            productoRepository.save(producto);
            status  = "INSERTED";
        }
        ProductoResponseDTO responseDTO = modelMapper.map(producto,ProductoResponseDTO.class);
        responseDTO.setStatus(status);
        return responseDTO;
    }

    @Override
    public ProductoDetalleResponseDTO obtenerProductoPorId(Long id) {
        Producto producto = productoRepository.findById(id).orElseThrow(() -> new NotFoundException("No se encontraron productos con el ID " + id));
        
        return modelMapper.map(producto, ProductoDetalleResponseDTO.class);
    }

    @Override
    public List<ProductoRequestDTO> listarProductos() {
        return productoRepository.findAll().stream().map(producto -> modelMapper.map(producto, ProductoRequestDTO.class)).collect(Collectors.toList());
    }
}
