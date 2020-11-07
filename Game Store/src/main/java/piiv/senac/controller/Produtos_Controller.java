package piiv.senac.controller;

import piiv.senac.dao.ImagemProdutoRepository;
import piiv.senac.dao.PerguntaRespostaRepository;
import piiv.senac.dao.ProdutoRepository;
import piiv.senac.entity.table_Produtos;
import piiv.senac.entity.ImagemProd;
import piiv.senac.entity.table_Pergunta_Resposta;
import java.util.logging.Logger;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class Produtos_Controller {

  @GetMapping("/Produtos")
  public ModelAndView showView() {

    ModelAndView mv = new ModelAndView("backoffice-produtos");
    ProdutoRepository produtoRepository = new ProdutoRepository();
    List<table_Produtos> produtos = produtoRepository.getTable_Produtos();
    mv.addObject("games", produtos);
    return mv;
  }

  @GetMapping("/Produtos/Novo")
  public ModelAndView exibirCadastro() {

    table_Produtos p = new table_Produtos();


    ModelAndView mv = new ModelAndView("backofficeProdutos");

    mv.addObject("produto", p);

    return mv;
  }

  @GetMapping("/Produtos/{id_produto}")
  public ModelAndView exibir_alterarProduto(@PathVariable("id_produto") int id_produto) {

    ModelAndView mv = new ModelAndView("backofficeProdutosAlterar");
    ProdutoRepository produtoRepository = new ProdutoRepository();
    table_Produtos p = produtoRepository.getProdutos(id_produto);

    ImagemProdutoRepository imagemProdutoRepository = new ImagemProdutoRepository();
    List<ImagemProd> listaImagens = imagemProdutoRepository.getImagensProduto(id_produto);

    PerguntaRespostaRepository perguntaRespostaRepository = new PerguntaRespostaRepository();
    List<table_Pergunta_Resposta> listaPerguntasRespostas = perguntaRespostaRepository.getPergunta_Resposta(id_produto);

    mv.addObject("produto", p);
    mv.addObject("listaPerguntasRespostas", listaPerguntasRespostas);
    mv.addObject("listaImagens", listaImagens);

    return mv;
  }

  @GetMapping("/Produtos/Visualizar/{id_produto}")
  public ModelAndView verProduto(@PathVariable("id_produto") int id_produto) {

    ModelAndView mv = new ModelAndView("produto");
    ProdutoRepository produtoRepository = new ProdutoRepository();
    table_Produtos p = produtoRepository.getProdutos(id_produto);


    ImagemProdutoRepository imagemProdutoRepository = new ImagemProdutoRepository();
    List<ImagemProd> listaImagens = imagemProdutoRepository.getImagensProduto(id_produto);

    PerguntaRespostaRepository perguntaRespostaRepository = new PerguntaRespostaRepository();
    List<table_Pergunta_Resposta> listaPerguntasRespostas = perguntaRespostaRepository.getPergunta_Resposta(id_produto);

    mv.addObject("produto", p);
    
    mv.addObject("listaPerguntasRespostas", listaPerguntasRespostas);
    mv.addObject("listaImagens", listaImagens);

    return mv;
  }
  	
  @PutMapping("/Produtos/{id_produto}")
  public ModelAndView alterarProduto(
          @PathVariable("id_produto") int id_produto,
          @ModelAttribute(value = "produto") table_Produtos p,
          @RequestParam(value = "imagem", required = false) String[] imagens,
          @RequestParam(value = "pergunta", required = false) String[] perguntas,
          @RequestParam(value = "resposta", required = false) String[] respostas) {

    ProdutoRepository produtoRepository = new ProdutoRepository();
    produtoRepository.alterarProduto(p);

    ImagemProdutoRepository imagemProdutoRepository = new ImagemProdutoRepository();
    imagemProdutoRepository.deletarImagensProduto(p.getId_produto());

    PerguntaRespostaRepository perguntaRespostaRepository = new PerguntaRespostaRepository();
    perguntaRespostaRepository.deletarPerguntaResposta(p.getId_produto());

    if (imagens != null) imagemProdutoRepository.salvarImagensProduto(p.getId_produto(), imagens);
    if (perguntas !=  null && respostas != null) perguntaRespostaRepository.salvarPerguntasRespostas(p.getId_produto(), perguntas, respostas);

    ModelAndView mv = new ModelAndView("redirect:/Produtos");

    return mv;
  }

  @PostMapping("/Produtos/Novo")
  public ModelAndView adicionarProduto(
          @ModelAttribute(value = "produto") table_Produtos p,
          @RequestParam(value = "imagem", required = false) String[] imagens,
          @RequestParam(value = "pergunta", required = false) String[] perguntas,
          @RequestParam(value = "resposta", required = false) String[] respostas) {

    ProdutoRepository produtoRepository = new ProdutoRepository();
    produtoRepository.salvarProduto(p);

    int id_produto = produtoRepository.getUltimoProduto();

    ImagemProdutoRepository imagemProdutoRepository = new ImagemProdutoRepository();
    PerguntaRespostaRepository perguntasRespostasProdutoRepository = new PerguntaRespostaRepository();
    
    if (imagens != null) imagemProdutoRepository.salvarImagensProduto(id_produto, imagens);
    if (perguntas !=  null && respostas != null) perguntasRespostasProdutoRepository.salvarPerguntasRespostas(id_produto, perguntas, respostas);

    ModelAndView mv = new ModelAndView("redirect:/Produtos");

    return mv;
  }

  @DeleteMapping("/Produtos/{id_produto}")
  public ModelAndView removeProduto(@PathVariable("id_produto") int id_produto) {

    ProdutoRepository produtoRepository = new ProdutoRepository();
    produtoRepository.inativarProduto(id_produto);

    ModelAndView mv = new ModelAndView("redirect:/Produtos");

    return mv;

  }

}
