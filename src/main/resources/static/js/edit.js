
let discountPrice;

    function getDiscountPrice (){
        let price = document.getElementById('price').value;
        let discount = document.getElementById('discount').value;
        price = parseFloat(price);
        discount = parseFloat(discount);
        let percent = (discount/100).toFixed(2); //its convert 10 into 0.10
        let mult = price*percent; // gives the value for subtract from price value
        discountPrice = (price-mult).toFixed(2);
        return discountPrice;
    }

    function printDiscountPrice (){
        getDiscountPrice();
        document.getElementById("discountPrice").value = discountPrice;
    }

window.onload=printDiscountPrice;

