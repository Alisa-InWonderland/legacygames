"use strict";

class NewPrice {
    constructor() {
        this.price = document.getElementById('price');
        this.priceValue = parseFloat(this.price.innerHTML);
        this.discount = document.getElementById('discount');
        this.discountValue = parseFloat(this.discount.innerHTML);
    }


    getPrice() {

        if (this.discountValue > 0) {
            this.price.className = 'new-price';

            let discountPrice = ((this.priceValue - ((this.discountValue / 100) * this.priceValue)).toFixed(2)).toString();

            let node = document.createElement("TD");
            let textnode = document.createTextNode(discountPrice + "€");
            node.appendChild(textnode);
            document.getElementById("tr-price").appendChild(node);
        }

        else {
            document.getElementById('discount-row').style.display = "none";
        }
    }

}


/*

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
*/
