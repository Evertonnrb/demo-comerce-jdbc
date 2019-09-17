/*tables*/
create table categorias(
		id serial primary key,
		nome varchar(100) not null
);
create table produtos(
	id serial primary key,
	nome varchar(80),
	valor decimal(8,2) check (valor>0),
	id_categoria int,
	foreign key (id_categoria) references categorias(id)
);
create table departamentos(
	id serial primary key,
	nome varchar(100) not null
);
create table vendedores(
	id serial primary key,
	nome varchar(100) not null,
	email varchar(100) not null,
	data_nascimento date not null,
	salario decimal(8,2) not null check (salario > 0),
	comissao decimal(8,2) not null check (comissao >0 and comissao < 0.3),
	id_departamento int,
	foreign key (id_departamento) references categorias(id)
);
/*inserts*/
insert into categorias(nome) values('Eletrônicos'),('Eletrodomesticos'),
	('Moveis'),('Utensílios'),('Vestuário'),('Infantil'),('Brinquedos');

insert into produtos(nome,valor,id_categoria) values ('IPad',2499.0,1);
insert into produtos(nome,valor,id_categoria) values ('Galaxy 9',5499.0,1),
	('Motorola One',1500.0,1), ('Aprirador de pó',400.0,2),('Radio MP3',259.9,2),
	('Batedeira',199,2),('Sofá Retratil',3500.0,3),('Guarda Roupas',2399.0,3),
	('Mesa Sofia Blindex',2899.0,3),('Painel suporte de TV',999.9,3),('Tv 50" 4K',5000.0,1),
	('Jogo de jantar Tramontina',450.9,4),('Panela de pressão 5LT',190.9,4),
	('Calça Feminina PitBull',400.0,5),('Sapatênis masculino',200.0,5),
	('Jogo Imobiliário Infantil',99.9,6),('Camisa Batman',50.0,6),('Vestido Lady Bug',400.0,6),
	('Boneco BuzLightYear',199.9,7),('Lady Bug tam 1.50',1200.0,7),('Max Still',300.0,7);

insert into departamentos(nome)values ('Eletronicos'),('Vestuarios'),('Infantil');
insert into vendedores(nome,email,data_nascimento,salario,comissao,id_departamento) values
	('Eliza S. Silva','elizzassilva@gmail.com','1999-01-01',990.0,0.1,1),
	('Ricardo Eleno','ricardo22@hotmail.com','1988-12-19',1200.0,0.2,2),
	('Rogerio F. Cenni','rgf@hotmail.com','1986-10-10',999.9,0.1,2),
	('Sabrina F.S. Soarez','sabrinardf@gmail.com','2000-12-12',1200.0,0.2,3),
	('Fabiola Ferraz','fer321@gmail.com','1995-12-18',999.9,0.1,3);

-- drop table vendedores;
select * from departamentos;
select * from vendedores;
select * from categorias;
select * from produtos;

select vendedores.*, departamentos.nome as nome_departamento, departamentos.id as id_departamento
                from vendedores inner join departamentos
                on vendedores.id = departamentos.id where vendedores.id = 1);