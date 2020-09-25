/*delete from cliente where id > 0; */

INSERT INTO cliente (nome, sobrenome, cpf) VALUES ('Pablo', 'Hernandes', '09388373464');

SELECT * FROM cliente;

delete from cliente where id = 2;

select * from produto;

INSERT INTO produto (descricao) VALUES ("Tinta");

select * from pedido;

delete from pedido where id=2;

insert into pedido (data, id_cliente) values ('2020-01-31 10:10:10', 9);

DELETE FROM item_do_pedido WHERE id_item > 0;

SELECT * FROM item_do_pedido;

INSERT INTO pedido (data, id_cliente) VALUES ('2020-01-01 10:10:10', 2);

SELECT * FROM pedido;

INSERT INTO item_do_pedido (id_pedido, id_produto, qtdade) VALUES (3, 4, 10);

/* Select para listar todos os Pedidos */
SELECT pedido.id AS "ID Pedido", pedido.data AS "Data Pedido", pedido.id_cliente AS "ID Cliente do Pedido",
	   i.id_item AS "ID Item", i.id_pedido AS "ID Pedido do Item", i.id_produto AS "ID Produto do Item", i.qtdade AS "ID Quantidade do Item",
       c.id AS "Cliente ID", c.cpf  AS "Cliente CPF", c.nome AS "Cliente Nome", c.sobrenome  AS "Cliente Sobrenome"
FROM pedido as pedido
INNER JOIN item_do_pedido as i ON pedido.id = i.id_pedido
INNER JOIN cliente as c ON pedido.id_cliente = c.id;

/* Select para listar todos os por ID CLIENTE */
SELECT pedido.id AS "ID Pedido", pedido.data AS "Data Pedido", pedido.id_cliente AS "ID Cliente do Pedido",
	   i.id_item AS "ID Item", i.id_pedido AS "ID Pedido do Item", i.id_produto AS "ID Produto do Item", i.qtdade AS "ID Quantidade do Item",
       c.id AS "Cliente ID", c.cpf  AS "Cliente CPF", c.nome AS "Cliente Nome", c.sobrenome  AS "Cliente Sobrenome"
FROM pedido as pedido
INNER JOIN item_do_pedido as i ON pedido.id = i.id_pedido
INNER JOIN cliente as c ON pedido.id_cliente = c.id
WHERE pedido.id_cliente = 2;

SELECT i.id_item, i.id_pedido, i.id_produto, prod.descricao, i.qtdade FROM item_do_pedido AS i
INNER JOIN produto AS prod ON i.id_produto = prod.id
INNER JOIN pedido as p ON p.id = i.id_pedido;

SELECT i.id_item, i.id_pedido, i.id_produto, prod.descricao, i.qtdade FROM item_do_pedido AS i
INNER JOIN produto AS prod ON i.id_produto = prod.id
INNER JOIN pedido as p ON p.id = i.id_pedido
WHERE p.id = 3;

INSERT INTO item_do_pedido (id_pedido, id_produto, qtdade) VALUES (pedido.last_insert_id(), 1, 10);

INSERT INTO pedido (data, id_cliente) VALUES ('2020-08-23 18:34:08', 11)