package com.demo.springboot.rest;

import com.demo.springboot.domain.dto.DocumentDto;
import com.demo.springboot.service.DocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(value="/api")
public class DocumentApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentApiController.class);


//  Dane z parametrow

    @RequestMapping(value = "/document/test",method = RequestMethod.GET)
    public ResponseEntity<Void> testDocument(@RequestParam(value = "firstName",required = false) String firstName,
                                             @RequestParam(value = "age", required = false) Integer age)  {
        LOGGER.info("### Dziala metoda testDocument!");
        LOGGER.info("### Potrafie odczytac parametr firstName(imie): {} oraz age(wiek): {}",firstName,age);



        return new ResponseEntity<>(HttpStatus.OK);
    }



//  JSON

//    @RequestMapping(value = "/document/test",method = RequestMethod.POST)
//    public ResponseEntity<Void> testDocument(@RequestBody DocumentDto documentDto)  {
//        LOGGER.info("### Dziala metoda testDocument!");
//        LOGGER.info("### Potrafie odczytac JSON'a!");
//        LOGGER.info("### Nazwa dokumentu: {}, rozmiar: {} i rozszerzenie: {}",
//                documentDto.getName(),
//                documentDto.getSize(),
//                documentDto.getExtension()
//                );
//
//
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }

//    Wstrzykiwanie

//    @Autowired
//    private DocumentService documentService;

//    @RequestMapping(value = "/document/test",method = RequestMethod.GET)
//    public ResponseEntity<String> testDocument()  {
//        LOGGER.info("### Dziala metoda testDocument!");
//        LOGGER.info("### Sprawdzamy czy dziala wstrzykiwanie zaleznosci!");
//
//        final String infoAboutDocument = documentService.getInfoAboutDocument();
//
//        LOGGER.info("### Komponent odpowiada: {}",infoAboutDocument);
//
//
//        return new ResponseEntity<>(infoAboutDocument,HttpStatus.OK);
//    }
}
