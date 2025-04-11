package ar.com.mercadolibre.crud.repository;

import ar.com.mercadolibre.crud.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    // HQL
}
