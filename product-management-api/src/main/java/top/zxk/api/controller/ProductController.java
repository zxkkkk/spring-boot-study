package top.zxk.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.zxk.api.entity.Product;
import top.zxk.api.service.ProuctService;
import top.zxk.api.util.OssUtil;

import java.util.List;


@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {
    private final ProuctService prouctService;
    private final OssUtil ossUtil;

    // 查询所有商品
    @GetMapping
    public List<Product> getAllProducts() {
        return prouctService.list();
    }

    // 查询单个商品
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return prouctService.getById(id);
    }

    // 创建商品
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Boolean createProduct(@RequestBody Product product) {
        return prouctService.save(product);
    }

    // 更新商品
    @PutMapping("/{id}")
    public Boolean updateProduct(@PathVariable Long id, @RequestBody Product product) {
        product.setId(id);
        return prouctService.updateById(product);
    }

    // 删除商品
    @DeleteMapping("/{id}")
    public Boolean deleteProduct(@PathVariable Long id) {
        return prouctService.removeById(id);
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        System.out.println(file.getOriginalFilename());
        return ossUtil.uploadFile(file);
    }
}
