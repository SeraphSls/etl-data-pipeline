package br.com.excel.maniac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ResourceController {

    private static final String TOPIC = "raw-data-import";

    @Autowired
    private KafkaTemplate<Object, Object> template;


    @PostMapping("/upload")
    public ResponseEntity<String> uploadExcel(@RequestParam("file") MultipartFile file) {
        //early return, a lógica de tratamento de erros deve ocorrer de forma previa
        if (file.isEmpty()){
            throw new RuntimeException("FIle cannot be null");
        }
        //TODO: disparar o processo do arquivo aqui, extraindo dados
        this.template.send(TOPIC, "sucess");

        return ResponseEntity.ok("Arquivo recebido com sucesso!");
    }

    @GetMapping
    public ResponseEntity<String> health  (){
        this.template.send(TOPIC, "started!");

        return  ResponseEntity.ok("application is up!") ;
    }
}
