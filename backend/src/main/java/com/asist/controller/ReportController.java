package com.asist.controller;

import com.asist.model.Report;
import com.asist.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * ReportController - Controlador REST para la gestión de reportes
 * 
 * Este controlador proporciona los endpoints básicos CRUD para la entidad Report:
 * - GET /api/reports - Listar todos los reportes
 * - GET /api/reports/{id} - Obtener un reporte por ID
 * - POST /api/reports - Crear un nuevo reporte
 * - PUT /api/reports/{id} - Actualizar un reporte existente
 * - DELETE /api/reports/{id} - Eliminar un reporte
 * 
 * @author AsisT Development Team
 * @version 1.0
 * @since 2025
 */
@RestController
@RequestMapping("/api/reports")
@CrossOrigin(origins = "*")
public class ReportController {

    @Autowired
    private ReportRepository reportRepository;

    /**
     * GET /api/reports
     * Obtiene la lista de todos los reportes del sistema
     * 
     * @return ResponseEntity con la lista de reportes y código 200 (OK)
     */
    @GetMapping
    public ResponseEntity<List<Report>> getAllReports() {
        try {
            List<Report> reports = reportRepository.findAll();
            if (reports.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(reports, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * GET /api/reports/{id}
     * Obtiene un reporte específico por su ID
     * 
     * @param id ID del reporte a buscar
     * @return ResponseEntity con el reporte encontrado y código 200 (OK), 
     *         o código 404 (NOT_FOUND) si no existe
     */
    @GetMapping("/{id}")
    public ResponseEntity<Report> getReportById(@PathVariable("id") Long id) {
        try {
            Optional<Report> reportData = reportRepository.findById(id);
            if (reportData.isPresent()) {
                return new ResponseEntity<>(reportData.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * POST /api/reports
     * Crea un nuevo reporte en el sistema
     * 
     * @param report Objeto Report con los datos del nuevo reporte
     * @return ResponseEntity con el reporte creado y código 201 (CREATED),
     *         o código 400 (BAD_REQUEST) si los datos son inválidos
     */
    @PostMapping
    public ResponseEntity<Report> createReport(@RequestBody Report report) {
        try {
            // Validaciones básicas
            if (report.getTitle() == null || report.getTitle().trim().isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (report.getLocation() == null || report.getLocation().trim().isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (report.getUserId() == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            
            Report savedReport = reportRepository.save(new Report(
                report.getTitle().trim(),
                report.getDescription(),
                report.getLocation().trim(),
                report.getDate(),
                report.getUserId()
            ));
            return new ResponseEntity<>(savedReport, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * PUT /api/reports/{id}
     * Actualiza un reporte existente
     * 
     * @param id ID del reporte a actualizar
     * @param report Objeto Report con los nuevos datos
     * @return ResponseEntity con el reporte actualizado y código 200 (OK),
     *         o código 404 (NOT_FOUND) si no existe
     */
    @PutMapping("/{id}")
    public ResponseEntity<Report> updateReport(@PathVariable("id") Long id, @RequestBody Report report) {
        try {
            Optional<Report> reportData = reportRepository.findById(id);
            if (reportData.isPresent()) {
                Report existingReport = reportData.get();
                
                // Validaciones básicas
                if (report.getTitle() != null && !report.getTitle().trim().isEmpty()) {
                    existingReport.setTitle(report.getTitle().trim());
                }
                if (report.getDescription() != null) {
                    existingReport.setDescription(report.getDescription());
                }
                if (report.getLocation() != null && !report.getLocation().trim().isEmpty()) {
                    existingReport.setLocation(report.getLocation().trim());
                }
                if (report.getDate() != null) {
                    existingReport.setDate(report.getDate());
                }
                if (report.getUserId() != null) {
                    existingReport.setUserId(report.getUserId());
                }
                
                Report updatedReport = reportRepository.save(existingReport);
                return new ResponseEntity<>(updatedReport, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * DELETE /api/reports/{id}
     * Elimina un reporte del sistema
     * 
     * @param id ID del reporte a eliminar
     * @return ResponseEntity con código 204 (NO_CONTENT) si se elimina correctamente,
     *         o código 404 (NOT_FOUND) si no existe
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteReport(@PathVariable("id") Long id) {
        try {
            Optional<Report> reportData = reportRepository.findById(id);
            if (reportData.isPresent()) {
                reportRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * DELETE /api/reports
     * Elimina todos los reportes del sistema (usar con precaución)
     * 
     * @return ResponseEntity con código 204 (NO_CONTENT)
     */
    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllReports() {
        try {
            reportRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
