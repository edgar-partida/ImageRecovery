package com.silknode.recovery.controller;

import com.silknode.recovery.service.ImageStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/images")
public class ImageUploadController {
    private static final Logger log = LoggerFactory.getLogger(ImageUploadController.class);
    private final ImageStorageService imageStorageService;


    public ImageUploadController(ImageStorageService imageStorageService) {
        this.imageStorageService = imageStorageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            log.info("Recibiendo archivo: {}", file.getOriginalFilename());
            String path = imageStorageService.storeImage(file);
            return ResponseEntity.ok("Imagen guardada en: " + path);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al guardar la imagen: " + e.getMessage());
        }
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        log.debug("Health check endpoint called");
        return ResponseEntity.ok("Image upload service is running");
    }
}