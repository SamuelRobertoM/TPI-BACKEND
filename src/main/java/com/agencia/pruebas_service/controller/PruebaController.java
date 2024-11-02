package com.agencia.pruebas_service.controller;

import com.agencia.pruebas_service.model.Prueba;
import com.agencia.pruebas_service.service.PruebaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pruebas")
public class PruebaController {
    @Autowired
    private PruebaService pruebaService;

    //endpoint de la nueva prueba
    @PostMapping
    public ResponseEntity<Prueba> crearPrueba(@RequestBody Prueba prueba) {
        Prueba nuevaPrueba = pruebaService.crearPrueba(prueba);
        return ResponseEntity.ok().body(nuevaPrueba);
    }


    //endpoint de la lista de pruebas en curso
    @GetMapping("/en-curso")
    public ResponseEntity<List<Prueba>> pruebasEnCurso() {
        List<Prueba> pruebaEnCurso = pruebaService.pruebasEnCurso();
        return ResponseEntity.ok().body(pruebaEnCurso);
    }


    //endpoint para finalizar
    @PutMapping({"/{id}/finalizar"})
    public ResponseEntity<Prueba> finalizarPrueba(@PathVariable Long id, @RequestBody String comentarios) {
        return pruebaService.finalizarPrueba(id, comentarios)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

    }
}