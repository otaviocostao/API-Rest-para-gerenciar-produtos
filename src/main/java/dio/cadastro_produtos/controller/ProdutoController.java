package dio.cadastro_produtos.controller;

import dio.cadastro_produtos.entity.Produto;
import dio.cadastro_produtos.exception.ProductPriceException;
import dio.cadastro_produtos.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;


@RestController
@RequestMapping(value = "/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping(value = "/save")
    public ResponseEntity<Produto> produtoSave(@RequestBody Produto produto) throws ProductPriceException {
        produto = produtoService.saveProduto(produto);
        return ResponseEntity.ok().body(produto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Produto> produtoFind(@PathVariable Long id){
        Produto produto = produtoService.findById(id);
        return ResponseEntity.ok().body(produto);
    }

    @GetMapping(value = "/busca-todos")
    public ResponseEntity<List<Produto>> produtoFind(){
        List<Produto> produtos = produtoService.findAll();
        return ResponseEntity.ok().body(produtos);
    }
}
