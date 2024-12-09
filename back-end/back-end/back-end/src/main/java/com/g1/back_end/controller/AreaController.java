package com.g1.back_end.controller;

import com.g1.back_end.domain.AreaDomain;
import com.g1.back_end.dto.AreaDTO;
import com.g1.back_end.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/area")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @PostMapping
    public ResponseEntity<AreaDomain> createArea(@RequestBody AreaDTO area) {
        return ResponseEntity.ok(areaService.save(area));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AreaDomain> getAreaById(@PathVariable Long id) {
        return areaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<AreaDomain>> getAllAreas() {
        return ResponseEntity.ok(areaService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArea(@PathVariable Long id) {
        areaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
