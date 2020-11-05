package piiv.senac.controller;
import piiv.senac.dao.ImagemProdutoRepository;
import piiv.senac.dao.PerguntaRespostaRepository;
import piiv.senac.dao.ProdutoRepository;
import piiv.senac.entity.table_Produtos;
import piiv.senac.entity.*;
import piiv.senac.dao.*;
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
public class Clientes_Controller {

	  @GetMapping("/Cadastros/Clientes")
	  public ModelAndView showView() {

	    ModelAndView mv = new ModelAndView("");
	    
	    ClientesRepository clientesRepository = new ClientesRepository();
	    List<table_Cliente> Cliente = clientesRepository.getTable_Cliente();
	   
	    mv.addObject("cadastro", Cliente);
	 
	    return mv;
	  }
	  
	  @GetMapping("/TelaInicial/Clientes/Novo")
	  public ModelAndView exibirCadastro() {

	    table_Cliente cliente = new table_Cliente();


	    ModelAndView mv = new ModelAndView("cadastro-cliente");

	    mv.addObject("cliente", cliente);

	    return mv;
	  }

	  @PostMapping("/TelaInicial/Cadastros/Clientes/novo")
	  public ModelAndView adicionarCliente(
		        @ModelAttribute(value = "cliente") table_Cliente cliente) {
		  
		ClientesRepository clientesRepository = new ClientesRepository();
		clientesRepository.aaaClientes(cliente);

	    ModelAndView mv = new ModelAndView("redirect:/login-sucesso");

	    return mv;
	  }
}
