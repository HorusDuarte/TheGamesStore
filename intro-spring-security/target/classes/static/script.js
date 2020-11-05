function addImage() {
  let url = document.getElementById('endereco_imagem').value;
  let lista = document.getElementById('lista-imagens');
  
  let s = `<li class="list-group-item d-flex justify-content-between align-items-center" style="overflow: auto">`
                +url+
                `<input name="imagem" type="hidden" value="` + url + `"><button type="button" class="btn btn-danger sm" onclick="$(this).closest('li').remove();">Remover</button>
              </li>`;

  lista.insertAdjacentHTML("beforeend", s);
  
  document.getElementById('endereco_imagem').value='';
  
}

function addPR() {
  let pergunta = document.getElementById('pergunta').value;
  let resposta = document.getElementById('resposta').value;
  let lista = document.getElementById('lista-pr');
  
  let s = `<li class="list-group-item d-flex justify-content-between align-items-center" style="padding-left: 0px">
                <div>
                  <p class="font-weight-normal">` + pergunta + `</p>
                  <input name="pergunta" type="hidden" value="` + pergunta + `">
                  <p class="font-weight-light" name="resposta">` + resposta + `</p>
                  <input name="resposta" type="hidden" value="` + resposta + `">
                </div>
                <button type="button" class="btn btn-danger sm" onclick="$(this).closest('li').remove();">Remover</button>
              </li>`;

  lista.insertAdjacentHTML("beforeend", s);
  
  document.getElementById('pergunta').value='';
   document.getElementById('resposta').value='';
}

function visualizarProduto(){
  
}