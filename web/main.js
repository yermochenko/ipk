let content = null;

const clearNode = function(node) {
	while(node.firstChild) {
		node.removeChild(node.firstChild);
	}
};

const contentLoaded = function(event) {
	console.log(event.target.status);
	let products = JSON.parse(event.target.response);
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
};

window.onload = function() {
	content = document.getElementById('content');
	clearNode(content);
	let myRequest = new XMLHttpRequest();
	//myRequest.open('GET', 'http://localhost/ipk/product');
	myRequest.open('GET', 'product');
	myRequest.onload = contentLoaded;
	myRequest.send();
};
