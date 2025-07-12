package br.com.excel.maniac.controller;


import br.com.excel.maniac.dto.Message;
import br.com.excel.maniac.services.ExcelProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ResourceController {

    @Autowired
    ExcelProducer excelProducer;

    @PostMapping("/test")
    public ResponseEntity<String> uploadExcel(@RequestBody Message file) {

        excelProducer.enviarMensagem(file);
        return ResponseEntity.ok("disparado para a fila com sucesso!");
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadExcel(@RequestParam("file") MultipartFile file) {
        //early return, a lógica de tratamento de erros deve ocorrer de forma previa
        if (file.isEmpty()) {
            throw new RuntimeException("FIle cannot be null");
        }
        //TODO: disparar o processo do arquivo aqui, extraindo dados


        return ResponseEntity.ok("Arquivo recebido com sucesso!");
    }


}
