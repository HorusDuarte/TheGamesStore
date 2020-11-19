package piiv.senac.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import piiv.senac.dao.ImagemProdutoRepository;
import piiv.senac.dao.PerguntaRespostaRepository;
import piiv.senac.dao.ProdutoRepository;
import piiv.senac.entity.ImagemProd;
import piiv.senac.entity.table_Pergunta_Resposta;
import piiv.senac.entity.table_Produtos;

@Controller
public class Home_Controller {
	
	
	@GetMapping("/home")
    public ModelAndView home() {
		
        ModelAndView mv = new ModelAndView("geral/home");  
        ProdutoRepository produtoRepository = new ProdutoRepository();

        ImagemProdutoRepository imagemProdutoRepository = new ImagemProdutoRepository();
        List<ImagemProd> listaImagens = imagemProdutoRepository.getImagem();
  

        mv.addObject("listaImagens", listaImagens);
        mv.addObject("listaProdutos", produtoRepository.getTable_Produtos());
        
        return mv;
	}
    
}