package piiv.senac.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import piiv.senac.dao.ProdutoRepository;
import piiv.senac.entity.ItensCompra;
import piiv.senac.entity.table_Cliente;
import piiv.senac.entity.table_Compra;
import piiv.senac.entity.table_Produtos;

@Controller
public class Carrinho_Controller {
	private static String caminhoImagens = "C:\\imagens\\";


	private List<ItensCompra> ITENS_COMPRAS = new ArrayList<ItensCompra>();
	private table_Compra compra = new table_Compra();
	
	private table_Cliente cliente;

	
private ProdutoRepository produtoRepository = new ProdutoRepository();



private void calcularTotal() {

	compra.setValorTotal(0.);
	for(ItensCompra it: ITENS_COMPRAS) {
		compra.setValorTotal(compra.getValorTotal() + it.getValorTotal());
		
	}
}

	@GetMapping("/carrinho")
	public ModelAndView showView() {
		ModelAndView mv = new ModelAndView("geral/carrinho");	
		calcularTotal();	

		mv.addObject("compra", compra);
		mv.addObject("listaItens", ITENS_COMPRAS);
		return mv;
	}
	
	
	@GetMapping("/finalizarCompra")
	public ModelAndView finalizarCompra() {
		ModelAndView mv = new ModelAndView("geral/finalizarCompra");	
		calcularTotal();	
		mv.addObject("compra", compra);
		mv.addObject("listaItens", ITENS_COMPRAS);
		return mv;
	}
	
	@GetMapping("/alterarQuantidade/{id_produto}/{acao}")	
		public String alterarQuantidade(@PathVariable Integer id_produto, @PathVariable Integer acao){
	
		for(ItensCompra it : ITENS_COMPRAS) {
			if(it.getTable_Produtos().getId_produto().equals(id_produto)) {
				if(acao.equals(1)) {
					it.setQuantidade(it.getQuantidade()+1);
					it.setValorTotal(0.); 
					it.setValorTotal(it.getValorTotal() + (it.getQuantidade() * it.getValorUnitario()));
				}else if(acao==0) {
					it.setQuantidade(it.getQuantidade() -1 );
					it.setValorTotal(0.);
					it.setValorTotal(it.getValorTotal() + (it.getQuantidade() * it.getValorUnitario()));
					
				}
				break;
			}
		
		}
		
		return "redirect:/carrinho";
	}
	
	@GetMapping("/removerProduto/{id_produto}")
	public String removerProduto(@PathVariable Integer id_produto){

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
	
		
	int controle = 0;
	for(ItensCompra it: ITENS_COMPRAS) {
		if(it.getTable_Produtos().getId_produto().equals(prod.getId_produto())) {
		it.setQuantidade(it.getQuantidade() + 1);
		it.setValorTotal(0.);
		it.setValorTotal(it.getValorTotal() + (it.getQuantidade() * it.getValorUnitario()));
		controle = 1;
		break;
		
	}
}
	
	if(controle == 0) {
		ItensCompra item = new ItensCompra();
		item.setTable_Produtos(prod);
		item.setValorUnitario(prod.getPreco_venda());
		item.setQuantidade(item.getQuantidade() +1 );
		item.setValorTotal(item.getValorTotal() + (item.getQuantidade() * item.getValorUnitario()));
		
		ITENS_COMPRAS.add(item);
	
		
	}
	return "redirect:/carrinho";
}

	@GetMapping("/carrinho/mostrarImg/{imagem}")
	@ResponseBody
	public byte[] retornarImagem(@PathVariable("imagem") String imagem) throws IOException {
		File imagemArquivo = new File(caminhoImagens + imagem);
		if (imagem != null || imagem.trim().length() > 0) {
			return Files.readAllBytes(imagemArquivo.toPath());
		}
		return null;
	}
	

}
