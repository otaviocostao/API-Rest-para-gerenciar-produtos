package dio.cadastro_produtos.controller;

import dio.cadastro_produtos.entity.Pedido;
import dio.cadastro_produtos.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/pedido")
public class PedidoController {

    @Autowired
    PedidoService pedidoService;

    @PostMapping(value = "/save")
    public ResponseEntity<Pedido> salvaProduto(@RequestBody Pedido pedido){
        pedido = pedidoService.pedido(pedido);

        return ResponseEntity.ok().body(pedido);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Pedido> buscaPedido(@PathVariable Long id){
        Pedido pedido = pedidoService.findById(id);

        return ResponseEntity.ok().body(pedido);
    }

    @GetMapping(value = "/busca-todos")
    public ResponseEntity<List<Pedido>> pedidoFind(){
        List<Pedido> pedidos = pedidoService.findAll();
        return ResponseEntity.ok().body(pedidos);
    }
}