package com.alfonsotienda.holaspring.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaRepository 
    extends CrudRepository<Factura, Integer>{

    
}