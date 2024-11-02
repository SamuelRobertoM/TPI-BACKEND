package com.agencia.pruebas_service.service;

import com.agencia.pruebas_service.model.Prueba;
import com.agencia.pruebas_service.repository.PruebaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PruebaService {
    @Autowired
    private PruebaRepository pruebaRepository;

    //crea una nueva prueba
    public Prueba crearPrueba(Prueba prueba) {
        return pruebaRepository.save(prueba);
    }

    //Devuelve todas las pruebas en curso
    public List<Prueba> pruebasEnCurso() {
        return pruebaRepository.findAll().stream()
                .filter(prueba -> prueba.getFechaHoraFin() == null)
                .toList();

    }

    //Finaliza la prueba
    public Optional<Prueba> finalizarPrueba(Long id, String comentarios){
        Optional<Prueba> pruebaOpt = pruebaRepository.findById(id);
        if (pruebaOpt.isPresent()) {
            Prueba prueba = pruebaOpt.get();
            prueba.setFechaHoraFin(LocalDateTime.now());
            prueba.setComentarios(comentarios);
            return Optional.of(pruebaRepository.save(prueba));
        }
        return Optional.empty();
    }
}
