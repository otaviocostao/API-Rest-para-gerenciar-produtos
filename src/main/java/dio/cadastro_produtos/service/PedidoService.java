package dio.cadastro_produtos.service;

import dio.cadastro_produtos.entity.Pedido;
import dio.cadastro_produtos.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido pedido(Pedido pedido){
        return pedidoRepository.save(pedido);
    }

    public Pedido findById(Long id){
        return pedidoRepository.findById(id).orElse(null);
    }

    public List<Pedido> findAll(){
        return pedidoRepository.findAll();
    }
}
