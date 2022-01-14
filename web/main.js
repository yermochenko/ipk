let content = null;

window.onload = async function() {
	content = document.getElementById('content');
	listProductHandler();
};

const clearNode = function(node) {
	while(node.firstChild) {
		node.removeChild(node.firstChild);
	}
};

// VIEWS

const generateProductsTable = function(products) {
	let h1 = document.createElement('h1');
	h1.appendChild(document.createTextNode('Продукты'));
	content.appendChild(h1);
	let table = document.createElement('table');
	let tableRow = document.createElement('tr');
	let tableCell = document.createElement('th');
	tableCell.appendChild(document.createTextNode('Название'));
	tableRow.appendChild(tableCell);
	tableCell = document.createElement('th');
	tableCell.appendChild(document.createTextNode('Цена'));
	tableRow.appendChild(tableCell);
	tableCell = document.createElement('th');
	tableRow.appendChild(tableCell);
	table.appendChild(tableRow);
	products.forEach(function(product) {
		tableRow = document.createElement('tr');
		tableCell = document.createElement('td');
		tableCell.appendChild(document.createTextNode(product.name));
		tableRow.appendChild(tableCell);
		tableCell = document.createElement('td');
		tableCell.appendChild(document.createTextNode(product.price / 100));
		tableRow.appendChild(tableCell);
		tableCell = document.createElement('td');

		let editForm = document.createElement('form');
		editForm.style.float = 'left';
		editForm.style.marginRight = '10px';
		let idInputEditForm = document.createElement('input');
		idInputEditForm.type = 'hidden';
		idInputEditForm.name = 'id';
		idInputEditForm.value = product.id;
		editForm.appendChild(idInputEditForm);
		let editButton = document.createElement('button');
		editButton.type = 'submit';
		editButton.appendChild(document.createTextNode('Редактировать'));
		editForm.appendChild(editButton);
		editForm.onsubmit = editButtonClickHandler;
		tableCell.appendChild(editForm);

		let deleteForm = document.createElement('form');
		deleteForm.style.float = 'left';
		deleteForm.style.marginRight = '10px';
		let idInputDeleteForm = document.createElement('input');
		idInputDeleteForm.type = 'hidden';
		idInputDeleteForm.name = 'id';
		idInputDeleteForm.value = product.id;
		deleteForm.appendChild(idInputDeleteForm);
		let deleteButton = document.createElement('button');
		deleteButton.type = 'submit';
		deleteButton.appendChild(document.createTextNode('Удалить'));
		deleteForm.appendChild(deleteButton);
		deleteForm.onsubmit = deleteButtonClickHandler;

		tableCell.appendChild(deleteForm);
		tableRow.appendChild(tableCell);
		table.appendChild(tableRow);
	});
	content.appendChild(table);
	let p = document.createElement('p');
	p.appendChild(document.createTextNode(`Итого продуктов: ${products.length}`));
	content.appendChild(p);
	let addButton = document.createElement('button');
	addButton.appendChild(document.createTextNode('Добавить'));
	addButton.onclick = addButtonClickHandler;
	content.appendChild(addButton);
};

const generateProductForm = function(product) {
	let h1 = document.createElement('h1');
	if(product) {
		h1.appendChild(document.createTextNode('Редактирование продукта “' + product.name + '”'));
	} else {
		h1.appendChild(document.createTextNode('Добавление нового продукта'));
	}
	content.appendChild(h1);
	let form = document.createElement('form');
	if(product) {
		let idInput = document.createElement('input');
		idInput.type = 'hidden';
		idInput.name = 'id';
		idInput.value = product.id;
		form.appendChild(idInput);
	}
	let nameLabel = document.createElement('label');
	nameLabel.style.display = 'block';
	nameLabel.style.marginBottom = '20px';
	nameLabel.appendChild(document.createTextNode('Название:'));
	let nameInput = document.createElement('input');
	nameInput.type = 'text';
	nameInput.name = 'name';
	if(product) {
		nameInput.value = product.name;
	}
	nameInput.style.display = 'block';
	nameLabel.appendChild(nameInput);
	form.appendChild(nameLabel);
	let priceLabel = document.createElement('label');
	priceLabel.style.display = 'block';
	priceLabel.style.marginBottom = '20px';
	priceLabel.appendChild(document.createTextNode('Цена:'));
	let priceInput = document.createElement('input');
	priceInput.type = 'text';
	priceInput.name = 'price';
	if(product) {
		priceInput.value = product.price / 100;
	}
	priceInput.style.display = 'block';
	priceLabel.appendChild(priceInput);
	form.appendChild(priceLabel);
	let saveButton = document.createElement('button');
	saveButton.type = 'submit';
	saveButton.style.marginRight = '20px';
	saveButton.appendChild(document.createTextNode('Сохранить'));
	form.appendChild(saveButton);
	let cancelButton = document.createElement('button');
	cancelButton.type = 'button';
	cancelButton.style.marginRight = '20px';
	cancelButton.appendChild(document.createTextNode('Отменить'));
	cancelButton.onclick = listProductHandler;
	form.appendChild(cancelButton);
	form.onsubmit = productFormSubmitHandler;
	content.appendChild(form);
};

// CONTROLLERS

const listProductHandler = function() {
	clearNode(content);
	readProducts().then(function(products) {
		generateProductsTable(products);
	});
};

const addButtonClickHandler = function() {
	clearNode(content);
	generateProductForm();
};

const editButtonClickHandler = function() {
	clearNode(content);
	readProduct(this.id.value).then(function(product) {
		generateProductForm(product);
	});
	return false;
};

const productFormSubmitHandler = function() {
	let product = {
		"name": this.name.value,
		"price": this.price.value * 100
	};
	if(this.id) {
		product.id = this.id.value << 0;
	}
	saveProduct(product).then(listProductHandler);
	return false;
};

const deleteButtonClickHandler = function() {
	clearNode(content);
	deleteProduct(this.id.value).then(listProductHandler);
	return false;
};

// MODELS

const readProducts = async function() {
	let response = await fetch('product');
	let products = await response.json();
	return products;
};

const readProduct = async function(id) {
	let response = await fetch('product?id=' + id);
	let product = await response.json();
	return product;
}

const saveProduct = async function(product) {
	let options = {
		headers: {"Content-Type": 'application/json;charset=utf8'},
		body: JSON.stringify(product)
	};
	if(product.id) {
		options.method = 'PUT';
	} else {
		options.method = 'POST';
	}
	let response = await fetch('product', options);
	await response.text();
	if(response.status != '201' && response.status != '204') {
		alert('ERROR');
	}
};

const deleteProduct = async function(id) {
	let response = await fetch('product?id=' + id, {method: 'DELETE'});
	await response.text();
	if(response.status != '204') {
		alert('ERROR');
	}
};
