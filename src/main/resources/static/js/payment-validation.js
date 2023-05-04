function isValidCardDetails() {
    if (isValidCardNumber() && isValidCardExpirationDate()) {
        return true;
    }
    let message = "Некорректные данные карты";
    if (!isValidCardNumber()) {
        message = "Некорректный номер карты";
    } else if (!isValidCardExpirationDate()) {
        message = "Некорректный срок действия карты";
    }
    let paymentForm = document.getElementById('payment-form');
    let errorText = document.getElementById('error-text');
    if (errorText == null) {
        errorText = document.createElement('p');
        errorText.id = 'error-text';
        errorText.classList.add('text-danger');
        paymentForm.insertBefore(errorText, paymentForm.firstChild);
    }
    errorText.innerText = message;
    return false;
}

function isValidCardNumber() {
    //Check if the number contains only numeric value
    //and is between 13 and 19 digits
    let cardNumber = document.getElementById("cardNumber").value;
    cardNumber = cardNumber.replace(/\s/g, '');
    const regex = new RegExp("^[0-9]{13,19}$");
    if (!regex.test(cardNumber)){
        return false;
    }

    return luhnCheck(cardNumber);
}

function luhnCheck (val) {
    let checksum = 0; // running checksum total
    let j = 1; // takes value of 1 or 2

    // Process each digit one by one starting from the last
    for (let i = val.length - 1; i >= 0; i--) {
        let calc = 0;
        // Extract the next digit and multiply by 1 or 2 on alternative digits.
        calc = Number(val.charAt(i)) * j;

        // If the result is in two digits add 1 to the checksum total
        if (calc > 9) {
            checksum = checksum + 1;
            calc = calc - 10;
        }

        // Add the units element to the checksum total
        checksum = checksum + calc;

        // Switch the value of j
        if (j === 1) {
            j = 2;
        } else {
            j = 1;
        }
    }

    //Check if it is divisible by 10 or not.
    return (checksum % 10) === 0;
}

function isValidCardExpirationDate() {
    let exMonth = document.getElementById("expirationMonth").value;
    let exYear = parseInt(document.getElementById("expirationYear").value) + 2000;
    let today = new Date();
    let expDate = new Date(exYear, exMonth);
    return expDate.getTime() > today.getTime();
}