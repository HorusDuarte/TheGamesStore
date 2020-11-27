package piiv.senac.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import piiv.senac.dao.ImagemProdutoRepository;
import piiv.senac.dao.PerguntaRespostaRepository;
import piiv.senac.dao.ProdutoRepository;
import piiv.senac.entity.ImagemProd;
import piiv.senac.entity.table_Pergunta_Resposta;
import piiv.senac.entity.table_Produtos;

@Controller
public class Home_Controller {

	private static String caminhoImagens = "C:\\imagens\\";

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
	
	
	@GetMapping("/")
	public ModelAndView showHome() {

		ModelAndView mv = new ModelAndView("geral/home");

		ProdutoRepository produtoRepository = new ProdutoRepository();

		ImagemProdutoRepository imagemProdutoRepository = new ImagemProdutoRepository();
		List<ImagemProd> listaImagens = imagemProdutoRepository.getImagem();

		mv.addObject("listaImagens", listaImagens);
		mv.addObject("listaProdutos", produtoRepository.getTable_Produtos());

		return mv;

	}
	

	@GetMapping("/viewImagem/{imagem}")
	@ResponseBody
	public byte[] retornarImagem(@PathVariable("imagem") String imagem) throws IOException {
		File imagemArquivo = new File(caminhoImagens + imagem);
		if (imagem != null || imagem.trim().length() > 0) {
			return Files.readAllBytes(imagemArquivo.toPath());
		}
		return null;
	}

}