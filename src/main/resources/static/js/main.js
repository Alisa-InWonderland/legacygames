"use strict";

let price = document.getElementById('price');
let priceValue = parseFloat(price.innerHTML);
let discount = document.getElementById('discount');
let discountValue = parseFloat(discount.innerHTML);
let discountPrice;


const getPrice = () => {
    if (discountValue > 0) {
        price.className = 'new-price';

        discountPrice = ((priceValue - ((discountValue / 100) * priceValue)).toFixed(2)).toString();

        let node = document.createElement("TD");
        let textnode = document.createTextNode(discountPrice + "€");
        node.appendChild(textnode);
        document.getElementById("tr-price").appendChild(node);
    }

    else {
        document.getElementById('discount-row').style.display = "none";
    }
}

getPrice();