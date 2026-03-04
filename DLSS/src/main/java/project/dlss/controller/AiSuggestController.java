package project.dlss.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.dlss.dto.AiSuggestRequest;
import project.dlss.service.AiSuggestService;

import java.util.List;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AiSuggestController {

    private final AiSuggestService aiSuggestService;

    @PostMapping("/suggest-label")
    public ResponseEntity<List<String>> suggestLabel(@Valid @RequestBody AiSuggestRequest request) {
        List<String> suggestions = aiSuggestService.suggestLabel(
                request.getDataItemId(),
                request.getContent()
        );
        return ResponseEntity.ok(suggestions);
    }
}
