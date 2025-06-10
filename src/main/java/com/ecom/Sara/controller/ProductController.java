package com.ecom.Sara.controller;

import com.ecom.Sara.model.Product;
import com.ecom.Sara.service.ProdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {
    @Autowired
    ProdService service;
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getallprods(){
        return new ResponseEntity<>(service.getallpods(), HttpStatus.OK);
    }
    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getprod(@PathVariable int id){
        Product prod=service.getprod(id);
        if (prod==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(prod, HttpStatus.OK);
    }
    @PostMapping("/product")
    public ResponseEntity<?> addProd(@RequestPart Product product, @RequestPart MultipartFile imageFile){
        try {
            return new ResponseEntity<>(service.addProd(product,imageFile), HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/product/{id}/image")
    public ResponseEntity<byte[]> imgbyid(@PathVariable int id){
        Product p=service.getprod(id);
        byte[] imgf=p.getImageData();
        return ResponseEntity.ok().contentType(MediaType.valueOf(p.getImageType())).body(imgf);
    }
    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProd(@PathVariable int id,@RequestPart Product product, @RequestPart MultipartFile imageFile){
        Product pr= null;
        try {
            pr = service.updateProd(id,product,imageFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (pr==null){
            return new ResponseEntity<>("Failed to Update",HttpStatus.BAD_REQUEST);

        }
        return new ResponseEntity<>("Updated",HttpStatus.OK);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProd(@PathVariable int id){
        service.deleteProd(id);
        return new ResponseEntity<>("deleted",HttpStatus.OK);
    }
    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProd(@RequestParam String keyword){
        return new ResponseEntity<>(service.searchProd(keyword), HttpStatus.OK);
    }




}
