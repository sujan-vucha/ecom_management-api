package com.ecom.Sara.service;


import com.ecom.Sara.model.Product;
import com.ecom.Sara.repo.ProdRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProdService {
    @Autowired
    ProdRepo repo;
    public List<Product> getallpods() {
        return repo.findAll();

    }

    public Product getprod(int id) {
        return repo.findById(id).orElse(null);
    }

    public Product addProd(Product product, MultipartFile imagefile) throws IOException {
        product.setImageName(imagefile.getOriginalFilename());
        product.setImageType(imagefile.getContentType());
        product.setImageData(imagefile.getBytes());
        return repo.save(product);
    }

    public Product updateProd(int id, Product product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());
        return repo.save(product);

    }

    public void deleteProd(int id) {
        repo.deleteById(id);

    }

    public List<Product> searchProd(String keyword) {
        return repo.searchProd(keyword);
    }
}
