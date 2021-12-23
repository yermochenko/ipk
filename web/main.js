let content = null;

const clearNode = function(node) {
	while(node.firstChild) {
		node.removeChild(node.firstChild);
	}
};

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
	table.appendChild(tableRow);
	products.forEach(function(product) {
		tableRow = document.createElement('tr');
		tableCell = document.createElement('td');
		tableCell.appendChild(document.createTextNode(product.name));
		tableRow.appendChild(tableCell);
		tableCell = document.createElement('td');
		tableCell.appendChild(document.createTextNode(product.price / 100));
		tableRow.appendChild(tableCell);
		table.appendChild(tableRow);
	});
	content.appendChild(table);
	let p = document.createElement('p');
	p.appendChild(document.createTextNode(`Итого продуктов: ${products.length}`));
	content.appendChild(p);
};

window.onload = async function() {
	content = document.getElementById('content');
	clearNode(content);
	//let response = await fetch('http://localhost/ipk/product');
	let response = await fetch('product');
	let products = await response.json();
	generateProductsTable(products);
};
