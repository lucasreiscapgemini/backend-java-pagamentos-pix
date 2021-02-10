# Capgemini - Desafio Backend | Java

Olá! Esse desafio técnico tem como propósito medir suas habilidades, ver como estuda, pensa e se organiza na prática, o  framework é de sua escolha mas a linguagem é java

Após finalizar o desafio, de preferencia faça um fork do projeto depois pull request, nos envie um link para repositório do projeto ou um zip com o código o que se sentir mais confortavel.

Existem diversas maneiras distintas de solucionar o problema que estamos propondo. Vamos listar algumas sub-tasks que podem guiá-lo(a) em relação a essas possibilidades.

## O desafio
Usuários Bradesco realizam diversos pagamentos pix por todo o Brasil, o desafio é fazer uma API em Node Js que cadastre os pagamentos pix e listar os pagamentos pix. Na tela de listagem de pagamento, deve informar a porcentagem que o valor pagamento feito pelo usuario na data dd/mm/yyyy representa  dos valores dos pagamentos totais do mês vigente.
Construir um micro-service que por sua vez terá um endPoint "/pagamentos" que terá devolverá para o usuário final um payload em JSON contentando dados para pagamento.

## Input
Você deve criar um micro-service que conterá um path e um endPoint que retornará dados em formato JSON contendo algumas informações de dados de pagamento PIX.


## Output
O cliente terá que devolver uma lista de dados pagamento PIX contendo alguns atributos que por sua vez são os atributos devolvidos pelo lado do servidor.

### Dados a serem coletados do pagamento pix :

* Nome destinatario
* CPF
* Instituição Bancaria
* Chave pix
* Valor
* Descrição
* Logica de porcentagem dos pagametnos (Ex: aplicar uma regra de porcentagem com base no valor do pagamento pix )

## Alguns pontos cruciais para entrega

* Criação de uma API que seja capaz de receber requesições feitas utilizando json e persistindo esses dados.
* Logica da porcentagem dos pagamentos pix

## Alguns pontos que serão bonús:

* Utilizar o frameowork Jarx-rs
* Organização do código 
* Organização do repositorio remoto
* Fork e Pull request
* CRUD completo de pagamentos pix.
* Testes Unitarios (TDD)
* Código bem comentado 
* Facilidade ao rodar o projeto
* Utilização de padrões de projeto 
* Utilização de DDD

## Dúvidas do processo ou enviar zip do projeto

Enviar e-mail para  lucas.reis@capgemini.com , kamila.castelano@capgemini.com , vinicius.pascucci@capgemini.com ou  levi.ferreira@capgemini.com.


**Happy coding! :-)**
