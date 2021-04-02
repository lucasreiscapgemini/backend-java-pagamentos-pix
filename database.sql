CREATE TABLE `tb_pagamento` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `destinatario` varchar(100) NOT NULL,
  `instituicao_bancaria` int(11) NOT NULL,
  `chave_pix` varchar(255) NOT NULL,
  `valor` decimal(10,2) NOT NULL,
  `data_pagamento` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `descricao` text DEFAULT NULL,
  `cpf` varchar(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENG

insert into pagamentos.tb_pagamento (destinatario, cpf,instituicao_bancaria,chave_pix,valor,data_pagamento,descricao) 
values('fulano de tal','12245678910',0,"12245678910",178.98,now(), 'Aluguel');

insert into pagamentos.tb_pagamento (destinatario, cpf,instituicao_bancaria,chave_pix,valor,data_pagamento,descricao) 
values('Jose','12245678910',0,"98978698012",178.98,now(), 'Passagem onibus');