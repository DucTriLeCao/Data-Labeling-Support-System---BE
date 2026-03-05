package project.dlss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.dlss.entity.Dataset;
import project.dlss.service.DatasetService;

import java.util.List;

@RestController
@RequestMapping("/api/datasets")
public class DatasetController {

    @Autowired
    private DatasetService datasetService;

    // CREATE
    @PostMapping
    public Dataset createDataset(@RequestBody Dataset dataset) {
        return datasetService.createDataset(dataset);
    }

    // GET ALL
    @GetMapping
    public List<Dataset> getAllDatasets() {
        return datasetService.getAllDatasets();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Dataset getDatasetById(@PathVariable Long id) {
        return datasetService.getDatasetById(id);
    }

    // GET DATASET BY PROJECT
    @GetMapping("/project/{projectId}")
    public List<Dataset> getDatasetsByProject(@PathVariable Long projectId) {
        return datasetService.getDatasetsByProject(projectId);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Dataset updateDataset(@PathVariable Long id,
                                 @RequestBody Dataset dataset) {
        return datasetService.updateDataset(id, dataset);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deleteDataset(@PathVariable Long id) {
        datasetService.deleteDataset(id);
    }

}