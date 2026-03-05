package project.dlss.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.dlss.dto.DataItemDTO;
import project.dlss.entity.DataItem;
import project.dlss.service.DataItemService;

import java.util.List;

@RestController
@RequestMapping("/data-items")
@RequiredArgsConstructor
public class DataItemController {

    private final DataItemService dataItemService;

    // CREATE
    @PostMapping
    public DataItem create(@RequestBody DataItemDTO dto) {
        return dataItemService.createDataItem(dto);
    }

    // GET ALL
    @GetMapping
    public List<DataItem> getAll() {
        return dataItemService.getAll();
    }

    // GET BY DATASET
    @GetMapping("/dataset/{datasetId}")
    public List<DataItem> getByDataset(@PathVariable Long datasetId) {
        return dataItemService.getByDataset(datasetId);
    }

    // UPDATE
    @PutMapping("/{id}")
    public DataItem update(@PathVariable Long id,
                           @RequestBody DataItemDTO dto) {
        return dataItemService.update(id, dto);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        dataItemService.delete(id);
    }

}