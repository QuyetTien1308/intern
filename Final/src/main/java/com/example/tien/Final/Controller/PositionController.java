package com.example.tien.Final.Controller;

import com.example.tien.Final.Dto.PositionDto;
import com.example.tien.Final.entity.Position;
import com.example.tien.Final.service.IPositionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/positions")
public class PositionController {
    public final IPositionService service;

    public PositionController(IPositionService service) {
        this.service = service;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<PositionDto> findAllPosition(){
        return service.findAll();
    }

//    @GetMapping("/{id}")
//    public Position findAllById(@PathVariable Long id){
//        return service.getPositionById(id);
//    }
//    @GetMapping("/{name}")
//    public Position findAllByName(@PathVariable String name){
//        return service.getPositionByName(name);
//    }
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public PositionDto addPosition(@RequestBody PositionDto position){
        return service.create(position);
    }
    @PutMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public PositionDto update(@RequestBody PositionDto dto, @PathVariable("id") Long id){
        dto.setId(id);
        return service.update(dto);
    }
    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String delete(@PathVariable("id") Long id){
        return service.delete(id);
    }

//    @PostMapping("/addPositions")
//    public List<Position> addPositions(@RequestBody List<Position> positions){
//        return service.savePositions(positions);
//    }
//    @PutMapping("/updatePosition/{id}")
//    public PositionDto updatePosition(@PathVariable Long id ,@RequestBody PositionDto positionDto){
//        positionDto.setId(id);
//        return service.updatePosition(positionDto);
//    }
//    @DeleteMapping("/deletePosition/{id}")
//    public String deletePosition(@PathVariable Long id){
//        return service.deletePosition(id);
//    }
////    @DeleteMapping("/positions")
////    public ResponseEntity<String> deleteAll(){
////        service.deleteAllPosition();
////        return ResponseEntity.ok("Delete all position");
////    }
}
