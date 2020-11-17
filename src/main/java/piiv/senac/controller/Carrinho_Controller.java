package piiv.senac.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import piiv.senac.dao.ProdutoRepository;
import piiv.senac.entity.ItensCompra;
import piiv.senac.entity.table_Produtos;

@Controller
public class Carrinho_Controller {
	
	private List<ItensCompra> ITENS_COMPRAS = new ArrayList<ItensCompra>();
	
private ProdutoRepository produtoRepository = new ProdutoRepository();
	private table_Produtos table_Produtos;
	
	@GetMapping("/carrinho")
	public ModelAndView showView() {
		
		ModelAndView mv = new ModelAndView("carrinho");
		ItensCompra item = new ItensCompra();
		mv.addObject("listaItens", ITENS_COMPRAS);
		return mv;
	}
	
	@GetMapping("/alterarQuantidade/{id_produto}/{acao}")	
		public String alterarQuantidade(@PathVariable Integer id_produto, @PathVariable Integer acao){
	
		for(ItensCompra it : ITENS_COMPRAS) {
			if(it.getTable_Produtos().getId_produto().equals(id_produto)) {
				if(acao.equals(1)) {
					it.setQuantidade(it.getQuantidade()+1);
				}else if(acao==0) {
					it.setQuantidade(it.getQuantidade() -1 );
					
				}
				break;
			}
		
		}
		
		return "redirect:/carrinho";
	}
	
	@GetMapping("/removerProduto/{id_produto}")
	public String removerProduto(@PathVariable Integer id_produto){
	ModelAndView mv = new ModelAndView("/carrinho");

	for(ItensCompra it : ITENS_COMPRAS) {
		if(it.getTable_Produtos().getId_produto().equals(id_produto)) {
			
			ITENS_COMPRAS.remove(it);			
			break;
		}
	
	}
	
	return "redirect:/carrinho";
}
	
	

	
	@GetMapping("/adicionarCarrinho/{id_produto}")
	public String adicionarCarrinho(@PathVariable int id_produto) {
		
	table_Produtos prod = produtoRepository.getProdutos(id_produto);
		//Optional<table_Produtos> prod = Optional.ofNullable(produtoRepository.getProdutos(id_produto));
		if (prod == null) {
			System.out.println("Produto não encontrado.");
		} else {
			 System.out.println("Produto encontrado.");
		}
		
		ItensCompra item = new ItensCompra();
		
		item.setTable_Produtos(prod);
		item.setValorUnitario(prod.getPreco_venda());
		item.setQuantidade(item.getQuantidade());
		item.setValorTotal(item.getQuantidade()*item.getValorUnitario());
		List<ItensCompra> itens = ITENS_COMPRAS.stream()
				.filter(it -> it.getTable_Produtos().getId_produto()==id_produto).collect(Collectors.toList());
		if(itens.isEmpty()){
			ITENS_COMPRAS.add(item);
		}else {
			ITENS_COMPRAS.remove(itens.get(0));
			item.setQuantidade(item.getQuantidade()+1);
			ITENS_COMPRAS.add(item);
		}
		
		return "redirect:/carrinho";
	}
	
}
