package project.dlss.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.dlss.dto.LabelRequestDTO;
import project.dlss.entity.Label;
import project.dlss.service.LabelService;

import java.util.List;

@RestController
@RequestMapping("/labels")
@RequiredArgsConstructor
public class LabelController {

    private final LabelService labelService;

    @PostMapping
    public Label create(@RequestBody LabelRequestDTO dto){
        return labelService.create(dto);
    }

    @GetMapping("/project/{projectId}")
    public List<Label> getByProject(@PathVariable Long projectId){
        return labelService.getLabelsByProject(projectId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        labelService.delete(id);
    }

}