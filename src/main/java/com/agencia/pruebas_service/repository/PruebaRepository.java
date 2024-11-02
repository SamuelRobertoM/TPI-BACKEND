package com.agencia.pruebas_service.repository;

import com.agencia.pruebas_service.model.Prueba;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PruebaRepository extends JpaRepository <Prueba, Long> {

}
