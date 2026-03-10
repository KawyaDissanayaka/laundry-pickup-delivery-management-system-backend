package com.laundry.controller;

import com.laundry.dto.CommitHistoryDTO;
import com.laundry.service.CommitHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.util.List;

@RestController
@RequestMapping("/api/commits")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000", "http://localhost:5174"})
public class CommitHistoryController {

    @Autowired
    private CommitHistoryService commitHistoryService;

    /**
     * Get all commit history as JSON
     * @return List of commits
     */
    @GetMapping("/history")
    public ResponseEntity<List<CommitHistoryDTO>> getCommitHistory() {
        List<CommitHistoryDTO> commits = commitHistoryService.getCommitHistory();
        return ResponseEntity.ok(commits);
    }

    /**
     * Generate and download commit history as an image
     * @return PNG image of the commit history
     */
    @GetMapping("/history/image")
    public ResponseEntity<byte[]> getCommitHistoryImage() {
        ByteArrayOutputStream imageStream = commitHistoryService.generateCommitHistoryImage();
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setContentDispositionFormData("attachment", "commit-history.png");
        
        return ResponseEntity.ok()
            .headers(headers)
            .body(imageStream.toByteArray());
    }

    /**
     * Display commit history image inline (for browser viewing)
     * @return PNG image of the commit history
     */
    @GetMapping("/history/image/view")
    public ResponseEntity<byte[]> viewCommitHistoryImage() {
        ByteArrayOutputStream imageStream = commitHistoryService.generateCommitHistoryImage();
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        
        return ResponseEntity.ok()
            .headers(headers)
            .body(imageStream.toByteArray());
    }
}
