package dio.cadastro_produtos.service;

import dio.cadastro_produtos.entity.Produto;
import dio.cadastro_produtos.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto saveProduto(Produto produto){
        return produtoRepository.save(produto);
    }

    public Produto findById(Long id){
        return produtoRepository.findById(id).orElse(null);
    }

    public List<Produto> findAll(){
        return produtoRepository.findAll();
    }

}